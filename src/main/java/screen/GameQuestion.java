package screen;

import game.Game;
import questions.Question;
import questions.QuestionCfop;
import questions.RightAnswer;
import questions.WrongAnswer;
import utils.Memento;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.ImageUtils.getImage;

public class GameQuestion {

    private String[] account =  Game.getInstance().getAccount();
    private Game currentGame = Game.getInstance();
    private ArrayList<Question> questionsList;
    private QuestionCfop currentQuestion;
    private static final int TROPHY1 = 6;
    private static final int TROPHY2 = 7;
    private static final int TROPHY3 = 8;
    private static final int TROPHY4 = 9;
    private static final int TROPHY5 = 10;

    JLabel btnNextLvl = new JLabel();
    JLabel townInput = new JLabel();
    JLabel receiverInput = new JLabel();
    JLabel levelInput = new JLabel();
    JLabel levelState = new JLabel();
    JLabel lifes = new JLabel();
    JLabel numberOfQuestions = new JLabel();
    JButton option1 = new JButton();
    JButton option2 = new JButton();
    JButton option3 = new JButton();
    JButton option4 = new JButton();
    JLabel inputOperation = new JLabel();
    JLabel senderInput = new JLabel();
    JLabel questionTitle = new JLabel();

    private final ArrayList<Memento> mementos = new ArrayList<>();
    private Duration replyTime;
    private int qntdQuestions;

    public GameQuestion(ArrayList<Question> questionsList) {
        this.currentGame.setLvl(1);
        this.questionsList = questionsList;

        JFrame frame = new JFrame();
        String qtd = JOptionPane.showInputDialog(frame, "Digite a quantidade de Perguntas!", 8);
        this.currentGame.setQuestionsQuantity(Integer.parseInt(qtd));
        this.qntdQuestions = currentGame.getQuestionsQuantity();
        while (qntdQuestions < 8 || qntdQuestions > 20) {
            JOptionPane.showMessageDialog(frame, "Minimo: 08 perguntas e Máximo: 20!");
            qtd = JOptionPane.showInputDialog(frame, "Digite a quantidade de Perguntas!", 8);
            qntdQuestions = Integer.parseInt(qtd);
        }
    }

    private void fillData() {
        currentQuestion = (QuestionCfop) questionsList.get((Integer.parseInt(levelState.getText()) - 1));

        String opera;
        if (this.currentQuestion.getOperation() == 1) {
            opera = "Entrada";
        } else {
            opera = "Saída";
        }

        numberOfQuestions.setText(String.valueOf(this.qntdQuestions));
        inputOperation.setText(opera);
        townInput.setText(this.currentQuestion.getTown());
        senderInput.setText(this.currentQuestion.getSender());
        receiverInput.setText(this.currentQuestion.getReceiver());
        questionTitle.setText(this.currentQuestion.getTitle());

        int[] answers = this.currentQuestion.getAnswers();
        option1.setText(String.valueOf(answers[0]));
        option2.setText(String.valueOf(answers[1]));
        option3.setText(String.valueOf(answers[2]));
        option4.setText(String.valueOf(answers[3]));

        this.showRightAnswer();

        Memento timeLoaded = new Memento(Instant.now());
        addMemento(timeLoaded);
    }

    private void showRightAnswer() {
        if(currentQuestion.getRightAnswer() == 1){
            option1.setBackground(Color.BLUE);
            option2.setBackground(Color.BLACK);
            option3.setBackground(Color.BLACK);
            option4.setBackground(Color.BLACK);
        }
        else if(currentQuestion.getRightAnswer() == 2){
            option2.setBackground(Color.BLUE);
            option1.setBackground(Color.BLACK);
            option3.setBackground(Color.BLACK);
            option4.setBackground(Color.BLACK);
        }
        else if(currentQuestion.getRightAnswer() == 3){
            option3.setBackground(Color.BLUE);
            option2.setBackground(Color.BLACK);
            option1.setBackground(Color.BLACK);
            option4.setBackground(Color.BLACK);
        }
        else if(currentQuestion.getRightAnswer() == 4){
            option4.setBackground(Color.BLUE);
            option2.setBackground(Color.BLACK);
            option3.setBackground(Color.BLACK);
            option1.setBackground(Color.BLACK);
        }
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
        int change = 0;
        // trofeu iniciou um jogo
        if (account[TROPHY1].equals("0")) {
            account[TROPHY1] = "1";
            change++;
        }
        // trofeu ligeirinho
        if (account[TROPHY2].equals("0")) {
            if (replyTime.getSeconds() < 5) {
                account[TROPHY2] = "1";
                change++;
            }
        }
        // trofeu 5 acertos seguidos
        if (account[TROPHY3].equals("0")) {
            if (currentGame.getSeq() >= 5) {
                account[TROPHY3] = "1";
                change++;
            }
        }
        // trofeu 8 acertos seguidos
        if (account[TROPHY4].equals("0")) {
            if (currentGame.getSeq() >= 8) {
                account[TROPHY4] = "1";
                change++;
            }
        }
        // trofeu completar a fase sem errar
        if (account[TROPHY5].equals("0")) {
            if (currentGame.getRightAnswers() == qntdQuestions) {
                account[TROPHY5] = "1";
                change++;
            }
        }

        if (change > 0) {
            updateInfo();
        }
    }

    public void disableAnswers() {
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);
    }

    private void answerQuestion(int option) {
        if (option >= 1 && option <= 4) {
            replyTime = Duration.between(getMemento().getActionTime(), mementos.get(0).getActionTime());
            if (currentQuestion.getRightAnswer() == option) {
                currentGame.setRightAnswers(currentGame.getRightAnswers() + 1);
                currentGame.setScore(currentGame.getScore() + 80);
                currentGame.setSeq(currentGame.getSeq() + 1);

                if(currentGame.getQuestions() != qntdQuestions) {
                    RightAnswer feedback;
                    feedback = new RightAnswer();
                    feedback.setDefaultCloseOperation(RightAnswer.DO_NOTHING_ON_CLOSE);
                    feedback.setVisible(true);
                }
            } else {
                currentGame.setWrongAnswers(currentGame.getWrongAnswers() + 1);
                currentGame.setScore(currentGame.getScore() - 30);
                currentGame.setSeq(0);
                int lifes = updateLifes();
                if(currentGame.getQuestions() != qntdQuestions) {
                    WrongAnswer feedback;
                    feedback = new WrongAnswer(lifes);
                    feedback.setDefaultCloseOperation(WrongAnswer.DO_NOTHING_ON_CLOSE);
                    feedback.setVisible(true);
                }
            }

            if (currentGame.getWrongAnswers() == 3) {
                Screen.getScreen().setScreen("GameResult");
            }

            currentGame.setQuestions(currentGame.getQuestions() + 1);

            if(currentGame.getQuestions() != qntdQuestions){
                levelState.setText(Integer.toString(currentGame.getQuestions()));
            } else {
                JFrame frame = new JFrame();
                int goToResult = JOptionPane.showConfirmDialog(
                        frame,
                        "Sessão completada, quantidade de questões finalizadas, deseja continuar jogando adicionando mais 8 questões?",
                        "Escolha uma opção",
                        JOptionPane.YES_NO_OPTION
                );

                if(goToResult == JOptionPane.YES_OPTION){
                    this.qntdQuestions = currentGame.getQuestionsQuantity() + 8;
                } else {
                    Screen.getScreen().setScreen("GameResult");
                }
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
    }

    public void principalMenu() {
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

    private void btnLeaveMouseClicked(java.awt.event.MouseEvent evt) {
        int result = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja sair?", "Encerrar Tentativa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            principalMenu();
        }
    }

    private void option2ActionPerformed(java.awt.event.ActionEvent evt) {
        Memento answeredTime = new Memento(Instant.now());
        addMemento(answeredTime);
        answerQuestion(2);
    }

    private void option1ActionPerformed(java.awt.event.ActionEvent evt) {
        Memento anseweredTime = new Memento(Instant.now());
        addMemento(anseweredTime);
        answerQuestion(1);
    }

    private void option3ActionPerformed(java.awt.event.ActionEvent evt) {
        Memento answeredTime = new Memento(Instant.now());
        addMemento(answeredTime);
        answerQuestion(3);
    }

    private void option4ActionPerformed(java.awt.event.ActionEvent evt) {
        Memento answeredTime = new Memento(Instant.now());
        addMemento(answeredTime);
        answerQuestion(4);
    }

    private void operationInputComponentAdded(java.awt.event.ContainerEvent evt) {
    }

    private void btnNextLvlMouseClicked(java.awt.event.MouseEvent evt) {
        if (currentGame.getQuestions() == qntdQuestions + 1) {
            Screen.getScreen().setScreen("GameResult");
            levelState.setText(Integer.toString(currentGame.getQuestions()));
        }
    }

    public ArrayList<JComponent> getAllComponents() {

        ArrayList<JComponent> listOfComponents = new ArrayList<JComponent>();

        JLabel titleLeft = new JLabel();
        lifes = new JLabel();
        numberOfQuestions = new JLabel();
        JLabel jLabel1 = new JLabel();
        JLabel truckIcon = new JLabel();
        JLabel tituloDados = new JLabel();
        JLabel fundoTituloDados = new JLabel();
        JLabel operacaoTitulo = new JLabel();
        inputOperation = new JLabel();
        JLabel cidadeTitulo = new JLabel();
        townInput = new JLabel();
        JLabel remetenteTitulo = new JLabel();
        senderInput = new JLabel();
        JLabel destinatarioTitulo = new JLabel();
        receiverInput = new JLabel();
        JLabel fundoDados = new JLabel();
        levelInput = new JLabel();
        levelState = new JLabel();
        JLabel fundoLevel = new JLabel();
        JLabel trofeu = new JLabel();
        questionTitle = new JLabel();
        JLabel fundoPergunta = new JLabel();
        option1 = new JButton();
        option2 = new JButton();
        option3 = new JButton();
        option4 = new JButton();
        JLabel madeText = new JLabel();
        JLabel btnSair = new JLabel();
        JLabel background = new JLabel();
        JLabel jLabel2 = new JLabel();

        titleLeft.setIcon(new ImageIcon(getImage("title_left.png"))); // NOI18N
        listOfComponents.add(titleLeft);
        titleLeft.setBounds(20, 20, 216, 34);

        lifes.setIcon(new ImageIcon(getImage("3vidas.png"))); // NOI18N
        listOfComponents.add(lifes);
        lifes.setBounds(930, 10, 170, 50);

        numberOfQuestions.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        numberOfQuestions.setForeground(new java.awt.Color(255, 255, 255));
        numberOfQuestions.setText("20");
        listOfComponents.add(numberOfQuestions);
        numberOfQuestions.setBounds(1250, 10, 40, 50);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("/");
        listOfComponents.add(jLabel1);
        jLabel1.setBounds(1230, 10, 14, 50);

        truckIcon.setIcon(new ImageIcon(getImage("truck.png"))); // NOI18N
        listOfComponents.add(truckIcon);
        truckIcon.setBounds(330, 160, 80, 60);

        tituloDados.setFont(new java.awt.Font("Krungthep", 0, 14)); // NOI18N
        tituloDados.setForeground(new java.awt.Color(255, 255, 255));
        tituloDados.setText("DADOS DA NOTA");
        listOfComponents.add(tituloDados);
        tituloDados.setBounds(320, 230, 118, 20);

        fundoTituloDados.setIcon(new ImageIcon(getImage("fundoTituloDados.png"))); // NOI18N
        listOfComponents.add(fundoTituloDados);
        fundoTituloDados.setBounds(290, 230, 158, 23);

        operacaoTitulo.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        operacaoTitulo.setForeground(new java.awt.Color(9, 0, 108));
        operacaoTitulo.setText("OPERAÇÃO");
        listOfComponents.add(operacaoTitulo);
        operacaoTitulo.setBounds(140, 290, 190, 24);

        inputOperation.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        inputOperation.setForeground(new java.awt.Color(142, 142, 142));
        inputOperation.setHorizontalAlignment(SwingConstants.RIGHT);
        inputOperation.setText("OPERAÇÃO");
        inputOperation.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                operationInputComponentAdded(evt);
            }
        });
        listOfComponents.add(inputOperation);
        inputOperation.setBounds(342, 290, 240, 24);

        cidadeTitulo.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        cidadeTitulo.setForeground(new java.awt.Color(9, 0, 108));
        cidadeTitulo.setText("CIDADE EMISSORA");
        listOfComponents.add(cidadeTitulo);
        cidadeTitulo.setBounds(140, 360, 190, 20);

        townInput.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        townInput.setForeground(new java.awt.Color(142, 142, 142));
        townInput.setHorizontalAlignment(SwingConstants.RIGHT);
        townInput.setText("CIDADE/UF");
        townInput.setToolTipText("");
        listOfComponents.add(townInput);
        townInput.setBounds(342, 360, 240, 24);

        remetenteTitulo.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        remetenteTitulo.setForeground(new java.awt.Color(9, 0, 108));
        remetenteTitulo.setText("REMETENTE");
        listOfComponents.add(remetenteTitulo);
        remetenteTitulo.setBounds(140, 470, 180, 20);

        senderInput.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        senderInput.setForeground(new java.awt.Color(142, 142, 142));
        senderInput.setHorizontalAlignment(SwingConstants.RIGHT);
        senderInput.setText("CIDADE/UF");
        senderInput.setToolTipText("");
        listOfComponents.add(senderInput);
        senderInput.setBounds(342, 470, 240, 24);

        destinatarioTitulo.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        destinatarioTitulo.setForeground(new java.awt.Color(9, 0, 108));
        destinatarioTitulo.setText("DESTINATÁRIO");
        listOfComponents.add(destinatarioTitulo);
        destinatarioTitulo.setBounds(140, 540, 180, 20);

        receiverInput.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        receiverInput.setForeground(new java.awt.Color(142, 142, 142));
        receiverInput.setHorizontalAlignment(SwingConstants.RIGHT);
        receiverInput.setText("CIDADE/UF");
        receiverInput.setToolTipText("");
        listOfComponents.add(receiverInput);
        receiverInput.setBounds(342, 540, 240, 24);

        fundoDados.setIcon(new ImageIcon(getImage("fundoDados.png"))); // NOI18N
        listOfComponents.add(fundoDados);
        fundoDados.setBounds(100, 140, 520, 500);

        levelInput.setBackground(new java.awt.Color(255, 255, 255));
        levelInput.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        levelInput.setForeground(new java.awt.Color(255, 255, 255));
        levelInput.setHorizontalAlignment(SwingConstants.CENTER);
        levelInput.setText("LEVEL:");
        listOfComponents.add(levelInput);
        levelInput.setBounds(1120, 10, 110, 50);

        levelState.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        levelState.setForeground(new java.awt.Color(255, 255, 255));
        levelState.setHorizontalAlignment(SwingConstants.CENTER);
        levelState.setText("1");
        levelState.setToolTipText("");
        listOfComponents.add(levelState);
        levelState.setBounds(1190, 10, 70, 50);

        fundoLevel.setIcon(new ImageIcon(getImage("fundoLevel.png"))); // NOI18N
        listOfComponents.add(fundoLevel);
        fundoLevel.setBounds(1120, 10, 170, 46);

        trofeu.setIcon(new ImageIcon(getImage("trofeu.png"))); // NOI18N
        listOfComponents.add(trofeu);
        trofeu.setBounds(1310, 10, 46, 50);

        questionTitle.setFont(new java.awt.Font("Krungthep", 0, 24)); // NOI18N
        questionTitle.setForeground(new java.awt.Color(255, 255, 255));
        questionTitle.setHorizontalAlignment(SwingConstants.CENTER);
        questionTitle.setText("TITULO PERGUNTA");
        listOfComponents.add(questionTitle);
        questionTitle.setBounds(730, 160, 550, 32);

        fundoPergunta.setIcon(new ImageIcon(getImage("tituloPergunta.png"))); // NOI18N
        listOfComponents.add(fundoPergunta);
        fundoPergunta.setBounds(730, 150, 550, 50);

        option1.setBackground(new java.awt.Color(255, 255, 255));
        option1.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        option1.setText("option1");
        option1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                option1ActionPerformed(evt);
            }
        });
        listOfComponents.add(option1);
        option1.setBounds(740, 240, 540, 60);

        option2.setBackground(new java.awt.Color(255, 255, 255));
        option2.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        option2.setText("option2");
        option2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                option2ActionPerformed(evt);
            }
        });
        listOfComponents.add(option2);
        option2.setBounds(740, 320, 540, 60);

        option3.setBackground(new java.awt.Color(255, 255, 255));
        option3.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        option3.setText("option3");
        option3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                option3ActionPerformed(evt);
            }
        });
        listOfComponents.add(option3);
        option3.setBounds(740, 400, 540, 60);

        option4.setBackground(new java.awt.Color(255, 255, 255));
        option4.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        option4.setText("option4");
        option4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                option4ActionPerformed(evt);
            }
        });
        listOfComponents.add(option4);
        option4.setBounds(740, 480, 540, 60);

        madeText.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        madeText.setForeground(new java.awt.Color(255, 255, 255));
        madeText.setText("FEITO POR CESAR VERGARA");
        listOfComponents.add(madeText);
        madeText.setBounds(20, 730, 260, 30);

        btnSair.setIcon(new ImageIcon(getImage("botaoSairBottom.png"))); // NOI18N
        btnSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLeaveMouseClicked(evt);
            }
        });
        listOfComponents.add(btnSair);
        btnSair.setBounds(1170, 710, 180, 45);

        btnNextLvl.setIcon(new ImageIcon(getImage("botaoResultadoe.png"))); // NOI18N
        btnNextLvl.setText("btnProxiFase");
        btnNextLvl.setEnabled(false);
        btnNextLvl.setName(""); // NOI18N
        btnNextLvl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextLvlMouseClicked(evt);
            }
        });
        listOfComponents.add(btnNextLvl);
        btnNextLvl.setBounds(870, 550, 280, 80);
        btnNextLvl.getAccessibleContext().setAccessibleName("");
        btnNextLvl.getAccessibleContext().setAccessibleDescription("");

        background.setIcon(new ImageIcon(getImage("fundo_sistema.jpg"))); // NOI18N
        background.setText("sdaa");
        listOfComponents.add(background);
        background.setBounds(0, 0, 1392, 770);
        background.getAccessibleContext().setAccessibleName("");

        jLabel2.setText("jLabel2");
        listOfComponents.add(jLabel2);
        jLabel2.setBounds(1180, 100, 34, 14);

        fillData();
        return listOfComponents;
    }
}
