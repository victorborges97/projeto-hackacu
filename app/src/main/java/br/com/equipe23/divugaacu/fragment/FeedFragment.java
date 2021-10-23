package br.com.equipe23.divugaacu.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.equipe23.divugaacu.R;
import br.com.equipe23.divugaacu.model.Cidade;

public class FeedFragment extends Fragment {

    private FragmentActivity myContext;
    private View rootView;
    private ViewPager viewPager;
    private SmartTabLayout viewPagerTab;
    private FragmentPagerItemAdapter adapter;
    private FragmentPagerItems pages;

    static ArrayList<Cidade> cidades = new ArrayList<Cidade>();

    public FeedFragment() {
        // Required empty public constructor
    }

    public static FeedFragment newInstance(String param1, String param2) {
        FeedFragment fragment = new FeedFragment();
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
        rootView = inflater.inflate(R.layout.fragment_feed, container, false);
        cidades.add(new Cidade("São João da Barra", "1"));
        cidades.add(new Cidade("Campos do Goytacazes", "2"));

        iniciarComponents();
        initializePageTab();

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    private void initializePageTab() {

        pages = new FragmentPagerItems(rootView.getContext());
        for (Cidade city : cidades) {
            pages.add(FragmentPagerItem.of(city.getNome(), City.class));
        }

        adapter = new FragmentPagerItemAdapter(myContext.getSupportFragmentManager(), pages);

        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);
    }

    private void iniciarComponents() {
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        viewPagerTab = (SmartTabLayout) rootView.findViewById(R.id.viewpagertab);
    }

}