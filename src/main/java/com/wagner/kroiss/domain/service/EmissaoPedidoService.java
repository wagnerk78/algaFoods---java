package com.wagner.kroiss.domain.service;

import com.wagner.kroiss.domain.exception.PedidoNaoEncontradoException;
import com.wagner.kroiss.domain.model.Pedido;
import com.wagner.kroiss.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmissaoPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscarOuFalhar(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
    }
}
