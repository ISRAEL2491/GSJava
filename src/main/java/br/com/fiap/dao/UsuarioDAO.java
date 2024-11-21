package br.com.fiap.dao;

import br.com.fiap.to.UsuarioTO;
import br.com.fiap.exception.UsuarioException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    // Criar um usuário
    public void create(UsuarioTO usuario) throws UsuarioException {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UsuarioException("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    // Listar todos os usuários
    public List<UsuarioTO> findAll() throws UsuarioException {
        List<UsuarioTO> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                UsuarioTO usuario = new UsuarioTO();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDataCadastro(rs.getTimestamp("data_cadastro"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new UsuarioException("Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    // Buscar um usuário por ID
    public UsuarioTO findById(int id) throws UsuarioException {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        UsuarioTO usuario = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioTO();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDataCadastro(rs.getTimestamp("data_cadastro"));
            }
        } catch (SQLException e) {
            throw new UsuarioException("Erro ao buscar usuário: " + e.getMessage());
        }
        return usuario;
    }

    // Atualizar um usuário
    public void update(UsuarioTO usuario) throws UsuarioException {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ? WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getIdUsuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UsuarioException("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    // Deletar um usuário
    public void delete(int id) throws UsuarioException {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UsuarioException("Erro ao excluir usuário: " + e.getMessage());
        }
    }
}
