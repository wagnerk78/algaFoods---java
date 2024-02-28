package com.wagner.kroiss.domain.service;


import com.wagner.kroiss.domain.exceptions.EntidadeEmUsoException;
import com.wagner.kroiss.domain.exceptions.EntidadeNaoEncontrada;
import com.wagner.kroiss.domain.model.Cidade;
import com.wagner.kroiss.domain.model.Cozinha;
import com.wagner.kroiss.domain.model.Estado;
import com.wagner.kroiss.domain.repository.CidadeRepository;
import com.wagner.kroiss.domain.repository.CozinhaRepository;
import com.wagner.kroiss.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return  estadoRepository.salvar(estado);
    }


    public void excluir(Long estadoId) {
        try {
            estadoRepository.remover(estadoId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontrada (
                    String.format("Não existe um estado com esse código %d", estadoId)
            );
        }

        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Estado de código %d não pode ser removida, pois está em uso", estadoId));
        }

    }
}
