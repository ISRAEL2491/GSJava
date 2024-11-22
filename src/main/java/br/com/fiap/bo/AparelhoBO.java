package br.com.fiap.bo;

import br.com.fiap.dao.AparelhoDAO;
import br.com.fiap.exception.AparelhoException;
import br.com.fiap.to.AparelhoTO;

import java.util.ArrayList;
import java.util.List;

public class AparelhoBO {

    private AparelhoDAO aparelhoDAO;

    // Método para criação de um aparelho
    public AparelhoTO create(AparelhoTO aparelho) throws AparelhoException {
        if (aparelho == null || aparelho.getNome() == null || aparelho.getNome().isEmpty() ||
                aparelho.getPotenciaWatts() == null || aparelho.getPotenciaWatts() <= 0 ||
                aparelho.getHorasUsoDia() == null || aparelho.getHorasUsoDia() <= 0) {
            throw new AparelhoException("Dados do aparelho inválidos. Nome, potência e horas de uso diárias são obrigatórios.");
        }

        try {
            aparelhoDAO = new AparelhoDAO();
            return aparelhoDAO.create(aparelho);
        } catch (Exception e) {
            throw new AparelhoException("Erro ao criar aparelho: " + e.getMessage());
        }
    }

    // Método para listar todos os aparelhos
    public ArrayList<AparelhoTO> findAll() throws AparelhoException {
        try {
            aparelhoDAO = new AparelhoDAO();
            return aparelhoDAO.findAll();
        } catch (Exception e) {
            throw new AparelhoException("Erro ao listar aparelhos: " + e.getMessage());
        }
    }

    // Método para buscar um aparelho pelo ID
    public AparelhoTO findById(Long idAparelho) throws AparelhoException {
        if (idAparelho == null || idAparelho <= 0) {
            throw new AparelhoException("ID do aparelho não pode ser nulo ou menor que zero.");
        }

        try {
            aparelhoDAO = new AparelhoDAO();
            return aparelhoDAO.findById(idAparelho);
        } catch (Exception e) {
            throw new AparelhoException("Erro ao buscar aparelho: " + e.getMessage());
        }
    }

    // Método para atualizar um aparelho
    public AparelhoTO update(AparelhoTO aparelho) throws AparelhoException {
        if (aparelho == null || aparelho.getIdAparelho() == null) {
            throw new AparelhoException("ID do aparelho inválido.");
        }
        if (aparelho.getNome() == null || aparelho.getNome().isEmpty()) {
            throw new AparelhoException("O nome do aparelho não pode ser nulo ou vazio.");
        }
        if (aparelho.getPotenciaWatts() == null || aparelho.getPotenciaWatts() <= 0) {
            throw new AparelhoException("A potência do aparelho não pode ser nula ou menor que zero.");
        }
        if (aparelho.getHorasUsoDia() == null || aparelho.getHorasUsoDia() <= 0) {
            throw new AparelhoException("As horas de uso diário não podem ser nulas ou menores que zero.");
        }

        try {
            aparelhoDAO = new AparelhoDAO();
            return aparelhoDAO.update(aparelho);
        } catch (Exception e) {
            throw new AparelhoException("Erro ao atualizar aparelho: " + e.getMessage());
        }
    }

    // Método para excluir um aparelho
    public boolean delete(Long idAparelho) throws AparelhoException {
        if (idAparelho == null || idAparelho <= 0) {
            throw new AparelhoException("ID do aparelho não pode ser nulo ou menor que zero.");
        }

        try {
            aparelhoDAO = new AparelhoDAO();
            return aparelhoDAO.delete(idAparelho);
        } catch (Exception e) {
            throw new AparelhoException("Erro ao excluir aparelho: " + e.getMessage());
        }
    }
}
