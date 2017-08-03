import java.io.Serializable;

/**
 * Created by Dang Tien Son on 7/29/2017.
 */
public class User implements Serializable,Comparable<User>{
    private String name,password;
    private boolean isAdmin;
    private int bestScore=0,currentScore=0;

    public User(String name, String password, boolean isAdmin, int bestScore){
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
        this.bestScore = bestScore;
    }
    public boolean isAdmin(){
        return isAdmin;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public int getBestScore(){ return bestScore; }
    public int getCurrentScore(){ return currentScore; }
    public void setCurrentScore(int score){ currentScore = score; }
    public void setBestScore(int score){ bestScore = score; }
    public String toString(){
        return "Name :"+name+", Pass: "+password+", isAdmin: "+isAdmin()+", Best score: "+bestScore+".";
    }

    @Override
    public int compareTo(User o) {
        return new Integer(o.bestScore).compareTo(new Integer(this.getBestScore()));
    }
}
