 package com.bernardolobato.source2it.exam.controller.form;

import javax.validation.constraints.NotBlank;
import com.bernardolobato.source2it.exam.model.Marca;
import com.bernardolobato.source2it.exam.validator.annotation.UniqueBrandValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MarcaForm {
    Long id;

    @NotBlank
    @UniqueBrandValidator
    String nome;
    
    public MarcaForm() {
    }

    public Marca converter() {
        return new Marca(this.id, this.nome);
    }
}
