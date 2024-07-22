package com.wagner.kroiss.domain.service;


import com.wagner.kroiss.domain.exceptions.EntidadeEmUsoException;
import com.wagner.kroiss.domain.exceptions.EntidadeNaoEncontrada;
import com.wagner.kroiss.domain.model.Cozinha;
import com.wagner.kroiss.domain.repository.CozinhaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    @Autowired
    public CozinhaRepository cozinhaRepository;

    private static final Logger logger = LoggerFactory.getLogger(CadastroCozinhaService.class);


    public Cozinha salvar(Cozinha cozinha) {
        return  cozinhaRepository.save(cozinha);
    }


    public void excluir(Long cozinhaId) {
        try {
            if (!cozinhaRepository.existsById(cozinhaId)) {
                throw new EntidadeNaoEncontrada(
                        String.format("Não existe uma cozinha com esse código %d", cozinhaId));
            }
            cozinhaRepository.deleteById(cozinhaId);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Erro ao excluir cozinha com ID {}: {}", cozinhaId, e.getMessage());
            throw new EntidadeNaoEncontrada(
                    String.format("Não existe uma cozinha com esse código %d", cozinhaId));
        } catch (DataIntegrityViolationException e) {
            logger.error("Erro de integridade ao excluir cozinha com ID {}: {}", cozinhaId, e.getMessage());
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId));
        }
    }
}
