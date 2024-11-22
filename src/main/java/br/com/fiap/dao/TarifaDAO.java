package br.com.fiap.dao;

import br.com.fiap.to.TarifaTO;
import br.com.fiap.exception.TarifaException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarifaDAO extends Repository{

    public TarifaTO create(TarifaTO tarifa) throws TarifaException {
        String sql = "INSERT INTO tarifas (nome_tarifa, valor_kwh, data_inicio, data_fim) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, tarifa.getNomeTarifa());
            ps.setFloat(2, tarifa.getValorKwh());
            ps.setDate(3, tarifa.getDataInicio());
            ps.setDate(4, tarifa.getDataFim());
            if (ps.executeUpdate() > 0){
                return tarifa;
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new TarifaException("Erro ao inserir tarifa: " + e.getMessage());
        }finally {
            closeConnection();
        }

    }

    public ArrayList<TarifaTO> findAll() throws TarifaException {
        ArrayList<TarifaTO> tarifas = new ArrayList<>();
        String sql = "SELECT * FROM tarifas order by id_tarifa";

        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();

            if (rs != null){
                while (rs.next()) {
                    TarifaTO tarifa = new TarifaTO();
                    tarifa.setIdTarifa(rs.getLong("id_tarifa"));
                    tarifa.setNomeTarifa(rs.getString("nome_tarifa"));
                    tarifa.setValorKwh(rs.getFloat("valor_kwh"));
                    tarifa.setDataInicio(rs.getDate("data_inicio"));
                    tarifa.setDataFim(rs.getDate("data_fim"));
                    tarifas.add(tarifa);
                }
            }else {
                return null;
            }

        } catch (SQLException e) {
            throw new TarifaException("Erro ao listar tarifas: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return tarifas;
    }

    public TarifaTO findById(Long idTarifa) throws TarifaException {
        TarifaTO tarifa = new TarifaTO();
        String sql = "SELECT * FROM tarifas WHERE id_tarifa = ?";

        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1, idTarifa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tarifa.setIdTarifa(rs.getLong("id_tarifa"));
                tarifa.setNomeTarifa(rs.getString("nome_tarifa"));
                tarifa.setValorKwh(rs.getFloat("valor_kwh"));
                tarifa.setDataInicio(rs.getDate("data_inicio"));
                tarifa.setDataFim(rs.getDate("data_fim"));
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new TarifaException("Erro ao buscar tarifa: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return tarifa;
    }

    // Atualizar um usuário
    public TarifaTO update(TarifaTO tarifa) throws TarifaException {
        String sql = "UPDATE tarifas SET nome_tarifa = ?, valor_kwh = ?, data_inicio = ?, data_fim = ? WHERE id_tarifa = ?";

        try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, tarifa.getNomeTarifa());
            ps.setFloat(2, tarifa.getValorKwh());
            ps.setDate(3, tarifa.getDataInicio());
            ps.setDate(4, tarifa.getDataFim());
            ps.setLong(5, tarifa.getIdTarifa());
            if (ps.executeUpdate() > 0){
                return tarifa;
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new TarifaException("Erro ao atualizar tarifa: " + e.getMessage());
        }finally {
            closeConnection();
        }

    }

    // Deletar um usuário
    public boolean delete(Long idTarifa) throws TarifaException {
        String sql = "DELETE FROM tarifas WHERE id_tarifa = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idTarifa);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new TarifaException("Erro ao excluir tarifa: " + e.getMessage());
        }finally {
            closeConnection();
        }
    }
}
