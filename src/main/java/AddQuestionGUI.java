import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

import static java.awt.Color.*;

/**
 * Created by Dang Tien Son on 7/29/2017.
 */
public class AddQuestionGUI extends JFrame implements ActionListener {
    JTextField Question,trueAnswerIndex;
    JTextField[] ans = new JTextField[5];
    JButton goBackButton,addButton;
    Manager manager;
    public AddQuestionGUI(Manager manager){
        this.manager = manager;
        setTitle("Add Question");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);


        JPanel p = new JPanel();
        p.setBackground(darkGray);
        p.setLayout(null);


        Question = new JTextField("Enter Question here",100);
        Question.setBackground(LIGHT_GRAY);
        Question.setForeground(black);
        Question.setBounds(50, 50, 500, 200);
        Question.addFocusListener(new FocusListener() {
            @Override public void focusLost(final FocusEvent pE) {
                Question.setText(Question.getText());
            }
            @Override public void focusGained(final FocusEvent pE) {
                Question.setText("");
            }
        });
        p.add(Question);
        int i;
        for(i=1;i<=4;i++){
            ans[i] = new JTextField("Enter an answer here");
            ans[i].setBackground(LIGHT_GRAY);
            ans[i].setForeground(black);
            ans[i].setBounds(50, 250+(i-1)*50, 500, 50);
            final int finalI = i;
            final int finalI1 = i;
            ans[i].addFocusListener(new FocusListener() {
                @Override public void focusLost(final FocusEvent pE) {
                    ans[finalI1].setText(ans[finalI].getText());
                }
                @Override public void focusGained(final FocusEvent pE) {
                    ans[finalI1].setText("");
                }
            });
            p.add(ans[i]);
        }
        trueAnswerIndex = new JTextField("Index of the true answer",100);
        trueAnswerIndex.setBackground(LIGHT_GRAY);
        trueAnswerIndex.setForeground(black);
        trueAnswerIndex.setBounds(50, 450, 150, 20);
        trueAnswerIndex.addFocusListener(new FocusListener() {
            @Override public void focusLost(final FocusEvent pE) {
                trueAnswerIndex.setText(trueAnswerIndex.getText());
            }
            @Override public void focusGained(final FocusEvent pE) {
                trueAnswerIndex.setText("");
            }
        });
        p.add(trueAnswerIndex);

        goBackButton = new JButton("Back");
        goBackButton.setBounds(350, 500, 80, 25);
        goBackButton.addActionListener(this);
        p.add(goBackButton);

        addButton = new JButton("Add");
        addButton.setBounds(200, 500, 80, 25);
        addButton.addActionListener(this);
        p.add(addButton);

        add(p);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goBackButton){
            this.dispose();
            new StartGUI(manager);
        }
        if (e.getSource() == addButton){
            manager.questions.add(new Question(Question.getText(),ans[1].getText(),ans[2].getText(),ans[3].getText(),ans[4].getText(),Integer.parseInt(trueAnswerIndex.getText())));
            try {
                manager.writeQuestionToFile();
                manager.importQuestion();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            this.dispose();
            new AddQuestionGUI(manager);

        }
    }
}
