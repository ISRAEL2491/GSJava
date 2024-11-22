package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.exception.UsuarioException;
import br.com.fiap.to.UsuarioTO;

import java.util.ArrayList;
import java.util.List;

public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    public UsuarioTO create(UsuarioTO usuario) throws UsuarioException {
        if (usuario == null || usuario.getNome() == null || usuario.getNome().isEmpty() ||
                usuario.getEmail() == null || usuario.getEmail().isEmpty() ||
                usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            throw new UsuarioException("Dados do usuário inválidos. Nome, email e senha são obrigatórios.");
        }

        try {
            usuarioDAO = new UsuarioDAO();
            return usuarioDAO.create(usuario);
        } catch (Exception e) {
            throw new UsuarioException("Erro ao criar usuário: " + e.getMessage());
        }
    }

    public ArrayList<UsuarioTO> findAll() throws UsuarioException {
        try {
            usuarioDAO = new UsuarioDAO();
            return usuarioDAO.findAll();
        } catch (Exception e) {
            throw new UsuarioException("Erro ao listar usuários: " + e.getMessage());
        }
    }

    public UsuarioTO findById(Long idUsuario) throws UsuarioException {
        if (idUsuario == null || idUsuario <= 0) {
            throw new UsuarioException("ID do usuário não pode ser nulo ou menor que zero.");
        }

        try {
            usuarioDAO = new UsuarioDAO();
            return usuarioDAO.findById(idUsuario);
        } catch (Exception e) {
            throw new UsuarioException("Erro ao buscar usuário: " + e.getMessage());
        }
    }

    public UsuarioTO update(UsuarioTO usuario) throws UsuarioException {
        if (usuario == null || usuario.getIdUsuario() == null || usuario.getIdUsuario() <= 0) {
            throw new UsuarioException("ID do usuário inválido.");
        }
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new UsuarioException("O nome do usuário não pode ser nulo ou vazio.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new UsuarioException("O email do usuário não pode ser nulo ou vazio.");
        }

        try {
            usuarioDAO = new UsuarioDAO();
            return usuarioDAO.update(usuario);
        } catch (Exception e) {
            throw new UsuarioException("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public boolean delete(Long idUsuario) throws UsuarioException {
        if (idUsuario == null || idUsuario <= 0) {
            throw new UsuarioException("ID do usuário não pode ser nulo ou menor que zero.");
        }

        try {
            usuarioDAO = new UsuarioDAO();
            return usuarioDAO.delete(idUsuario);
        } catch (Exception e) {
            throw new UsuarioException("Erro ao excluir usuário: " + e.getMessage());
        }
    }
}
