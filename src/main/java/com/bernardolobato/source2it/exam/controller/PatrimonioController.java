 package com.bernardolobato.source2it.exam.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import com.bernardolobato.source2it.exam.controller.form.PatrimonioForm;
import com.bernardolobato.source2it.exam.model.Marca;
import com.bernardolobato.source2it.exam.model.Patrimonio;
import com.bernardolobato.source2it.exam.repository.MarcaRepository;
import com.bernardolobato.source2it.exam.repository.PatrimonioRepository;
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
@RequestMapping("/api/patrimonios")
public class PatrimonioController {

    @Autowired
    PatrimonioRepository patrimonioRepository;

    @Autowired
    MarcaRepository marcaRepository;

    @PostMapping("/")
    public ResponseEntity<?> salvar(@Valid @RequestBody PatrimonioForm patrimonioForm) {
        try {
            Patrimonio p = patrimonioForm.converter();
            Optional<Marca> om = marcaRepository.findById(patrimonioForm.getMarcaId());
            if (om.isPresent()) {
                p.setMarca(om.get());
                p = this.patrimonioRepository.save(p);
                return ResponseEntity.ok().body(p);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/")
    public ResponseEntity<?> lista() {
        try {
            final List<Patrimonio> patrimonios = this.patrimonioRepository.findAll();
            return ResponseEntity.ok().body(patrimonios);
        } catch (final Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable final Long id) {
        final Optional<Patrimonio> u = this.patrimonioRepository.findById(id);
        if (u.isPresent()) {
            return ResponseEntity.ok().body(u.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualiza(@RequestBody final PatrimonioForm patrimonioForm, @PathVariable final Long id) {

        final Optional<Patrimonio> p = patrimonioRepository.findById(id).map(el -> {
            el.setNome(patrimonioForm.getNome());
            el.setDescricao(patrimonioForm.getDescricao());
            if (patrimonioForm.getMarcaId() != el.getMarca().getId()) {
                Optional<Marca> om = marcaRepository.findById(patrimonioForm.getMarcaId());
                if (om.isPresent()) {
                    el.setMarca(om.get());
                } else {
                    return null;
                }
            }
            return patrimonioRepository.save(el);
        });
        if (p.isPresent()) {
            return ResponseEntity.ok().body(p.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleta(@PathVariable final Long id) {
        try {
            patrimonioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (final EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (final Exception e) {
        System.out.println(e);
        return ResponseEntity.badRequest().build();
    }
  }
}
