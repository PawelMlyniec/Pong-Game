package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Paweł Młyniec
 * @version 1.0
 * Class getting last record from database
 */
public class GetLastRecordID {

        private final Connection conn;

    private static final String JDBC_URL = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
    private static final String JDBC_USER = "pmlyniec";
    private static final String JDBC_PASS = "pmlyniec";
    private int i;

        public GetLastRecordID() throws SQLException {
            this.conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        }

        public int check() throws SQLException {
            try {
                Statement stmt = conn.createStatement();
                ResultSet rset = stmt.executeQuery("SELECT MAX(RECORD_NR) FROM RECORDS ");
                while (rset.next())
                 i = rset.getInt(1);


                rset.close();
                stmt.close();

            }
            catch (SQLException e){
                System.out.println("no connection");
                return 0;
            }
            finally {
                return i;
            }

        }






}
