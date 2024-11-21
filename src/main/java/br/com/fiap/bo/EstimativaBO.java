package br.com.fiap.bo;

import br.com.fiap.dao.EstimativaDAO;
import br.com.fiap.exception.EstimativaException;
import br.com.fiap.to.EstimativaTO;
import java.util.List;

public class EstimativaBO {

    private EstimativaDAO estimativaDAO;

    // Construtor para injetar o DAO
    public EstimativaBO() {
        this.estimativaDAO = estimativaDAO;
    }

    // Método para criar uma estimativa
    public void criarEstimativa(EstimativaTO estimativa) throws EstimativaException {
        if (estimativa.getConsumoEstimado() <= 0) {
            throw new EstimativaException("O consumo estimado deve ser maior que zero.");
        }

        if (estimativa.getDataInicio() == null) {
            throw new EstimativaException("A data de início da estimativa não pode ser nula.");
        }

        // Lógica para salvar a estimativa no banco de dados
        try {
            estimativaDAO.create(estimativa);
        } catch (Exception e) {
            throw new EstimativaException("Erro ao criar estimativa: " + e.getMessage());
        }
    }

    // Método para listar todas as estimativas
    public List<EstimativaTO> listarEstimativas() throws EstimativaException {
        try {
            return estimativaDAO.findAll();
        } catch (Exception e) {
            throw new EstimativaException("Erro ao listar estimativas: " + e.getMessage());
        }
    }

    // Método para buscar estimativa por ID
    public EstimativaTO buscarEstimativaPorId(Integer id) throws EstimativaException {
        if (id == null || id <= 0) {
            throw new EstimativaException("ID da estimativa não pode ser nulo ou menor que zero.");
        }

        try {
            return estimativaDAO.findById(id);
        } catch (Exception e) {
            throw new EstimativaException("Erro ao buscar estimativa: " + e.getMessage());
        }
    }

    // Método para atualizar uma estimativa
    public void atualizarEstimativa(EstimativaTO estimativa) throws EstimativaException {
        if (estimativa.getConsumoEstimado() <= 0) {
            throw new EstimativaException("O consumo estimado deve ser maior que zero.");
        }

        if (estimativa.getDataInicio() == null) {
            throw new EstimativaException("A data de início da estimativa não pode ser nula.");
        }

        // Lógica para atualizar a estimativa no banco de dados
        try {
            estimativaDAO.update(estimativa);
        } catch (Exception e) {
            throw new EstimativaException("Erro ao atualizar estimativa: " + e.getMessage());
        }
    }

    // Método para excluir uma estimativa
    public void excluirEstimativa(Integer id) throws EstimativaException {
        if (id == null || id <= 0) {
            throw new EstimativaException("ID da estimativa não pode ser nulo ou menor que zero.");
        }

        // Lógica para excluir a estimativa no banco de dados
        try {
            estimativaDAO.delete(id);
        } catch (Exception e) {
            throw new EstimativaException("Erro ao excluir estimativa: " + e.getMessage());
        }
    }
}
