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
                con = DriverManager.getConnection("jdbc:hsqldb:file:\\base\\MyBase", "admin", "");
                stmt = con.createStatement();
                String sql = "";
                sql = "INSERT INTO \"PUBLIC.DURATION_RESULT\"\n" +
                        "( \"ID\", \"DURATION\", \"TYPE\", \"HOST\", \"ALERT\" )\n" +
                        "VALUES ( '" + model.id + "', '" + model.duration + "', '" + model.type + "', '" + model.host + "', '" + model.alert + "')";
                ResultSet stmta = stmt.executeQuery("SELECT * FROM \"PUBLIC\".DURATION_RESULT LIMIT 100;");
                System.out.println(stmta);
                con.commit();
                con.close();

            }  catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }

}
