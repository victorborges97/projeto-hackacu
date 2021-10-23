package br.com.equipe23.divugaacu.base;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    void getNextActivity(Class activity) {
        startActivity(new Intent(this, activity));
    }

    void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    void dialogError(String title, String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(text);
        AlertDialog alerta = builder.create();
        alerta.show();
    }

    void dialogSuccess(String title, String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(text);
        AlertDialog alerta = builder.create();
        alerta.show();
    }

}
