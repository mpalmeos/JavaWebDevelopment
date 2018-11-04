package validation;

import model.Order;

import java.util.ArrayList;
import java.util.List;

public class ValidationAdvice {
   public static ValidationErrors orderValidation(Order order){
      String orderNr = order.getOrderNumber();
      ValidationErrors allErrors = new ValidationErrors();
      List<ValidationError> errorList = new ArrayList<>();

      if (orderNr.length() < 2){
         String length_error = "too_short_number";
         errorList.add(new ValidationError(length_error));
         allErrors.setErrors(errorList);
         return allErrors;
      } else return null;
   }
}
