import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.darkGray;
import static java.awt.Color.gray;

/**
 * Created by Dang Tien Son on 7/28/2017.
 */
public class LoginGUI extends JFrame implements ActionListener {
    JButton loginButton,goBackButton;
    JLabel labelUserName,labelPassword;
    JTextField userName;
    JPasswordField password;
    Manager manager;
    public LoginGUI(Manager manager){
        this.manager = manager;
        setTitle("Log in");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(600,400);

        JPanel p = new JPanel();
        p.setBackground(gray);
        p.setLayout(null);


        labelUserName = new JLabel("User Name: ");
        labelUserName.setBounds(150, 100, 100, 30);
        labelUserName.setForeground(Color.black);
        labelUserName.setBackground(darkGray);
        p.add(labelUserName);

        userName = new JTextField(20);
        userName.setBounds(350, 100, 100, 30);
        p.add(userName);

        labelPassword = new JLabel("Password: ");
        labelPassword.setBounds(150, 200, 100, 30);
        labelPassword.setForeground(Color.black);
        labelPassword.setBackground(darkGray);
        p.add(labelPassword);

        password = new JPasswordField(20);
        password.setBounds(350, 200, 100, 30);
        p.add(password);

        loginButton = new JButton("Log in");
        loginButton.setBounds(100, 300, 80, 25);
        loginButton.addActionListener(this);
        p.add(loginButton);

        goBackButton = new JButton("Back");
        goBackButton.setBounds(350, 300, 80, 25);
        goBackButton.addActionListener(this);
        p.add(goBackButton);

        add(p);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == goBackButton) {
            this.dispose();
            new StartGUI(manager);
        }
        if(e.getSource() == loginButton) {
            User d = null;
            for (User s : manager.users){
                if (s.getName().equals(userName.getText()) && s.getPassword().equals(password.getText())) d = s;
            }
            if (d==null)
                JOptionPane.showMessageDialog(new JFrame(), "Wrong username or password");
            else {
                this.dispose();
                manager.logined = true;
                manager.userPlaying = d;
                new StartGUI(manager);
            }
        }
    }
}

