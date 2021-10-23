package br.com.equipe23.divugaacu.view.cadastraranuncio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import br.com.equipe23.divugaacu.R;

public class CadastrarAnuncioActivity extends AppCompatActivity {

    private TextInputEditText textInputEditTextTítulo, textInputEditTextPreco, textInputEditTextCidade, textInputEditTextEndereco, textInputEditTextDescricao,
            textInputEditTextWhatsapp, textInputEditTextInstagram;
    private AlertDialog dialog;
    private Button buttonCadastrarAnuncio;
    private ImageView imagem0, imagem1, imagem2, imagem3, imagem4;
    private List<String> listaFotosRecuperadas = new ArrayList<>();
    private List<String> listaURLFotos = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_anuncio);

        iniciarComponentes();
    }

    public void salvarAnuncio(){
        //Salvando imagem no Storage
        for (int i = 0; i < listaFotosRecuperadas.size(); i++){
            String urlImagem = listaFotosRecuperadas.get(i);
            int tamanhoLista = listaFotosRecuperadas.size();
            salvarFotoSotorage(urlImagem, tamanhoLista, i);
        }
    }

    private void salvarFotoSotorage(String urlString, int totalFotos, int contador){
        //Nó do Storage


        //Fazendo upload

    }

    public void OnClikImagem(View view){
        switch (view.getId()){
            case R.id.imageView0:
                escolherImagem(0);
                break;
            case R.id.imageView1:
                escolherImagem(1);
                break;
            case R.id.imageView2:
                escolherImagem(2);
                break;
            case R.id.imageView3:
                escolherImagem(3);
                break;
            case R.id.imageView4:
                escolherImagem(4);
                break;
        }
    }

    public void escolherImagem(int requestCode){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, requestCode);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK);{
            //Recuperar imagem
            Uri imagemSelecionada = data.getData();
            String caminhoImagem = imagemSelecionada.toString();

            //Config. imagem no ImageView
            if (requestCode == 0){
                imagem0.setImageURI(imagemSelecionada);
            }else if (requestCode == 1){
                imagem1.setImageURI(imagemSelecionada);
            }else if (requestCode == 2){
                imagem2.setImageURI(imagemSelecionada);
            }else if (requestCode == 3){
                imagem3.setImageURI(imagemSelecionada);
            }else if (requestCode == 4){
                imagem4.setImageURI(imagemSelecionada);
            }
            listaFotosRecuperadas.add(caminhoImagem);
        }
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

        imagem0 = findViewById(R.id.imageView0);
        imagem1 = findViewById(R.id.imageView1);
        imagem2 = findViewById(R.id.imageView2);
        imagem3 = findViewById(R.id.imageView3);
        imagem4 = findViewById(R.id.imageView4);

        imagem0.setOnClickListener(this::OnClikImagem);
        imagem1.setOnClickListener(this::OnClikImagem);
        imagem2.setOnClickListener(this::OnClikImagem);
        imagem3.setOnClickListener(this::OnClikImagem);
        imagem4.setOnClickListener(this::OnClikImagem);
    }
}