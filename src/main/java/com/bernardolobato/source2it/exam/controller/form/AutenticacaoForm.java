 package com.bernardolobato.source2it.exam.controller.form;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AutenticacaoForm {
    @NotBlank
    String email;

    @NotBlank
    String password;

    public AutenticacaoForm() {}
}
