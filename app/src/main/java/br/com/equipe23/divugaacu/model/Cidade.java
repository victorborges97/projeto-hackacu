package br.com.equipe23.divugaacu.model;

public class Cidade {
    private String nome;
    private String id = "";

    public Cidade(String nome, String id) {
        this.nome = nome;
        this.id = id;
    }

    public Cidade() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\nCidade{" +
                "nome='" + nome + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
