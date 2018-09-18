package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Util {

//Antud kood saadud https://bitbucket.org/mkalmo/exservlet
   public static String asString(InputStream is) throws IOException {
      try (BufferedReader buffer = new BufferedReader(new InputStreamReader(is))) {
         return buffer.lines().collect(Collectors.joining("\n"));
      }
   }

   public static String getOrder (String s){
      /*Order newOne = new Order();
      Long number = 1L;*/

      String buffer = s.trim().replaceAll(Pattern.quote("{"), " ").
              replaceAll(Pattern.quote("}"), " ").trim().
              replaceAll(Pattern.quote("\""), " ").trim().
              replaceAll(Pattern.quote(" "), "");
      String[] result = buffer.split(":");

      String answer = result[1];
      /*newOne.setOrderNumber(result[1]);
      newOne.setId(number);
      number++;*/

      return answer;
   }
}
