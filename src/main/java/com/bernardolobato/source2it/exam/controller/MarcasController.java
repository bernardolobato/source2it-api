 package com.bernardolobato.source2it.exam.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import com.bernardolobato.source2it.exam.controller.form.MarcaForm;
import com.bernardolobato.source2it.exam.model.Marca;
import com.bernardolobato.source2it.exam.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/marcas")
public class MarcasController {

    @Autowired
    MarcaRepository marcaRepository;

    @PostMapping("/")
    public ResponseEntity<?> salvar(@Valid @RequestBody final MarcaForm marcaForm) {
        try {
            final Marca m = this.marcaRepository.save(marcaForm.converter());
            return ResponseEntity.ok().body(m);
        } catch (final Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/")
    public ResponseEntity<?> lista() {
        try {
            final List<Marca> marcas = this.marcaRepository.findAll();
            return ResponseEntity.ok().body(marcas);
        } catch (final Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable final Long id) {
        final Optional<Marca> m = this.marcaRepository.findById(id);
        if (m.isPresent()) {
            return ResponseEntity.ok().body(m.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody final MarcaForm marcaForm, @PathVariable final Long id) {

        final Optional<Marca> m = marcaRepository.findById(id).map(m2 -> {
            m2.setNome(marcaForm.getNome());
            return marcaRepository.save(m2);
        });
        if (m.isPresent()) {
            return ResponseEntity.ok().body(m.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        try {
            marcaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (final EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (final Exception e) {
        System.out.println(e);
        return ResponseEntity.badRequest().build();
    }
  }
}
