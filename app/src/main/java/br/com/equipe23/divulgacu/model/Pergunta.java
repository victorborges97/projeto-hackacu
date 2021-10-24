package br.com.equipe23.divulgacu.model;

public class Pergunta {
    private String titulo;
    private String descricao;

    public Pergunta(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Pergunta() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
