package com.example.ai.IO;

import javax.swing.*;
import java.awt.*;

public class FrameLayout {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("BorderLayoutDemo");
        frame.setBounds(500, 200, 300, 300);
        frame.setLayout(new BorderLayout(10, 10));
        frame.add(new JButton("北"), BorderLayout.NORTH);
        frame.add(new JButton("东"), BorderLayout.EAST);
        frame.add(new JButton("南"), BorderLayout.SOUTH);
        frame.add(new JButton("西"), BorderLayout.WEST);
        frame.add(new JButton("中"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
