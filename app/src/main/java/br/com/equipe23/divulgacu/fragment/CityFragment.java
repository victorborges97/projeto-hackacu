package br.com.equipe23.divulgacu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import java.util.ArrayList;
import java.util.List;
import br.com.equipe23.divulgacu.R;
import br.com.equipe23.divulgacu.adapter.AdapterFeedCityFragment;
import br.com.equipe23.divulgacu.config.ConfiguracaoFirebase;
import br.com.equipe23.divulgacu.config.RecyclerItemClickListener;
import br.com.equipe23.divulgacu.model.Anuncio;
import br.com.equipe23.divulgacu.model.Cidade;
import br.com.equipe23.divulgacu.repository.Firebase;
import br.com.equipe23.divulgacu.view.DetalhesAnuncioActivity;

public class CityFragment extends Fragment {
    private Cidade city;
    private View rootView;

    private RecyclerView rvCityFragment;
    private AdapterFeedCityFragment adapterCity;
    private List<Anuncio> listaAnuncios = new ArrayList<>();
    private DatabaseReference anunciosRef;

    public CityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        rootView = inflater.inflate(R.layout.fragment_city, container, false);
        iniciarComponents();
        initializeRecycle();
        initializeLista();
        return rootView;
    }

    private void iniciarComponents() {
        int position = FragmentPagerItem.getPosition(getArguments());
        rvCityFragment = rootView.findViewById(R.id.rvCityFragment);
        city = FeedFragment.cidades.get(position);


        listaAnuncios = new ArrayList<>();
        anunciosRef = ConfiguracaoFirebase.getFirebaseDatabase().child("anuncios");
    }

    private void initializeRecycle() {
        //Configurar adapter
        adapterCity = new AdapterFeedCityFragment(listaAnuncios);

        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvCityFragment.setLayoutManager(layoutManager);
        rvCityFragment.setHasFixedSize(true);
        rvCityFragment.setAdapter(adapterCity);

        //Clique
        rvCityFragment.addOnItemTouchListener(new RecyclerItemClickListener(rootView.getContext(), rvCityFragment, new RecyclerItemClickListener.OnItemClickListener() {
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
    }

    private void initializeLista() {
        listaAnuncios.clear();

        Firebase.getAnuncios(city.getNome()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            listaAnuncios.clear();
                            for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                listaAnuncios.add(postSnapshot.getValue(Anuncio.class));
                            }
                            adapterCity.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        listaAnuncios.clear();
                    }
                });

    }
}
