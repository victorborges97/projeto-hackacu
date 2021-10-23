package br.com.equipe23.divugaacu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import br.com.equipe23.divugaacu.R;

public class CityCampos extends Fragment {
    private View rootView;

    public CityCampos() {
        // Required empty public constructor
    }

    public static CityCampos newInstance(String param1, String param2) {
        CityCampos fragment = new CityCampos();
        Bundle args = new Bundle();
        // args.putString(ARG_PARAM1, param1);

        // fragment.setArguments(args);
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
        rootView = inflater.inflate(R.layout.fragment_city_campos, container, false);
        iniciarComponents();
        return rootView;
    }

    private void iniciarComponents() {

    }
}
