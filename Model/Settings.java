package Model;
/**
 * @author Paweł Młyniec
 * @version 1.0
 * Data needed to set game
 */
public class Settings {
    private int score1 ;
    private int score2 ;
    private int endingScore;
    private boolean start ;
    private boolean twoPlayers ;
    private boolean timelinePlay ;

    public Settings(){
        score1 = 0;
        score2 = 0;
        endingScore = 3;
        start = false;
        twoPlayers = false;
        timelinePlay = false;
    }

    public Settings(int Score1, int Score2, boolean Start, boolean TwoPlayers, boolean TimelinePlay ){
        score1 = Score1;
        score2 = Score2;
        start = Start;
        twoPlayers = TwoPlayers;
        timelinePlay = TimelinePlay;
    }

    public int getEndingScore() {
        return endingScore;
    }

    public boolean isStart() {
        return start;
    }

    public boolean isTimelinePlay() {
        return timelinePlay;
    }

    public boolean isTwoPlayers() {
        return twoPlayers;
    }

    public  int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void setTimelinePlay(boolean timelinePlay) {
        this.timelinePlay = timelinePlay;
    }

    public void setTwoPlayers(boolean twoPlayers) {
        this.twoPlayers = twoPlayers;
    }
}

