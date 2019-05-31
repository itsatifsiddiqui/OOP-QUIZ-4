package app;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class StudentForm extends JFrame {

    JLabel usernameLabel;
    JLabel passwordLabel;
    JTextField usernameField;
    JTextField passwordField;

    JButton submitButton;

    public StudentForm(boolean isAdd) {

        setTitle(isAdd ? "Add" : "Login");
        setLayout(new BorderLayout());
        setSize(640, 200);
        setVisible(true);

        Panel gridPanel = new Panel();
        gridPanel.setLayout(new GridLayout(2, 2));

        usernameLabel = new JLabel("    Enter Username");
        passwordLabel = new JLabel("    Enter Password");
        usernameField = new JTextField();
        passwordField = new JTextField();

        submitButton = new JButton(isAdd ? "Submit" : "Login");

        gridPanel.add(usernameLabel);
        gridPanel.add(usernameField);
        gridPanel.add(passwordLabel);
        gridPanel.add(passwordField);

        add(gridPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                if (username.isEmpty() || password.isEmpty())
                    JOptionPane.showMessageDialog(null, "Please Fill Out All The Fields");

                if (isAdd) {
                    ArrayList<Student> stud = Operations.readAllData();
                    Student newStudent = new Student(username, password);
                    stud.add(newStudent);

                    Operations.addArrayListToFile(stud);
                    JOptionPane.showMessageDialog(null, "Student Added Successfully");
                    setVisible(false);
                    dispose();
                } else {
                    ArrayList<Student> stud = Operations.readAllData();
                    boolean found = false;
                    for (Student student : stud) {
                        if (student.getUsername().equals(usernameField.getText())
                                && student.getPassword().equals(passwordField.getText())) {
                            JOptionPane.showMessageDialog(null, "Login Succesfull");
                            found = true;
                            break;

                        }
                    }

                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Inavlid Credentials");
                    }

                }

            }
        });

    }

}