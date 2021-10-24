package br.com.equipe23.divulgacu.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;
import java.util.List;
import br.com.equipe23.divulgacu.config.ConfiguracaoFirebase;

public class Anuncio implements Parcelable {
    private String idAnuncio;
    private String nomeEmpresa = "";
    private String nomeEmpresaPesquisa = "";
    private String logo = "";
    private Endereco endereco = new Endereco();
    private String descricao = "";
    private String whatsapp = "";
    private String instagram = "";
    private String cidade = "";
    private List<String> imagens = new ArrayList<>();

    protected Anuncio(Parcel in) {
        idAnuncio = in.readString();
        nomeEmpresa = in.readString();
        nomeEmpresaPesquisa = in.readString();
        logo = in.readString();
        endereco = in.readParcelable(Endereco.class.getClassLoader());
        descricao = in.readString();
        whatsapp = in.readString();
        instagram = in.readString();
        cidade = in.readString();
        imagens = in.createStringArrayList();
    }

    public static final Creator<Anuncio> CREATOR = new Creator<Anuncio>() {
        @Override
        public Anuncio createFromParcel(Parcel in) {
            return new Anuncio(in);
        }

        @Override
        public Anuncio[] newArray(int size) {
            return new Anuncio[size];
        }
    };

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Anuncio(){
        DatabaseReference anuncioRef = ConfiguracaoFirebase.getFirebaseDatabase()
                .child("meus_anuncios");
        setIdAnuncio(anuncioRef.push().getKey());
    }

    public void salvar(){

        String idUsuario = ConfiguracaoFirebase.getIdUsuario();
        DatabaseReference anuncioRef = ConfiguracaoFirebase.getFirebaseDatabase()
                .child("meus_anuncios");

        anuncioRef.child(idUsuario)
                //.child(getIdAnuncio())
                .setValue(this);

        salvarAnuncioPublico();
    }

    public void salvarAnuncioPublico(){


        DatabaseReference anuncioRef = ConfiguracaoFirebase.getFirebaseDatabase()
                .child("anuncios");

        anuncioRef.child(endereco.getCidade())
                .child(getIdAnuncio())
                .setValue(this);
    }

    public String getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(String idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public Anuncio(String nomeEmpresa, String endereco, String whatsapp, String logo){
        this.nomeEmpresa = nomeEmpresa;
//        this.endereco = endereco;
        this.whatsapp = whatsapp;
        this.logo = logo;
    }


    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
        this.nomeEmpresaPesquisa = nomeEmpresa.toUpperCase();
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public List<String> getImagens() {
        return imagens;
    }

    public void setImagens(List<String> imagens) {
        this.imagens = imagens;
    }

    public String getNomeEmpresaPesquisa() {
        return nomeEmpresaPesquisa;
    }

    public void setNomeEmpresaPesquisa(String nomeEmpresaPesquisa) {
        this.nomeEmpresaPesquisa = nomeEmpresaPesquisa;
    }

    @Override
    public String toString() {
        return "\nAnuncio{" +
                "nameEmpresa='" + nomeEmpresa + '\'' +
                ", endereco='" + endereco + '\'' +
                ", whatsapp='" + whatsapp + '\'' +
                ", instagram='" + instagram + '\'' +
                '}';
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idAnuncio);
        parcel.writeString(nomeEmpresa);
        parcel.writeString(nomeEmpresaPesquisa);
        parcel.writeString(logo);
        parcel.writeParcelable(endereco, i);
        parcel.writeString(descricao);
        parcel.writeString(whatsapp);
        parcel.writeString(instagram);
        parcel.writeString(cidade);
        parcel.writeStringList(imagens);
    }
}
