package br.com.equipe23.divulgacu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import br.com.equipe23.divulgacu.R;
import br.com.equipe23.divulgacu.adapter.AdapterHelpFragment;
import br.com.equipe23.divulgacu.model.Pergunta;

public class HelpFragment extends Fragment {
    private View rootView;
    private RecyclerView recyclerPerguntas;
    private List<Pergunta> listaPerguntas = new ArrayList<>();
    private Pergunta pergunta;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        rootView = inflater.inflate(R.layout.fragment_help, container, false);
        iniciarComponentes();

        //Listagem de perguntas
        criarPerguntas();


        //Configurar adapter
        AdapterHelpFragment adapterPerguntas = new AdapterHelpFragment(listaPerguntas);

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
        Pergunta p1 = new Pergunta(
                "Como entrar com contato?",
                getString(R.string.d_p1)
        );
        Pergunta p2 = new Pergunta(
                "Como é feito o cadastro da meu negócio?",
                getString(R.string.teste_resposta1)
        );
        Pergunta p3 = new Pergunta(
                "Como encontro o serviço que quero?",
                getString(R.string.r_p3)
        );
        Pergunta p4 = new Pergunta(
                "Como escolher outra cidade?",
                getString(R.string.d_p4)
        );
        Pergunta p5 = new Pergunta(
                "Como ver ou alterar meu usuário?",
                getString(R.string.d_p5)
        );
        Pergunta p6 = new Pergunta(
                "Como funciona a comunicação cliente/fornecedor?",
                getString(R.string.d_p6)
        );
        Pergunta p7 = new Pergunta(
                "Como sair do aplicativo?",
                getString(R.string.d_p7)
        );

        this.listaPerguntas.add(p1);
        this.listaPerguntas.add(p2);
        this.listaPerguntas.add(p3);
        this.listaPerguntas.add(p4);
        this.listaPerguntas.add(p5);
        this.listaPerguntas.add(p6);
        this.listaPerguntas.add(p7);
    }

}