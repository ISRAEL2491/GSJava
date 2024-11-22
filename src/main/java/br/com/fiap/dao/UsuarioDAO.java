package br.com.fiap.dao;

import br.com.fiap.to.UsuarioTO;
import br.com.fiap.exception.UsuarioException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends Repository{

    public UsuarioTO create(UsuarioTO usuario) throws UsuarioException {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            if (ps.executeUpdate() > 0){
                return usuario;
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new UsuarioException("Erro ao inserir usuário: " + e.getMessage());
        }finally {
            closeConnection();
        }

    }

    public ArrayList<UsuarioTO> findAll() throws UsuarioException {
        ArrayList<UsuarioTO> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios order by id_usuario";

        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();

            if (rs != null){
                while (rs.next()) {
                    UsuarioTO usuario = new UsuarioTO();
                    usuario.setIdUsuario(rs.getLong("id_usuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuarios.add(usuario);
                }
            }else {
                return null;
            }

        } catch (SQLException e) {
            throw new UsuarioException("Erro ao listar usuários: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return usuarios;
    }

    public UsuarioTO findById(Long idUsuario) throws UsuarioException {
        UsuarioTO usuario = new UsuarioTO();
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";

        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario.setIdUsuario(rs.getLong("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new UsuarioException("Erro ao buscar usuário: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return usuario;
    }

    // Atualizar um usuário
    public UsuarioTO update(UsuarioTO usuario) throws UsuarioException {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ? WHERE id_usuario = ?";

        try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setLong(4, usuario.getIdUsuario());
            if (ps.executeUpdate() > 0){
                return usuario;
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new UsuarioException("Erro ao atualizar usuário: " + e.getMessage());
        }finally {
            closeConnection();
        }

    }

    // Deletar um usuário
    public boolean delete(Long idUsuario) throws UsuarioException {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new UsuarioException("Erro ao excluir usuário: " + e.getMessage());
        }finally {
            closeConnection();
        }
    }
}
