package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Antud kood saadud https://bitbucket.org/mkalmo/exservlet
 * */
public class Util {
   public static String readFileFromClasspath(String pathOnClasspath) {
      try (InputStream is = Util.class.getClassLoader().getResourceAsStream(pathOnClasspath)) {
         if (is == null) {
            throw new IllegalStateException("can't load file: " + pathOnClasspath);
         }

         BufferedReader buffer = new BufferedReader(new InputStreamReader(is));

         return buffer.lines().collect(Collectors.joining("\n"));

      } catch (IOException e) {
         throw new RuntimeException();
      }
   }

   public static String asString(InputStream is) throws IOException {
      try (BufferedReader buffer = new BufferedReader(new InputStreamReader(is))) {
         return buffer.lines().collect(Collectors.joining("\n"));
      }
   }
}
