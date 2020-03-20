 package com.bernardolobato.source2it.exam.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import com.bernardolobato.source2it.exam.controller.form.UsuarioForm;
import com.bernardolobato.source2it.exam.model.Usuario;
import com.bernardolobato.source2it.exam.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;
    
    @PostMapping("/")
    public ResponseEntity<?> salvar(@Valid @RequestBody UsuarioForm usuarioForm) {
        try {
            usuarioForm.setPassword(passwordEncoder.encode(usuarioForm.getPassword()));
            Usuario u = this.usuarioRepository.save(usuarioForm.converter());
            return ResponseEntity.ok().body(u);
        } catch (Exception e) {
            System.err.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/")
    public ResponseEntity<?> lista() {
        try {
            final List<Usuario> usuarios = this.usuarioRepository.findAll();
            return ResponseEntity.ok().body(usuarios);
        } catch (final Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable final Long id) {
        final Optional<Usuario> u = this.usuarioRepository.findById(id);
        if (u.isPresent()) {
            return ResponseEntity.ok().body(u.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualiza(@RequestBody final UsuarioForm usuario, @PathVariable final Long id) {

        final Optional<Usuario> u = usuarioRepository.findById(id).map(el -> {
            el.setNome(usuario.getNome());
            el.setDataNascimento(usuario.getDataNascimento());
            el.setEmail(usuario.getEmail());
            return usuarioRepository.save(el);
        });
        if (u.isPresent()) {
            return ResponseEntity.ok().body(u.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleta(@PathVariable final Long id) {
        try {
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (final EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (final Exception e) {
        System.out.println(e);
        return ResponseEntity.badRequest().build();
    }
  }
}
