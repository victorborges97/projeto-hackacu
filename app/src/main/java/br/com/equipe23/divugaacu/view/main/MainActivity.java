package br.com.equipe23.divugaacu.view.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_add:
                this.showToast("add");
                return true;
            case R.id.action_search:
                this.showToast("search");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        commitFragment(new FeedFragment());

        // TODO COMMIT TESTE
    }

    void initializeComponents() {
        bottomNavigationView = findViewById(R.id.bnMain);
        bottomNavigationView.setSelectedItemId(R.id.feed);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 switch (item.getItemId()){
                     case R.id.perfil:
                         commitFragment(new PerfilFragment());
                         return true;
                     case R.id.feed:
                         commitFragment(new FeedFragment());
                         return true;
                     case R.id.help:
                         commitFragment(new HelpFragment());
                         return true;
                 }
                 return false;
            }
        });
    }

    void commitFragment(Fragment selectedFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fMain, selectedFragment).commit();
    }

}