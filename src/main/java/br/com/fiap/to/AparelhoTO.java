package br.com.fiap.to;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class AparelhoTO {
    private Integer idAparelho;

    @NotNull(message = "O ID do usuário não pode ser nulo")
    private Integer idUsuario;

    @NotNull(message = "O nome do aparelho não pode ser nulo")
    private String nome;

    @NotNull(message = "A potência do aparelho não pode ser nula")
    private Float potenciaWatts;

    @NotNull(message = "As horas de uso diárias não podem ser nulas")
    private Float horasUsoDia;

    private Timestamp dataAdicionado;

    // Construtor sem parâmetros
    public AparelhoTO() {
    }

    // Construtor com parâmetros
    public AparelhoTO(@NotNull Integer idAparelho, @NotNull Integer idUsuario, @NotNull String nome,
                      @NotNull Float potenciaWatts, @NotNull Float horasUsoDia, Timestamp dataAdicionado) {
        this.idAparelho = idAparelho;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.potenciaWatts = potenciaWatts;
        this.horasUsoDia = horasUsoDia;
        this.dataAdicionado = dataAdicionado;
    }

    // Getters e Setters
    public Integer getIdAparelho() {
        return idAparelho;
    }

    public void setIdAparelho(Integer idAparelho) {
        this.idAparelho = idAparelho;
    }

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

    public Float getPotenciaWatts() {
        return potenciaWatts;
    }

    public void setPotenciaWatts(Float potenciaWatts) {
        this.potenciaWatts = potenciaWatts;
    }

    public Float getHorasUsoDia() {
        return horasUsoDia;
    }

    public void setHorasUsoDia(Float horasUsoDia) {
        this.horasUsoDia = horasUsoDia;
    }

    public Timestamp getDataAdicionado() {
        return dataAdicionado;
    }

    public void setDataAdicionado(Timestamp dataAdicionado) {
        this.dataAdicionado = dataAdicionado;
    }
}
