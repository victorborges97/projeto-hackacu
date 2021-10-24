package br.com.equipe23.divugaacu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import br.com.equipe23.divugaacu.Model.Perguntas;
import br.com.equipe23.divugaacu.R;
import br.com.equipe23.divugaacu.adapter.Adapter;

public class HelpFragment extends Fragment {
    private View rootView;
    private RecyclerView recyclerPerguntas;
    private List<Perguntas> listaPerguntas = new ArrayList<>();
    private Perguntas perguntas;

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
        perguntas = new Perguntas("Como entar com contato?", "Clicando no botão do WhatsApp, você entrará diretamente em contato com o fornecedor, podendo falar diretamente com ele para realizar seu negócio.");
        this.listaPerguntas.add(perguntas);
        perguntas = new Perguntas("Como é feito o cadastro da meu negócio?", "Após entrar no aplicativo, o usuário terá apenas que clicar no botão de adicionar no topo da tela, assim só é necessário preecher os dados e apertar o botão cadastrar, pronto, Registrado!");
        perguntas = new Perguntas("Como funciona a comunicação cliente/fornecedor?", "Da forma mais simples possivel, o cliente procura o serviço que deseja e assim começa sua negociação, diretamente com o fornecedor, uma relação inter-pessoal, assim os  dois entram em acrodo e um negócio é feito");
        this.listaPerguntas.add(perguntas);
        this.listaPerguntas.add(perguntas);
    }

}