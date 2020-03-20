 package com.bernardolobato.source2it.exam.repository;

import com.bernardolobato.source2it.exam.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    public Marca findByNome(String nome);
}
