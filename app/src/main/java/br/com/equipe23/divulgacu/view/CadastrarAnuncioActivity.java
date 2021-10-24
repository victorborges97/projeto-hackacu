package br.com.equipe23.divulgacu.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
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
import br.com.equipe23.divulgacu.config.Mask;
import br.com.equipe23.divulgacu.config.Permissao;
import br.com.equipe23.divulgacu.model.Anuncio;
import br.com.equipe23.divulgacu.model.Endereco;

public class CadastrarAnuncioActivity extends AppCompatActivity {

    private TextInputEditText textInputEditTextTítulo, textInputEditTextPreco, textInputEditTextCidade, textInputEditTextEndereco, textInputEditTextDescricao,
            textInputEditTextWhatsapp, textInputEditTextInstagram;
    private Button buttonCadastrarAnuncio;
    private ImageView imagem0, imagem1, imagem2, imagem3, imagem4;
    private List<String> listaFotosRecuperadas = new ArrayList<>();
    private List<String> listaURLFotos = new ArrayList<>();
    private Spinner spinnerCidade;
    private StorageReference firebaseStorage;
    private Anuncio anuncio;
    private AlertDialog dialog;

    private String[] permissoes = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_anuncio);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#464545\">" + "Cadastrar Anúncio" + "</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        firebaseStorage = ConfiguracaoFirebase.getFirebaseStorage();

        //validar permissao
        Permissao.validarPermissoes(permissoes, this, 1);

        iniciarComponentes();
        carregarDadosSpinner();


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
        final StorageReference imagemAnuncio = firebaseStorage.child("imagens")
                .child("anuncios")
                .child(anuncio.getIdAnuncio())
                .child("imagem" + contador);

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
                            anuncio.setLogo(listaURLFotos.get(0));
                            anuncio.setImagens(listaURLFotos);
                            anuncio.salvar();

                            finish();
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

    private Anuncio configurarAnuncio(){
        String cidade = spinnerCidade.getSelectedItem().toString();
        String seuNegocio = textInputEditTextTítulo.getText().toString();
        String endereco = textInputEditTextEndereco.getText().toString();
        String whatsapp = textInputEditTextWhatsapp.getText().toString();
        String descricao = textInputEditTextDescricao.getText().toString();
        String instagram = textInputEditTextInstagram.getText().toString();


        Endereco ende = new Endereco();
        ende.setRua(endereco);
        ende.setCidade(cidade);

        Anuncio anuncio = new Anuncio();
        anuncio.setDescricao(descricao);
        anuncio.setWhatsapp(whatsapp);
        anuncio.setInstagram(instagram);
        anuncio.setEndereco(ende);
        anuncio.setNomeEmpresa(seuNegocio);

        return anuncio;
    }

    public void validarDadosAnuncio(){
        anuncio = configurarAnuncio();

        //salvarAnuncio();
     if (listaFotosRecuperadas.size() != 0){
            if (!anuncio.getCidade().isEmpty()){
                if (!anuncio.getNomeEmpresa().isEmpty()){
                    if (!anuncio.getEndereco().getRua().isEmpty() || anuncio.getEndereco().getCidade().isEmpty() || anuncio.getEndereco().getBairro().isEmpty() || anuncio.getEndereco().getNumero().isEmpty()){
                        if (!anuncio.getDescricao().isEmpty()){
                            if (!anuncio.getWhatsapp().isEmpty() && anuncio.getWhatsapp().length() >= 10){
                                salvarAnuncio();
                            }else {
                                exibirMensagemErro("Digite o número do Whatsapp");
                            }
                        }else {
                            exibirMensagemErro("Digite uma descriçao");
                        }
                    }else {
                        exibirMensagemErro("Digite o endereço completo");
                    }
                }else {
                    exibirMensagemErro("Digite o título do anúncio");
                }
            }else {
                exibirMensagemErro("Selecione a cidade");
            }
        }else {
            exibirMensagemErro("Escolha pelo menos uma foto");
        }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK);{
            //Recuperar imagem
            try {
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
            } catch (Exception e) {
                Log.d("FOTO", "ERROR AO PEGA A FOTO");
            }
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

        textInputEditTextWhatsapp.addTextChangedListener(Mask.insert("(##)#####-####", textInputEditTextWhatsapp));

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

        buttonCadastrarAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarDadosAnuncio();
                startActivity(new Intent(getBaseContext(),MainActivity.class));
                finish();

            }
        });
    }

    private void carregarDadosSpinner(){
        String[] cidade = getResources().getStringArray(R.array.cidade);
        ArrayAdapter<String> adapterCidade = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cidade);
        adapterCidade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCidade.setAdapter(adapterCidade);

    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int permissaoResultado : grantResults){
            if (permissaoResultado == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
            }
        }
    }

    private void alertaValidacaoPermissao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Aceitar Permissões");
        builder.setMessage("Para poder enviar ou tirar fotos, é necessário aceitar as permissões");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        //dialog.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}