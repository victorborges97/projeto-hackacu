package br.com.equipe23.divulgacu.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.text.NumberFormat;

import br.com.equipe23.divulgacu.R;
import br.com.equipe23.divulgacu.fragment.CityFragment;
import br.com.equipe23.divulgacu.model.Anuncio;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetalhesAnuncioActivity extends AppCompatActivity {

    private TextView textNomePerfilDetalhes, textProfissaoPerfilDetalhes, textMiniBioPerfilDetalhes, textRuaDetalhes, textBairroDetalhes, textNumeroCasaDetalhes, textIrParaLocal;
    private Button buttonInstagram, buttonWhatsapp;
    private CarouselView carouselView;
    private ImageView imageViewProdutoDetalhes;
    private CircleImageView imageViewFotoPerfilDetalhes;
    private Anuncio anuncioSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_anuncio);

        iniciarComponentes();

        getSupportActionBar().setTitle("Detalhes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonWhatsapp.setOnClickListener(view -> {

            Log.i("botao perfil","foi Whatsapp");
        });

        buttonInstagram.setOnClickListener(view -> {
            Log.i("botao perfil","foi Instagram");
        });

        //Recuperar anuncio
        anuncioSelecionado = (Anuncio) getIntent().getParcelableExtra("anuncioSelecionado");

        if (anuncioSelecionado != null){
            textNomePerfilDetalhes.setText(anuncioSelecionado.getNomeEmpresa());
            textMiniBioPerfilDetalhes.setText(anuncioSelecionado.getDescricao());
            textRuaDetalhes.setText(anuncioSelecionado.getEndereco().getRua());
            textBairroDetalhes.setText(anuncioSelecionado.getEndereco().getBairro());
            textNumeroCasaDetalhes.setText(anuncioSelecionado.getEndereco().getNumero());

            ImageListener imageListener = new ImageListener() {
                @Override
                public void setImageForPosition(int position, ImageView imageView) {
                    String urlString = anuncioSelecionado.getImagens().get(position);
                    Picasso.get().load(urlString).into(imageView);

                }
            };

            carouselView.setPageCount(anuncioSelecionado.getImagens().size());
            carouselView.setImageListener(imageListener);

        }

    }

    private  void iniciarComponentes() {
        textNomePerfilDetalhes = findViewById(R.id.textNomePerfilDetalhes);
        textMiniBioPerfilDetalhes = findViewById(R.id.textMiniBioPerfilDetalhes);
        textRuaDetalhes = findViewById(R.id.textRuaDetalhes);
        textBairroDetalhes = findViewById(R.id.textBairroDetalhes);
        textNumeroCasaDetalhes = findViewById(R.id.textNumeroCasaDetalhes);
        textIrParaLocal = findViewById(R.id.textIrParaLocal);
        buttonInstagram = findViewById(R.id.buttonInstagram);
        buttonWhatsapp = findViewById(R.id.buttonWhatsapp);
        imageViewFotoPerfilDetalhes = findViewById(R.id.imageViewFotoPerfilDetalhes);
        carouselView = findViewById(R.id.carouselView);
        //imageViewProdutoDetalhes = findViewById(R.id.imageViewProdutoDetalhes);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}