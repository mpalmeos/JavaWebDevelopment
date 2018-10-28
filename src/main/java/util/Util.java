package util;

import model.Order;
import model.ValidationError;
import model.ValidationErrors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

//Antud kood saadud https://bitbucket.org/mkalmo/exservlet
   public static String asString(InputStream is) throws IOException {
      try (BufferedReader buffer = new BufferedReader(new InputStreamReader(is))) {
         return buffer.lines().collect(Collectors.joining("\n"));
      }
   }

   public static Long getLong(String input){
      try{
         return Long.parseLong(input);
      } catch (NumberFormatException e){
         return null;
      }
   }

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
