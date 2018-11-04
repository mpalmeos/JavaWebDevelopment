package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
}
