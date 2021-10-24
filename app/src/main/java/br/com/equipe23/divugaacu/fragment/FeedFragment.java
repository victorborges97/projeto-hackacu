package br.com.equipe23.divugaacu.fragment;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import java.util.ArrayList;
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
        iniciarComponents();
        iniciarCidade();
        initializePageTab();

        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar_feed, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    private void iniciarCidade() {
        cidades.clear();
        cidades.add(new Cidade("São João da Barra", "1"));
        cidades.add(new Cidade("Campos do Goytacazes", "2"));
    }

    private void initializePageTab() {

        pages = new FragmentPagerItems(rootView.getContext());
        for (Cidade city : cidades) {
            pages.add(FragmentPagerItem.of(city.getNome(), CityFragment.class));
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