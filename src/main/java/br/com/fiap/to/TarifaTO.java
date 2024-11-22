package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;


import java.util.Date;

public class TarifaTO {
    private Long idTarifa;

    @NotNull(message = "O nome da tarifa não pode ser nulo")
    private String nomeTarifa;

    @NotNull(message = "O valor do kWh não pode ser nulo")
    private Float valorKwh;

    @NotNull(message = "A data de início não pode ser nula")
    private Date dataInicio;

    private Date dataFim;

    // Construtor sem parâmetros
    public TarifaTO() {
    }

    // Construtor com parâmetros
    public TarifaTO(@NotNull Long idTarifa, @NotNull String nomeTarifa, @NotNull Float valorKwh,
                    @NotNull Date dataInicio, Date dataFim) {
        this.idTarifa = idTarifa;
        this.nomeTarifa = nomeTarifa;
        this.valorKwh = valorKwh;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    // Getters e Setters
    public Long getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(Long idTarifa) {
        this.idTarifa = idTarifa;
    }

    public String getNomeTarifa() {
        return nomeTarifa;
    }

    public void setNomeTarifa(String nomeTarifa) {
        this.nomeTarifa = nomeTarifa;
    }

    public Float getValorKwh() {
        return valorKwh;
    }

    public void setValorKwh(Float valorKwh) {
        this.valorKwh = valorKwh;
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
