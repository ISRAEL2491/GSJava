package br.com.fiap.to;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ConsumoTO {
    private Integer idConsumo;

    @NotNull(message = "O ID do aparelho não pode ser nulo")
    private Integer idAparelho;

    @NotNull(message = "A data do registro não pode ser nula")
    private LocalDate dataRegistro;

    @NotNull(message = "O consumo em kWh não pode ser nulo")
    private Float consumoKwh;

    // Construtor sem parâmetros
    public ConsumoTO() {
    }

    // Construtor com parâmetros
    public ConsumoTO(@NotNull Integer idConsumo, @NotNull Integer idAparelho, @NotNull LocalDate dataRegistro,
                     @NotNull Float consumoKwh) {
        this.idConsumo = idConsumo;
        this.idAparelho = idAparelho;
        this.dataRegistro = dataRegistro;
        this.consumoKwh = consumoKwh;
    }

    // Getters e Setters
    public Integer getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(Integer idConsumo) {
        this.idConsumo = idConsumo;
    }

    public Integer getIdAparelho() {
        return idAparelho;
    }

    public void setIdAparelho(Integer idAparelho) {
        this.idAparelho = idAparelho;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Float getConsumoKwh() {
        return consumoKwh;
    }

    public void setConsumoKwh(Float consumoKwh) {
        this.consumoKwh = consumoKwh;
    }
}
