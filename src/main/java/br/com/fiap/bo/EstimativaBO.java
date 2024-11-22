package br.com.fiap.bo;

import br.com.fiap.dao.EstimativaDAO;
import br.com.fiap.exception.EstimativaException;
import br.com.fiap.to.EstimativaTO;

import java.util.ArrayList;

public class EstimativaBO {

    private EstimativaDAO estimativaDAO;

    public EstimativaTO create(EstimativaTO estimativa) throws EstimativaException {
        if (estimativa == null || estimativa.getConsumoEstimado() == null || estimativa.getConsumoEstimado() <= 0 ||
                estimativa.getDataInicio() == null) {
            throw new EstimativaException("Dados da estimativa inválidos. Consumo estimado e data de início são obrigatórios.");
        }

        try {
            estimativaDAO = new EstimativaDAO();
            return estimativaDAO.create(estimativa);
        } catch (Exception e) {
            throw new EstimativaException("Erro ao criar estimativa: " + e.getMessage());
        }
    }

    public ArrayList<EstimativaTO> findAll() throws EstimativaException {
        try {
            estimativaDAO = new EstimativaDAO();
            return estimativaDAO.findAll();
        } catch (Exception e) {
            throw new EstimativaException("Erro ao listar estimativas: " + e.getMessage());
        }
    }

    public EstimativaTO findById(Long idEstimativa) throws EstimativaException {
        if (idEstimativa == null || idEstimativa <= 0) {
            throw new EstimativaException("ID da estimativa não pode ser nulo ou menor que zero.");
        }

        try {
            estimativaDAO = new EstimativaDAO();
            return estimativaDAO.findById(idEstimativa);
        } catch (Exception e) {
            throw new EstimativaException("Erro ao buscar estimativa: " + e.getMessage());
        }
    }

    public EstimativaTO update(EstimativaTO estimativa) throws EstimativaException {
        if (estimativa == null || estimativa.getIdEstimativa() == null || estimativa.getIdEstimativa() <= 0) {
            throw new EstimativaException("ID da estimativa inválido.");
        }
        if (estimativa.getConsumoEstimado() == null || estimativa.getConsumoEstimado() <= 0) {
            throw new EstimativaException("O consumo estimado não pode ser nulo ou menor que zero.");
        }
        if (estimativa.getDataInicio() == null) {
            throw new EstimativaException("A data de início não pode ser nula.");
        }

        try {
            estimativaDAO = new EstimativaDAO();
            return estimativaDAO.update(estimativa);
        } catch (Exception e) {
            throw new EstimativaException("Erro ao atualizar estimativa: " + e.getMessage());
        }
    }

    public boolean delete(Long idEstimativa) throws EstimativaException {
        if (idEstimativa == null || idEstimativa <= 0) {
            throw new EstimativaException("ID da estimativa não pode ser nulo ou menor que zero.");
        }

        try {
            estimativaDAO = new EstimativaDAO();
            return estimativaDAO.delete(idEstimativa);
        } catch (Exception e) {
            throw new EstimativaException("Erro ao excluir estimativa: " + e.getMessage());
        }
    }
}
