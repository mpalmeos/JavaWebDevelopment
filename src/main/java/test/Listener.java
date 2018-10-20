package test;

import dao.SetupDao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class Listener implements ServletContextListener{

   public void contextInitialized(ServletContextEvent sce) {
      new SetupDao().initDatabase();
   }

   public void contextDestroyed(ServletContextEvent sce) {

   }
}
