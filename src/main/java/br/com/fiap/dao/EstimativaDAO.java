package br.com.fiap.dao;

import br.com.fiap.to.EstimativaTO;
import br.com.fiap.exception.EstimativaException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstimativaDAO extends Repository{

    public EstimativaTO create(EstimativaTO estimativa) throws EstimativaException {
        String sql = "INSERT INTO estimativas (consumo_estimado, data_fim, data_fim) VALUES (?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setFloat(1, estimativa.getConsumoEstimado());
            ps.setDate(2, estimativa.getDataInicio());
            ps.setDate(3, estimativa.getDataFim());
            if (ps.executeUpdate() > 0){
                return estimativa;
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new EstimativaException("Erro ao inserir estimativa: " + e.getMessage());
        }finally {
            closeConnection();
        }

    }

    public ArrayList<EstimativaTO> findAll() throws EstimativaException {
        ArrayList<EstimativaTO> estimativas = new ArrayList<>();
        String sql = "SELECT * FROM estimativas order by id_estimativa";

        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();

            if (rs != null){
                while (rs.next()) {
                    EstimativaTO estimativa = new EstimativaTO();
                    estimativa.setIdEstimativa(rs.getLong("id_estimativa"));
                    estimativa.setConsumoEstimado(rs.getFloat("consumo_estimado"));
                    estimativa.setDataInicio(rs.getDate("data_inicio"));
                    estimativa.setDataFim(rs.getDate("data_fim"));
                    estimativas.add(estimativa);
                }
            }else {
                return null;
            }

        } catch (SQLException e) {
            throw new EstimativaException("Erro ao listar estimativas: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return estimativas;
    }

    public EstimativaTO findById(Long idEstimativa) throws EstimativaException {
        EstimativaTO estimativa = new EstimativaTO();
        String sql = "SELECT * FROM estimativas WHERE id_estimativa = ?";

        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1, idEstimativa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                estimativa.setIdEstimativa(rs.getLong("id_estimativa"));
                estimativa.setConsumoEstimado(rs.getFloat("consumo_estimado"));
                estimativa.setDataInicio(rs.getDate("data_inicio"));
                estimativa.setDataFim(rs.getDate("data_fim"));
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new EstimativaException("Erro ao buscar estimativa: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return estimativa;
    }

    // Atualizar um estimativa
    public EstimativaTO update(EstimativaTO estimativa) throws EstimativaException {
        String sql = "UPDATE estimativas SET consumo_estimado = ?, data_inicio = ?, data_fim = ? WHERE id_estimativa = ?";

        try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setFloat(1, estimativa.getConsumoEstimado());
            ps.setDate(2, estimativa.getDataInicio());
            ps.setDate(3, estimativa.getDataFim());
            ps.setLong(4, estimativa.getIdEstimativa());
            if (ps.executeUpdate() > 0){
                return estimativa;
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new EstimativaException("Erro ao atualizar estimativa: " + e.getMessage());
        }finally {
            closeConnection();
        }

    }

    // Deletar um estimativa
    public boolean delete(Long idEstimativa) throws EstimativaException {
        String sql = "DELETE FROM estimativas WHERE id_estimativa = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idEstimativa);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new EstimativaException("Erro ao excluir estimativa: " + e.getMessage());
        }finally {
            closeConnection();
        }
    }
}
