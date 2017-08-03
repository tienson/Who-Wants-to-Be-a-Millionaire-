import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static java.awt.Color.blue;
import static java.awt.Color.darkGray;
import static java.awt.Color.gray;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Created by Dang Tien Son on 7/29/2017.
 */
public class SignUpGUI extends JFrame implements ActionListener {
    JButton signUpButton,goBackButton;
    JLabel labelUserName,labelPassword;
    JTextField userName;
    JPasswordField password;
    JCheckBox isAdmin;
    Manager manager;
    public SignUpGUI(Manager manager){
        this.manager = manager;
        setTitle("Sign up");
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

        isAdmin = new JCheckBox("Create Admin Acount",false);
        isAdmin.setBounds(150,250,180,30);
        isAdmin.setBackground(gray);
        p.add(isAdmin);

        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(100, 300, 80, 25);
        signUpButton.addActionListener(this);
        p.add(signUpButton);

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
        if(e.getSource() == signUpButton) {
            boolean d = true;
            for (User s : manager.users){
                if (s.getName().equals(userName.getText())) d = false;
            }
            if (d==false)
                JOptionPane.showMessageDialog(new JFrame(), "Identical Username");
            else {
                this.dispose();
                User newUser = new User(userName.getText(), password.getText(), isAdmin.isSelected(), 0);
                manager.users.add(newUser);
                try {
                    manager.writeUserToFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                manager.logined = true;
                manager.userPlaying = newUser;
                new StartGUI(manager);
            }
        }
    }
}
