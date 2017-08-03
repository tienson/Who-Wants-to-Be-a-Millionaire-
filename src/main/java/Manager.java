import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Dang Tien Son on 7/28/2017.
 */
public class Manager {
    public  ArrayList<Question> questions = new ArrayList<Question>();
    public  ArrayList<User> users = new ArrayList<User>();
    public  boolean logined = false;
    public  User userPlaying = null;
    public  void importQuestion() throws IOException {
        File f = new File("data/Questions.txt");
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
            ArrayList<Question> ListObject = new ArrayList<Question>();
            Question Object;
            while(true) {
                try {
                    Object = (Question) in.readObject();
                    if (Object != null) {
                        ListObject.add(Object);
                    }
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    break;
                }
            }
            in.close();
        questions = ListObject;
        } catch (EOFException e){}
    }
    public  void importUser() throws IOException {
        File f = new File("data/Users.txt");
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));

            ArrayList<User> ListObject = new ArrayList<User>();
            User Object;
            while(true) {
                try {
                    Object = (User) in.readObject();
                    if (Object != null) {
                        ListObject.add(Object);
                    }
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    break;
                }
            }
            in.close();
            users = ListObject;
        }catch (EOFException e){}
    }
    public  void writeUserToFile() throws IOException {
        File f = new File("data/Users.txt");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        for (User user : users)
            out.writeObject(user);
        out.close();
    }
    public  void writeQuestionToFile() throws IOException {
        File f = new File("data/Questions.txt");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        for (Question question : questions)
            out.writeObject(question);
        out.close();
    }
    public  void printUser(){
        System.out.println("Users:\n");
        for (User user:users){
            System.out.println(user.toString());
        }
    }
    public  void printQuestion(){
        System.out.println("Questions:\n");
        for (Question question :questions){
            System.out.println(question.toString());
        }
    }
    public  Question getRandomQuestion(){
        Random random = new Random();
        return questions.get(random.nextInt(questions.size()));
    }
    public static void main(String[] args) throws IOException {
        Manager manager = new Manager();
        manager.questions.add(new Question("question","ans1","ans2","ans3","ans4",1));
        manager.importQuestion();
        manager.importUser();
        manager.printUser();
        manager.printQuestion();
        new StartGUI(manager);
    }
}
