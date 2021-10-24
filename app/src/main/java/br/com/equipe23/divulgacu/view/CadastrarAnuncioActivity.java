package br.com.equipe23.divulgacu.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.util.ArrayList;
import java.util.List;

import br.com.equipe23.divulgacu.R;
import br.com.equipe23.divulgacu.config.ConfiguracaoFirebase;

public class CadastrarAnuncioActivity extends AppCompatActivity {

    private TextInputEditText textInputEditTextTítulo, textInputEditTextPreco, textInputEditTextCidade, textInputEditTextEndereco, textInputEditTextDescricao,
            textInputEditTextWhatsapp, textInputEditTextInstagram;
    private Button buttonCadastrarAnuncio;
    private ImageView imagem0, imagem1, imagem2, imagem3, imagem4;
    private List<String> listaFotosRecuperadas = new ArrayList<>();
    private List<String> listaURLFotos = new ArrayList<>();
    private Spinner spinnerCidade;
    private StorageReference firebaseStorage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_anuncio);

        iniciarComponentes();
        carregarDadosSpinner();

        firebaseStorage = ConfiguracaoFirebase.getFirebaseStorage();
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
        final StorageReference imagemAnuncio = firebaseStorage.child("imagens");

        //Fazendo upload
        UploadTask uploadTask = imagemAnuncio.putFile(Uri.parse(urlString));
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imagemAnuncio.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        Uri firebaseUrl = task.getResult();
                        String urlConvertida = firebaseUrl.toString();

                        listaURLFotos.add(urlConvertida);
                        if (totalFotos == listaURLFotos.size()){

                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                exibirMensagemErro("Falha ao fazer upload");
            }
        });
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

    public void exibirMensagemErro(String mensagem){
        Toast.makeText(CadastrarAnuncioActivity.this, mensagem, Toast.LENGTH_SHORT).show();
    }

    public void iniciarComponentes() {
        textInputEditTextTítulo = findViewById(R.id.textInputEditTextTítulo);
        textInputEditTextEndereco = findViewById(R.id.textInputEditTextEndereco);
        textInputEditTextDescricao = findViewById(R.id.textInputEditTextDescricao);
        textInputEditTextWhatsapp = findViewById(R.id.textInputEditTextWhatsapp);
        textInputEditTextInstagram = findViewById(R.id.textInputEditTextInstagram);
        buttonCadastrarAnuncio = findViewById(R.id.buttonCadastrarAnuncio);
        spinnerCidade = findViewById(R.id.spinnerCidade);

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

    private void carregarDadosSpinner(){
        String[] cidade = getResources().getStringArray(R.array.cidade);
        ArrayAdapter<String> adapterCidade = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cidade);
        adapterCidade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCidade.setAdapter(adapterCidade);

    }
}