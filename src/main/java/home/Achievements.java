package home;

import javax.swing.*;

public class Achievements extends JFrame {

    private static final int TROFEU1 = 6;
    private static final int TROFEU2 = 7;
    private static final int TROFEU3 = 8;
    private static final int TROFEU4 = 9;
    private static final int TROFEU5 = 10;
    private static final int TROFEU6 = 11;
    String[] account = null;

    JLabel star1;
    JLabel star2;
    JLabel star3;
    JLabel star4;
    JLabel star5;
    JLabel star6;

    public Achievements() {
        initComponents();
    }

    public Achievements(String[] conta) {
        initComponents();
        this.account = conta;
        preencherConquistas();
    }


    private void initComponents() {

        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        JLabel titleLeft = new JLabel();
        JLabel iconConquistas = new JLabel();
        JLabel tituloConquistas = new JLabel();
        star1 = new JLabel();
        JLabel tituloStar1 = new JLabel();
        JLabel descricaoStar1 = new JLabel();
        star2 = new JLabel();
        JLabel tituloStar2 = new JLabel();
        JLabel descricaoStar2 = new JLabel();
        star3 = new JLabel();
        JLabel tituloStar3 = new JLabel();
        JLabel descricaoStar3 = new JLabel();
        star4 = new JLabel();
        JLabel tituloStar4 = new JLabel();
        JLabel descricaoStar4 = new JLabel();
        star5 = new JLabel();
        JLabel tituloStar5 = new JLabel();
        JLabel descricaoStar5 = new JLabel();
        star6 = new JLabel();
        JLabel tituloStar6 = new JLabel();
        JLabel descricaoStar6 = new JLabel();
        JLabel btnVoltar = new JLabel();
        JLabel backgroundConquistas = new JLabel();
        JLabel madeText = new JLabel();
        JLabel background = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setLayout(null);

        titleLeft.setIcon(new ImageIcon(getClass().getResource("/resources/title_left.png")));
        jPanel1.add(titleLeft);
        titleLeft.setBounds(20, 20, 216, 34);

        iconConquistas.setIcon(new ImageIcon(getClass().getResource("/resources/conquistasIcone.png")));
        jPanel1.add(iconConquistas);
        iconConquistas.setBounds(680, 100, 80, 78);

        tituloConquistas.setFont(new java.awt.Font("Krungthep", 1, 36));
        tituloConquistas.setForeground(new java.awt.Color(221, 87, 144));
        tituloConquistas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloConquistas.setText("CONQUISTAS");
        tituloConquistas.setToolTipText("");
        jPanel1.add(tituloConquistas);
        tituloConquistas.setBounds(320, 190, 780, 50);

        star1.setIcon(new ImageIcon(getClass().getResource("/resources/starGray.png")));
        jPanel1.add(star1);
        star1.setBounds(400, 290, 46, 44);

        tituloStar1.setFont(new java.awt.Font("Krungthep", 1, 24));
        tituloStar1.setForeground(new java.awt.Color(255, 193, 7));
        tituloStar1.setText("APRENDIZ");
        jPanel1.add(tituloStar1);
        tituloStar1.setBounds(460, 280, 200, 40);

        descricaoStar1.setFont(new java.awt.Font("Krungthep", 0, 14));
        descricaoStar1.setForeground(new java.awt.Color(255, 193, 7));
        descricaoStar1.setText("Iniciou o jogo uma vez");
        jPanel1.add(descricaoStar1);
        descricaoStar1.setBounds(460, 310, 200, 40);

        star2.setIcon(new ImageIcon(getClass().getResource("/resources/starGray.png")));
        jPanel1.add(star2);
        star2.setBounds(770, 290, 46, 44);

        tituloStar2.setFont(new java.awt.Font("Krungthep", 1, 24));
        tituloStar2.setForeground(new java.awt.Color(255, 193, 7));
        tituloStar2.setText("LIGEIRINHO");
        jPanel1.add(tituloStar2);
        tituloStar2.setBounds(830, 280, 220, 40);

        descricaoStar2.setFont(new java.awt.Font("Krungthep", 0, 14));
        descricaoStar2.setForeground(new java.awt.Color(255, 193, 7));
        descricaoStar2.setText("Acertou uma pergunta rápido");
        jPanel1.add(descricaoStar2);
        descricaoStar2.setBounds(830, 310, 250, 40);

        star3.setIcon(new ImageIcon(getClass().getResource("/resources/starGray.png")));
        jPanel1.add(star3);
        star3.setBounds(400, 390, 46, 44);

        tituloStar3.setFont(new java.awt.Font("Krungthep", 1, 24));
        tituloStar3.setForeground(new java.awt.Color(255, 193, 7));
        tituloStar3.setText("VELOZ");
        jPanel1.add(tituloStar3);
        tituloStar3.setBounds(460, 380, 200, 40);

        descricaoStar3.setFont(new java.awt.Font("Krungthep", 0, 14));
        descricaoStar3.setForeground(new java.awt.Color(255, 193, 7));
        descricaoStar3.setText("Acertou 5 perguntas seguidas");
        jPanel1.add(descricaoStar3);
        descricaoStar3.setBounds(460, 410, 230, 40);

        star4.setIcon(new ImageIcon(getClass().getResource("/resources/starGray.png")));
        jPanel1.add(star4);
        star4.setBounds(770, 390, 46, 44);

        tituloStar4.setFont(new java.awt.Font("Krungthep", 1, 24));
        tituloStar4.setForeground(new java.awt.Color(255, 193, 7));
        tituloStar4.setText("CRAQUE");
        jPanel1.add(tituloStar4);
        tituloStar4.setBounds(830, 380, 170, 40);

        descricaoStar4.setFont(new java.awt.Font("Krungthep", 0, 14));
        descricaoStar4.setForeground(new java.awt.Color(255, 193, 7));
        descricaoStar4.setText("Acertou 08 perguntas seguidas");
        jPanel1.add(descricaoStar4);
        descricaoStar4.setBounds(830, 410, 250, 40);

        star5.setIcon(new ImageIcon(getClass().getResource("/resources/starGray.png")));
        jPanel1.add(star5);
        star5.setBounds(400, 490, 46, 44);

        tituloStar5.setFont(new java.awt.Font("Krungthep", 1, 24));
        tituloStar5.setForeground(new java.awt.Color(255, 193, 7));
        tituloStar5.setText("INVENCÍVEL");
        jPanel1.add(tituloStar5);
        tituloStar5.setBounds(460, 480, 220, 40);

        descricaoStar5.setFont(new java.awt.Font("Krungthep", 0, 14));
        descricaoStar5.setForeground(new java.awt.Color(255, 193, 7));
        descricaoStar5.setText("Completou um nível sem errar");
        jPanel1.add(descricaoStar5);
        descricaoStar5.setBounds(460, 510, 240, 40);

        star6.setIcon(new ImageIcon(getClass().getResource("/resources/starGray.png")));
        jPanel1.add(star6);
        star6.setBounds(770, 490, 46, 44);

        tituloStar6.setFont(new java.awt.Font("Krungthep", 1, 24));
        tituloStar6.setForeground(new java.awt.Color(255, 193, 7));
        tituloStar6.setText("FISCAL");
        jPanel1.add(tituloStar6);
        tituloStar6.setBounds(830, 480, 150, 40);

        descricaoStar6.setFont(new java.awt.Font("Krungthep", 0, 14));
        descricaoStar6.setForeground(new java.awt.Color(255, 193, 7));
        descricaoStar6.setText("Completou o game");
        jPanel1.add(descricaoStar6);
        descricaoStar6.setBounds(830, 510, 250, 40);

        btnVoltar.setIcon(new ImageIcon(getClass().getResource("/resources/botaoVoltar.png")));
        btnVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVoltarMouseClicked(evt);
            }
        });
        jPanel1.add(btnVoltar);
        btnVoltar.setBounds(620, 630, 210, 45);

        backgroundConquistas.setIcon(new ImageIcon(getClass().getResource("/resources/backgroundConquistas.png")));
        jPanel1.add(backgroundConquistas);
        backgroundConquistas.setBounds(320, 60, 784, 650);

        madeText.setFont(new java.awt.Font("Krungthep", 0, 18));
        madeText.setForeground(new java.awt.Color(255, 255, 255));
        madeText.setText("FEITO POR CESAR VERGARA");
        jPanel1.add(madeText);
        madeText.setBounds(20, 730, 260, 30);

        background.setIcon(new ImageIcon(getClass().getResource("/resources/fundo_sistema.jpg")));
        jPanel1.add(background);
        background.setBounds(0, 0, 1366, 770);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1366, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
        );

        pack();
    }

    private void btnVoltarMouseClicked(java.awt.event.MouseEvent evt) {
        Home jogo = new Home(account);
        jogo.setLocationRelativeTo(null);
        jogo.setVisible(true);
        this.dispose();
    }

    private void preencherConquistas() {
        ImageIcon estrela = new ImageIcon(getClass().getResource("/resources/starIcon.png"));
        if (account[TROFEU1].equals("1")) {
            star1.setIcon(estrela);
        }
        if (account[TROFEU2].equals("1")) {
            star2.setIcon(estrela);
        }
        if (account[TROFEU3].equals("1")) {
            star3.setIcon(estrela);
        }
        if (account[TROFEU4].equals("1")) {
            star4.setIcon(estrela);
        }
        if (account[TROFEU5].equals("1")) {
            star5.setIcon(estrela);
        }
        if (account[TROFEU6].equals("1")) {
            star6.setIcon(estrela);
        }
    }


}
