import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionDB {

        public void insertToTable(Migration model) {
            Connection con = null;
            Statement stmt = null;
            int res = 0;
            try {
                Class.forName("org.hsqldb.jdbc.JDBCDriver");
                con = DriverManager.getConnection("jdbc:hsqldb:file:base\\myBase", "admin", "admin");
                stmt = con.createStatement();
                String sql = "";
                sql = "INSERT INTO duration_result (id, duration, type, host, alert) VALUES ('"+ model.id +
                        "','"+ model.duration +"','"+ model.type +"','"+ model.host +"',"+ model.alert + ")";
                res = stmt.executeUpdate(sql);
                con.commit();
                con.close();

            }  catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }

}
