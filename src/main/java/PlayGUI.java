import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static java.awt.Color.black;
import static java.awt.Color.darkGray;
import static java.awt.Color.gray;

/**
 * Created by Dang Tien Son on 7/28/2017.
 */
public class PlayGUI extends JFrame implements ActionListener {
    JLabel labelQuestion,present;
    JButton[] ans = new JButton[5];
    JButton stopPlay;
    Question question;
    Manager manager;
    public PlayGUI(Manager manager){
        this.manager = manager;
        setTitle("Good luck!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        question = manager.getRandomQuestion();

        JPanel p = new JPanel();
        p.setBackground(gray);
        p.setLayout(null);

        present = new JLabel("Current score: "+ manager.userPlaying.getCurrentScore());
        present.setBackground(darkGray);
        present.setForeground(black);
        present.setBounds(50, 30, 100, 30);
        p.add(present);

        labelQuestion = new JLabel(question.getQuestion());
        labelQuestion.setBackground(darkGray);
        labelQuestion.setForeground(black);
        labelQuestion.setBounds(50, 50, 500, 250);
        p.add(labelQuestion);

        for(int i=1;i<=4;i++){
            ans[i] = new JButton(question.getAns()[i]);
            ans[i].setBackground(darkGray);
            ans[i].setForeground(black);
            ans[i].setBounds(50, 250+(i-1)*50, 500, 50);
            ans[i].addActionListener(this);
            p.add(ans[i]);
        }

        stopPlay = new JButton("Stop now");
        stopPlay.setBounds(250, 500, 100, 30);
        stopPlay.addActionListener(this);
        p.add(stopPlay);

        add(p);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == stopPlay) {
            JOptionPane.showMessageDialog(new JFrame(), "your Score: " + manager.userPlaying.getCurrentScore());
            manager.userPlaying.setBestScore(Math.max(manager.userPlaying.getCurrentScore(),manager.userPlaying.getBestScore()));
            manager.userPlaying.setCurrentScore(0);
            try {
                manager.writeUserToFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            this.dispose();
            new StartGUI(manager);
        }
        for(int i=1;i<=4;i++){
            if (e.getSource() == ans[i]){
                if (question.getTrueAnswer()==i){
                    this.dispose();
                    manager.userPlaying.setCurrentScore(manager.userPlaying.getCurrentScore()+1);
                    if (manager.userPlaying.getCurrentScore()>15) {
                        JOptionPane.showMessageDialog(new JFrame(), "You win");
                        manager.userPlaying.setBestScore(15);
                        try {
                            manager.writeUserToFile();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        System.exit(0);
                    }
                    new PlayGUI(manager);
                }
                else {
                    if (manager.userPlaying.getCurrentScore()>=5 && manager.userPlaying.getCurrentScore()<=9)
                        manager.userPlaying.setCurrentScore(5);
                    //if (Manager.userPlaying.getCurrentScore()<5)
                    //    Manager.userPlaying.setCurrentScore(0);
                    JOptionPane.showMessageDialog(new JFrame(), "your Score: " + manager.userPlaying.getCurrentScore());
                    manager.userPlaying.setBestScore(Math.max(manager.userPlaying.getCurrentScore(),manager.userPlaying.getBestScore()));
                    manager.userPlaying.setCurrentScore(0);
                    try {
                        manager.writeUserToFile();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    this.dispose();
                    new StartGUI(manager);
                }
            }
        }
    }
}
