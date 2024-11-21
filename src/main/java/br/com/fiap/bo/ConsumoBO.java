package br.com.fiap.bo;

import br.com.fiap.dao.ConsumoDAO;
import br.com.fiap.exception.ConsumoException;
import br.com.fiap.to.ConsumoTO;
import java.util.List;

public class ConsumoBO {

    private ConsumoDAO consumoDAO;

    // Construtor para injetar o DAO
    public ConsumoBO() {
        this.consumoDAO = consumoDAO;
    }

    // Método para criar um consumo
    public void criarConsumo(ConsumoTO consumo) throws ConsumoException {
        if (consumo.getConsumoKwh() <= 0) {
            throw new ConsumoException("O consumo em kWh deve ser maior que zero.");
        }

        // Lógica para salvar o consumo no banco de dados
        try {
            consumoDAO.create(consumo);
        } catch (Exception e) {
            throw new ConsumoException("Erro ao criar consumo: " + e.getMessage());
        }
    }

    // Método para listar todos os consumos
    public List<ConsumoTO> listarConsumos() throws ConsumoException {
        try {
            return consumoDAO.findAll();
        } catch (Exception e) {
            throw new ConsumoException("Erro ao listar consumos: " + e.getMessage());
        }
    }

    // Método para buscar consumo por ID
    public ConsumoTO buscarConsumoPorId(Integer id) throws ConsumoException {
        try {
            return consumoDAO.findById(id);
        } catch (Exception e) {
            throw new ConsumoException("Erro ao buscar consumo: " + e.getMessage());
        }
    }

    // Método para atualizar um consumo
    public void atualizarConsumo(ConsumoTO consumo) throws ConsumoException {
        if (consumo.getConsumoKwh() <= 0) {
            throw new ConsumoException("O consumo em kWh deve ser maior que zero.");
        }

        // Lógica para atualizar o consumo no banco de dados
        try {
            consumoDAO.update(consumo);
        } catch (Exception e) {
            throw new ConsumoException("Erro ao atualizar consumo: " + e.getMessage());
        }
    }

    // Método para excluir um consumo
    public void excluirConsumo(Integer id) throws ConsumoException {
        try {
            consumoDAO.delete(id);
        } catch (Exception e) {
            throw new ConsumoException("Erro ao excluir consumo: " + e.getMessage());
        }
    }
}
