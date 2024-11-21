package br.com.fiap.to;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import java.sql.Timestamp;

public class UsuarioTO {
    private Integer idUsuario;

    @NotNull(message = "O nome não pode ser nulo")
    private String nome;

    @NotNull(message = "O email não pode ser nulo")
    @Email(message = "Email inválido")
    private String email;

    @NotNull(message = "A senha não pode ser nula")
    private String senha;

    private Timestamp dataCadastro;

    // Construtor sem parâmetros
    public UsuarioTO() {
    }

    // Construtor com parâmetros
    public UsuarioTO(@NotNull Integer idUsuario, @NotNull String nome, @NotNull @Email String email,
                     @NotNull String senha, Timestamp dataCadastro) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataCadastro = dataCadastro;
    }

    // Getters e Setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
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

    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
