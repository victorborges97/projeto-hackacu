package br.com.equipe23.divulgacu.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import br.com.equipe23.divulgacu.R;
import br.com.equipe23.divulgacu.base.BaseActivity;
import br.com.equipe23.divulgacu.config.ConfiguracaoFirebase;
import br.com.equipe23.divulgacu.fragment.FeedFragment;
import br.com.equipe23.divulgacu.fragment.HelpFragment;
import br.com.equipe23.divulgacu.fragment.PerfilFragment;
import br.com.equipe23.divulgacu.fragment.PesquisaFragment;

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
                        if(ConfiguracaoFirebase.getUsuario() == null){
                            mostrarConfirmaLogin();
                            return false;
                        }
                        commitFragment("Perfil", true, new PerfilFragment());
                        return true;
                        case R.id.feed:
                        commitFragment("", false, new FeedFragment());
                        return true;
                    case R.id.help:
                        commitFragment("Ajuda", true, new HelpFragment());
                        return true;
                    case R.id.search:
                        commitFragment("Pesquisa", true, new PesquisaFragment());
                        return true;
                }
                return false;
            }
        });
    }

    public void commitFragment(String title, Boolean isTitle, Fragment selectedFragment) {
        if(isTitle){
            getSupportActionBar().setTitle(title);
            setLogo(isTitle);
        } else {
            setLogo(isTitle);
            getSupportActionBar().setTitle(null);
        }
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fMain, selectedFragment).commit();
    }

    private void setLogo(Boolean isTitle) {
        getSupportActionBar().setLogo(R.drawable.logo_h);
        getSupportActionBar().setDisplayUseLogoEnabled(!isTitle);
        getSupportActionBar().setDisplayShowHomeEnabled(!isTitle);
    }

    private void mostrarConfirmaLogin() {
        this.dialog(
                "Cria sua conta",
                "Voce ainda não tem uma conta no divulgAÇU, deseja criar o seu perfil e anunciar o seu negocio ?",
                () -> {
                    Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                    MainActivity.this.startActivity(myIntent);
                }
        );
    }
}