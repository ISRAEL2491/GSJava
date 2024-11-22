package br.com.fiap.bo;

import br.com.fiap.dao.TarifaDAO;
import br.com.fiap.exception.TarifaException;
import br.com.fiap.to.TarifaTO;

import java.util.ArrayList;
import java.util.List;

public class TarifaBO {

    private TarifaDAO tarifaDAO;

    public TarifaTO create(TarifaTO tarifa) throws TarifaException {
        if (tarifa == null || tarifa.getNomeTarifa() == null || tarifa.getNomeTarifa().isEmpty() ||
                tarifa.getValorKwh() == null || tarifa.getValorKwh().isNaN() ||
                tarifa.getDataInicio() == null || tarifa.getDataFim() == null) {
            throw new TarifaException("Dados de tarifa inválidos.");
        }

        try {
            tarifaDAO = new TarifaDAO();
            return tarifaDAO.create(tarifa);
        } catch (Exception e) {
            throw new TarifaException("Erro ao criar tarifa: " + e.getMessage());
        }
    }

    public ArrayList<TarifaTO> findAll() throws TarifaException {
        try {
            tarifaDAO = new TarifaDAO();
            return tarifaDAO.findAll();
        } catch (Exception e) {
            throw new TarifaException("Erro ao listar tarifas: " + e.getMessage());
        }
    }

    public TarifaTO findById(Long idTarifa) throws TarifaException {
        if (idTarifa == null || idTarifa <= 0) {
            throw new TarifaException("ID de tarifa não pode ser nulo ou menor que zero.");
        }

        try {
            tarifaDAO = new TarifaDAO();
            return tarifaDAO.findById(idTarifa);
        } catch (Exception e) {
            throw new TarifaException("Erro ao buscar tarifa: " + e.getMessage());
        }
    }

    public TarifaTO update(TarifaTO tarifa) throws TarifaException {
        if (tarifa == null || tarifa.getIdTarifa() == null || tarifa.getIdTarifa() <= 0) {
            throw new TarifaException("ID de tarifa inválido.");
        }
        if (tarifa.getNomeTarifa() == null || tarifa.getNomeTarifa().isEmpty()) {
            throw new TarifaException("O nome de tarifa não pode ser nulo ou vazio.");
        }
        if (tarifa.getValorKwh() == null || tarifa.getValorKwh().isNaN()) {
            throw new TarifaException("O valor kwh de tarifa não pode ser nulo ou vazio.");
        }
        if (tarifa.getDataInicio() == null) {
            throw new TarifaException("A data de início de tarifa não pode ser nulo ou vazio.");
        }
        if (tarifa.getDataFim() == null) {
            throw new TarifaException("A data de fim de tarifa não pode ser nulo ou vazio.");
        }



        try {
            tarifaDAO = new TarifaDAO();
            return tarifaDAO.update(tarifa);
        } catch (Exception e) {
            throw new TarifaException("Erro ao atualizar tarifa: " + e.getMessage());
        }
    }

    public boolean delete(Long idTarifa) throws TarifaException {
        if (idTarifa == null || idTarifa <= 0) {
            throw new TarifaException("ID de tarifa não pode ser nulo ou menor que zero.");
        }

        try {
            tarifaDAO = new TarifaDAO();
            return tarifaDAO.delete(idTarifa);
        } catch (Exception e) {
            throw new TarifaException("Erro ao excluir tarifa: " + e.getMessage());
        }
    }
}
