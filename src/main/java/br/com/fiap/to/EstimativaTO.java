package br.com.fiap.to;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class EstimativaTO {
    private Integer idEstimativa;

    @NotNull(message = "O ID do usuário não pode ser nulo")
    private Integer idUsuario;

    @NotNull(message = "O ID da tarifa não pode ser nulo")
    private Integer idTarifa;

    @NotNull(message = "O consumo estimado não pode ser nulo")
    private Float consumoEstimado;

    @NotNull(message = "A data de início não pode ser nula")
    private LocalDate dataInicio;

    private LocalDate dataFim;

    // Construtor sem parâmetros
    public EstimativaTO() {
    }

    // Construtor com parâmetros
    public EstimativaTO(@NotNull Integer idEstimativa, @NotNull Integer idUsuario, @NotNull Integer idTarifa,
                        @NotNull Float consumoEstimado, @NotNull LocalDate dataInicio, LocalDate dataFim) {
        this.idEstimativa = idEstimativa;
        this.idUsuario = idUsuario;
        this.idTarifa = idTarifa;
        this.consumoEstimado = consumoEstimado;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    // Getters e Setters
    public Integer getIdEstimativa() {
        return idEstimativa;
    }

    public void setIdEstimativa(Integer idEstimativa) {
        this.idEstimativa = idEstimativa;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(Integer idTarifa) {
        this.idTarifa = idTarifa;
    }

    public Float getConsumoEstimado() {
        return consumoEstimado;
    }

    public void setConsumoEstimado(Float consumoEstimado) {
        this.consumoEstimado = consumoEstimado;
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
