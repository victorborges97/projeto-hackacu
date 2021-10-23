package br.com.equipe23.divugaacu.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import br.com.equipe23.divugaacu.R;


public class City extends Fragment {
    private static final String ARG_PARAM1 = "city";
    private br.com.equipe23.divugaacu.model.Cidade city;
    private View rootView;

    private TextView tvCity;

    public City() {
        // Required empty public constructor
    }

    public static City newInstance(String param1) {
        City fragment = new City();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            cityName = getArguments().getString(ARG_PARAM1);
//        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        rootView = inflater.inflate(R.layout.fragment_city, container, false);
        iniciarComponents();

        tvCity.setText("CIDADE: " + city.getNome());
        return rootView;
    }

    private void iniciarComponents() {
        int position = FragmentPagerItem.getPosition(getArguments());
        tvCity = rootView.findViewById(R.id.testeCity);
        city = FeedFragment.cidades.get(position);
    }
}
