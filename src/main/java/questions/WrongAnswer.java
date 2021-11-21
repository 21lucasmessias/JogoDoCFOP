package questions;

import utils.MediaPlayer;
import javax.swing.*;

import java.awt.event.KeyEvent;

import static utils.ImageUtils.getImage;

public class WrongAnswer extends JFrame {

    private JLabel finish;
    private JLabel iconeCerto;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JLabel tituloCerto;

    boolean ultima = false;
    int vidas = 3;

    public WrongAnswer() {
        initComponents();
    }

    public WrongAnswer(boolean last, int lifes) {
        this.ultima = last;
        this.vidas = lifes;
        initComponents();
        if (last == true && lifes > 0) {
            finish.setText("Você finalizou o jogo!");
        } else {
            MediaPlayer som = new MediaPlayer("src/main/resources/errada.wav");
            som.play();
            if (lifes == 0) {
                finish.setText("Você perdeu o jogo!");
            }
        }
    }

    private void initComponents() {
        jPanel1 = new JPanel();
        iconeCerto = new JLabel();
        tituloCerto = new JLabel();
        jLabel2 = new JLabel();
        finish = new JLabel();
        jLabel1 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setLayout(null);

        iconeCerto.setIcon(new ImageIcon(getImage("erradoIcone.png"))); // NOI18N
        jPanel1.add(iconeCerto);
        iconeCerto.setBounds(130, 10, 140, 140);

        tituloCerto.setFont(new java.awt.Font("Krungthep", 1, 24)); // NOI18N
        tituloCerto.setForeground(new java.awt.Color(246, 0, 0));
        tituloCerto.setHorizontalAlignment(SwingConstants.CENTER);
        tituloCerto.setText("Resposta Errada!");
        jPanel1.add(tituloCerto);
        tituloCerto.setBounds(0, 160, 400, 30);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("(Enter)");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(230, 240, 70, 40);

        finish.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        finish.setForeground(new java.awt.Color(246, 0, 0));
        finish.setHorizontalAlignment(SwingConstants.CENTER);
        finish.setText("Você errou esta pergunta!");
        jPanel1.add(finish);
        finish.setBounds(0, 190, 400, 30);

        jLabel1.setIcon(new ImageIcon(getImage("botaoOkErrado.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
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

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {
        this.dispose();
    }

    private void formKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.dispose();
        }
    }
}
