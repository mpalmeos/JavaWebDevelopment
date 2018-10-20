package dao;

import util.DataSourceProvider;
import util.FileUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupDao {
   public static String URL = "jdbc:hsqldb:mem:my-database";

   public void initDatabase(){
      DataSourceProvider.setDbUrl(URL);

      try(Connection conn = DataSourceProvider.getDataSource().getConnection();
          Statement stmt = conn.createStatement()) {

         stmt.executeUpdate(FileUtil.readFileFromClasspath("schema.sql"));

      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
   }
}
