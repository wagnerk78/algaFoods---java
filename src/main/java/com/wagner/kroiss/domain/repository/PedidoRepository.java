package com.wagner.kroiss.domain.repository;

import com.wagner.kroiss.domain.model.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {

    Optional<Pedido> findByCodigo(String codigo);

    @Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha join fetch r.endereco " +
            "a join fetch a.cidade b join fetch b.estado")
    List<Pedido> findAll();

}
