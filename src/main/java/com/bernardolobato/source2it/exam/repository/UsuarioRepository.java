 package com.bernardolobato.source2it.exam.repository;

import com.bernardolobato.source2it.exam.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findFirstByEmail(String email);
}
