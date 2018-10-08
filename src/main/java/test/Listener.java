package test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class Listener implements ServletContextListener{

   /*// Public constructor is required by servlet spec
   public test.Listener() {
   }*/

   public void contextInitialized(ServletContextEvent sce) {

   }

   public void contextDestroyed(ServletContextEvent sce) {

   }
}
