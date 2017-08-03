import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.awt.Color.black;
import static java.awt.Color.darkGray;
import static java.awt.Color.gray;

/**
 * Created by Dang Tien Son on 7/28/2017.
 */
public class HighscoreGUI extends  JFrame implements ActionListener {
    JTable table;
    JScrollPane scroll;
    JButton goBackButton;
    Manager manager;
    public HighscoreGUI(Manager manager){
        this.manager = manager;
        ArrayList<User> users = manager.users;
        Collections.sort(users);
        String[][] data = new String[users.size()][2];

        setTitle("High Score");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);

        JPanel p = new JPanel();
        p.setBackground(gray);
        p.setLayout(null);

        for(int i=0;i<users.size();i++){
            data[i][0] = users.get(i).getName();
            data[i][1] = Integer.toString(users.get(i).getBestScore());
        }
        String[] column = {"Name", "Score"};
        table = new JTable(data,column);
        table.setFillsViewportHeight(true);
        table.setEnabled(false);
        scroll = new JScrollPane();
        scroll.getViewport().add (table);
        scroll.setBounds(50,00,500,300);
        p.add(scroll);

        goBackButton = new JButton("Back");
        goBackButton.setBounds(260, 310, 80, 25);
        goBackButton.addActionListener(this);
        p.add(goBackButton);


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
    }
}
