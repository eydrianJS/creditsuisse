import java.sql.*;
import java.util.ArrayList;

public class ConnectionDB {
        private static Connection con = null;

        public static Connection connection() {

            try {
                Class.forName("org.hsqldb.jdbc.JDBCDriver");
                con = DriverManager.getConnection("jdbc:hsqldb:file:base\\myBase", "admin", "admin");
            } catch (Exception ex) {
                System.out.println("Error" + ex);
            }

            return con;
        }



        public void insertToTable(ArrayList<Migration> models) {
            int res = 0;
            String sqlSelects = "";
            String sqlInserts = "";

            for (Migration model: models) {
                sqlSelects += "SELECT * FROM duration_result WHERE ID ='" + model.id + "';";
                sqlInserts += "INSERT INTO duration_result (id, duration, type, host, alert) VALUES ('"+ model.id +
                        "','"+ model.duration +"','"+ model.type +"','"+ model.host +"',"+ model.alert + ")";
            }
            try{
                connection();
                Statement stmt = con.createStatement();
                ResultSet resultSet = stmt.executeQuery(sqlSelects);

                if (resultSet.next()) throw new IllegalArgumentException("One ID is set in DB." );
                res = stmt.executeUpdate(sqlInserts);
                con.commit();
                con.close();
            } catch (Exception ex) {
                System.out.println("Exception" + ex);
            }
        }

}
