package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class EstimativaTO {
    private Long idEstimativa;

    @NotNull(message = "O consumo estimado não pode ser nulo")
    private Float consumoEstimado;

    @NotNull(message = "A data de início não pode ser nula")
    private Date dataInicio;

    private Date dataFim;

    // Construtor sem parâmetros
    public EstimativaTO() {
    }

    // Construtor com parâmetros
    public EstimativaTO(@NotNull Long idEstimativa,
                        @NotNull Float consumoEstimado, @NotNull Date dataInicio, Date dataFim) {
        this.idEstimativa = idEstimativa;
        this.consumoEstimado = consumoEstimado;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    // Getters e Setters
    public Long getIdEstimativa() {
        return idEstimativa;
    }

    public void setIdEstimativa(Long idEstimativa) {
        this.idEstimativa = idEstimativa;
    }

    public Float getConsumoEstimado() {
        return consumoEstimado;
    }

    public void setConsumoEstimado(Float consumoEstimado) {
        this.consumoEstimado = consumoEstimado;
    }

    public java.sql.Date getDataInicio() {
        return (java.sql.Date) dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public java.sql.Date getDataFim() {
        return (java.sql.Date) dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}
