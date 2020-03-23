 package com.bernardolobato.source2it.exam.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.bernardolobato.source2it.exam.model.Patrimonio;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatrimonioForm {
    @NotBlank
    String nome;
    @NotBlank
    String descricao;
    @NotNull
    Long marcaId;
    
    public PatrimonioForm() {
    }

    public Patrimonio converter() {
        return new Patrimonio(null, this.nome, this.descricao, null);
    }
}
