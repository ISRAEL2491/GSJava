package br.com.fiap.dao;


import br.com.fiap.to.EstimativaTO;
import br.com.fiap.exception.EstimativaException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstimativaDAO {

    private Connection connection;

    public EstimativaDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(EstimativaTO estimativa) throws EstimativaException {
        String sql = "INSERT INTO estimativas (id_usuario, consumo_estimado, data_inicio, data_fim) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, estimativa.getIdUsuario());
            stmt.setFloat(2, estimativa.getConsumoEstimado());
            stmt.setDate(3, Date.valueOf(estimativa.getDataInicio()));
            stmt.setDate(4, Date.valueOf(estimativa.getDataFim()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new EstimativaException("Erro ao inserir estimativa: " + e.getMessage());
        }
    }

    public List<EstimativaTO> findAll() throws EstimativaException {
        List<EstimativaTO> estimativas = new ArrayList<>();
        String sql = "SELECT * FROM estimativas";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                EstimativaTO estimativa = new EstimativaTO();
                estimativa.setIdEstimativa(rs.getInt("id_estimativa"));
                estimativa.setIdUsuario(rs.getInt("id_usuario"));
                estimativa.setConsumoEstimado(rs.getFloat("consumo_estimado"));
                estimativa.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                estimativa.setDataFim(rs.getDate("data_fim").toLocalDate());
                estimativas.add(estimativa);
            }
        } catch (SQLException e) {
            throw new EstimativaException("Erro ao listar estimativas: " + e.getMessage());
        }
        return estimativas;
    }

    public EstimativaTO findById(int id) throws EstimativaException {
        String sql = "SELECT * FROM estimativas WHERE id_estimativa = ?";
        EstimativaTO estimativa = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                estimativa = new EstimativaTO();
                estimativa.setIdEstimativa(rs.getInt("id_estimativa"));
                estimativa.setIdUsuario(rs.getInt("id_usuario"));
                estimativa.setConsumoEstimado(rs.getFloat("consumo_estimado"));
                estimativa.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                estimativa.setDataFim(rs.getDate("data_fim").toLocalDate());
            }
        } catch (SQLException e) {
            throw new EstimativaException("Erro ao buscar estimativa: " + e.getMessage());
        }
        return estimativa;
    }

    public void update(EstimativaTO estimativa) throws EstimativaException {
        String sql = "UPDATE estimativas SET id_usuario = ?, consumo_estimado = ?, data_inicio = ?, data_fim = ? WHERE id_estimativa = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, estimativa.getIdUsuario());
            stmt.setFloat(2, estimativa.getConsumoEstimado());
            stmt.setDate(3, Date.valueOf(estimativa.getDataInicio()));
            stmt.setDate(4, Date.valueOf(estimativa.getDataFim()));
            stmt.setInt(5, estimativa.getIdEstimativa());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new EstimativaException("Erro ao atualizar estimativa: " + e.getMessage());
        }
    }

    public void delete(int id) throws EstimativaException {
        String sql = "DELETE FROM estimativas WHERE id_estimativa = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new EstimativaException("Erro ao excluir estimativa: " + e.getMessage());
        }
    }
}
