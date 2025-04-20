package com.tfg.DyDM.anotaciones;
import com.tfg.DyDM.model.Objeto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ClausulaEquipoImpl implements ConstraintValidator<ClausulaEquipo, List<Objeto>> {

    @Override
    public boolean isValid(List<Objeto> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        for (Objeto objeto : value) {
            if (!"Equipo".equals(objeto.getTipo())) {
                return false;
            }
        }
        return true;
    }
}
