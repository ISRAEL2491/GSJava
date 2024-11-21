package br.com.fiap.to;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TarifaTO {
    private Integer idTarifa;

    @NotNull(message = "O nome da tarifa não pode ser nulo")
    private String nomeTarifa;

    @NotNull(message = "O valor do kWh não pode ser nulo")
    private Float valorKwh;

    @NotNull(message = "A data de início não pode ser nula")
    private LocalDate dataInicio;

    private LocalDate dataFim;

    // Construtor sem parâmetros
    public TarifaTO() {
    }

    // Construtor com parâmetros
    public TarifaTO(@NotNull Integer idTarifa, @NotNull String nomeTarifa, @NotNull Float valorKwh,
                    @NotNull LocalDate dataInicio, LocalDate dataFim) {
        this.idTarifa = idTarifa;
        this.nomeTarifa = nomeTarifa;
        this.valorKwh = valorKwh;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    // Getters e Setters
    public Integer getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(Integer idTarifa) {
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
