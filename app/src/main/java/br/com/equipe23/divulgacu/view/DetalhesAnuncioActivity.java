package br.com.equipe23.divulgacu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.equipe23.divulgacu.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetalhesAnuncioActivity extends AppCompatActivity {

    private TextView textNomePerfilDetalhes, textProfissaoPerfilDetalhes, textMiniBioPerfilDetalhes, textRuaDetalhes, textBairroDetalhes, textNumeroCasaDetalhes, textIrParaLocal;
    private Button buttonInstagram, buttonWhatsapp;
    private ImageView imageViewProdutoDetalhes;
    private CircleImageView imageViewFotoPerfilDetalhes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_anuncio);

        iniciarComponentes();

        buttonWhatsapp.setOnClickListener(view -> {

            Log.i("botao perfil","foi Whatsapp");
        });

        buttonInstagram.setOnClickListener(view -> {
            Log.i("botao perfil","foi Instagram");
        });
    }

    private  void iniciarComponentes() {
        textNomePerfilDetalhes = findViewById(R.id.textNomePerfilDetalhes);
        textProfissaoPerfilDetalhes = findViewById(R.id.textProfissaoPerfilDetalhes);
        textMiniBioPerfilDetalhes = findViewById(R.id.textMiniBioPerfilDetalhes);
        textRuaDetalhes = findViewById(R.id.textRuaDetalhes);
        textBairroDetalhes = findViewById(R.id.textBairroDetalhes);
        textNumeroCasaDetalhes = findViewById(R.id.textNumeroCasaDetalhes);
        textIrParaLocal = findViewById(R.id.textIrParaLocal);
        buttonInstagram = findViewById(R.id.buttonInstagram);
        buttonWhatsapp = findViewById(R.id.buttonWhatsapp);
        imageViewFotoPerfilDetalhes = findViewById(R.id.imageViewFotoPerfilDetalhes);
        imageViewProdutoDetalhes = findViewById(R.id.imageViewProdutoDetalhes);
    }
}