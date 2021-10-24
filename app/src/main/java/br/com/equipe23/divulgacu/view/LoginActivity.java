package br.com.equipe23.divulgacu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.common.SignInButton;

import br.com.equipe23.divulgacu.R;

public class LoginActivity extends AppCompatActivity {

    private SignInButton botaoGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
    }
}