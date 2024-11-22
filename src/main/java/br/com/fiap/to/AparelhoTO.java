package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public class AparelhoTO {
    private Long idAparelho;

    @NotNull(message = "O nome do aparelho não pode ser nulo")
    private String nome;

    @NotNull(message = "A potência do aparelho não pode ser nula")
    private Float potenciaWatts;

    @NotNull(message = "As horas de uso diárias não podem ser nulas")
    private Float horasUsoDia;

    // Construtor sem parâmetros
    public AparelhoTO() {
    }

    // Construtor com parâmetros
    public AparelhoTO(@NotNull Long idAparelho, @NotNull String nome,
                      @NotNull Float potenciaWatts, @NotNull Float horasUsoDia) {
        this.idAparelho = idAparelho;
        this.nome = nome;
        this.potenciaWatts = potenciaWatts;
        this.horasUsoDia = horasUsoDia;
    }

    // Getters e Setters
    public Long getIdAparelho() {
        return idAparelho;
    }

    public void setIdAparelho(Long idAparelho) {
        this.idAparelho = idAparelho;
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

}
