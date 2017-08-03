import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static java.awt.Color.*;

/**
 * Created by Dang Tien Son on 7/28/2017.
 */
public class StartGUI  extends JFrame implements ActionListener {
    private JButton playButton;
    private JButton logInButton;
    private JButton exitButton;
    private JButton highscoreButton;
    private JButton signUpButton;
    private JButton logOutButton;
    private JButton addQuestion;
    Manager manager;
    public StartGUI(Manager manager){
        this.manager = manager;
        setTitle("Who is the millionaire");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(600,600);
        setResizable(false);

        JPanel menu = new JPanel(new FlowLayout(FlowLayout.CENTER));
        menu.setLayout(new FlowLayout());
        menu.setBackground(gray);

        if (manager.logined == true){
            String admin;
            if (manager.userPlaying.isAdmin()==true)  admin = "(Admin)";
            else  admin = "(not Admin)";
            String present = "User: " + manager.userPlaying.getName() + " " + admin + "\n";
            JLabel header = new JLabel(present);
            header.setPreferredSize(new Dimension(300,30));
            menu.add(header);
            present = "Best score: " + manager.userPlaying.getBestScore();
            header = new JLabel(present);
            header.setPreferredSize(new Dimension(300,30));
            menu.add(header);
        }
        playButton = new JButton("Play");
        playButton.addActionListener(this);
        playButton.setPreferredSize(new Dimension(300,80));
        playButton.setForeground(Color.black);

        logInButton = new JButton("Login");
        logInButton.addActionListener(this);
        logInButton.setPreferredSize(new Dimension(300,80));
        logInButton.setForeground(Color.black);

        signUpButton = new JButton("Sign up");
        signUpButton.addActionListener(this);
        signUpButton.setPreferredSize(new Dimension(300,80));
        signUpButton.setForeground(Color.black);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        exitButton.setPreferredSize(new Dimension(300,80));
        exitButton.setForeground(Color.black);

        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(this);
        logOutButton.setPreferredSize(new Dimension(300,80));
        logOutButton.setForeground(Color.black);

        highscoreButton = new JButton("High Scores");
        highscoreButton.addActionListener(this);
        highscoreButton.setPreferredSize(new Dimension(300,80));
        highscoreButton.setForeground(Color.black);

        addQuestion = new JButton("Add Question");
        addQuestion.addActionListener(this);
        addQuestion.setPreferredSize(new Dimension(300,80));
        addQuestion.setForeground(Color.black);

        playButton.setBackground(Color.darkGray);
        logInButton.setBackground(Color.darkGray);
        exitButton.setBackground(Color.darkGray);
        logOutButton.setBackground(Color.darkGray);
        highscoreButton.setBackground(Color.darkGray);
        signUpButton.setBackground(Color.darkGray);
        addQuestion.setBackground(Color.darkGray);


        playButton.setFont(new Font("Arial",Font.PLAIN, 40));
        logInButton.setFont(new Font("Arial",Font.PLAIN, 40));
        exitButton.setFont(new Font("Arial",Font.PLAIN, 40));
        logOutButton.setFont(new Font("Arial",Font.PLAIN, 40));
        highscoreButton.setFont(new Font("Arial",Font.PLAIN, 40));
        signUpButton.setFont(new Font("Arial",Font.PLAIN, 40));
        addQuestion.setFont(new Font("Arial",Font.PLAIN, 40));


        if (manager.logined == false) menu.add(logInButton);
        if (manager.logined == false) menu.add(signUpButton);
        if (manager.logined != false) menu.add(playButton);
        menu.add(highscoreButton);
        if (manager.logined != false) menu.add(logOutButton);
        if (manager.logined != false && manager.userPlaying.isAdmin()==true) menu.add(addQuestion);
        menu.add(exitButton);
        add(menu);

        setVisible(true);
        setLocationRelativeTo(null);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logInButton) {
            this.dispose();
            new LoginGUI(manager);
        }
        if(e.getSource() == signUpButton) {
            this.dispose();
            new SignUpGUI(manager);
        }
        if (e.getSource() == playButton){
            this.dispose();
            new PlayGUI(manager);
        }
        if (e.getSource() == logOutButton){
            manager.logined = false;
            this.dispose();
            new StartGUI(manager);
        }
        if (e.getSource() == highscoreButton){
            this.dispose();
            new HighscoreGUI(manager);
        }
        if (e.getSource() == addQuestion){
            this.dispose();
            new AddQuestionGUI(manager);
        }
        if(e.getSource() == exitButton) {
            try {
                manager.writeUserToFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.exit(0);
        }
    }
}
