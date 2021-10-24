package br.com.equipe23.divulgacu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import br.com.equipe23.divulgacu.R;
import br.com.equipe23.divulgacu.model.Cidade;


public class CityFragment extends Fragment {
    private Cidade city;
    private View rootView;

    private TextView tvCity;

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

        tvCity.setText("CIDADE: " + city.getNome());
        return rootView;
    }

    private void iniciarComponents() {
        int position = FragmentPagerItem.getPosition(getArguments());
        tvCity = rootView.findViewById(R.id.testeCity);
        city = FeedFragment.cidades.get(position);
    }
}