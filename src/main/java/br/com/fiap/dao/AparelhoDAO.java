package br.com.fiap.dao;

import br.com.fiap.to.AparelhoTO;
import br.com.fiap.exception.AparelhoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AparelhoDAO {

    private Connection connection;

    public AparelhoDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(AparelhoTO aparelho) throws AparelhoException {
        String sql = "INSERT INTO aparelhos (id_aparelho, id_usuario, nome, potencia_watts, horas_uso_dia, dataAdicionado) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, aparelho.getIdAparelho());
            stmt.setInt(2, aparelho.getIdUsuario());
            stmt.setString(3, aparelho.getNome());
            stmt.setFloat(4, aparelho.getPotenciaWatts());
            stmt.setFloat(5, aparelho.getHorasUsoDia());
            stmt.setTimestamp(6, aparelho.getDataAdicionado());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new AparelhoException("Erro ao inserir aparelho: " + e.getMessage());
        }
    }

    public List<AparelhoTO> findAll() throws AparelhoException {
        List<AparelhoTO> aparelhos = new ArrayList<>();
        String sql = "SELECT * FROM aparelhos";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                AparelhoTO aparelho = new AparelhoTO();
                aparelho.setIdAparelho(rs.getInt("id_aparelho"));
                aparelho.setIdUsuario(rs.getInt("id_usuario"));
                aparelho.setNome(rs.getString("nome"));
                aparelho.setPotenciaWatts(rs.getFloat("potencia_watts"));
                aparelho.setHorasUsoDia(rs.getFloat("horas_uso_dia"));
                aparelhos.add(aparelho);
            }
        } catch (SQLException e) {
            throw new AparelhoException("Erro ao listar aparelhos: " + e.getMessage());
        }
        return aparelhos;
    }

    public AparelhoTO findById(int id) throws AparelhoException {
        String sql = "SELECT * FROM aparelhos WHERE id_aparelho = ?";
        AparelhoTO aparelho = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                aparelho = new AparelhoTO();
                aparelho.setIdAparelho(rs.getInt("id_aparelho"));
                aparelho.setIdUsuario(rs.getInt("id_usuario"));
                aparelho.setNome(rs.getString("nome"));
                aparelho.setPotenciaWatts(rs.getFloat("potencia_watts"));
                aparelho.setHorasUsoDia(rs.getFloat("horas_uso_dia"));
            }
        } catch (SQLException e) {
            throw new AparelhoException("Erro ao buscar aparelho: " + e.getMessage());
        }
        return aparelho;
    }

    public void update(AparelhoTO aparelho) throws AparelhoException {
        String sql = "UPDATE aparelhos SET nome = ?, potencia_watts = ?, horas_uso_dia = ? WHERE id_aparelho = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aparelho.getNome());
            stmt.setFloat(2, aparelho.getPotenciaWatts());
            stmt.setFloat(3, aparelho.getHorasUsoDia());
            stmt.setInt(4, aparelho.getIdAparelho());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new AparelhoException("Erro ao atualizar aparelho: " + e.getMessage());
        }
    }

    public boolean delete(int id) throws AparelhoException {
        String sql = "DELETE FROM aparelhos WHERE id_aparelho = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new AparelhoException("Erro ao excluir aparelho: " + e.getMessage());
        }
        return false;
    }
}
