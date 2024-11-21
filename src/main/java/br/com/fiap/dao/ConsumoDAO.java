package br.com.fiap.dao;

import br.com.fiap.to.ConsumoTO;
import br.com.fiap.exception.ConsumoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsumoDAO {

    private Connection connection;

    public ConsumoDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(ConsumoTO consumo) throws ConsumoException {
        String sql = "INSERT INTO consumos (id_aparelho, data_registro, consumo_kwh) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, consumo.getIdAparelho());
            stmt.setDate(2, Date.valueOf(consumo.getDataRegistro()));
            stmt.setFloat(3, consumo.getConsumoKwh());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ConsumoException("Erro ao inserir consumo: " + e.getMessage());
        }
    }

    public List<ConsumoTO> findAll() throws ConsumoException {
        List<ConsumoTO> consumos = new ArrayList<>();
        String sql = "SELECT * FROM consumos";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ConsumoTO consumo = new ConsumoTO();
                consumo.setIdConsumo(rs.getInt("id_consumo"));
                consumo.setIdAparelho(rs.getInt("id_aparelho"));

                // Verificando se a data é nula antes de converter
                Date dataRegistro = rs.getDate("data_registro");
                if (dataRegistro != null) {
                    consumo.setDataRegistro(dataRegistro.toLocalDate());
                } else {
                    consumo.setDataRegistro(null);
                }

                consumo.setConsumoKwh(rs.getFloat("consumo_kwh"));
                consumos.add(consumo);
            }
        } catch (SQLException e) {
            throw new ConsumoException("Erro ao listar consumos: " + e.getMessage());
        }
        return consumos;
    }

    public ConsumoTO findById(int id) throws ConsumoException {
        String sql = "SELECT * FROM consumos WHERE id_consumo = ?";
        ConsumoTO consumo = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                consumo = new ConsumoTO();
                consumo.setIdConsumo(rs.getInt("id_consumo"));
                consumo.setIdAparelho(rs.getInt("id_aparelho"));

                // Verificando se a data é nula antes de converter
                Date dataRegistro = rs.getDate("data_registro");
                if (dataRegistro != null) {
                    consumo.setDataRegistro(dataRegistro.toLocalDate());
                } else {
                    consumo.setDataRegistro(null);
                }

                consumo.setConsumoKwh(rs.getFloat("consumo_kwh"));
            }
        } catch (SQLException e) {
            throw new ConsumoException("Erro ao buscar consumo: " + e.getMessage());
        }
        return consumo;
    }

    public void update(ConsumoTO consumo) throws ConsumoException {
        String sql = "UPDATE consumos SET id_aparelho = ?, data_registro = ?, consumo_kwh = ? WHERE id_consumo = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, consumo.getIdAparelho());
            stmt.setDate(2, Date.valueOf(consumo.getDataRegistro()));
            stmt.setFloat(3, consumo.getConsumoKwh());
            stmt.setInt(4, consumo.getIdConsumo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ConsumoException("Erro ao atualizar consumo: " + e.getMessage());
        }
    }

    public void delete(int id) throws ConsumoException {
        String sql = "DELETE FROM consumos WHERE id_consumo = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ConsumoException("Erro ao excluir consumo: " + e.getMessage());
        }
    }
}
