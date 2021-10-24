package br.com.equipe23.divulgacu.model;

import java.util.ArrayList;

public class Anuncio {
    private String nameEmpresa = "";
    private String nomeEmpresaPesquisa = "";
    private String logo = "";
    private String preco = "";
    private Endereco endereco = new Endereco();
    private String descricao = "";
    private String whatsapp = "";
    private String instagram = "";
    private ArrayList<String> imagens = new ArrayList<>();

    public Anuncio(){}

    public Anuncio(String nameEmpresa, String endereco, String whatsapp, String logo){
        this.nameEmpresa = nameEmpresa;
//        this.endereco = endereco;
        this.whatsapp = whatsapp;
        this.logo = logo;
    }


    public String getNameEmpresa() {
        return nameEmpresa;
    }

    public void setNameEmpresa(String nameEmpresa) {
        this.nameEmpresa = nameEmpresa;
        this.nomeEmpresaPesquisa = nameEmpresa.toUpperCase();
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
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

    public ArrayList<String> getImagens() {
        return imagens;
    }

    public void setImagens(ArrayList<String> imagens) {
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
                "nameEmpresa='" + nameEmpresa + '\'' +
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
}
