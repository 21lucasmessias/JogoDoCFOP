package questions;

import utils.MediaPlayer;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static utils.ImageUtils.getImage;

public class RightAnswer extends JFrame {
    private JLabel finish;
    private JLabel iconeCerto;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JLabel tituloCerto;

    public RightAnswer() {
        initComponents();
        MediaPlayer som = new MediaPlayer("src/main/resources/certa.wav");
        som.play();
    }

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {
        this.dispose();
    }

    private void jLabel1KeyPressed(KeyEvent evt) {

    }

    private void formKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.dispose();
        }
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        iconeCerto = new JLabel();
        tituloCerto = new JLabel();
        jLabel2 = new JLabel();
        finish = new JLabel();
        jLabel1 = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setLayout(null);

        iconeCerto.setIcon(new javax.swing.ImageIcon(getImage("certoIcone.png"))); // NOI18N
        jPanel1.add(iconeCerto);
        iconeCerto.setBounds(130, 10, 140, 140);

        tituloCerto.setFont(new java.awt.Font("Krungthep", 1, 24)); // NOI18N
        tituloCerto.setForeground(new java.awt.Color(79, 186, 111));
        tituloCerto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloCerto.setText("Resposta Certa!");
        jPanel1.add(tituloCerto);
        tituloCerto.setBounds(0, 160, 400, 30);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("(Enter)");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(230, 240, 70, 40);

        finish.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        finish.setForeground(new java.awt.Color(79, 186, 111));
        finish.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        finish.setText("Você acertou essa pergunta!");
        jPanel1.add(finish);
        finish.setBounds(0, 190, 400, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getImage("botaoOK.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jLabel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                jLabel1KeyPressed(evt);
            }
        });
        jPanel1.add(jLabel1);
        jLabel1.setBounds(90, 240, 213, 40);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        setBounds(450, 350, 400, 322);
    }
}
