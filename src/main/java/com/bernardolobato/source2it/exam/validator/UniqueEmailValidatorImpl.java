 package com.bernardolobato.source2it.exam.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.bernardolobato.source2it.exam.model.Usuario;
import com.bernardolobato.source2it.exam.repository.UsuarioRepository;
import com.bernardolobato.source2it.exam.validator.annotation.UniqueEmailValidator;
import org.springframework.beans.factory.annotation.Autowired;


public class UniqueEmailValidatorImpl implements ConstraintValidator<UniqueEmailValidator, String> {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext cxt) {
		
		Usuario u = usuarioRepository.findFirstByEmail(email);

		if(u==null)
		{
			return true;
		}
		return false;		
	}
}