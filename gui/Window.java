package gui;

import javax.swing.*;

/**
 * Created by matoran on 4/3/17.
 */
public class Window {
    public static void main(String[] args) {
        JFrame frame = new JFrame("CFF-Network");
        frame.setContentPane(new Panel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1000,1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
