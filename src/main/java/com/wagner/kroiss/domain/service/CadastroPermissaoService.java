package com.wagner.kroiss.domain.service;

import com.wagner.kroiss.domain.exception.PermissaoNaoEncontradaException;
import com.wagner.kroiss.domain.model.Permissao;
import com.wagner.kroiss.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
    }
}
