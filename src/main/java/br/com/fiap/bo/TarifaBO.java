package br.com.fiap.bo;

import br.com.fiap.dao.TarifaDAO;
import br.com.fiap.exception.TarifaException;
import br.com.fiap.to.TarifaTO;
import java.util.List;

public class TarifaBO {

    private TarifaDAO tarifaDAO;

    // Construtor para injetar o DAO
    public TarifaBO() {
        this.tarifaDAO = tarifaDAO;
    }

    // Método para criar uma tarifa
    public void criarTarifa(TarifaTO tarifa) throws TarifaException {
        if (tarifa.getNomeTarifa() == null || tarifa.getNomeTarifa().isEmpty()) {
            throw new TarifaException("O nome da tarifa não pode ser nulo ou vazio.");
        }
        if (tarifa.getValorKwh() <= 0) {
            throw new TarifaException("O valor do kWh deve ser maior que zero.");
        }

        // Lógica para salvar a tarifa no banco de dados
        try {
            tarifaDAO.create(tarifa);
        } catch (Exception e) {
            throw new TarifaException("Erro ao criar tarifa: " + e.getMessage());
        }
    }

    // Método para listar todas as tarifas
    public List<TarifaTO> listarTarifas() throws TarifaException {
        try {
            return tarifaDAO.findAll();
        } catch (Exception e) {
            throw new TarifaException("Erro ao listar tarifas: " + e.getMessage());
        }
    }

    // Método para buscar tarifa por ID
    public TarifaTO buscarTarifaPorId(Integer id) throws TarifaException {
        if (id == null || id <= 0) {
            throw new TarifaException("ID da tarifa não pode ser nulo ou menor que zero.");
        }

        try {
            return tarifaDAO.findById(id);
        } catch (Exception e) {
            throw new TarifaException("Erro ao buscar tarifa: " + e.getMessage());
        }
    }

    // Método para atualizar uma tarifa
    public void atualizarTarifa(TarifaTO tarifa) throws TarifaException {
        if (tarifa.getNomeTarifa() == null || tarifa.getNomeTarifa().isEmpty()) {
            throw new TarifaException("O nome da tarifa não pode ser nulo ou vazio.");
        }
        if (tarifa.getValorKwh() <= 0) {
            throw new TarifaException("O valor do kWh deve ser maior que zero.");
        }

        // Lógica para atualizar a tarifa no banco de dados
        try {
            tarifaDAO.update(tarifa);
        } catch (Exception e) {
            throw new TarifaException("Erro ao atualizar tarifa: " + e.getMessage());
        }
    }

    // Método para excluir uma tarifa
    public void excluirTarifa(Integer id) throws TarifaException {
        if (id == null || id <= 0) {
            throw new TarifaException("ID da tarifa não pode ser nulo ou menor que zero.");
        }

        // Lógica para excluir a tarifa no banco de dados
        try {
            tarifaDAO.delete(id);
        } catch (Exception e) {
            throw new TarifaException("Erro ao excluir tarifa: " + e.getMessage());
        }
    }
}
