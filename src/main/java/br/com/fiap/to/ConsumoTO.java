package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;


import java.util.Date;

public class ConsumoTO {
    private Long idConsumo;

    @NotNull(message = "A data do registro não pode ser nula")
    private Date dataRegistro;

    @NotNull(message = "O consumo em kWh não pode ser nulo")
    private Float consumoKwh;

    // Construtor sem parâmetros
    public ConsumoTO() {
    }

    // Construtor com parâmetros
    public ConsumoTO(@NotNull Long idConsumo, @NotNull Date dataRegistro,
                     @NotNull Float consumoKwh) {
        this.idConsumo = idConsumo;
        this.dataRegistro = dataRegistro;
        this.consumoKwh = consumoKwh;
    }

    // Getters e Setters
    public Long getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(Long idConsumo) {
        this.idConsumo = idConsumo;
    }

    public java.sql.Date getDataRegistro() {
        return (java.sql.Date) dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Float getConsumoKwh() {
        return consumoKwh;
    }

    public void setConsumoKwh(Float consumoKwh) {
        this.consumoKwh = consumoKwh;
    }
}
