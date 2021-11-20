package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static java.awt.Font.PLAIN;
import static javax.swing.SwingConstants.CENTER;
import static utils.ImageUtils.getImage;

public class GameInstruction {


    public ArrayList<JComponent> getAllComponents() {
        ArrayList<JComponent> listOfComponents = new ArrayList<JComponent>();

        javax.swing.JDialog jDialog1 = new javax.swing.JDialog();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel titleLeft = new javax.swing.JLabel();
        javax.swing.JLabel btnPlay = new javax.swing.JLabel();
        javax.swing.JLabel imagemVideo = new javax.swing.JLabel();
        javax.swing.JLabel madeText = new javax.swing.JLabel();
        javax.swing.JLabel btnSair = new javax.swing.JLabel();
        javax.swing.JLabel background = new javax.swing.JLabel();

        org.jdesktop.layout.GroupLayout jDialog1Layout = new org.jdesktop.layout.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
                jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
                jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(0, 300, Short.MAX_VALUE)
        );


        titleLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/title_left.png")));
        listOfComponents.add(titleLeft);
        titleLeft.setBounds(20, 20, 216, 34);

        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/botao_Jogar.png")));
        btnPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPlayMouseClicked(evt);
            }
        });
        listOfComponents.add(btnPlay);
        btnPlay.setBounds(720, 520, 520, 160);

        imagemVideo.setForeground(new java.awt.Color(102, 102, 0));
        imagemVideo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/infos.png")));
        listOfComponents.add(imagemVideo);
        imagemVideo.setBounds(110, 110, 1150, 570);

        madeText.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        madeText.setForeground(new java.awt.Color(255, 255, 255));
        madeText.setText("FEITO POR CESAR VERGARA");
        listOfComponents.add(madeText);
        madeText.setBounds(20, 730, 260, 30);

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/botaoSairBottom.png")));
        btnSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
        });
        listOfComponents.add(btnSair);
        btnSair.setBounds(1170, 710, 180, 45);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/fundo_sistema.jpg")));
        listOfComponents.add(background);
        background.setBounds(0, 0, 1366, 770);

        return listOfComponents;
    }


}
