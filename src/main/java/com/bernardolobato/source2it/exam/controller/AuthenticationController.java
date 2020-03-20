 package com.bernardolobato.source2it.exam.controller;

import javax.validation.Valid;
import com.bernardolobato.source2it.exam.controller.form.AutenticacaoForm;
import com.bernardolobato.source2it.exam.security.SecurityConstants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SecurityConstants.AUTH_LOGIN_URL)
public class AuthenticationController {

    @PostMapping("/")
    public void authenticate(@Valid @RequestBody AutenticacaoForm autenticacaoForm) {
    }

}
