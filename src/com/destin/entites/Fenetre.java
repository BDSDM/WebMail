package com.destin.entites;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Fenetre extends JFrame {

    JTextArea textArea;

    public Fenetre(){

        //Construction de l'interface graphique

        super("Envoi de mails à un destinataire");
        this.setSize(600,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Construction et injection de la barre d'outils
        JPanel contentPane = (JPanel) this.getContentPane();

        contentPane.add(createToolBar(),BorderLayout.NORTH);
        this.setJMenuBar(createMenubar());

        textArea = new JTextArea("Bonjour");
        contentPane.add(textArea);

       JScrollPane jsp = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(jsp);

        JLabel lab = new JLabel("Envoyer un message");
        contentPane.add(lab,BorderLayout.SOUTH);


    }

    private JMenuBar createMenubar() {

        JMenuBar menuBar = new JMenuBar();

        JMenu mnuMessage = new JMenu("Message");
        mnuMessage.setMnemonic('M');
        menuBar.add(mnuMessage);

        JMenuItem mnuNouveau = new JMenuItem("Nouveau");
        mnuNouveau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnuNouveauListener();

            }
        });
        mnuNouveau.setMnemonic('N');
        mnuMessage.add(mnuNouveau);
        mnuMessage.addSeparator();

        JMenuItem mnuOuvrir = new JMenuItem("Ouvrir");
       mnuOuvrir.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               mnuOuvrirListener();
           }
       });

        mnuOuvrir.setMnemonic('O');
        mnuMessage.add(mnuOuvrir);
        mnuMessage.addSeparator();

        JMenuItem mnuEnvoyer = new JMenuItem("Envoyer");
        mnuEnvoyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                FileWriter fichier = null;
                try {

                    String message = textArea.getText();
                    fichier = new FileWriter("test.txt");
                    fichier.write(message);
                    fichier.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        mnuEnvoyer.setMnemonic('E');
        mnuMessage.add(mnuEnvoyer);
        mnuMessage.addSeparator();

        JMenuItem mnuQuitter = new JMenuItem("Quitter");
        mnuQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mnuQuitter.setMnemonic('Q');
        mnuMessage.add(mnuQuitter);

        JMenu mnuOptions = new JMenu("Options");
        mnuOptions.setMnemonic('O');
        menuBar.add(mnuOptions);

        JMenuItem mnuParametres = new JMenuItem("Paramètres");

        mnuParametres.setMnemonic('P');
        mnuOptions.add(mnuParametres);

        JMenu mnuAide = new JMenu("?");

        mnuAide.setMnemonic('?');


        menuBar.add(mnuAide);

        JMenuItem mnuA = new JMenuItem("A propos");
        mnuA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Envoi de mails à un destinataire\n(c) JC Rigal 2003\nversion 1.0","A propos de Web Mail",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        mnuA.setMnemonic('A');
        mnuAide.add(mnuA);



        return menuBar;
    }

    private JToolBar createToolBar() {

        JToolBar toolBar = new JToolBar();

        JButton btnNew = new JButton(new ImageIcon("icons/new.png"));
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnuNouveauListener();
            }
        });
        toolBar.add(btnNew);

        JButton btn2 = new JButton(new ImageIcon("icons/redo.png"));
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnuOuvrirListener();

            }
        });
        toolBar.add(btn2);

        JButton btn3 = new JButton(new ImageIcon("icons/save.png"));
        toolBar.add(btn3);

        JLabel lab1 = new JLabel("Sujet");
        toolBar.add(lab1);

        JTextField texte1 = new JTextField("Lycos WebCenter");
        texte1.setPreferredSize(new Dimension(150,30));
        toolBar.add(texte1);

        JLabel lab2 = new JLabel("Pour");
        toolBar.add(lab2);
        Choice combo = new Choice();
        combo.addItem("Paul DUPOND pdupond@free.fr");
        toolBar.add(combo);

        return toolBar;

    }
    public void mnuOuvrirListener(){
        Path filePath = Path.of("test.txt");

        try {
            String content = Files.readString(filePath);
            JOptionPane.showMessageDialog(null,content,"Mail envoyé : ",JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
    public void mnuNouveauListener(){
        textArea.setText("");

    }
}
