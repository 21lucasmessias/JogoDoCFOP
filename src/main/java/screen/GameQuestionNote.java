package screen;

import game.Game;
import game.GameResult;
import questions.*;
import utils.Memento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.ImageUtils.getImage;

public class GameQuestionNote {

    private String[] account = Game.getInstance().getAccount();
    private Game currentGame = Game.getInstance();
    private ArrayList<Question> questionsList;
    private FiscalNoteQuestion currentQuestion;
    private static final int TROPHY1 = 6;
    private static final int TROPHY2 = 7;
    private static final int TROPHY3 = 8;
    private static final int TROPHY4 = 9;
    private static final int TROPHY5 = 10;
    private static final int TROPHY6 = 11;
    private ArrayList<Memento> mementos = new ArrayList<>();
    private Duration answerTime;
    private int numberOfQuestions;
    private int lvl = 2;

    JLabel year = new JLabel();
    JLabel btnResult = new JLabel();
    JLabel cnpj = new JLabel();
    JLabel NumericCode = new JLabel();
    JLabel digit = new JLabel();
    JLabel levelInput = new JLabel();
    JLabel levelState = new JLabel();
    JLabel lifes = new JLabel();
    JLabel month = new JLabel();
    JLabel model = new JLabel();
    JLabel questionsNumber = new JLabel();
    JLabel number = new JLabel();
    JButton option1 = new JButton();
    JButton option2 = new JButton();
    JButton option3 = new JButton();
    JButton option4 = new JButton();
    JLabel serie = new JLabel();
    JLabel questionTitle = new JLabel();
    JLabel uf = new JLabel();

    public GameQuestionNote(ArrayList<Question> questions) {
        this.questionsList = questions;
        this.currentGame.setLvl(2);

        JFrame frame = new JFrame();
        String qtd = JOptionPane.showInputDialog(frame, "Digite a quantidade de Perguntas!", 8);
        this.currentGame.setQuestionsQuantity(Integer.parseInt(qtd));
        this.numberOfQuestions = currentGame.getQuestionsQuantity();
        while (numberOfQuestions < 8 || numberOfQuestions > 20) {
            JOptionPane.showMessageDialog(null, "Minimo: 08 perguntas e Máximo: 20!");
            qtd = JOptionPane.showInputDialog(frame, "Digite a quantidade de Perguntas!", 8);
            numberOfQuestions = Integer.parseInt(qtd);
        }
    }

    private void fillData() {
        currentQuestion = (FiscalNoteQuestion) questionsList.get((Integer.parseInt(levelState.getText()) - 1));
        
        questionsNumber.setText(String.valueOf(this.numberOfQuestions));
        questionTitle.setText(this.currentQuestion.getTitle());
        uf.setText(String.valueOf(this.currentQuestion.getUf()));
        year.setText(String.valueOf(this.currentQuestion.getYear()));
        month.setText(String.valueOf(this.currentQuestion.getMonth()));
        cnpj.setText(String.valueOf(this.currentQuestion.getCnpj()));
        model.setText(String.valueOf(this.currentQuestion.getModel()));
        serie.setText(String.valueOf(this.currentQuestion.getSerie()));
        number.setText(String.valueOf(this.currentQuestion.getNumber()));
        NumericCode.setText(String.valueOf(this.currentQuestion.getNumericCode()));
        digit.setText(String.valueOf(this.currentQuestion.getVerificatorDigit()));
        
        int[] answers = this.currentQuestion.getAnswers();
        option1.setText(String.valueOf(answers[0]));
        option2.setText(String.valueOf(answers[1]));
        option3.setText(String.valueOf(answers[2]));
        option4.setText(String.valueOf(answers[3]));
        Memento timeLoaded = new Memento(Instant.now());
        addMemento(timeLoaded);
    }

    private void updateInfo() {

        String finished = "não";
        if (currentGame.getSeq() > Integer.parseInt(account[5])) {
            account[5] = Integer.toString(currentGame.getSeq());
        }
        File f = new File(account[0] + ".txt");
        if (f.exists()) {
            try (PrintWriter out = new PrintWriter(account[0] + ".txt")) {
                String strPass = account[1];
                int[] achievements = new int[6];
                for (int i = 0; i < 6; i++) {
                    achievements[i] = Integer.parseInt(account[6 + i]);
                }
                if (achievements[5] == 1) {
                    finished = "sim";
                }
                out.println(account[0] + "," + strPass + "," + account[2] + "," + account[3] + "," + finished + "," + account[5] + "," + achievements[0] + "," + achievements[1] + "," + achievements[2] + "," + achievements[3] + "," + achievements[4] + "," + achievements[5]);
                System.out.println("Conta atualizada com sucesso.");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Erro ao atualizar conta: conta não existe.");
        }
    }

    private void verifyTrophys() {
        int changes = 0;
        // trofeu iniciou um jogo
        if (account[TROPHY1].equals("0")) {
            account[TROPHY1] = "1";
            changes++;
        }
        // trofeu ligeirinho
        if (account[TROPHY2].equals("0")) {
            if (answerTime.getSeconds() < 3) {
                account[TROPHY2] = "1";
                changes++;
            }
        }
        // trofeu 5 acertos seguidos
        if (account[TROPHY3].equals("0")) {
            if (currentGame.getSeq() >= 5) {
                account[TROPHY3] = "1";
                changes++;
            }
        }
        // trofeu 08 acertos seguidos
        if (account[TROPHY4].equals("0")) {
            if (currentGame.getSeq() >= 8) {
                account[TROPHY4] = "1";
                changes++;
            }
        }
        // trofeu finalizar a fase sem errar
        if (account[TROPHY5].equals("0")) {
            if (currentGame.getRightAnswers() == numberOfQuestions) {
                account[TROPHY5] = "1";
                changes++;
            }
        }
        // trofeu completou o game
        if (account[TROPHY6].equals("0")) {
            if (currentGame.getQuestions() == numberOfQuestions) {
                account[TROPHY6] = "1";
                changes++;
            }
        }
        if (changes > 0) {
            updateInfo();
        }
    }
//desabilita campos respostas

    public void disableAnswers() {
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);

    }

    private void AnswerQuestion(int option) {
        if (option >= 1 && option <= 4) {
            answerTime = Duration.between(getMemento().getActionTime(), mementos.get(0).getActionTime());
            System.out.println("pergunta.resposta: " + option + " Resposta Certa: " + currentQuestion.getRightAnswer() + " Tempo levado: " + answerTime.toSeconds() + " segundos.");
            if (currentQuestion.getRightAnswer() == option) {
                currentGame.setRightAnswers(currentGame.getRightAnswers() + 1);
                currentGame.setScore(currentGame.getScore() + 80);
                currentGame.setSeq(currentGame.getSeq() + 1);
                RightAnswer feedback;
                if (currentGame.getQuestions() < 19 && currentGame.getWrongAnswers() < 3) {
                    feedback = new RightAnswer(false);
                } else {
                    feedback = new RightAnswer(true);
                    feedback.setLocationRelativeTo(null);
                }
                feedback.setDefaultCloseOperation(RightAnswer.DO_NOTHING_ON_CLOSE);
                feedback.setVisible(true);
            } else {
                currentGame.setWrongAnswers(currentGame.getWrongAnswers() + 1);
                currentGame.setScore(currentGame.getScore() - 30);
                currentGame.setSeq(0);
                int lifes = updateLifes();
                WrongAnswer feedback;
                if (currentGame.getQuestions() < 19 && currentGame.getWrongAnswers() < 3) {
                    feedback = new WrongAnswer(false, lifes);
                } else {
                    feedback = new WrongAnswer(true, lifes);
                    feedback.setLocationRelativeTo(null);
                }
                feedback.setDefaultCloseOperation(WrongAnswer.DO_NOTHING_ON_CLOSE);
                feedback.setVisible(true);
            }
            if (currentGame.getQuestions() >= 21 || currentGame.getWrongAnswers() == 3) {
                PrincipalMenu();
            }
            // atualiza o level 
            currentGame.setQuestions(currentGame.getQuestions() + 1);
            if (currentGame.getQuestions() <= 21) {
                levelState.setText(Integer.toString(currentGame.getQuestions()));
                if (currentGame.getQuestions() == numberOfQuestions + 1) {
                    btnResult.setEnabled(true);
                    disableAnswers();
                }

            } else {
                levelState.setText("?");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Resposta Inválida.");
        }
        try {
            Thread.sleep(500);
            verifyTrophys();
            fillData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Situacao: " + currentGame.getStatus() + " Pontuacao: " + currentGame.getScore() + " Perguntas: " + currentGame.getQuestions() + " Acertos: " + currentGame.getRightAnswers() + " Erros: " + currentGame.getWrongAnswers() + " Seq: " + currentGame.getSeq());
    }

    public void PrincipalMenu() {
        Screen.getScreen().setScreen("Home");
    }

    public int updateLifes() {
        int lifes = 3 - currentGame.getWrongAnswers();
        ImageIcon life = new ImageIcon(getImage(lifes + "vidas.png"));
        this.lifes.setIcon(life);
        return lifes;
    }

    public void addMemento(Memento m) {
        mementos.add(0, m);
    }

    public Memento getMemento() {
        return mementos.get(1);
    }

    public ArrayList<JComponent> getAllComponents() {
        ArrayList<JComponent> listOfComponents = new ArrayList<JComponent>();

        JLabel titleLeft = new JLabel();
        lifes = new JLabel();
        questionsNumber = new JLabel();
        JLabel truckIcon = new JLabel();
        JLabel txtKey = new JLabel();
        JLabel jLabel4 = new JLabel();
        JLabel dataTitle = new JLabel();
        JLabel dataTitleBackground = new JLabel();
        JLabel jLabel2 = new JLabel();
        uf = new JLabel();
        year = new JLabel();
        month = new JLabel();
        cnpj = new JLabel();
        model = new JLabel();
        serie = new JLabel();
        number = new JLabel();
        NumericCode = new JLabel();
        digit = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel dataBackground = new JLabel();
        levelInput = new JLabel();
        levelState = new JLabel();
        JLabel Lvlbackground = new JLabel();
        JLabel trophy = new JLabel();
        questionTitle = new JLabel();
        JLabel questionBackground = new JLabel();
        option1 = new JButton();
        option2 = new JButton();
        JLabel jLabel6 = new JLabel();
        option3 = new JButton();
        option4 = new JButton();
        JLabel madeText = new JLabel();
        JLabel btnLeave = new JLabel();
        btnResult = new JLabel();
        JLabel background = new JLabel();

        titleLeft.setIcon(new ImageIcon(getImage("title_left.png")));
        listOfComponents.add(titleLeft);
        titleLeft.setBounds(20, 20, 216, 34);

        lifes.setIcon(new ImageIcon(getImage("3vidas.png")));
        listOfComponents.add(lifes);
        lifes.setBounds(930, 10, 170, 50);

        questionsNumber.setFont(new Font("Roboto", 1, 24));
        questionsNumber.setForeground(new Color(255, 255, 255));
        questionsNumber.setText("20");
        listOfComponents.add(questionsNumber);
        questionsNumber.setBounds(1240, 10, 30, 50);

        truckIcon.setIcon(new ImageIcon(getImage("truck.png")));
        listOfComponents.add(truckIcon);
        truckIcon.setBounds(290, 180, 80, 60);

        txtKey.setFont(new Font("Tahoma", 0, 18));
        txtKey.setText("CHAVE");
        listOfComponents.add(txtKey);
        txtKey.setBounds(290, 430, 60, 40);

        jLabel4.setIcon(new ImageIcon(getImage("codBarras.png")));
        jLabel4.setText("jLabel4");
        listOfComponents.add(jLabel4);
        jLabel4.setBounds(110, 370, 440, 50);

        dataTitle.setFont(new Font("Krungthep", 0, 14));
        dataTitle.setForeground(new Color(255, 255, 255));
        dataTitle.setText("CHAVE DE ACESSO DA NOTA");
        listOfComponents.add(dataTitle);
        dataTitle.setBounds(230, 260, 300, 20);

        dataTitleBackground.setIcon(new ImageIcon(getImage("fundoTituloDados.png")));
        listOfComponents.add(dataTitleBackground);
        dataTitleBackground.setBounds(180, 260, 160, 23);

        jLabel2.setFont(new Font("Tahoma", 1, 18));
        jLabel2.setForeground(new Color(255, 255, 255));
        jLabel2.setText("/");
        listOfComponents.add(jLabel2);
        jLabel2.setBounds(1230, 14, 10, 40);

        uf.setFont(new Font("Tahoma", 0, 18));
        uf.setText("21");
        uf.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 255)));
        uf.setEnabled(false);
        listOfComponents.add(uf);
        uf.setBounds(70, 490, 23, 30);

        year.setFont(new Font("Tahoma", 0, 18));
        year.setText("16");
        year.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 255)));
        year.setEnabled(false);
        listOfComponents.add(year);
        year.setBounds(100, 490, 23, 30);

        month.setFont(new Font("Tahoma", 0, 18));
        month.setText("03");
        month.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 255)));
        month.setEnabled(false);
        listOfComponents.add(month);
        month.setBounds(120, 490, 23, 30);

        cnpj.setFont(new Font("Tahoma", 0, 18));
        cnpj.setText("07100000039412");
        cnpj.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 255)));
        cnpj.setEnabled(false);
        listOfComponents.add(cnpj);
        cnpj.setBounds(150, 490, 150, 30);

        model.setFont(new Font("Tahoma", 0, 18));
        model.setText("03");
        model.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 255)));
        model.setEnabled(false);
        listOfComponents.add(model);
        model.setBounds(300, 490, 23, 30);

        serie.setFont(new Font("Tahoma", 0, 18));
        serie.setText("532");
        serie.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 255)));
        serie.setEnabled(false);
        listOfComponents.add(serie);
        serie.setBounds(330, 490, 35, 30);

        number.setFont(new Font("Tahoma", 0, 18));
        number.setText("036000039");
        number.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 255)));
        number.setEnabled(false);
        listOfComponents.add(number);
        number.setBounds(370, 490, 97, 30);

        NumericCode.setFont(new Font("Tahoma", 0, 18));
        NumericCode.setText("126353203");
        NumericCode.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 255)));
        NumericCode.setEnabled(false);
        listOfComponents.add(NumericCode);
        NumericCode.setBounds(470, 490, 97, 30);

        digit.setFont(new Font("Tahoma", 0, 18));
        digit.setText("6");
        digit.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 255)));
        digit.setEnabled(false);
        listOfComponents.add(digit);
        digit.setBounds(570, 490, 15, 30);

        jLabel3.setIcon(new ImageIcon(getImage("fundoTituloDados.png")));
        listOfComponents.add(jLabel3);
        jLabel3.setBounds(320, 260, 196, 23);

        dataBackground.setIcon(new ImageIcon(getImage("fundoDados.png")));
        listOfComponents.add(dataBackground);
        dataBackground.setBounds(70, 150, 520, 500);

        levelInput.setBackground(new Color(255, 255, 255));
        levelInput.setFont(new Font("Roboto", 1, 24));
        levelInput.setForeground(new Color(255, 255, 255));
        levelInput.setHorizontalAlignment(SwingConstants.CENTER);
        levelInput.setText("LEVEL:");
        listOfComponents.add(levelInput);
        levelInput.setBounds(1120, 10, 110, 50);

        levelState.setFont(new Font("Roboto", 1, 24));
        levelState.setForeground(new Color(255, 255, 255));
        levelState.setHorizontalAlignment(SwingConstants.CENTER);
        levelState.setText("1");
        listOfComponents.add(levelState);
        levelState.setBounds(1190, 10, 70, 50);

        Lvlbackground.setIcon(new ImageIcon(getImage("fundoLevel.png")));
        listOfComponents.add(Lvlbackground);
        Lvlbackground.setBounds(1120, 10, 170, 46);

        trophy.setIcon(new ImageIcon(getImage("trofeu.png")));
        listOfComponents.add(trophy);
        trophy.setBounds(1310, 10, 46, 50);

        questionTitle.setFont(new Font("Krungthep", 0, 24));
        questionTitle.setForeground(new Color(255, 255, 255));
        questionTitle.setHorizontalAlignment(SwingConstants.CENTER);
        questionTitle.setText("TITULO PERGUNTA");
        listOfComponents.add(questionTitle);
        questionTitle.setBounds(610, 160, 740, 32);

        questionBackground.setIcon(new ImageIcon(getImage("tituloPergunta.png")));
        listOfComponents.add(questionBackground);
        questionBackground.setBounds(600, 150, 250, 50);

        option1.setBackground(new Color(255, 255, 255));
        option1.setFont(new Font("Roboto", 0, 36));
        option1.setText("opcao1");
        option1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                option1ActionPerformed(evt);
            }
        });
        listOfComponents.add(option1);
        option1.setBounds(720, 240, 540, 60);

        option2.setBackground(new Color(255, 255, 255));
        option2.setFont(new Font("Roboto", 0, 36));
        option2.setText("opcao2");
        option2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                option2ActionPerformed(evt);
            }
        });
        listOfComponents.add(option2);
        option2.setBounds(720, 320, 540, 60);

        jLabel6.setIcon(new ImageIcon(getImage("tituloPergunta.png")));
        listOfComponents.add(jLabel6);
        jLabel6.setBounds(810, 150, 560, 50);

        option3.setBackground(new Color(255, 255, 255));
        option3.setFont(new Font("Roboto", 0, 36));
        option3.setText("opcao3");
        option3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                option3ActionPerformed(evt);
            }
        });
        listOfComponents.add(option3);
        option3.setBounds(720, 400, 540, 60);

        option4.setBackground(new Color(255, 255, 255));
        option4.setFont(new Font("Roboto", 0, 36));
        option4.setText("opcao4");
        option4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                option4ActionPerformed(evt);
            }
        });
        listOfComponents.add(option4);
        option4.setBounds(720, 480, 540, 60);

        madeText.setFont(new Font("Krungthep", 0, 18));
        madeText.setForeground(new Color(255, 255, 255));
        madeText.setText("FEITO POR CESAR VERGARA");
        listOfComponents.add(madeText);
        madeText.setBounds(20, 730, 260, 30);

        btnLeave.setIcon(new ImageIcon(getImage("botaoSairBottom.png")));
        btnLeave.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnLeaveMouseClicked(evt);
            }
        });
        listOfComponents.add(btnLeave);
        btnLeave.setBounds(1170, 710, 180, 45);

        btnResult.setIcon(new ImageIcon(getImage("botaoResultadoe.png")));
        btnResult.setText("btnProxiFase");
        btnResult.setEnabled(false);
        btnResult.setName("");
        btnResult.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnResultMouseClicked(evt);
            }
        });
        listOfComponents.add(btnResult);
        btnResult.setBounds(850, 550, 280, 80);
        btnResult.getAccessibleContext().setAccessibleDescription("");

        background.setIcon(new ImageIcon(getImage("fundo_sistema.jpg")));
        background.setText("sdaa");
        listOfComponents.add(background);
        background.setBounds(0, 0, 1392, 770);

        fillData();

        return listOfComponents;
    }

    private void btnLeaveMouseClicked(java.awt.event.MouseEvent evt) {

        int result = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja sair?", "Encerrar Tentativa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            PrincipalMenu();
        }
    }

    private void option2ActionPerformed(java.awt.event.ActionEvent evt) {
        Memento tempoRespondeu = new Memento(Instant.now());
        addMemento(tempoRespondeu);
        AnswerQuestion(2);
    }

    private void option1ActionPerformed(java.awt.event.ActionEvent evt) {
        Memento tempoRespondeu = new Memento(Instant.now());
        addMemento(tempoRespondeu);
        AnswerQuestion(1);
    }

    private void option3ActionPerformed(java.awt.event.ActionEvent evt) {
        Memento tempoRespondeu = new Memento(Instant.now());
        addMemento(tempoRespondeu);
        AnswerQuestion(3);
    }

    private void option4ActionPerformed(java.awt.event.ActionEvent evt) {
        Memento tempoRespondeu = new Memento(Instant.now());
        addMemento(tempoRespondeu);
        AnswerQuestion(4);
    }

    private void btnResultMouseClicked(java.awt.event.MouseEvent evt) {
        if (currentGame.getQuestions() == numberOfQuestions + 1) {
            levelState.setText(Integer.toString(currentGame.getQuestions()));
            Screen.getScreen().setScreen("GameResult");
        }

    }
}
