package br.com.fiap.dao;

import br.com.fiap.to.AparelhoTO;
import br.com.fiap.exception.AparelhoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AparelhoDAO extends Repository{

    public AparelhoTO create(AparelhoTO aparelho) throws AparelhoException {
        String sql = "INSERT INTO aparelhos (nome, potencia_watts, horas_uso_dia) VALUES (?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, aparelho.getNome());
            ps.setFloat(2, aparelho.getPotenciaWatts());
            ps.setFloat(3, aparelho.getHorasUsoDia());
            if (ps.executeUpdate() > 0){
                return aparelho;
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new AparelhoException("Erro ao inserir aparelho: " + e.getMessage());
        }finally {
            closeConnection();
        }

    }

    public ArrayList<AparelhoTO> findAll() throws AparelhoException {
        ArrayList<AparelhoTO> aparelhos = new ArrayList<>();
        String sql = "SELECT * FROM aparelhos order by id_aparelho";

        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();

            if (rs != null){
                while (rs.next()) {
                    AparelhoTO aparelho = new AparelhoTO();
                    aparelho.setIdAparelho(rs.getLong("id_aparelho"));
                    aparelho.setNome(rs.getString("nome"));
                    aparelho.setPotenciaWatts(rs.getFloat("potencia_watts"));
                    aparelho.setHorasUsoDia(rs.getFloat("horas_uso_dia"));
                    aparelhos.add(aparelho);
                }
            }else {
                return null;
            }

        } catch (SQLException e) {
            throw new AparelhoException("Erro ao listar aparelhos: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return aparelhos;
    }

    public AparelhoTO findById(Long idAparelho) throws AparelhoException {
        AparelhoTO aparelho = new AparelhoTO();
        String sql = "SELECT * FROM aparelhos WHERE id_aparelho = ?";

        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1, idAparelho);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                aparelho.setIdAparelho(rs.getLong("id_aparelho"));
                aparelho.setNome(rs.getString("nome"));
                aparelho.setPotenciaWatts(rs.getFloat("potencia_watts"));
                aparelho.setHorasUsoDia(rs.getFloat("horas_uso_dia"));
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new AparelhoException("Erro ao buscar aparelho: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return aparelho;
    }

    // Atualizar um usuário
    public AparelhoTO update(AparelhoTO aparelho) throws AparelhoException {
        String sql = "UPDATE aparelhos SET nome = ?, potencia_watts = ?, horas_uso_dia = ? WHERE id_aparelho = ?";

        try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, aparelho.getNome());
            ps.setFloat(2, aparelho.getPotenciaWatts());
            ps.setFloat(3, aparelho.getHorasUsoDia());
            ps.setLong(4, aparelho.getIdAparelho());
            if (ps.executeUpdate() > 0){
                return aparelho;
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new AparelhoException("Erro ao atualizar aparelho: " + e.getMessage());
        }finally {
            closeConnection();
        }

    }

    // Deletar um usuário
    public boolean delete(Long idAparelho) throws AparelhoException {
        String sql = "DELETE FROM aparelhos WHERE id_aparelho = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idAparelho);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new AparelhoException("Erro ao excluir aparelho: " + e.getMessage());
        }finally {
            closeConnection();
        }
    }
}
