package br.com.fiap.to;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public class UsuarioTO {
    private Long idUsuario;

    @NotNull(message = "O nome não pode ser nulo")
    private String nome;

    @NotNull(message = "O email não pode ser nulo")
    @Email(message = "Email inválido")
    private String email;

    @NotNull(message = "A senha não pode ser nula")
    private String senha;

    // Construtor sem parâmetros
    public UsuarioTO() {
    }

    // Construtor com parâmetros
    public UsuarioTO(@NotNull Long idUsuario, @NotNull String nome, @NotNull @Email String email,
                     @NotNull String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Getters e Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
