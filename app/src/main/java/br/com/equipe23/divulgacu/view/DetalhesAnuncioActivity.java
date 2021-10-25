package br.com.equipe23.divulgacu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import br.com.equipe23.divulgacu.R;
import br.com.equipe23.divulgacu.base.BaseActivity;
import br.com.equipe23.divulgacu.config.Shared;
import br.com.equipe23.divulgacu.model.Anuncio;
import br.com.equipe23.divulgacu.model.Endereco;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetalhesAnuncioActivity extends BaseActivity {

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

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#464545\">" + "Detalhes" + "</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        //Recuperar anuncio
        anuncioSelecionado = (Anuncio) getIntent().getParcelableExtra("anuncioSelecionado");

        setDadosAnuncio();

        textIrParaLocal.setOnClickListener(view -> {
            if(anuncioSelecionado != null && !anuncioSelecionado.getEndereco().getRua().isEmpty()){
                Endereco e = anuncioSelecionado.getEndereco();
                String endereco = "";
                if(!e.getRua().isEmpty()){
                    endereco = endereco+e.getRua();
                }
                if(!e.getNumero().isEmpty()){
                    endereco = endereco+", "+ e.getNumero();
                }
                if(!e.getBairro().isEmpty()){
                    endereco = endereco+", "+ e.getBairro();
                }
                if(!e.getCidade().isEmpty()){
                    endereco = endereco+" - "+ e.getCidade();
                }
                Shared.openMaps(this, endereco);
            } else {
                this.showToast("Endereço não cadastrado");
            }
            Log.i("botao mapa","foi Maps");
        });

        buttonWhatsapp.setOnClickListener(view -> {
            if(anuncioSelecionado != null && !anuncioSelecionado.getWhatsapp().isEmpty()){
                Shared.openWhatsApp(this, anuncioSelecionado.getWhatsapp(), "Testando");
            } else {
                this.showToast("WhastApp não cadastrado");
            }
            Log.i("botao perfil","foi Whatsapp");
        });

        buttonInstagram.setOnClickListener(view -> {
            if(anuncioSelecionado != null && !anuncioSelecionado.getInstagram().isEmpty()){
                Shared.openInstagram(this, anuncioSelecionado.getInstagram());
            } else {
                this.showToast("Instagram não cadastrado");
            }
            Log.i("botao perfil","foi Instagram");
        });

    }

    private void setDadosAnuncio() {
        if (anuncioSelecionado != null){
            textNomePerfilDetalhes.setText(anuncioSelecionado.getNomeEmpresa());
            textMiniBioPerfilDetalhes.setText(anuncioSelecionado.getDescricao());
            textRuaDetalhes.setText("Rua: "+ anuncioSelecionado.getEndereco().getRua());
            textBairroDetalhes.setText("Bairro: " + anuncioSelecionado.getEndereco().getBairro());
            textNumeroCasaDetalhes.setText("Número: " + anuncioSelecionado.getEndereco().getNumero());

            if (!anuncioSelecionado.getLogo().isEmpty()) {
                Uri uri = Uri.parse(anuncioSelecionado.getLogo());
                Glide.with(this).load(uri).into(imageViewFotoPerfilDetalhes);
            }else {
                imageViewFotoPerfilDetalhes.setImageResource(R.drawable.unknown);
            }

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