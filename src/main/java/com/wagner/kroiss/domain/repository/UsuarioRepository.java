package com.wagner.kroiss.domain.repository;

import com.wagner.kroiss.domain.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

}
