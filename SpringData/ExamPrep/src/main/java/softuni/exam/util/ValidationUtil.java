package softuni.exam.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;



public interface ValidationUtil {




    <E> boolean isValid(E entity);
}
