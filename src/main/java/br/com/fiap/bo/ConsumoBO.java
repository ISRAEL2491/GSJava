package br.com.fiap.bo;

import br.com.fiap.dao.ConsumoDAO;
import br.com.fiap.exception.ConsumoException;
import br.com.fiap.to.ConsumoTO;

import java.util.ArrayList;

public class ConsumoBO {

    private ConsumoDAO consumoDAO;

    // Método para criação de um consumo
    public ConsumoTO create(ConsumoTO consumo) throws ConsumoException {
        if (consumo == null || consumo.getConsumoKwh() == null || consumo.getConsumoKwh() <= 0 ||
                consumo.getDataRegistro() == null) {
            throw new ConsumoException("Dados do consumo inválidos. Consumo em kWh e data de registro são obrigatórios.");
        }

        try {
            consumoDAO = new ConsumoDAO();
            return consumoDAO.create(consumo);
        } catch (Exception e) {
            throw new ConsumoException("Erro ao criar consumo: " + e.getMessage());
        }
    }

    // Método para listar todos os consumos
    public ArrayList<ConsumoTO> findAll() throws ConsumoException {
        try {
            consumoDAO = new ConsumoDAO();
            return consumoDAO.findAll();
        } catch (Exception e) {
            throw new ConsumoException("Erro ao listar consumos: " + e.getMessage());
        }
    }

    // Método para buscar um consumo pelo ID
    public ConsumoTO findById(Long idConsumo) throws ConsumoException {
        if (idConsumo == null || idConsumo <= 0) {
            throw new ConsumoException("ID do consumo não pode ser nulo ou menor que zero.");
        }

        try {
            consumoDAO = new ConsumoDAO();
            return consumoDAO.findById(idConsumo);
        } catch (Exception e) {
            throw new ConsumoException("Erro ao buscar consumo: " + e.getMessage());
        }
    }

    // Método para atualizar um consumo
    public ConsumoTO update(ConsumoTO consumo) throws ConsumoException {
        if (consumo == null || consumo.getIdConsumo() == null || consumo.getIdConsumo() <= 0) {
            throw new ConsumoException("ID do consumo inválido.");
        }
        if (consumo.getConsumoKwh() == null || consumo.getConsumoKwh() <= 0) {
            throw new ConsumoException("O consumo em kWh não pode ser nulo ou menor que zero.");
        }
        if (consumo.getDataRegistro() == null) {
            throw new ConsumoException("A data de registro não pode ser nula.");
        }

        try {
            consumoDAO = new ConsumoDAO();
            return consumoDAO.update(consumo);
        } catch (Exception e) {
            throw new ConsumoException("Erro ao atualizar consumo: " + e.getMessage());
        }
    }

    // Método para excluir um consumo
    public boolean delete(Long idConsumo) throws ConsumoException {
        if (idConsumo == null || idConsumo <= 0) {
            throw new ConsumoException("ID do consumo não pode ser nulo ou menor que zero.");
        }

        try {
            consumoDAO = new ConsumoDAO();
            return consumoDAO.delete(idConsumo);
        } catch (Exception e) {
            throw new ConsumoException("Erro ao excluir consumo: " + e.getMessage());
        }
    }
}
