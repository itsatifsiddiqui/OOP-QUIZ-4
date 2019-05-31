package app;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Menu extends JFrame {

    private JButton addButton, searchButton;

    public Menu() {

        setTitle("Main Menu");
        setSize(640, 480);
        setLayout(new GridLayout(2, 1));
        setVisible(true);

        addButton = new JButton("Add Student");
        searchButton = new JButton("Search Student");

        add(addButton);
        add(searchButton);

        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("ADD");
                new StudentForm(true);

            }
        });

        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentForm(false);
            }
        });
    }

}