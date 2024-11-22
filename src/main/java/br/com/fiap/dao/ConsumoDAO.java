package br.com.fiap.dao;

import br.com.fiap.to.ConsumoTO;
import br.com.fiap.exception.ConsumoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsumoDAO extends Repository{

    public ConsumoTO create(ConsumoTO consumo) throws ConsumoException {
        String sql = "INSERT INTO consumos (consumo_kwh, data_registro) VALUES (?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setFloat(1, consumo.getConsumoKwh());
            ps.setDate(2, consumo.getDataRegistro());
            if (ps.executeUpdate() > 0){
                return consumo;
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConsumoException("Erro ao inserir consumo: " + e.getMessage());
        }finally {
            closeConnection();
        }

    }

    public ArrayList<ConsumoTO> findAll() throws ConsumoException {
        ArrayList<ConsumoTO> consumos = new ArrayList<>();
        String sql = "SELECT * FROM consumos order by id_consumo";

        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();

            if (rs != null){
                while (rs.next()) {
                    ConsumoTO consumo = new ConsumoTO();
                    consumo.setIdConsumo(rs.getLong("id_consumo"));
                    consumo.setConsumoKwh(rs.getFloat("consumo_kwh"));
                    consumo.setDataRegistro(rs.getDate("data_registro"));
                    consumos.add(consumo);
                }
            }else {
                return null;
            }

        } catch (SQLException e) {
            throw new ConsumoException("Erro ao listar consumos: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return consumos;
    }

    public ConsumoTO findById(Long idConsumo) throws ConsumoException {
        ConsumoTO consumo = new ConsumoTO();
        String sql = "SELECT * FROM consumos WHERE id_consumo = ?";

        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1, idConsumo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                consumo.setIdConsumo(rs.getLong("id_consumo"));
                consumo.setConsumoKwh(rs.getFloat("consumo_kwh"));
                consumo.setDataRegistro(rs.getDate("data_registro"));
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new ConsumoException("Erro ao buscar consumo: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return consumo;
    }

    // Atualizar um consumo
    public ConsumoTO update(ConsumoTO consumo) throws ConsumoException {
        String sql = "UPDATE consumos SET consumo_kwh = ?, data_registro = ? WHERE id_consumo = ?";

        try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setFloat(1, consumo.getConsumoKwh());
            ps.setDate(2, consumo.getDataRegistro());
            ps.setLong(3, consumo.getIdConsumo());
            if (ps.executeUpdate() > 0){
                return consumo;
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new ConsumoException("Erro ao atualizar consumo: " + e.getMessage());
        }finally {
            closeConnection();
        }

    }

    // Deletar um consumo
    public boolean delete(Long idConsumo) throws ConsumoException {
        String sql = "DELETE FROM consumos WHERE id_consumo = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idConsumo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new ConsumoException("Erro ao excluir consumo: " + e.getMessage());
        }finally {
            closeConnection();
        }
    }
}
