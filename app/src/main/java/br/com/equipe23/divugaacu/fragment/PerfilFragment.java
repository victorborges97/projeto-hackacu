package br.com.equipe23.divugaacu.fragment;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import br.com.equipe23.divugaacu.R;

public class PerfilFragment extends Fragment {

    private TextView textViewNomePerfil, textViewTelefonePerfil, textViewCidadePerfil, textViewEnderecoPerfil,
            textViewNegocioPerfilCardView, textViewRuaPerfilCardView, textViewTelefonePerfilCardView;
    private ImageView imageViewFotoPerfil, imageViewNegocioPerfilCardView;
    private Button buttonSairPerfil;

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

        return rootView;
    }

    private  void iniciarComponentes(View rootView) {
        textViewNomePerfil = rootView.findViewById(R.id.textViewNomePerfil);
        textViewTelefonePerfil = rootView.findViewById(R.id.textViewTelefonePerfil);
        textViewCidadePerfil = rootView.findViewById(R.id.textViewCidadePerfil);
        textViewEnderecoPerfil = rootView.findViewById(R.id.textViewEnderecoPerfil);
        textViewNegocioPerfilCardView = rootView.findViewById(R.id.textViewNegocioPerfilCardView);
        textViewRuaPerfilCardView = rootView.findViewById(R.id.textViewRuaPerfilCardView);
        textViewTelefonePerfilCardView = rootView.findViewById(R.id.textViewTelefonePerfilCardView);
        imageViewFotoPerfil = rootView.findViewById(R.id.imageViewFotoPerfil);
        imageViewNegocioPerfilCardView = rootView.findViewById(R.id.imageViewNegocioPerfilCardView);
        buttonSairPerfil = rootView.findViewById(R.id.buttonSairPerfil);
    }
}