package br.com.equipe23.divugaacu.fragment;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;


import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
=======

>>>>>>> f9a8441d900012e5db688f4079abd2096d212fb2
import br.com.equipe23.divugaacu.Model.Perguntas;

import br.com.equipe23.divugaacu.R;
import br.com.equipe23.divugaacu.adapter.Adapter;

public class HelpFragment extends Fragment {


    private View rootView;
    private RecyclerView recyclerPerguntas;
    private List<Perguntas> listaPerguntas = new ArrayList<>();
    private Perguntas perguntas;


    public HelpFragment() {
        // Required empty public constructor
    }

    public static HelpFragment newInstance(String param1, String param2) {
        HelpFragment fragment = new HelpFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState


    ) {


        rootView = inflater.inflate(R.layout.fragment_help, container, false);
        iniciarComponentes();

        //Listagem de perguntas
        this.criarPerguntas();


        //Configurar adapter
        Adapter adapterPerguntas = new Adapter(listaPerguntas);

        //Configurar RecyclerView

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerPerguntas.setLayoutManager(layoutManager);
        recyclerPerguntas.setHasFixedSize(true);
        recyclerPerguntas.setAdapter(adapterPerguntas);


        return rootView;
    }


    public void iniciarComponentes() {


        recyclerPerguntas = rootView.findViewById(R.id.recyclerPerguntas);

    }

    public void criarPerguntas(){

        perguntas = new Perguntas("Como funciona a comunicação cliente/fornecedor?", "Da forma mais simples possivel, o cliente procura o serviço que deseja e assim começa sua negociação, diretamente com o fornecedor, uma relação inter-pessoal, assim os  dois entram em acrodo e um negócio é feito");
        this.listaPerguntas.add(perguntas);

        perguntas = new Perguntas("Como funciona a comunicação cliente/fornecedor?", "Da forma mais simples possivel, o cliente procura o serviço que deseja e assim começa sua negociação, diretamente com o fornecedor, uma relação inter-pessoal, assim os  dois entram em acrodo e um negócio é feito");
        this.listaPerguntas.add(perguntas);


    }


}