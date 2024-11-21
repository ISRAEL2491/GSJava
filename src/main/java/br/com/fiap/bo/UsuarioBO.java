package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.exception.UsuarioException;
import br.com.fiap.to.UsuarioTO;
import java.util.List;

public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    // Construtor para injetar o DAO
    public UsuarioBO() {
        this.usuarioDAO = usuarioDAO;
    }

    // Método para criar um usuário
    public void criarUsuario(UsuarioTO usuario) throws UsuarioException {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new UsuarioException("O nome do usuário não pode ser nulo ou vazio.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new UsuarioException("O email do usuário não pode ser nulo ou vazio.");
        }

        // Lógica para salvar o usuário no banco de dados
        try {
            usuarioDAO.create(usuario);
        } catch (Exception e) {
            throw new UsuarioException("Erro ao criar usuário: " + e.getMessage());
        }
    }

    // Método para listar todos os usuários
    public List<UsuarioTO> listarUsuarios() throws UsuarioException {
        try {
            return usuarioDAO.findAll();
        } catch (Exception e) {
            throw new UsuarioException("Erro ao listar usuários: " + e.getMessage());
        }
    }

    // Método para buscar usuário por ID
    public UsuarioTO buscarUsuarioPorId(Integer id) throws UsuarioException {
        if (id == null || id <= 0) {
            throw new UsuarioException("ID do usuário não pode ser nulo ou menor que zero.");
        }

        try {
            return usuarioDAO.findById(id);
        } catch (Exception e) {
            throw new UsuarioException("Erro ao buscar usuário: " + e.getMessage());
        }
    }

    // Método para atualizar um usuário
    public void atualizarUsuario(UsuarioTO usuario) throws UsuarioException {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new UsuarioException("O nome do usuário não pode ser nulo ou vazio.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new UsuarioException("O email do usuário não pode ser nulo ou vazio.");
        }

        // Lógica para atualizar o usuário no banco de dados
        try {
            usuarioDAO.update(usuario);
        } catch (Exception e) {
            throw new UsuarioException("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    // Método para excluir um usuário
    public void excluirUsuario(Integer id) throws UsuarioException {
        if (id == null || id <= 0) {
            throw new UsuarioException("ID do usuário não pode ser nulo ou menor que zero.");
        }

        // Lógica para excluir o usuário no banco de dados
        try {
            usuarioDAO.delete(id);
        } catch (Exception e) {
            throw new UsuarioException("Erro ao excluir usuário: " + e.getMessage());
        }
    }
}
