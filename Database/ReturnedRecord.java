package Database;
/**
 * @author Paweł Młyniec
 * @version 1.0
 * Stores data needed, according storing records in database
 */
public class ReturnedRecord {
    private int recordId;
    private String playerName;
    private String oponentName;
    private int playerScore;
    private int  oponentScore;
    private double timePlayed;

    public ReturnedRecord (int recordId,String playerName,String oponentName,int playerScore,int  oponentScore,double timePlayed){
        this.recordId = recordId;
        this.oponentName = oponentName;
        this.oponentScore = oponentScore;
        this.playerName = playerName;
        this.playerScore = playerScore;
        this.timePlayed = timePlayed;

    }
    @Override
    public String toString() {
        return " Game Nr = " + recordId  + ", time = " + timePlayed + ", " + playerName
                + "with score = " + playerScore + ", played vs " + oponentName +  " who gained "+ oponentScore + " points";
    }



}
