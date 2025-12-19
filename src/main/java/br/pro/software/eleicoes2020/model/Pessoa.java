package br.pro.software.eleicoes2020.model;

import java.io.Serializable;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    @Column(length = 20, nullable = false)
    private String docOrigem;
    @Column(unique = true)
    private String login;
    private String senha;
    private String email;
    private String celular;
    private Boolean apto;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eleicao_id", nullable = false)
    private Eleicao eleicao;

    public Pessoa(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public static Pessoa of(Login login) {
        return new Pessoa(login.getLogin(), login.getSenha());
    }

    public boolean temEmailValido() {
        return email != null && email.length() > 5 && email.contains("@");
    }
}
