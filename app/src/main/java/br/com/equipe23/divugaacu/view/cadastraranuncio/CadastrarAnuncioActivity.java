package br.com.equipe23.divugaacu.view.cadastraranuncio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import br.com.equipe23.divugaacu.R;

public class CadastrarAnuncioActivity extends AppCompatActivity {

    private TextInputEditText textInputEditTextTítulo, textInputEditTextPreco, textInputEditTextCidade, textInputEditTextEndereco, textInputEditTextDescricao,
            textInputEditTextWhatsapp, textInputEditTextInstagram;
    private AlertDialog dialog;
    private Button buttonCadastrarAnuncio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_anuncio);

        iniciarComponentes();
    }

    public void iniciarComponentes() {
        textInputEditTextTítulo = findViewById(R.id.textInputEditTextTítulo);
        textInputEditTextPreco = findViewById(R.id.textInputEditTextPreco);
        textInputEditTextCidade = findViewById(R.id.textInputEditTextCidade);
        textInputEditTextEndereco = findViewById(R.id.textInputEditTextEndereco);
        textInputEditTextDescricao = findViewById(R.id.textInputEditTextDescricao);
        textInputEditTextWhatsapp = findViewById(R.id.textInputEditTextWhatsapp);
        textInputEditTextInstagram = findViewById(R.id.textInputEditTextInstagram);
        buttonCadastrarAnuncio = findViewById(R.id.buttonCadastrarAnuncio);
    }
}