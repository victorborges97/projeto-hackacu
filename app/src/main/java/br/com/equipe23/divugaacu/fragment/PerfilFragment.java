package br.com.equipe23.divugaacu.fragment;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import br.com.equipe23.divugaacu.R;

public class PerfilFragment extends Fragment {

    private TextView textNomePerfil, textProfissaoPerfil, textMiniBioPerfil, textRua, textBairro, textNumero, textIrParaLocal;
    private Button buttonInstagram, buttonWhatsapp;
    private ImageView imageViewFotoPerfil;

    public PerfilFragment() {
        // Required empty public constructor
    }

    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        // args.putString(ARG_PARAM1, param1);

        // fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View rootView = inflater.inflate(R.layout.fragment_perfil, container, false);

        iniciarComponentes(rootView);

        buttonWhatsapp.setOnClickListener(view -> {

            Log.i("teste","foi");
        });

        return rootView;
    }

    private  void iniciarComponentes(View rootView) {
        textNomePerfil = rootView.findViewById(R.id.textNomePerfil);
        textProfissaoPerfil = rootView.findViewById(R.id.textProfissaoPerfil);
        textMiniBioPerfil = rootView.findViewById(R.id.textMiniBioPerfil);
        textRua = rootView.findViewById(R.id.textRua);
        textBairro = rootView.findViewById(R.id.textBairro);
        textNumero = rootView.findViewById(R.id.textNumero);
        textIrParaLocal = rootView.findViewById(R.id.textIrParaLocal);
        buttonInstagram = rootView.findViewById(R.id.buttonInstagram);
        buttonWhatsapp = rootView.findViewById(R.id.buttonWhatsapp);
        imageViewFotoPerfil = rootView.findViewById(R.id.imageViewFotoPerfil);
    }
}