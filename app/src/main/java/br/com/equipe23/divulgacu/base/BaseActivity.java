package br.com.equipe23.divulgacu.base;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    protected void getNextActivity(Class activity) {
        startActivity(new Intent(this, activity));
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void dialogError(String title, String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(text);
        AlertDialog alerta = builder.create();
        alerta.show();
    }

    protected void dialogSuccess(String title, String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(text);
        AlertDialog alerta = builder.create();
        alerta.show();
    }

}
