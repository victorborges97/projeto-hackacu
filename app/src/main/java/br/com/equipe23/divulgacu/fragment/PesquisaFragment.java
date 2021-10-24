package br.com.equipe23.divulgacu.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.equipe23.divulgacu.R;
import br.com.equipe23.divulgacu.adapter.AdapterPesquisa;
import br.com.equipe23.divulgacu.config.ConfiguracaoFirebase;
import br.com.equipe23.divulgacu.config.RecyclerItemClickListener;
import br.com.equipe23.divulgacu.model.Anuncio;
import br.com.equipe23.divulgacu.view.DetalhesAnuncioActivity;

public class PesquisaFragment extends Fragment {

    private SearchView searchViewPesquisa;
    private RecyclerView recyclerPesquisa;
    private List<Anuncio> listaAnuncios;
    private DatabaseReference anunciosRef;
    private AdapterPesquisa adapterPesquisa;
    private String idAnuncio;


    public PesquisaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pesquisa, container, false);

        iniciarComponentes(rootView);

        listaAnuncios = new ArrayList<>();
        anunciosRef = ConfiguracaoFirebase.getFirebaseDatabase().child("anuncios");

        recyclerPesquisa.setHasFixedSize(true);
        recyclerPesquisa.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterPesquisa = new AdapterPesquisa(listaAnuncios, getActivity());
        recyclerPesquisa.setAdapter(adapterPesquisa);

        recyclerPesquisa.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerPesquisa, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Anuncio anuncioSelecionado = listaAnuncios.get(position);
                Intent intent = new Intent(getActivity(), DetalhesAnuncioActivity.class);
                intent.putExtra("anuncioSelecionado", anuncioSelecionado);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));

        searchViewPesquisa.setQueryHint("Buscar Anúncios");
        searchViewPesquisa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String textoDigitado = s.toUpperCase();
                pesquisarAnuncios(textoDigitado);
                return true;
            }
        });

        return rootView;
    }

    private void pesquisarAnuncios(String texto) {

        listaAnuncios.clear();

        if (texto.length() > 2) {
            listaAnuncios.clear();
            Query query = anunciosRef.child("São João da Barra").orderByChild("nomeEmpresaPesquisa").startAt(texto).endAt(texto + "\uf8ff");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        listaAnuncios.add(dataSnapshot.getValue(Anuncio.class));
                    }
                    adapterPesquisa.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Query query2 = anunciosRef.child("Campos dos Goytacazes").orderByChild("nomeEmpresaPesquisa").startAt(texto).endAt(texto + "\uf8ff");
            query2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        listaAnuncios.add(dataSnapshot.getValue(Anuncio.class));
                    }
                    adapterPesquisa.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            Log.d("ANUNCIOS", listaAnuncios.toString());
        }
    }

    private  void iniciarComponentes(View rootView) {
        searchViewPesquisa = rootView.findViewById(R.id.searchViewPesquisa);
        recyclerPesquisa = rootView.findViewById(R.id.recyclerPesquisa);
    }
}