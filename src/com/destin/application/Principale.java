package com.destin.application;

import com.destin.entites.Fenetre;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Principale {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        Fenetre fenetre = new Fenetre();
        fenetre.setVisible(true);
    }
}
