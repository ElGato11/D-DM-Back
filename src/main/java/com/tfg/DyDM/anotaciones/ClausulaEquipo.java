package com.tfg.DyDM.anotaciones;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ClausulaEquipoImpl.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ClausulaEquipo {
    String message() default "Todos los objetos en la lista deben ser de tipo 'Equipo'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}
