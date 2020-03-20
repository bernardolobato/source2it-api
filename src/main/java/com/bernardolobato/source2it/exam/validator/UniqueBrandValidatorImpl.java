 package com.bernardolobato.source2it.exam.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.bernardolobato.source2it.exam.model.Marca;
import com.bernardolobato.source2it.exam.repository.MarcaRepository;
import com.bernardolobato.source2it.exam.validator.annotation.UniqueBrandValidator;
import org.springframework.beans.factory.annotation.Autowired;


public class UniqueBrandValidatorImpl implements ConstraintValidator<UniqueBrandValidator, String> {

	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@Override
	public boolean isValid(String nome, ConstraintValidatorContext cxt) {
		
		Marca m = marcaRepository.findByNome(nome);
		System.out.println(m);
		if(m==null)
		{
			return true;
		}
		return false;		
	}
}