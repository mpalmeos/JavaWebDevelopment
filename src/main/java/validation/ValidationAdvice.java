package validation;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class ValidationAdvice {

   @ResponseBody
   @ExceptionHandler
   @ResponseStatus(value = HttpStatus.BAD_REQUEST)
   public ValidationErrors handleErrors(MethodArgumentNotValidException exception){
      ValidationErrors errorList = new ValidationErrors();
      List<FieldError> errors = exception.getBindingResult().getFieldErrors();

      for (FieldError error : errors) {
         errorList.addError(error);
      }

      return errorList;
   }
}
