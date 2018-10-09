package test;

import dao.OrderDao;
import util.DataSourceProvider;
import util.FileUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebListener()
public class Listener implements ServletContextListener{

   public void contextInitialized(ServletContextEvent sce) {
      DataSourceProvider.setDbUrl(OrderDao.URL);

      try(Connection c1 = DataSourceProvider.getDataSource().getConnection();
          Statement stmt = c1.createStatement()) {

         stmt.executeUpdate(FileUtil.readFileFromClasspath("schema.sql"));

      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
   }

   public void contextDestroyed(ServletContextEvent sce) {

   }
}
