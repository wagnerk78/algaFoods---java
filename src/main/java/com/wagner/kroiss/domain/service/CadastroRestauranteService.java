package com.wagner.kroiss.domain.service;

import com.wagner.kroiss.domain.exceptions.EntidadeNaoEncontrada;
import com.wagner.kroiss.domain.model.Cozinha;
import com.wagner.kroiss.domain.model.Restaurante;
import com.wagner.kroiss.domain.repository.CozinhaRepository;
import com.wagner.kroiss.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha == null) {
            throw new EntidadeNaoEncontrada(
                    String.format("Não existe uma cozinha com código: %d.", cozinhaId));
        }

        restaurante.setCozinha(cozinha);

        return restauranteRepository.salvar(restaurante);
    }
}
