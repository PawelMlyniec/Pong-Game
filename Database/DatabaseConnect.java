package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paweł Młyniec
 * @version 1.0
 * Class conecting to database, getting records and inserting them
 */

    public class DatabaseConnect {
        private final Connection conn;

        private static final String JDBC_URL = "jdbc:oracle:thin:***";
        private static final String JDBC_USER = "***";
        private static final String JDBC_PASS = "***";
        private static  String playerLogin;
        private static String oponentLogin;
        private static long time_played;


        private static int record_nr,player_score,oponent_score;
        private GetLastRecordID getLastRecordID;




        public DatabaseConnect(String PlayerLogin,String OponentLogin,long Time_played,int Player_score,int Oponent_score) throws SQLException {

                try {
                    Class.forName("oracle.jdbc.OracleDriver");
                } catch (ClassNotFoundException e) {
                    System.err.println("Driver not found.");
                    System.exit(1);
                }

            this.conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
            playerLogin = PlayerLogin;
            oponentLogin = OponentLogin;
            time_played = Time_played;
            player_score = Player_score;
            oponent_score = Oponent_score;
        getLastRecordID = new GetLastRecordID();

        }

        public DatabaseConnect() throws SQLException{
            try {
                Class.forName("oracle.jdbc.OracleDriver");
            } catch (ClassNotFoundException e) {
                System.err.println("Driver not found.");
                System.exit(1);
            }

            this.conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        }

        private void insertRecord() throws SQLException {

            try{
                conn.setAutoCommit(false);
                record_nr = getLastRecordID.check()+1;

                PreparedStatement stmt = conn.prepareStatement("INSERT INTO records (RECORD_NR,PLAYER,OPONENT,TIME_PLAYED,PLAYER_SCORE,OPONENT_SCORE) VALUES (?,?,?,?,?,?) ");
                stmt.setInt(1,record_nr);
                stmt.setString(2, playerLogin);
                stmt.setString(3, oponentLogin);
                stmt.setLong(4,time_played );
                stmt.setInt(5,player_score);
                stmt.setInt(6,oponent_score);

                int update = stmt.executeUpdate();

                if(update == 1 )
                    conn.commit();

                stmt.close();

            } catch(SQLException e){
                conn.rollback();
                System.out.println("no connection");
            }
            finally{
                conn.setAutoCommit(true);
            }
        }

        public List<ReturnedRecord> getNRecords(int n) throws SQLException{
            List<ReturnedRecord> list = new ArrayList<>();
            try{
                ReturnedRecord returnedRecord;
                PreparedStatement stmt = conn.prepareStatement("SELECT RECORD_NR,PLAYER,OPONENT,PLAYER_SCORE,OPONENT_SCORE,TIME_PLAYED FROM RECORDS  ORDER  BY TIME_PLAYED");

                ResultSet rset = stmt.executeQuery();

                int i = 0;
                while (rset.next()&&i<n){
                    returnedRecord = new ReturnedRecord(rset.getInt(1),rset.getString(2),rset.getString(3),rset.getInt(4),rset.getInt(5),rset.getInt(6));
                    list.add(returnedRecord);
                    i++;

                }

                rset.close();
                stmt.close();
//
            } catch(SQLException e){

                System.out.println("no connection");
            }

            return list;
        }
//


        public void run() throws SQLException {
            this.insertRecord();
        }


}
