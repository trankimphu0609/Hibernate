/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Shadow
 */
public class header extends JPanel {
    private int height, width;

    public header(int w, int h) {
        width = w;
        height = h;
        init();
    }

    public void init() {
        setLayout(null);
        setSize(width, height);
        setBackground(null);

        JLabel logo = new JLabel(new ImageIcon("./src/main/java/image/SieuThi_25px.png"), JLabel.CENTER);
        logo.setBounds(new Rectangle(30, 10, 25, 25));
        Font font = new Font("Segoe UI", Font.BOLD, 15);
        JLabel name = new JLabel("QUẢN LÝ COFFEE", JLabel.CENTER);
        name.setFont(font);
        name.setForeground(Color.white);
        name.setBounds(new Rectangle(60, 0, 150, 40));

        add(logo);
        add(name);
    }
}
