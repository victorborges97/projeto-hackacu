package br.com.equipe23.divugaacu.view.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.com.equipe23.divugaacu.R;
import br.com.equipe23.divugaacu.base.BaseActivity;
import br.com.equipe23.divugaacu.fragment.FeedFragment;
import br.com.equipe23.divugaacu.fragment.HelpFragment;
import br.com.equipe23.divugaacu.fragment.PerfilFragment;

public class MainActivity extends BaseActivity {

    private String TAG = "MAIN";
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        initializeBottomNavigation();
        commitFragment("", false, new FeedFragment());

        // TODO COMMIT TESTE
    }

    void initializeComponents() {
        bottomNavigationView = findViewById(R.id.bnMain);
    }

    private void initializeBottomNavigation() {
        bottomNavigationView.setSelectedItemId(R.id.feed);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.perfil:
                        commitFragment("Perfil", true, new PerfilFragment());
                        return true;
                    case R.id.feed:
                        commitFragment("", false, new FeedFragment());
                        return true;
                    case R.id.help:
                        commitFragment("Ajuda", true, new HelpFragment());
                        return true;
                }
                return false;
            }
        });
    }

    void commitFragment(String title, Boolean isTitle, Fragment selectedFragment) {
        if(isTitle){
            getSupportActionBar().setTitle(title);
            setLogo(isTitle);
        } else {
            setLogo(isTitle);
            getSupportActionBar().setTitle(null);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fMain, selectedFragment).commit();
    }

    private void setLogo(Boolean isTitle) {
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(!isTitle);
        getSupportActionBar().setDisplayShowHomeEnabled(!isTitle);
    }

}