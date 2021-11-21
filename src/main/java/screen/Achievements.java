package screen;

import game.Game;

import javax.swing.*;
import java.util.ArrayList;

import static utils.ImageUtils.getImage;

public class Achievements {
    private static final int TROHPY1 = 6;
    private static final int TROHPY2 = 7;
    private static final int TROPHY3 = 8;
    private static final int TROPHY4 = 9;
    private static final int TROPHY5 = 10;
    private static final int TROPHY6 = 11;

    JLabel star1 = new JLabel();
    JLabel star2 = new JLabel();
    JLabel star3 = new JLabel();
    JLabel star4 = new JLabel();
    JLabel star5 = new JLabel();
    JLabel star6 = new JLabel();

    public Achievements() {
        fillAchievements();
    }

    public ArrayList<JComponent> getAllComponents() {
        ArrayList<JComponent> listOfComponents = new ArrayList<JComponent>();

        JLabel titleLeft = new JLabel();
        JLabel iconAchievements = new JLabel();
        JLabel titleAchievements = new JLabel();
        JLabel titleStar1 = new JLabel();
        JLabel descriptionStar1 = new JLabel();
        JLabel titleStar2 = new JLabel();
        JLabel descriptionStar2 = new JLabel();
        JLabel titleStar3 = new JLabel();
        JLabel descriptionStar3 = new JLabel();
        JLabel titleStar4 = new JLabel();
        JLabel descriptionStar4 = new JLabel();
        JLabel titleStar5 = new JLabel();
        JLabel descriptionStar5 = new JLabel();
        JLabel titleStar6 = new JLabel();
        JLabel descriptionStar6 = new JLabel();
        JLabel btnBack = new JLabel();
        JLabel backgroundAchievements = new JLabel();
        JLabel madeText = new JLabel();
        JLabel background = new JLabel();

        titleLeft.setIcon(new ImageIcon(getImage("title_left.png")));
        listOfComponents.add(titleLeft);
        titleLeft.setBounds(20, 20, 216, 34);

        iconAchievements.setIcon(new ImageIcon(getImage("conquistasIcone.png")));
        listOfComponents.add(iconAchievements);
        iconAchievements.setBounds(680, 100, 80, 78);

        titleAchievements.setFont(new java.awt.Font("Krungthep", 1, 36));
        titleAchievements.setForeground(new java.awt.Color(221, 87, 144));
        titleAchievements.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleAchievements.setText("CONQUISTAS");
        titleAchievements.setToolTipText("");
        listOfComponents.add(titleAchievements);
        titleAchievements.setBounds(320, 190, 780, 50);

        star1.setIcon(new ImageIcon(getImage("starGray.png")));
        listOfComponents.add(star1);
        star1.setBounds(400, 290, 46, 44);

        titleStar1.setFont(new java.awt.Font("Krungthep", 1, 24));
        titleStar1.setForeground(new java.awt.Color(255, 193, 7));
        titleStar1.setText("APRENDIZ");
        listOfComponents.add(titleStar1);
        titleStar1.setBounds(460, 280, 200, 40);

        descriptionStar1.setFont(new java.awt.Font("Krungthep", 0, 14));
        descriptionStar1.setForeground(new java.awt.Color(255, 193, 7));
        descriptionStar1.setText("Iniciou o jogo uma vez");
        listOfComponents.add(descriptionStar1);
        descriptionStar1.setBounds(460, 310, 200, 40);

        star2.setIcon(new ImageIcon(getImage("starGray.png")));
        listOfComponents.add(star2);
        star2.setBounds(770, 290, 46, 44);

        titleStar2.setFont(new java.awt.Font("Krungthep", 1, 24));
        titleStar2.setForeground(new java.awt.Color(255, 193, 7));
        titleStar2.setText("LIGEIRINHO");
        listOfComponents.add(titleStar2);
        titleStar2.setBounds(830, 280, 220, 40);

        descriptionStar2.setFont(new java.awt.Font("Krungthep", 0, 14));
        descriptionStar2.setForeground(new java.awt.Color(255, 193, 7));
        descriptionStar2.setText("Acertou uma pergunta rápido");
        listOfComponents.add(descriptionStar2);
        descriptionStar2.setBounds(830, 310, 250, 40);

        star3.setIcon(new ImageIcon(getImage("starGray.png")));
        listOfComponents.add(star3);
        star3.setBounds(400, 390, 46, 44);

        titleStar3.setFont(new java.awt.Font("Krungthep", 1, 24));
        titleStar3.setForeground(new java.awt.Color(255, 193, 7));
        titleStar3.setText("VELOZ");
        listOfComponents.add(titleStar3);
        titleStar3.setBounds(460, 380, 200, 40);

        descriptionStar3.setFont(new java.awt.Font("Krungthep", 0, 14));
        descriptionStar3.setForeground(new java.awt.Color(255, 193, 7));
        descriptionStar3.setText("Acertou 5 perguntas seguidas");
        listOfComponents.add(descriptionStar3);
        descriptionStar3.setBounds(460, 410, 230, 40);

        star4.setIcon(new ImageIcon(getImage("starGray.png")));
        listOfComponents.add(star4);
        star4.setBounds(770, 390, 46, 44);

        titleStar4.setFont(new java.awt.Font("Krungthep", 1, 24));
        titleStar4.setForeground(new java.awt.Color(255, 193, 7));
        titleStar4.setText("CRAQUE");
        listOfComponents.add(titleStar4);
        titleStar4.setBounds(830, 380, 170, 40);

        descriptionStar4.setFont(new java.awt.Font("Krungthep", 0, 14));
        descriptionStar4.setForeground(new java.awt.Color(255, 193, 7));
        descriptionStar4.setText("Acertou 08 perguntas seguidas");
        listOfComponents.add(descriptionStar4);
        descriptionStar4.setBounds(830, 410, 250, 40);

        star5.setIcon(new ImageIcon(getImage("starGray.png")));
        listOfComponents.add(star5);
        star5.setBounds(400, 490, 46, 44);

        titleStar5.setFont(new java.awt.Font("Krungthep", 1, 24));
        titleStar5.setForeground(new java.awt.Color(255, 193, 7));
        titleStar5.setText("INVENCÍVEL");
        listOfComponents.add(titleStar5);
        titleStar5.setBounds(460, 480, 220, 40);

        descriptionStar5.setFont(new java.awt.Font("Krungthep", 0, 14));
        descriptionStar5.setForeground(new java.awt.Color(255, 193, 7));
        descriptionStar5.setText("Completou um nível sem errar");
        listOfComponents.add(descriptionStar5);
        descriptionStar5.setBounds(460, 510, 240, 40);

        star6.setIcon(new ImageIcon(getImage("starGray.png")));
        listOfComponents.add(star6);
        star6.setBounds(770, 490, 46, 44);

        titleStar6.setFont(new java.awt.Font("Krungthep", 1, 24));
        titleStar6.setForeground(new java.awt.Color(255, 193, 7));
        titleStar6.setText("FISCAL");
        listOfComponents.add(titleStar6);
        titleStar6.setBounds(830, 480, 150, 40);

        descriptionStar6.setFont(new java.awt.Font("Krungthep", 0, 14));
        descriptionStar6.setForeground(new java.awt.Color(255, 193, 7));
        descriptionStar6.setText("Completou o game");
        listOfComponents.add(descriptionStar6);
        descriptionStar6.setBounds(830, 510, 250, 40);

        btnBack.setIcon(new ImageIcon(getImage("botaoVoltar.png")));
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVoltarMouseClicked(evt);
            }
        });
        listOfComponents.add(btnBack);
        btnBack.setBounds(620, 630, 210, 45);

        backgroundAchievements.setIcon(new ImageIcon(getImage("backgroundConquistas.png")));
        listOfComponents.add(backgroundAchievements);
        backgroundAchievements.setBounds(320, 60, 784, 650);

        madeText.setFont(new java.awt.Font("Krungthep", 0, 18));
        madeText.setForeground(new java.awt.Color(255, 255, 255));
        madeText.setText("FEITO POR CESAR VERGARA");
        listOfComponents.add(madeText);
        madeText.setBounds(20, 730, 260, 30);

        background.setIcon(new ImageIcon(getImage("fundo_sistema.jpg")));
        listOfComponents.add(background);
        background.setBounds(0, 0, 1366, 770);

        return listOfComponents;
    }

    private void btnVoltarMouseClicked(java.awt.event.MouseEvent evt) {
        Screen.getScreen().setScreen("Home");
    }

    private void fillAchievements() {
        ImageIcon star = new ImageIcon(getImage("starIcon.png"));
        String[] account = Game.getInstance().getAccount();

        if (account[TROHPY1].equals("1")) {
            star1.setIcon(star);
        }
        if (account[TROHPY2].equals("1")) {
            star2.setIcon(star);
        }
        if (account[TROPHY3].equals("1")) {
            star3.setIcon(star);
        }
        if (account[TROPHY4].equals("1")) {
            star4.setIcon(star);
        }
        if (account[TROPHY5].equals("1")) {
            star5.setIcon(star);
        }
        if (account[TROPHY6].equals("1")) {
            star6.setIcon(star);
        }
    }


}
