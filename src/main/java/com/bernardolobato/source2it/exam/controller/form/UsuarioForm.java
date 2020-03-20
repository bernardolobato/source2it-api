 package com.bernardolobato.source2it.exam.controller.form;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import com.bernardolobato.source2it.exam.model.Usuario;
import com.bernardolobato.source2it.exam.validator.annotation.UniqueEmailValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class UsuarioForm {
    Long id;
    String nome;
    @JsonFormat
          (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")          
    Date dataNascimento;

    @NotBlank
    @UniqueEmailValidator
    String email;

    @Setter
    String password;

    public UsuarioForm() {

    }

    public Usuario converter() {
        return new Usuario(this.id, this.nome, this.dataNascimento, this.email, this.password);
    }

}
