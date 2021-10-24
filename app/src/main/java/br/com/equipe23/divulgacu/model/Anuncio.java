package br.com.equipe23.divulgacu.model;

import java.util.ArrayList;

public class Anuncio {
    private String nameEmpresa = "";
    private String preco = "";
    private String endereco = "";
    private String descricao = "";
    private String whatsapp = "";
    private String instagram = "";
    private ArrayList<String> imagens = new ArrayList<>();


    public String getNameEmpresa() {
        return nameEmpresa;
    }

    public void setNameEmpresa(String nameEmpresa) {
        this.nameEmpresa = nameEmpresa;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
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

    public ArrayList<String> getImagens() {
        return imagens;
    }

    public void setImagens(ArrayList<String> imagens) {
        this.imagens = imagens;
    }

    @Override
    public String toString() {
        return "\nAnuncio{" +
                "nameEmpresa='" + nameEmpresa + '\'' +
                ", endereco='" + endereco + '\'' +
                ", whatsapp='" + whatsapp + '\'' +
                ", instagram='" + instagram + '\'' +
                '}';
    }
}
