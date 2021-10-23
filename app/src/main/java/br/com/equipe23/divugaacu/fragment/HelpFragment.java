package br.com.equipe23.divugaacu.fragment;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import br.com.equipe23.divugaacu.R;

public class HelpFragment extends Fragment {

    TextView detailText;
    LinearLayout layout;

    public HelpFragment() {
        // Required empty public constructor
    }

    public static HelpFragment newInstance(String param1, String param2) {
        HelpFragment fragment = new HelpFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);
        iniciarComponentes(rootView);
        return rootView;
    }

    public void iniciarComponentes(View rootView) {

        detailText = rootView.findViewById(R.id.details);
        layout = rootView.findViewById(R.id.layoutTeste);
        layout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);


    }

    public void expandir(View view) {
        int v = (detailText.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;

        TransitionManager.beginDelayedTransition(layout, new AutoTransition());
        detailText.setVisibility(v);


    }

}