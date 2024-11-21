package br.com.fiap.dao;

import br.com.fiap.to.TarifaTO;
import br.com.fiap.exception.TarifaException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarifaDAO {

    private Connection connection;

    public TarifaDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(TarifaTO tarifa) throws TarifaException {
        String sql = "INSERT INTO tarifas (nome_tarifa, valor_kwh, data_inicio, data_fim) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tarifa.getNomeTarifa());
            stmt.setFloat(2, tarifa.getValorKwh());
            stmt.setDate(3, Date.valueOf(tarifa.getDataInicio()));
            stmt.setDate(4, Date.valueOf(tarifa.getDataFim()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new TarifaException("Erro ao inserir tarifa: " + e.getMessage());
        }
    }

    public List<TarifaTO> findAll() throws TarifaException {
        List<TarifaTO> tarifas = new ArrayList<>();
        String sql = "SELECT * FROM tarifas";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TarifaTO tarifa = new TarifaTO();
                tarifa.setIdTarifa(rs.getInt("id_tarifa"));
                tarifa.setNomeTarifa(rs.getString("nome_tarifa"));
                tarifa.setValorKwh(rs.getFloat("valor_kwh"));
                tarifa.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                tarifa.setDataFim(rs.getDate("data_fim").toLocalDate());
                tarifas.add(tarifa);
            }
        } catch (SQLException e) {
            throw new TarifaException("Erro ao listar tarifas: " + e.getMessage());
        }
        return tarifas;
    }

    public TarifaTO findById(int id) throws TarifaException {
        String sql = "SELECT * FROM tarifas WHERE id_tarifa = ?";
        TarifaTO tarifa = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tarifa = new TarifaTO();
                tarifa.setIdTarifa(rs.getInt("id_tarifa"));
                tarifa.setNomeTarifa(rs.getString("nome_tarifa"));
                tarifa.setValorKwh(rs.getFloat("valor_kwh"));
                tarifa.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                tarifa.setDataFim(rs.getDate("data_fim").toLocalDate());
            }
        } catch (SQLException e) {
            throw new TarifaException("Erro ao buscar tarifa: " + e.getMessage());
        }
        return tarifa;
    }

    public void update(TarifaTO tarifa) throws TarifaException {
        String sql = "UPDATE tarifas SET nome_tarifa = ?, valor_kwh = ?, data_inicio = ?, data_fim = ? WHERE id_tarifa = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tarifa.getNomeTarifa());
            stmt.setFloat(2, tarifa.getValorKwh());
            stmt.setDate(3, Date.valueOf(tarifa.getDataInicio()));
            stmt.setDate(4, Date.valueOf(tarifa.getDataFim()));
            stmt.setInt(5, tarifa.getIdTarifa());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new TarifaException("Erro ao atualizar tarifa: " + e.getMessage());
        }
    }

    public void delete(int id) throws TarifaException {
        String sql = "DELETE FROM tarifas WHERE id_tarifa = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new TarifaException("Erro ao excluir tarifa: " + e.getMessage());
        }
    }
}
