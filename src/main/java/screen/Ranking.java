package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static utils.ImageUtils.getImage;

public class Ranking {

    JLabel emailFirstPlace = new JLabel();
    JLabel emailSecondPlace = new JLabel();
    JLabel emailThirdPlace = new JLabel();
    JLabel emailFourthPlace = new JLabel();
    JLabel emailFifthPlace = new JLabel();

    JLabel firstPlaceName = new JLabel();
    JLabel secondPlaceName = new JLabel();
    JLabel thirdPlaceName = new JLabel();
    JLabel fourthPlaceName = new JLabel();
    JLabel fifthPlaceName = new JLabel();

    JLabel firstPlacePoints = new JLabel();
    JLabel secondPlacePoints = new JLabel();
    JLabel thirdPlacePoints = new JLabel();
    JLabel fourthPlacePoints = new JLabel();
    JLabel fifthPlacePoints = new JLabel();

    JLabel emailFirstPlace2 = new JLabel();
    JLabel emailSecondPlace2 = new JLabel();
    JLabel emailThirdPlace2 = new JLabel();
    JLabel emailFourthPlace2 = new JLabel();
    JLabel emailFifthPlace2 = new JLabel();

    JLabel firstPlaceName2 = new JLabel();
    JLabel secondPlaceName2 = new JLabel();
    JLabel thirdPlaceName2 = new JLabel();
    JLabel fourthPlaceName2 = new JLabel();
    JLabel fifthPlaceName2 = new JLabel();

    JLabel firstPlacePoints2 = new JLabel();
    JLabel secondPlacePoints2 = new JLabel();
    JLabel thirdPlacePoints2 = new JLabel();
    JLabel fourthPlacePoints2 = new JLabel();
    JLabel fifthPlacePoints2 = new JLabel();




    private String fileName;

    public Ranking() {}

    public void fillFields() {
            ranking1();
            ranking2();

    }

    public void ranking1() {
        File rank1 = new File("rankingFase1.txt");
        if (rank1.exists()) {
            try {
                this.fileName = "rankingFase1.txt";

                //ler o arquivo ranking.txt e verificar maior pontuacao                                                
                FileReader lerR = new FileReader(fileName);
                BufferedReader lerL = new BufferedReader(lerR);
                String ver = lerL.readLine();
                if (ver == null) {
                    ver = 0 + "," + "teste" + "," + "teste" + "," + 0;
                }
                String valEmail = ver.split(",")[1];
                String valPonto = ver.split(",")[2];


                FileWriter writeFile = new FileWriter(fileName, true);
                BufferedWriter writeLine = new BufferedWriter(writeFile);


                //ler o arquivo ranking
                //FileReader lerArqRanking = new FileReader("ranking.txt");
                FileReader readFileRanking = new FileReader(fileName);
                BufferedReader readRanking = new BufferedReader(readFileRanking);
                String rank;//  = lerRanking.readLine();

                //retorna total linhas arquivo ranking                
                LineNumberReader lineCounter = new LineNumberReader(new FileReader(fileName));
                lineCounter.skip(Long.MAX_VALUE);
                int numberOfLines = lineCounter.getLineNumber();

                if(numberOfLines < 5) {
                    numberOfLines = 5;
                }

                // array  pontuacao, jogador e login(email) com tamanho definido pelo total de linhas do arquivo
                Integer[] PlayerPointsList = new Integer[numberOfLines];
                String[] PlayerNameList = new String[numberOfLines];
                String[] PlayerLoginList = new String[numberOfLines];

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
                ranking1();
            } catch (IOException ex) {
                System.out.println("nao existe");
            }
        }
    }

    public void ranking2() {
        File rank2 = new File("rankingFase2.txt");

        if (rank2.exists()) {
            try {
                this.fileName = "rankingFase2.txt";

                //ler o arquivo ranking.txt e verificar maior pontuacao
                FileReader lerR = new FileReader(fileName);
                BufferedReader lerL = new BufferedReader(lerR);


                FileWriter writeFile = new FileWriter(fileName, true);


                //ler o arquivo ranking
                //FileReader lerArqRanking = new FileReader("ranking.txt");
                FileReader readFileRanking = new FileReader(fileName);
                BufferedReader readRanking = new BufferedReader(readFileRanking);
                String rank;//  = lerRanking.readLine();

                //retorna total linhas arquivo ranking
                LineNumberReader lineCounter = new LineNumberReader(new FileReader(fileName));
                lineCounter.skip(Long.MAX_VALUE);
                int numberOfLines = lineCounter.getLineNumber();

                if(numberOfLines < 5) {
                    numberOfLines = 5;
                }

                // array  pontuacao, jogador e login(email) com tamanho definido pelo total de linhas do arquivo
                Integer[] PlayerPointsList = new Integer[numberOfLines];
                String[] PlayerNameList = new String[numberOfLines];
                String[] PlayerLoginList = new String[numberOfLines];

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
                firstPlacePoints2.setText(String.valueOf(FirstPlace));
                secondPlacePoints2.setText(String.valueOf(secondPlace));
                thirdPlacePoints2.setText(String.valueOf(thirdPlace));
                fourthPlacePoints2.setText(String.valueOf(fourthPlace));
                fifthPlacePoints2.setText(String.valueOf(fifthPlace));

                //imprime nome jogador posicao
                firstPlaceName2.setText(playerFirstName);
                secondPlaceName2.setText(playerSecondName);
                thirdPlaceName2.setText(playerThirdName);
                fourthPlaceName2.setText(playerFourthName);
                fifthPlaceName2.setText(playerFifthName);

                //imprime jogador login(email)
                emailFirstPlace2.setText(playerFirstLogin);
                emailSecondPlace2.setText(playerSecondLogin);
                emailThirdPlace2.setText(playerThirdLogin);
                emailFourthPlace2.setText(playerFourthLogin);
                emailFifthPlace2.setText(playerFifthLogin);

                // }
            } catch (IOException ex) {
                System.out.println("existe");
            }

        } else {
            try {
                rank2.createNewFile();
                ranking2();
            } catch (IOException ex) {
                System.out.println("nao existe");
            }
        }
    }

    public ArrayList<JComponent> getAllComponents() {
        ArrayList<JComponent> listOfComponents = new ArrayList<JComponent>();

        JLabel trophy2 = new JLabel();
        JLabel trophy1 = new JLabel();

        emailFirstPlace2 = new JLabel();
        emailSecondPlace2 = new JLabel();
        emailThirdPlace2 = new JLabel();
        emailFourthPlace2 = new JLabel();
        emailFifthPlace2 = new JLabel();

        firstPlacePoints2 = new JLabel();
        secondPlacePoints2 = new JLabel();
        thirdPlacePoints2 = new JLabel();
        fourthPlacePoints2 = new JLabel();
        fifthPlacePoints2 = new JLabel();

        firstPlaceName2 = new JLabel();
        secondPlaceName2 = new JLabel();
        thirdPlaceName2 = new JLabel();
        fourthPlaceName2 = new JLabel();
        fifthPlaceName2 = new JLabel();

        JLabel firstPlace = new JLabel();
        JLabel secondPlace = new JLabel();
        JLabel thirdPlace = new JLabel();
        JLabel fourthPlace = new JLabel();
        JLabel fifthPlace = new JLabel();

        JLabel firstPlace2 = new JLabel();
        JLabel secondPlace2 = new JLabel();
        JLabel thirdPlace2 = new JLabel();
        JLabel fourthPlace2 = new JLabel();
        JLabel fifthPlace2 = new JLabel();

        JLabel firstPlaceBackground = new JLabel();
        JLabel secondPlaceBackground = new JLabel();
        JLabel thirdPlaceBackground = new JLabel();
        JLabel fourthPlaceBackground = new JLabel();
        JLabel fifthPlaceBackground = new JLabel();


        JLabel firstPlaceBackground2 = new JLabel();
        JLabel secondPlaceBackground2 = new JLabel();
        JLabel thirdPlaceBackground2 = new JLabel();
        JLabel fourthPlaceBackground2 = new JLabel();
        JLabel fifthPlaceBackground2 = new JLabel();

        JLabel RankingTitle2 = new JLabel();
        JLabel RankingTitle1 = new JLabel();

        JLabel btnReturn = new JLabel();
        JLabel backgroundPoints = new JLabel();
        JLabel madeText = new JLabel();

        JLabel rankingBackground = new JLabel();
        JLabel background = new JLabel();

        trophy2.setIcon(new ImageIcon(getImage("trofeu.png")));
        listOfComponents.add(trophy2);
        trophy2.setBounds(1040, 270, 46, 40);

        trophy1.setIcon(new ImageIcon(getImage("trofeu.png")));
        listOfComponents.add(trophy1);
        trophy1.setBounds(430, 270, 46, 40);


        btnReturn.setIcon(new ImageIcon(getImage("botaoVoltar.png")));
        btnReturn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnReturnMouseClicked(evt);
            }
        });
        listOfComponents.add(btnReturn);
        btnReturn.setBounds(530, 700, 210, 45);

        firstPlace.setFont(new Font("Tahoma", 1, 36));
        firstPlace.setForeground(new Color(255, 255, 255));
        firstPlace.setText("1");
        listOfComponents.add(firstPlace);
        firstPlace.setBounds(120, 260, 50, 60);

        secondPlace.setFont(new Font("Tahoma", 1, 36));
        secondPlace.setForeground(new Color(255, 255, 255));
        secondPlace.setText("2");
        listOfComponents.add(secondPlace);
        secondPlace.setBounds(140, 330, 23, 30);

        thirdPlace.setFont(new Font("Tahoma", 1, 36));
        thirdPlace.setForeground(new Color(255, 255, 255));
        thirdPlace.setText("3");
        listOfComponents.add(thirdPlace);
        thirdPlace.setBounds(160, 380, 30, 30);

        fourthPlace.setFont(new Font("Tahoma", 1, 36));
        fourthPlace.setForeground(new Color(255, 255, 255));
        fourthPlace.setText("4");
        listOfComponents.add(fourthPlace);
        fourthPlace.setBounds(180, 430, 30, 44);

        fifthPlace.setFont(new Font("Tahoma", 1, 36));
        fifthPlace.setForeground(new Color(255, 255, 255));
        fifthPlace.setText("5");
        listOfComponents.add(fifthPlace);
        fifthPlace.setBounds(200, 490, 30, 40);

        emailFirstPlace.setFont(new Font("Tahoma", 0, 14));
        emailFirstPlace.setForeground(new Color(255, 193, 7));
        emailFirstPlace.setText("JLabel");
        listOfComponents.add(emailFirstPlace);
        emailFirstPlace.setBounds(190, 280, 200, 40);

        emailSecondPlace.setFont(new Font("Tahoma", 0, 14));
        emailSecondPlace.setForeground(new Color(255, 195, 7));
        emailSecondPlace.setText("jLabel4");
        listOfComponents.add(emailSecondPlace);
        emailSecondPlace.setBounds(210, 334, 180, 40);

        emailThirdPlace.setFont(new Font("Tahoma", 0, 14));
        emailThirdPlace.setForeground(new Color(255, 195, 7));
        emailThirdPlace.setText("jLabel4");
        listOfComponents.add(emailThirdPlace);
        emailThirdPlace.setBounds(230, 400, 130, 20);

        emailFourthPlace.setFont(new Font("Tahoma", 0, 14));
        emailFourthPlace.setForeground(new Color(255, 195, 7));
        emailFourthPlace.setText("jLabel4");
        listOfComponents.add(emailFourthPlace);
        emailFourthPlace.setBounds(250, 447, 140, 30);

        emailFifthPlace.setFont(new Font("Tahoma", 0, 14));
        emailFifthPlace.setForeground(new Color(255, 195, 7));
        emailFifthPlace.setText("jLabel4");
        listOfComponents.add(emailFifthPlace);
        emailFifthPlace.setBounds(270, 504, 160, 30);

        firstPlaceName.setFont(new Font("Tahoma", 0, 20));
        firstPlaceName.setForeground(new Color(0, 204, 102));
        firstPlaceName.setText("teste");
        listOfComponents.add(firstPlaceName);
        firstPlaceName.setBounds(180, 260, 260, 40);

        secondPlaceName.setFont(new Font("Tahoma", 0, 20));
        secondPlaceName.setForeground(new Color(0, 204, 102));
        secondPlaceName.setText("teste");
        secondPlaceName.setToolTipText("");
        listOfComponents.add(secondPlaceName);
        secondPlaceName.setBounds(200, 320, 290, 30);

        thirdPlaceName.setFont(new Font("Tahoma", 0, 20));
        thirdPlaceName.setForeground(new Color(0, 204, 102));
        thirdPlaceName.setText("teste");
        listOfComponents.add(thirdPlaceName);
        thirdPlaceName.setBounds(220, 375, 270, 30);

        fourthPlaceName.setFont(new Font("Tahoma", 0, 20));
        fourthPlaceName.setForeground(new Color(0, 204, 102));
        fourthPlaceName.setText("teste");
        listOfComponents.add(fourthPlaceName);
        fourthPlaceName.setBounds(240, 430, 250, 30);

        fifthPlaceName.setFont(new Font("Tahoma", 0, 20));
        fifthPlaceName.setForeground(new Color(0, 204, 102));
        fifthPlaceName.setText("teste");
        listOfComponents.add(fifthPlaceName);
        fifthPlaceName.setBounds(260, 490, 230, 25);

        emailFirstPlace2.setFont(new Font("Tahoma", 0, 14));
        emailFirstPlace2.setForeground(new Color(255, 193, 7));
        emailFirstPlace2.setText("JLabel");
        listOfComponents.add(emailFirstPlace2);
        emailFirstPlace2.setBounds(790, 280, 200, 40);

        emailSecondPlace2.setFont(new Font("Tahoma", 0, 14));
        emailSecondPlace2.setForeground(new Color(255, 195, 7));
        emailSecondPlace2.setText("jLabel4");
        listOfComponents.add(emailSecondPlace2);
        emailSecondPlace2.setBounds(810, 334, 180, 40);

        emailThirdPlace2.setFont(new Font("Tahoma", 0, 14));
        emailThirdPlace2.setForeground(new Color(255, 195, 7));
        emailThirdPlace2.setText("jLabel4");
        listOfComponents.add(emailThirdPlace2);
        emailThirdPlace2.setBounds(830, 400, 130, 20);

        emailFourthPlace2.setFont(new Font("Tahoma", 0, 14));
        emailFourthPlace2.setForeground(new Color(255, 195, 7));
        emailFourthPlace2.setText("jLabel4");
        listOfComponents.add(emailFourthPlace2);
        emailFourthPlace2.setBounds(850, 447, 140, 30);

        emailFifthPlace2.setFont(new Font("Tahoma", 0, 14));
        emailFifthPlace2.setForeground(new Color(255, 195, 7));
        emailFifthPlace2.setText("jLabel4");
        listOfComponents.add(emailFifthPlace2);
        emailFifthPlace2.setBounds(870, 504, 160, 30);

        firstPlaceName2.setFont(new Font("Tahoma", 0, 20));
        firstPlaceName2.setForeground(new Color(0, 204, 102));
        firstPlaceName2.setText("teste");
        listOfComponents.add(firstPlaceName2);
        firstPlaceName2.setBounds(780, 260, 260, 40);

        secondPlaceName2.setFont(new Font("Tahoma", 0, 20));
        secondPlaceName2.setForeground(new Color(0, 204, 102));
        secondPlaceName2.setText("teste");
        secondPlaceName2.setToolTipText("");
        listOfComponents.add(secondPlaceName2);
        secondPlaceName2.setBounds(800, 320, 290, 30);

        thirdPlaceName2.setFont(new Font("Tahoma", 0, 20));
        thirdPlaceName2.setForeground(new Color(0, 204, 102));
        thirdPlaceName2.setText("teste");
        listOfComponents.add(thirdPlaceName2);
        thirdPlaceName2.setBounds(820, 375, 270, 30);

        fourthPlaceName2.setFont(new Font("Tahoma", 0, 20));
        fourthPlaceName2.setForeground(new Color(0, 204, 102));
        fourthPlaceName2.setText("teste");
        listOfComponents.add(fourthPlaceName2);
        fourthPlaceName2.setBounds(840, 430, 250, 30);

        fifthPlaceName2.setFont(new Font("Tahoma", 0, 20));
        fifthPlaceName2.setForeground(new Color(0, 204, 102));
        fifthPlaceName2.setText("teste");
        listOfComponents.add(fifthPlaceName2);
        fifthPlaceName2.setBounds(860, 490, 230, 25);

        firstPlacePoints.setFont(new Font("Tahoma", 0, 28));
        firstPlacePoints.setForeground(new Color(255, 255, 255));
        firstPlacePoints.setEnabled(false);
        listOfComponents.add(firstPlacePoints);
        firstPlacePoints.setBounds(480, 270, 70, 40);

        secondPlacePoints.setFont(new Font("Tahoma", 0, 28));
        secondPlacePoints.setForeground(new Color(255, 255, 255));
        secondPlacePoints.setEnabled(false);
        listOfComponents.add(secondPlacePoints);
        secondPlacePoints.setBounds(480, 320, 70, 40);

        thirdPlacePoints.setFont(new Font("Tahoma", 0, 28));
        thirdPlacePoints.setForeground(new Color(255, 255, 255));
        thirdPlacePoints.setEnabled(false);
        listOfComponents.add(thirdPlacePoints);
        thirdPlacePoints.setBounds(480, 380, 70, 40);

        fourthPlacePoints.setFont(new Font("Tahoma", 0, 28));
        fourthPlacePoints.setForeground(new Color(255, 255, 255));
        fourthPlacePoints.setEnabled(false);
        listOfComponents.add(fourthPlacePoints);
        fourthPlacePoints.setBounds(480, 430, 70, 40);

        fifthPlacePoints.setFont(new Font("Tahoma", 0, 28));
        fifthPlacePoints.setForeground(new Color(255, 255, 255));
        fifthPlacePoints.setEnabled(false);
        listOfComponents.add(fifthPlacePoints);
        fifthPlacePoints.setBounds(480, 490, 70, 40);

        firstPlacePoints2.setFont(new Font("Tahoma", 0, 28));
        firstPlacePoints2.setForeground(new Color(255, 255, 255));
        firstPlacePoints2.setEnabled(false);
        listOfComponents.add(firstPlacePoints2);
        firstPlacePoints2.setBounds(1090, 270, 70, 40);

        secondPlacePoints2.setFont(new Font("Tahoma", 0, 28));
        secondPlacePoints2.setForeground(new Color(255, 255, 255));
        secondPlacePoints2.setEnabled(false);
        listOfComponents.add(secondPlacePoints2);
        secondPlacePoints2.setBounds(1090, 320, 70, 40);

        thirdPlacePoints2.setFont(new Font("Tahoma", 0, 28));
        thirdPlacePoints2.setForeground(new Color(255, 255, 255));
        thirdPlacePoints2.setEnabled(false);
        listOfComponents.add(thirdPlacePoints2);
        thirdPlacePoints2.setBounds(1090, 380, 70, 40);

        fourthPlacePoints2.setFont(new Font("Tahoma", 0, 28));
        fourthPlacePoints2.setForeground(new Color(255, 255, 255));
        fourthPlacePoints2.setEnabled(false);
        listOfComponents.add(fourthPlacePoints2);
        fourthPlacePoints2.setBounds(1090, 430, 70, 40);

        fifthPlacePoints2.setFont(new Font("Tahoma", 0, 28));
        fifthPlacePoints2.setForeground(new Color(255, 255, 255));
        fifthPlacePoints2.setEnabled(false);
        listOfComponents.add(fifthPlacePoints2);
        fifthPlacePoints2.setBounds(1090, 490, 70, 40);

        RankingTitle1.setFont(new Font("Ink Free", 1, 36));
        RankingTitle1.setText("Ranking Nível 1");
        RankingTitle1.setToolTipText("RANKING");
        listOfComponents.add(RankingTitle1);
        RankingTitle1.setBounds(190, 210, 350, 40);

        firstPlaceBackground.setIcon(new ImageIcon(getImage("tituloPergunta.png")));
        listOfComponents.add(firstPlaceBackground);
        firstPlaceBackground.setBounds(110, 260, 440, 60);

        secondPlaceBackground.setIcon(new ImageIcon(getImage("tituloPergunta.png")));
        listOfComponents.add(secondPlaceBackground);
        secondPlaceBackground.setBounds(130, 310, 420, 70);

        thirdPlaceBackground.setIcon(new ImageIcon(getImage("tituloPergunta.png")));
        listOfComponents.add(thirdPlaceBackground);
        thirdPlaceBackground.setBounds(150, 370, 400, 60);

        fourthPlaceBackground.setIcon(new ImageIcon(getImage("tituloPergunta.png")));
        listOfComponents.add(fourthPlaceBackground);
        fourthPlaceBackground.setBounds(170, 430, 380, 50);

        fifthPlaceBackground.setIcon(new ImageIcon(getImage("tituloPergunta.png")));
        listOfComponents.add(fifthPlaceBackground);
        fifthPlaceBackground.setBounds(190, 480, 360, 60);

        backgroundPoints.setIcon(new ImageIcon(getImage("backgroundConquistas.png")));
        listOfComponents.add(backgroundPoints);
        backgroundPoints.setBounds(110, 100, 440, 570);

        madeText.setFont(new Font("Krungthep", 0, 18));
        madeText.setForeground(new Color(255, 255, 255));
        madeText.setText("FEITO POR CESAR VERGARA");
        listOfComponents.add(madeText);
        madeText.setBounds(20, 730, 260, 30);

        firstPlace2.setFont(new Font("Tahoma", 1, 36));
        firstPlace2.setForeground(new Color(255, 255, 255));
        firstPlace2.setText("1");
        listOfComponents.add(firstPlace2);
        firstPlace2.setBounds(730, 260, 50, 60);

        secondPlace2.setFont(new Font("Tahoma", 1, 36));
        secondPlace2.setForeground(new Color(255, 255, 255));
        secondPlace2.setText("2");
        listOfComponents.add(secondPlace2);
        secondPlace2.setBounds(750, 330, 23, 30);

        thirdPlace2.setFont(new Font("Tahoma", 1, 36));
        thirdPlace2.setForeground(new Color(255, 255, 255));
        thirdPlace2.setText("3");
        listOfComponents.add(thirdPlace2);
        thirdPlace2.setBounds(770, 380, 30, 30);

        fourthPlace2.setFont(new Font("Tahoma", 1, 36));
        fourthPlace2.setForeground(new Color(255, 255, 255));
        fourthPlace2.setText("4");
        listOfComponents.add(fourthPlace2);
        fourthPlace2.setBounds(790, 430, 30, 44);

        fifthPlace2.setFont(new Font("Tahoma", 1, 36));
        fifthPlace2.setForeground(new Color(255, 255, 255));
        fifthPlace2.setText("5");
        listOfComponents.add(fifthPlace2);
        fifthPlace2.setBounds(810, 490, 30, 40);

        RankingTitle2.setFont(new Font("Ink Free", 1, 36));
        RankingTitle2.setText("Ranking Nível 2");
        RankingTitle2.setToolTipText("RANKING");
        listOfComponents.add(RankingTitle2);
        RankingTitle2.setBounds(800, 210, 350, 40);

        firstPlaceBackground2.setIcon(new ImageIcon(getImage("tituloPergunta.png")));
        listOfComponents.add(firstPlaceBackground2);
        firstPlaceBackground2.setBounds(720, 260, 440, 60);

        secondPlaceBackground2.setIcon(new ImageIcon(getImage("tituloPergunta.png")));
        listOfComponents.add(secondPlaceBackground2);
        secondPlaceBackground2.setBounds(740, 310, 420, 70);

        thirdPlaceBackground2.setIcon(new ImageIcon(getImage("tituloPergunta.png")));
        listOfComponents.add(thirdPlaceBackground2);
        thirdPlaceBackground2.setBounds(760, 370, 400, 60);

        fourthPlaceBackground2.setIcon(new ImageIcon(getImage("tituloPergunta.png")));
        listOfComponents.add(fourthPlaceBackground2);
        fourthPlaceBackground2.setBounds(780, 430, 380, 50);

        fifthPlaceBackground2.setIcon(new ImageIcon(getImage("tituloPergunta.png")));
        listOfComponents.add(fifthPlaceBackground2);
        fifthPlaceBackground2.setBounds(800, 480, 360, 60);

        rankingBackground.setIcon(new ImageIcon(getImage("backgroundConquistas.png")));
        listOfComponents.add(rankingBackground);
        rankingBackground.setBounds(720, 100, 440, 570);


        background.setIcon(new ImageIcon(getImage("fundo_sistema.jpg")));
        listOfComponents.add(background);
        background.setBounds(0, 0, 1366, 770);

        this.fillFields();

        return listOfComponents;
    }

    private void btnReturnMouseClicked(MouseEvent evt) {
        Screen.getScreen().setScreen("Home");
    }

}
