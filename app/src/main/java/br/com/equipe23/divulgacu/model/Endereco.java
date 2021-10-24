package br.com.equipe23.divulgacu.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Endereco implements Parcelable {
    private String rua = "";
    private String numero = "";
    private String bairro = "";
    private String cidade = "";

    public Endereco(){

    }

    protected Endereco(Parcel in) {
        rua = in.readString();
        numero = in.readString();
        bairro = in.readString();
        cidade = in.readString();
    }

    public static final Creator<Endereco> CREATOR = new Creator<Endereco>() {
        @Override
        public Endereco createFromParcel(Parcel in) {
            return new Endereco(in);
        }

        @Override
        public Endereco[] newArray(int size) {
            return new Endereco[size];
        }
    };

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "\nEndereco{" +
                "rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(rua);
        parcel.writeString(numero);
        parcel.writeString(bairro);
        parcel.writeString(cidade);
    }
}
