package game;

import screen.Screen;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static utils.ImageUtils.getImage;

public class GameResult extends JFrame {

    private Game game;
    String[] account;
    private static final int TROPHY1 = 6;
    private static final int TROPHY2 = 7;
    private static final int TROPHY3 = 8;
    private static final int TROPHY4 = 9;
    private static final int TROPHY5 = 10;
    private static final int TROPHY6 = 11;
    private String fileName;

    public GameResult() {
        initComponents();
    }

    public GameResult(String[] account) {
        initComponents();
        this.account = account;
        this.game = Game.getInstance();
        fillAchievements();
        //arquivoEscrita();
        //arquivoLeitura();
        ranking();
        if (game.getLvl() == 2) {
            fillIcon();
        }
    }

    //verifica o ranking dos jogadores
    public void ranking() {
        File rank1 = new File("rankingFase1.txt");
        File rank2 = new File("rankingFase2.txt");
        if (rank1.exists() && rank2.exists()) {
            try {
                if (game.getLvl() == 1) {
                    this.fileName = "rankingFase1.txt";
                } else {
                    this.fileName = "rankingFase2.txt";
                }
                //le o arquivo conforme email de entrada (login)
                FileReader readFile = new FileReader(game.getLogin() + ".txt");
                //  FileReader lerArquivo = new FileReader("jogador.txt");
                BufferedReader readLine = new BufferedReader(readFile);
                String line = readLine.readLine();
                String email = line.split(",")[0];
                String name = line.split(",")[2];

                //ler o arquivo ranking.txt e verificar maior pontuacao                                                
                FileReader lerR = new FileReader(fileName);
                BufferedReader lerL = new BufferedReader(lerR);
                String ver = lerL.readLine();
                if (ver == null) {
                    ver = 0 + "," + "teste" + "," + "teste" + "," + 0;
                }
                String valEmail = ver.split(",")[1];
                String valPonto = ver.split(",")[2];
                System.out.println(valEmail);
                System.out.println(email);

                //   if (email.equals(valEmail) && Integer.parseInt(valPonto) == jogo.getPontuacao()) {
                //     System.out.println("sadasdasdasdas");
                //   }
                //else {
                //escrever arquivo ranking               
                //FileWriter escreverArquivo = new FileWriter("ranking.txt", true);
                FileWriter writeFile = new FileWriter(fileName, true);
                BufferedWriter writeLine = new BufferedWriter(writeFile);

                if (game.getLvl() == 1) {
                    writeLine.write(1 + "," + email + "," + name + "," + game.getScore());
                } else {
                    writeLine.write(2 + "," + email + "," + name + "," + game.getScore());
                }
                writeLine.newLine();
                writeLine.close();
                writeFile.close();

                //ler o arquivo ranking
                //FileReader lerArqRanking = new FileReader("ranking.txt");
                FileReader readFileRanking = new FileReader(fileName);
                BufferedReader readRanking = new BufferedReader(readFileRanking);
                String rank;//  = lerRanking.readLine();

                //retorna total linhas arquivo ranking                
                LineNumberReader lineCounter = new LineNumberReader(new FileReader(fileName));
                lineCounter.skip(Long.MAX_VALUE);
                int numberOfLines = lineCounter.getLineNumber() + 4;

                // array  pontuacao, jogador e login(email) com tamanho definido pelo total de linhas do arquivo
                Integer[] PlayerPointsList = new Integer[numberOfLines];
                String[] PlayerNameList = new String[numberOfLines];
                String[] PlayerLoginList = new String[numberOfLines];
                System.out.println(numberOfLines);

                //escreve valores nos vetores pontuacao e jogadores                                      
                int i = 0;
                while ((rank = readRanking.readLine()) != null && i <= numberOfLines) {
                    String lvl = rank.split(",")[0];
                    String login = rank.split(",")[1];
                    String PlayerName = rank.split(",")[2];
                    String points = rank.split(",")[3];

                    PlayerLoginList[i] = login;
                    PlayerNameList[i] = PlayerName;
                    PlayerPointsList[i] = Integer.valueOf(points);
                    i++;
                }

                //algoritimo bubble sort ordenar arrays 5 primeiros colocados jogador e pontuacao
                for (int a = 0; a <= numberOfLines; a++) {
                    for (int b = 1; b <= (numberOfLines - 1); b++) {
                        if (PlayerPointsList[b] == null) {
                            PlayerPointsList[b] = 0;
                        }
                        if (PlayerPointsList[b - 1] < PlayerPointsList[b]) {
                            int temp = PlayerPointsList[b - 1];
                            PlayerPointsList[b - 1] = PlayerPointsList[b];
                            PlayerPointsList[b] = temp;

                            String flagName = PlayerNameList[b - 1];
                            PlayerNameList[b - 1] = PlayerNameList[b];
                            PlayerNameList[b] = flagName;

                            String flagLogin = PlayerLoginList[b - 1];
                            PlayerLoginList[b - 1] = PlayerLoginList[b];
                            PlayerLoginList[b] = flagLogin;
                        }
                    }
                }

                // recebe os valores dos arrays de pontuacao e jogadores
                int FirstPlace = PlayerPointsList[0];
                int secondPlace = PlayerPointsList[1];
                int thirdPlace = PlayerPointsList[2];
                int fourthPlace = PlayerPointsList[3];
                int fifthPlace = PlayerPointsList[4];

                String playerFirstName = PlayerNameList[0];
                String playerSecondName = PlayerNameList[1];
                String playerThirdName = PlayerNameList[2];
                String playerFourthName = PlayerNameList[3];
                String playerFifthName = PlayerNameList[4];

                String playerFirstLogin = PlayerLoginList[0];
                String playerSecondLogin = PlayerLoginList[1];
                String playerThirdLogin = PlayerLoginList[2];
                String playerFourthLogin = PlayerLoginList[3];
                String playerFifthLogin = PlayerLoginList[4];

                //cria hash mape dos arrays de jogador e pontos
                HashMap<String, Integer> map = new HashMap<>();
                map.put(playerFirstName, FirstPlace);
                map.put(playerSecondName, secondPlace);
                map.put(playerThirdName, thirdPlace);
                map.put(playerFourthName, fourthPlace);
                map.put(playerFifthName, fifthPlace);

                Stream<Map.Entry<String, Integer>> sorteio = map.entrySet().stream().sorted(Map.Entry.comparingByValue());
                sorteio.forEach(System.out::println);

                //imprime jogado pontuacao
                firstPlacePoints.setText(String.valueOf(FirstPlace));
                secondPlacePoints.setText(String.valueOf(secondPlace));
                thirdPlacePoints.setText(String.valueOf(thirdPlace));
                fourthPlacePoints.setText(String.valueOf(fourthPlace));
                fifthPlacePoints.setText(String.valueOf(fifthPlace));

                //imprime nome jogador posicao
                firstPlaceName.setText(playerFirstName);
                secondPlaceName.setText(playerSecondName);
                thirdPlaceName.setText(playerThirdName);
                fourthPlaceName.setText(playerFourthName);
                fifthPlaceName.setText(playerFifthName);

                //imprime jogador login(email)
                emailFirstPlace.setText(playerFirstLogin);
                emailSecondPlace.setText(playerSecondLogin);
                emailThirdPlace.setText(playerThirdLogin);
                emailFourthPlace.setText(playerFourthLogin);
                emailFifthPlace.setText(playerFifthLogin);

                // }
            } catch (IOException ex) {
                System.out.println("existe");
            }

        } else {
            try {
                rank1.createNewFile();
                rank2.createNewFile();
                ranking();
            } catch (IOException ex) {
                System.out.println("nao existe");
            }
        }
    }

    private void initComponents() {

        JPanel jPanel1 = new JPanel();
        totalQuestions = new JLabel();
        JLabel gameCFOP = new JLabel();
        JLabel iconAchievements = new JLabel();
        JLabel titlePoints = new JLabel();
        emailFirstPlace = new JLabel();
        emailSecondPlace = new JLabel();
        emailThirdPlace = new JLabel();
        emailFourthPlace = new JLabel();
        emailFifthPlace = new JLabel();
        CorrectPoints = new JLabel();
        WrongPoints = new JLabel();
        star1 = new JLabel();
        star2 = new JLabel();
        star3 = new JLabel();
        star4 = new JLabel();
        star5 = new JLabel();
        star6 = new JLabel();
        totalPoints = new JLabel();
        QuestionsTitle = new JLabel();
        JLabel CorrectTitle = new JLabel();
        JLabel WrongTitle = new JLabel();
        totalPointsBackground = new JLabel();
        btnNextLvlAchievements = new JLabel();
        JLabel btnReturn = new JLabel();
        firstPlaceName = new JLabel();
        JLabel backgroundPoints = new JLabel();
        JLabel madeText = new JLabel();
        JLabel firstPlace = new JLabel();
        firstPlacePoints = new JLabel();
        JLabel trophy = new JLabel();
        secondPlacePoints = new JLabel();
        thirdPlacePoints = new JLabel();
        fifthPlacePoints = new JLabel();
        fourthPlacePoints = new JLabel();
        secondPlaceName = new JLabel();
        thirdPlaceName = new JLabel();
        fourthPlaceName = new JLabel();
        fifthPlaceName = new JLabel();
        JLabel fourthPlace = new JLabel();
        JLabel RankingTitle = new JLabel();
        JLabel fifthPlace = new JLabel();
        JLabel secondPlace = new JLabel();
        JLabel thirdPlace = new JLabel();
        JLabel firstPlaceBackground = new JLabel();
        JLabel secondPlaceBackground = new JLabel();
        JLabel thirdPlaceBackground = new JLabel();
        JLabel fourthPlaceBackground = new JLabel();
        JLabel fifthPlaceBackground = new JLabel();
        JLabel rankingBackground = new JLabel();
        JLabel background = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setLayout(null);

        totalQuestions.setFont(new java.awt.Font("Tahoma", 1, 38)); // NOI18N
        totalQuestions.setForeground(new java.awt.Color(221, 87, 144));
        jPanel1.add(totalQuestions);
        totalQuestions.setBounds(310, 430, 60, 50);

        gameCFOP.setIcon(new ImageIcon(getImage("/resources/title_left.png"))); // NOI18N
        jPanel1.add(gameCFOP);
        gameCFOP.setBounds(20, 20, 216, 34);

        iconAchievements.setIcon(new ImageIcon(getImage("/resources/conquistasIcone.png"))); // NOI18N
        jPanel1.add(iconAchievements);
        iconAchievements.setBounds(900, 120, 80, 90);

        titlePoints.setBackground(new java.awt.Color(51, 51, 255));
        titlePoints.setFont(new java.awt.Font("Ink Free", 1, 36)); // NOI18N
        titlePoints.setForeground(new java.awt.Color(221, 87, 144));
        titlePoints.setHorizontalAlignment(SwingConstants.CENTER);
        titlePoints.setText("PONTOS");
        titlePoints.setToolTipText("");
        jPanel1.add(titlePoints);
        titlePoints.setBounds(230, 190, 190, 50);
        titlePoints.getAccessibleContext().setAccessibleName("RESULTADO");

        emailFirstPlace.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailFirstPlace.setForeground(new java.awt.Color(255, 193, 7));
        emailFirstPlace.setText("JLabel");
        jPanel1.add(emailFirstPlace);
        emailFirstPlace.setBounds(790, 280, 200, 40);

        emailSecondPlace.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailSecondPlace.setForeground(new java.awt.Color(255, 195, 7));
        emailSecondPlace.setText("jLabel4");
        jPanel1.add(emailSecondPlace);
        emailSecondPlace.setBounds(810, 334, 180, 40);

        emailThirdPlace.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailThirdPlace.setForeground(new java.awt.Color(255, 195, 7));
        emailThirdPlace.setText("jLabel4");
        jPanel1.add(emailThirdPlace);
        emailThirdPlace.setBounds(830, 400, 130, 20);

        emailFourthPlace.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailFourthPlace.setForeground(new java.awt.Color(255, 195, 7));
        emailFourthPlace.setText("jLabel4");
        jPanel1.add(emailFourthPlace);
        emailFourthPlace.setBounds(850, 447, 140, 30);

        emailFifthPlace.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailFifthPlace.setForeground(new java.awt.Color(255, 195, 7));
        emailFifthPlace.setText("jLabel4");
        jPanel1.add(emailFifthPlace);
        emailFifthPlace.setBounds(870, 504, 160, 30);

        CorrectPoints.setFont(new java.awt.Font("Krungthep", 1, 36)); // NOI18N
        CorrectPoints.setForeground(new java.awt.Color(51, 153, 0));
        CorrectPoints.setText("Iniciou o jogo uma vez");
        jPanel1.add(CorrectPoints);
        CorrectPoints.setBounds(150, 330, 50, 40);

        WrongPoints.setFont(new java.awt.Font("Krungthep", 1, 36)); // NOI18N
        WrongPoints.setForeground(new java.awt.Color(255, 0, 0));
        WrongPoints.setText("Acertou uma pergunta rápido");
        jPanel1.add(WrongPoints);
        WrongPoints.setBounds(380, 330, 40, 40);

        star1.setIcon(new ImageIcon(getImage("/resources/starGray.png"))); // NOI18N
        jPanel1.add(star1);
        star1.setBounds(360, 150, 46, 44);

        star2.setIcon(new ImageIcon(getImage("/resources/starGray.png"))); // NOI18N
        jPanel1.add(star2);
        star2.setBounds(240, 150, 46, 44);

        star3.setIcon(new ImageIcon(getImage("/resources/starGray.png"))); // NOI18N
        jPanel1.add(star3);
        star3.setBounds(300, 140, 46, 44);

        star4.setIcon(new ImageIcon(getImage("/resources/starGray.png"))); // NOI18N
        jPanel1.add(star4);
        star4.setBounds(310, 430, 46, 44);

        star5.setIcon(new ImageIcon(getImage("/resources/starGray.png"))); // NOI18N
        jPanel1.add(star5);
        star5.setBounds(140, 330, 46, 44);

        star6.setIcon(new ImageIcon(getImage("/resources/starGray.png"))); // NOI18N
        jPanel1.add(star6);
        star6.setBounds(370, 330, 46, 44);

        totalPoints.setFont(new java.awt.Font("Krungthep", 1, 48)); // NOI18N
        totalPoints.setForeground(new java.awt.Color(51, 204, 0));
        totalPoints.setText("Acertou 5 perguntas seguidas");
        jPanel1.add(totalPoints);
        totalPoints.setBounds(290, 230, 110, 80);

        QuestionsTitle.setFont(new java.awt.Font("Krungthep", 1, 24)); // NOI18N
        QuestionsTitle.setForeground(new java.awt.Color(255, 193, 7));
        QuestionsTitle.setText("Total de Perguntas");
        QuestionsTitle.setToolTipText("");
        jPanel1.add(QuestionsTitle);
        QuestionsTitle.setBounds(230, 400, 230, 40);

        CorrectTitle.setFont(new java.awt.Font("Krungthep", 1, 24)); // NOI18N
        CorrectTitle.setForeground(new java.awt.Color(255, 193, 7));
        CorrectTitle.setText("Acertos");
        CorrectTitle.setToolTipText("");
        jPanel1.add(CorrectTitle);
        CorrectTitle.setBounds(210, 330, 220, 40);

        WrongTitle.setFont(new java.awt.Font("Krungthep", 1, 24)); // NOI18N
        WrongTitle.setForeground(new java.awt.Color(255, 193, 7));
        WrongTitle.setText("Erros");
        jPanel1.add(WrongTitle);
        WrongTitle.setBounds(440, 330, 100, 40);

        totalPointsBackground.setFont(new java.awt.Font("Krungthep", 0, 14)); // NOI18N
        totalPointsBackground.setIcon(new ImageIcon(getImage("/resources/fundoLevel.png"))); // NOI18N
        totalPointsBackground.setText("Total de pontos:");
        jPanel1.add(totalPointsBackground);
        totalPointsBackground.setBounds(240, 240, 170, 60);

        btnNextLvlAchievements.setFont(new java.awt.Font("Krungthep", 1, 24)); // NOI18N
        btnNextLvlAchievements.setForeground(new java.awt.Color(255, 193, 7));
        btnNextLvlAchievements.setIcon(new ImageIcon(getImage("/resources/botaoProxFase.png"))); // NOI18N
        btnNextLvlAchievements.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextLvlAchievementsMouseClicked(evt);
            }
        });
        jPanel1.add(btnNextLvlAchievements);
        btnNextLvlAchievements.setBounds(800, 580, 290, 50);

        btnReturn.setIcon(new ImageIcon(getImage("/resources/botaoVoltar.png"))); // NOI18N
        btnReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReturnMouseClicked(evt);
            }
        });
        jPanel1.add(btnReturn);
        btnReturn.setBounds(220, 580, 210, 45);

        firstPlaceName.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        firstPlaceName.setForeground(new java.awt.Color(0, 204, 102));
        firstPlaceName.setText("teste");
        jPanel1.add(firstPlaceName);
        firstPlaceName.setBounds(780, 260, 260, 40);

        backgroundPoints.setIcon(new ImageIcon(getImage("/resources/backgroundConquistas.png"))); // NOI18N
        jPanel1.add(backgroundPoints);
        backgroundPoints.setBounds(110, 100, 440, 570);

        madeText.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        madeText.setForeground(new java.awt.Color(255, 255, 255));
        madeText.setText("FEITO POR CESAR VERGARA");
        jPanel1.add(madeText);
        madeText.setBounds(20, 730, 260, 30);

        firstPlace.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        firstPlace.setForeground(new java.awt.Color(255, 255, 255));
        firstPlace.setText("1");
        jPanel1.add(firstPlace);
        firstPlace.setBounds(730, 260, 50, 60);

        firstPlacePoints.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        firstPlacePoints.setForeground(new java.awt.Color(255, 255, 255));
        firstPlacePoints.setEnabled(false);
        jPanel1.add(firstPlacePoints);
        firstPlacePoints.setBounds(1090, 270, 70, 40);

        trophy.setIcon(new ImageIcon(getImage("/resources/trofeu.png"))); // NOI18N
        jPanel1.add(trophy);
        trophy.setBounds(1040, 270, 46, 40);

        secondPlacePoints.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        secondPlacePoints.setForeground(new java.awt.Color(255, 255, 255));
        secondPlacePoints.setEnabled(false);
        jPanel1.add(secondPlacePoints);
        secondPlacePoints.setBounds(1090, 320, 70, 40);

        thirdPlacePoints.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        thirdPlacePoints.setForeground(new java.awt.Color(255, 255, 255));
        thirdPlacePoints.setEnabled(false);
        jPanel1.add(thirdPlacePoints);
        thirdPlacePoints.setBounds(1090, 380, 70, 40);

        fifthPlacePoints.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        fifthPlacePoints.setForeground(new java.awt.Color(255, 255, 255));
        fifthPlacePoints.setEnabled(false);
        jPanel1.add(fifthPlacePoints);
        fifthPlacePoints.setBounds(1090, 490, 70, 40);

        fourthPlacePoints.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        fourthPlacePoints.setForeground(new java.awt.Color(255, 255, 255));
        fourthPlacePoints.setEnabled(false);
        jPanel1.add(fourthPlacePoints);
        fourthPlacePoints.setBounds(1090, 430, 70, 40);

        secondPlaceName.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        secondPlaceName.setForeground(new java.awt.Color(0, 204, 102));
        secondPlaceName.setText("teste");
        secondPlaceName.setToolTipText("");
        jPanel1.add(secondPlaceName);
        secondPlaceName.setBounds(800, 320, 290, 30);

        thirdPlaceName.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        thirdPlaceName.setForeground(new java.awt.Color(0, 204, 102));
        thirdPlaceName.setText("teste");
        jPanel1.add(thirdPlaceName);
        thirdPlaceName.setBounds(820, 375, 270, 30);

        fourthPlaceName.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        fourthPlaceName.setForeground(new java.awt.Color(0, 204, 102));
        fourthPlaceName.setText("teste");
        jPanel1.add(fourthPlaceName);
        fourthPlaceName.setBounds(840, 430, 250, 30);

        fifthPlaceName.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        fifthPlaceName.setForeground(new java.awt.Color(0, 204, 102));
        fifthPlaceName.setText("teste");
        jPanel1.add(fifthPlaceName);
        fifthPlaceName.setBounds(860, 490, 230, 25);

        fourthPlace.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        fourthPlace.setForeground(new java.awt.Color(255, 255, 255));
        fourthPlace.setText("4");
        jPanel1.add(fourthPlace);
        fourthPlace.setBounds(790, 430, 30, 44);

        RankingTitle.setFont(new java.awt.Font("Ink Free", 1, 36)); // NOI18N
        RankingTitle.setText("Ranking");
        RankingTitle.setToolTipText("RANKING");
        jPanel1.add(RankingTitle);
        RankingTitle.setBounds(880, 210, 150, 40);

        fifthPlace.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        fifthPlace.setForeground(new java.awt.Color(255, 255, 255));
        fifthPlace.setText("5");
        jPanel1.add(fifthPlace);
        fifthPlace.setBounds(810, 490, 30, 40);

        secondPlace.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        secondPlace.setForeground(new java.awt.Color(255, 255, 255));
        secondPlace.setText("2");
        jPanel1.add(secondPlace);
        secondPlace.setBounds(750, 330, 23, 30);

        thirdPlace.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        thirdPlace.setForeground(new java.awt.Color(255, 255, 255));
        thirdPlace.setText("3");
        jPanel1.add(thirdPlace);
        thirdPlace.setBounds(770, 380, 30, 30);

        firstPlaceBackground.setIcon(new ImageIcon(getImage("/resources/tituloPergunta.png"))); // NOI18N
        firstPlaceBackground.setText("jLabel3");
        jPanel1.add(firstPlaceBackground);
        firstPlaceBackground.setBounds(720, 260, 440, 60);

        secondPlaceBackground.setIcon(new ImageIcon(getImage("/resources/tituloPergunta.png"))); // NOI18N
        jPanel1.add(secondPlaceBackground);
        secondPlaceBackground.setBounds(740, 310, 420, 70);

        thirdPlaceBackground.setIcon(new ImageIcon(getImage("/resources/tituloPergunta.png"))); // NOI18N
        jPanel1.add(thirdPlaceBackground);
        thirdPlaceBackground.setBounds(760, 370, 400, 60);

        fourthPlaceBackground.setIcon(new ImageIcon(getImage("/resources/tituloPergunta.png"))); // NOI18N
        jPanel1.add(fourthPlaceBackground);
        fourthPlaceBackground.setBounds(780, 430, 380, 50);

        fifthPlaceBackground.setIcon(new ImageIcon(getImage("/resources/tituloPergunta.png"))); // NOI18N
        jPanel1.add(fifthPlaceBackground);
        fifthPlaceBackground.setBounds(800, 480, 360, 60);

        rankingBackground.setIcon(new ImageIcon(getImage("/resources/backgroundConquistas.png"))); // NOI18N
        rankingBackground.setText("jLabel2");
        jPanel1.add(rankingBackground);
        rankingBackground.setBounds(720, 100, 440, 570);

        background.setIcon(new ImageIcon(getImage("/resources/fundo_sistema.jpg"))); // NOI18N
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
    }// </editor-fold>//GEN-END:initComponents

    private void btnReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoltarMouseClicked
        Screen.getScreen().setScreen("Home");
    }//GEN-LAST:event_btnVoltarMouseClicked

    private void btnNextLvlAchievementsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProxFaseConquistasMouseClicked
        // TODO add your handling code here:
        if (game.getLvl() == 1) {
            Screen.getScreen().setScreen("GameInstructionNote");
        } else {
            Screen.getScreen().setScreen("Achievements");
        }
    }//GEN-LAST:event_btnProxFaseConquistasMouseClicked
    public void fillIcon() {
        ImageIcon img = new ImageIcon(getImage("/resources/botaoVerConquistas.png"));
        btnNextLvlAchievements.setIcon(img);

    }
//preenche os campos de acertos, erros, pontuacao

    private void fillAchievements() {
        totalQuestions.setText(String.valueOf(game.getQuestions() - 1));
        WrongPoints.setText(String.valueOf(game.getWrongAnswers()));
        CorrectPoints.setText(String.valueOf(game.getRightAnswers()));
        totalPoints.setText(String.valueOf(game.getScore()));
        ImageIcon estrela = new ImageIcon(getImage("/resources/starIcon.png"));
        if (account[TROPHY1].equals("1")) {
            star1.setIcon(estrela);
        }
        if (account[TROPHY2].equals("1")) {
            star2.setIcon(estrela);
        }
        if (account[TROPHY3].equals("1")) {
            star3.setIcon(estrela);
        }
        if (account[TROPHY4].equals("1")) {
            star4.setIcon(estrela);
        }
        if (account[TROPHY5].equals("1")) {
            star5.setIcon(estrela);
        }
        if (account[TROPHY6].equals("1")) {
            star6.setIcon(estrela);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    JLabel btnNextLvlAchievements;
    JLabel emailFirstPlace;
    JLabel emailFourthPlace;
    JLabel emailFifthPlace;
    JLabel emailSecondPlace;
    JLabel emailThirdPlace;
    JLabel totalPointsBackground;
    JLabel firstPlaceName;
    JLabel fourthPlaceName;
    JLabel fifthPlaceName;
    JLabel secondPlaceName;
    JLabel thirdPlaceName;
    JLabel CorrectPoints;
    JLabel WrongPoints;
    JLabel firstPlacePoints;
    JLabel fourthPlacePoints;
    JLabel fifthPlacePoints;
    JLabel secondPlacePoints;
    JLabel thirdPlacePoints;
    JLabel star1;
    JLabel star2;
    JLabel star3;
    JLabel star4;
    JLabel star5;
    JLabel star6;
    JLabel QuestionsTitle;
    JLabel totalQuestions;
    JLabel totalPoints;
    // End of variables declaration//GEN-END:variables

}
