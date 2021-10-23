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

import br.com.equipe23.divugaacu.Adapter.Adapter;
import br.com.equipe23.divugaacu.R;

public class HelpFragment extends Fragment {

    TextView textQ1, textQ2;
    LinearLayout layoutQ1, layoutQ2;
    CardView cvq1, cvq2;
    private View rootView;
    private RecyclerView recyclerPerguntas;


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

        //Configurar adapter
        Adapter adapterPerguntas = new Adapter();

        //Configurar RecyclerView

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerPerguntas.setLayoutManager(layoutManager);
        recyclerPerguntas.setHasFixedSize(true);
        recyclerPerguntas.setAdapter(adapterPerguntas);


        return rootView;
    }


    public void iniciarComponentes() {

        textQ1 = rootView.findViewById(R.id.textQ1);
        layoutQ1 = rootView.findViewById(R.id.layoutQ1);

        cvq1 = rootView.findViewById(R.id.cvq1);

        textQ2 = rootView.findViewById(R.id.textQ2);
        layoutQ2 = rootView.findViewById(R.id.layoutQ2);

        cvq2 = rootView.findViewById(R.id.cvq2);

        recyclerPerguntas = rootView.findViewById(R.id.recyclerPerguntas);

    }


}