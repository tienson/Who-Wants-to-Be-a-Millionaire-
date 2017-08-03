import java.io.Serializable;

/**
 * Created by Dang Tien Son on 7/28/2017.
 */
public class Question implements Serializable {
    private String question;
    private String[] ans = new String[5];
    private int trueAnswer = 1;
    public Question(String question, String ans1,String ans2,String ans3,String ans4,int trueAnswer){
        this.question = question;
        ans[1] = ans1;
        ans[2] = ans2;
        ans[3] = ans3;
        ans[4] = ans4;
        this.trueAnswer = trueAnswer;
    }
    public String getQuestion(){
        return question;
    }
    public String[] getAns(){
        return ans;
    }
    public int getTrueAnswer(){ return trueAnswer; }
    public String toString(){
        return "Question: "+question+"\nAnswer1: "+ans[1]+"\nAnswer2: "+ans[2]+"\nAnswer3: "+ans[3]+"\nAnswer4: "+ans[4];
    }
}
