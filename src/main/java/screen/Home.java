package screen;

import javax.swing.*;
import java.util.ArrayList;

public class Home {
    public ArrayList<JComponent> getAllComponents() {

        JPanel jPanel1 = new JPanel();
        JLabel jogoCFOP = new JLabel();
        JLabel btnJogar = new JLabel();
        JLabel btnConquistas = new JLabel();
        JLabel btnSair = new JLabel();
        JLabel jLabel1 = new JLabel();
        JLabel background = new JLabel();

        jogoCFOP.setIcon(new ImageIcon(getClass().getResource("/resources/titulo.png"))); // NOI18N
        jPanel1.add(jogoCFOP);
        jogoCFOP.setBounds(320, 80, 730, 130);

        btnJogar.setIcon(new ImageIcon(getClass().getResource("/resources/botaoJogar.png"))); // NOI18N
        btnJogar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnJogarMouseClicked(evt);
            }
        });
        jPanel1.add(btnJogar);
        btnJogar.setBounds(510, 260, 354, 90);

        btnConquistas.setIcon(new ImageIcon(getClass().getResource("/resources/botaoConquistas.png"))); // NOI18N
        btnConquistas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConquistasMouseClicked(evt);
            }
        });
        jPanel1.add(btnConquistas);
        btnConquistas.setBounds(510, 380, 354, 89);

        btnSair.setIcon(new ImageIcon(getClass().getResource("/resources/botaoSair.png"))); // NOI18N
        btnSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSairMouseClicked(evt);
            }
        });
        jPanel1.add(btnSair);
        btnSair.setBounds(510, 500, 354, 89);

        jLabel1.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FEITO POR CESAR VERGARA");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 730, 260, 30);

        background.setBackground(new java.awt.Color(255, 255, 204));
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/fundo_sistema.jpg"))); // NOI18N
        background.setToolTipText("");
        jPanel1.add(background);
        background.setBounds(0, 0, 1366, 770);

    }

}
