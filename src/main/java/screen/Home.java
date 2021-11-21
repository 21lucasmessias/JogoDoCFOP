package screen;

import game.Game;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static utils.ImageUtils.getImage;

public class Home {
    private void btnLeaveMouseClicked(java.awt.event.MouseEvent evt) {
        int result = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja encerrar o login?", "Encerrar Login",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            Screen.getScreen().setScreen("Login");
        }
    }

    public void callInstruction() {
        Screen.getScreen().setScreen("GameInstruction");
    }

    public void callInstructionNote() {
        Screen.getScreen().setScreen("GameInstructionNote");
    }

    private void btnAchievementsMouseClicked(java.awt.event.MouseEvent evt) {
        Screen.getScreen().setScreen("Achievements");
    }

    public void validatePlayerLevel() {
        int selectlvl = 0;
        int i = 0;
        Game jogo = Game.getInstance();

        try {
            FileReader fileLvl = new FileReader("rankingFase1.txt");
            BufferedReader linhaFase = new BufferedReader(fileLvl);
            String val;

            while ((val = linhaFase.readLine()) != null && selectlvl == 0) {
                String lvl = val.split(",")[0];
                String login = val.split(",")[1];
                if (Integer.parseInt(lvl) == 1 && jogo.getLogin().equals(login)) {
                    selectlvl = 3;
                    String[] option = {"Voltar Fase 1", " Seguir Fase 2"};
                    int result = JOptionPane.showOptionDialog(null, "jogador.Jogador já completou alguma fase. O que deseja fazer?", "Fases do jogo.Jogo!",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, option, option[0]);
                    if (result == JOptionPane.YES_OPTION) {
                        selectlvl = 1;
                    }
                    if (result == JOptionPane.NO_OPTION) {
                        selectlvl = 2;
                    }
                }
                i++;
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

        if (selectlvl == 1 || selectlvl == 0) {
            callInstruction();
        }
        if (selectlvl == 2) {
            callInstructionNote();
        }
    }

    private void btnPlayMouseClicked(java.awt.event.MouseEvent evt) {
        validatePlayerLevel();
    }

    private void btnRankingMouseClicked(java.awt.event.MouseEvent evt) {
        Screen.getScreen().setScreen("GameResult", true);
    }


    public ArrayList<JComponent> getAllComponents() {

        ArrayList<JComponent> listOfComponents = new ArrayList<JComponent>();

        JLabel gameCFOP = new JLabel();
        JLabel buttonPlay = new JLabel();
        JLabel buttonAchievements = new JLabel();
        JLabel buttonRanking = new JLabel();
        JLabel buttonLeave = new JLabel();
        JLabel jLabel1 = new JLabel();
        JLabel background = new JLabel();

        gameCFOP.setIcon(new ImageIcon(getImage("titulo.png")));
        listOfComponents.add(gameCFOP);
        gameCFOP.setBounds(320, 20, 730, 130);

        buttonRanking.setIcon(new ImageIcon(getImage("botaoResultadoe.png")));
        buttonRanking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRankingMouseClicked(evt);
            }
        });
        listOfComponents.add(buttonRanking);
        buttonRanking.setBounds(510, 170, 354, 90);


        buttonPlay.setIcon(new ImageIcon(getImage("botaoJogar.png")));
        buttonPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPlayMouseClicked(evt);
            }
        });
        listOfComponents.add(buttonPlay);
        buttonPlay.setBounds(510, 290, 354, 90);

        buttonAchievements.setIcon(new ImageIcon(getImage("botaoConquistas.png")));
        buttonAchievements.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAchievementsMouseClicked(evt);
            }
        });
        listOfComponents.add(buttonAchievements);
        buttonAchievements.setBounds(510, 410, 354, 89);

        buttonLeave.setIcon(new ImageIcon(getImage("botaoSair.png")));
        buttonLeave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLeaveMouseClicked(evt);
            }
        });
        listOfComponents.add(buttonLeave);
        buttonLeave.setBounds(510, 530, 354, 89);

        jLabel1.setFont(new java.awt.Font("Krungthep", 0, 18));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FEITO POR CESAR VERGARA");
        listOfComponents.add(jLabel1);
        jLabel1.setBounds(20, 730, 260, 30);

        background.setBackground(new java.awt.Color(255, 255, 204));
        background.setIcon(new ImageIcon(getImage("fundo_sistema.jpg")));
        background.setToolTipText("");
        listOfComponents.add(background);
        background.setBounds(0, 0, 1366, 770);

        return listOfComponents;
    }
}

