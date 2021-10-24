package br.com.equipe23.divulgacu.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import br.com.equipe23.divulgacu.model.Anuncio;
import br.com.equipe23.divulgacu.model.Cidade;
import br.com.equipe23.divulgacu.repository.Firebase;

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
        Log.d("TESTE", "TESTE "+city);
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
                            Log.d("LISTA", "LISTA CITY: "+listaAnuncios.toString());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        listaAnuncios.clear();
                    }
                });

//        if(city.getNome().toLowerCase().equals("Campos do Goytacazes".toLowerCase())){
//            listaAnuncios.add(new Anuncio(
//                    "Peixaria Lago Azul",
//                    "Coronel Linhares",
//                    "(22) 12345678",
//                    "https://mir-s3-cdn-cf.behance.net/projects/404/3ad70030226797.561919275e644.jpg"
//            ));
//            listaAnuncios.add(new Anuncio(
//                    "Peixaria Lago Azul",
//                    "Coronel Linhares",
//                    "(22) 12345678",
//                    "https://mir-s3-cdn-cf.behance.net/projects/404/3ad70030226797.561919275e644.jpg"
//            ));
//        }
//        else {
//            listaAnuncios.add(new Anuncio(
//                    "Peixaria Lago Azul",
//                    "Coronel Linhares",
//                    "(22) 12345678",
//                    "https://mir-s3-cdn-cf.behance.net/projects/404/3ad70030226797.561919275e644.jpg"
//            ));
//            listaAnuncios.add(new Anuncio(
//                    "Peixaria Lago Azul",
//                    "Coronel Linhares",
//                    "(22) 12345678",
//                    "https://mir-s3-cdn-cf.behance.net/projects/404/3ad70030226797.561919275e644.jpg"
//            ));
//            listaAnuncios.add(new Anuncio(
//                    "Peixaria Lago Azul",
//                    "Coronel Linhares",
//                    "(22) 12345678",
//                    "https://mir-s3-cdn-cf.behance.net/projects/404/3ad70030226797.561919275e644.jpg"
//            ));
//            listaAnuncios.add(new Anuncio(
//                    "Peixaria Lago Azul",
//                    "Coronel Linhares",
//                    "(22) 12345678",
//                    "https://mir-s3-cdn-cf.behance.net/projects/404/3ad70030226797.561919275e644.jpg"
//            ));
//            listaAnuncios.add(new Anuncio(
//                    "Peixaria Lago Azul",
//                    "Coronel Linhares",
//                    "(22) 12345678",
//                    "https://mir-s3-cdn-cf.behance.net/projects/404/3ad70030226797.561919275e644.jpg"
//            ));
//        }
    }
}
