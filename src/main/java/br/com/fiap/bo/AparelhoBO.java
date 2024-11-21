package br.com.fiap.bo;

import br.com.fiap.dao.AparelhoDAO;
import br.com.fiap.exception.AparelhoException;
import br.com.fiap.to.AparelhoTO;

import java.util.List;

public class AparelhoBO {

    private AparelhoDAO aparelhoDAO;

    // Construtor para injetar o DAO
    public AparelhoBO() {
        this.aparelhoDAO = aparelhoDAO;
    }

    // Método para criar um aparelho
    public void criarAparelho(AparelhoTO aparelho) throws AparelhoException {
        if (aparelho.getNome() == null || aparelho.getNome().isEmpty()) {
            throw new AparelhoException("O nome do aparelho não pode ser nulo ou vazio.");
        }
        if (aparelho.getPotenciaWatts() <= 0) {
            throw new AparelhoException("A potência do aparelho deve ser maior que zero.");
        }

        // Lógica para salvar o aparelho no banco de dados (DAO)
        try {
            aparelhoDAO.create(aparelho);
        } catch (Exception e) {
            throw new AparelhoException("Erro ao criar o aparelho: " + e.getMessage());
        }
    }

    // Método para listar todos os aparelhos
    public List<AparelhoTO> listarAparelhos() {
        // Lógica para listar todos os aparelhos (DAO)
        try {
            return aparelhoDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar aparelhos: " + e.getMessage());
        }
    }

    // Método para buscar aparelho por ID
    public AparelhoTO buscarAparelhoPorId(Integer id) throws AparelhoException {
        if (id == null || id <= 0) {
            throw new AparelhoException("O ID do aparelho deve ser válido.");
        }

        // Lógica para buscar aparelho por ID (DAO)
        AparelhoTO aparelho = aparelhoDAO.findById(id);
        if (aparelho == null) {
            throw new AparelhoException("Aparelho não encontrado com o ID fornecido.");
        }
        return aparelho;
    }

    // Método para atualizar um aparelho
    public void atualizarAparelho(AparelhoTO aparelho) throws AparelhoException {
        if (aparelho.getIdAparelho() == null || aparelho.getIdAparelho() <= 0) {
            throw new AparelhoException("O ID do aparelho para atualização é inválido.");
        }
        if (aparelho.getNome() == null || aparelho.getNome().isEmpty()) {
            throw new AparelhoException("O nome do aparelho não pode ser nulo ou vazio.");
        }
        if (aparelho.getPotenciaWatts() <= 0) {
            throw new AparelhoException("A potência do aparelho deve ser maior que zero.");
        }

        // Lógica para atualizar o aparelho no banco de dados (DAO)
        try {
            aparelhoDAO.update(aparelho);
        } catch (Exception e) {
            throw new AparelhoException("Erro ao atualizar o aparelho: " + e.getMessage());
        }
    }

    // Método para excluir um aparelho
    public void excluirAparelho(Integer id) throws AparelhoException {
        if (id == null || id <= 0) {
            throw new AparelhoException("O ID do aparelho para exclusão é inválido.");
        }

        // Lógica para excluir o aparelho no banco de dados (DAO)
        try {
            boolean deleted = aparelhoDAO.delete(id);
            if (!deleted) {
                throw new AparelhoException("Aparelho não encontrado para exclusão.");
            }
        } catch (Exception e) {
            throw new AparelhoException("Erro ao excluir o aparelho: " + e.getMessage());
        }
    }
}
