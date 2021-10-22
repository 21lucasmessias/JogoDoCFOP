package game;

import home.Home;
import questions.Question;
import questions.QuestionCfop;
import utils.sortContext;
import utils.questionsRandom;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameInstruction extends JFrame {

    ArrayList<Question> questionsList = new ArrayList<>();
    protected String[] account = null;
    private Game currentGame;
    private JPanel mainPanel;
    private JLabel labelTest;

    public GameInstruction() {
        startComponents();
    }

    public GameInstruction(String[] account) {
        this.account = account;
        this.currentGame = Game.getInstance();

        startComponents();
        clearGame();
        fillQuestions();

        // padrao strategy
        sortContext ctx = new sortContext();
        ctx.setOrdemStrategy(new questionsRandom());
        ctx.createList(questionsList);
    }

    public void startComponents() {
        initComponents();
    }

    private void clearGame() {
        currentGame.setRightAnswers(0);
        currentGame.setWrongAnswers(0);
        currentGame.setQuestions(1);
        currentGame.setScore(0);
        currentGame.setSeq(0);
        currentGame.setStatus(1);
    }

    public void fillQuestions() {

        int[] respostas = {6, 3, 2, 5};
        Question aux = new QuestionCfop(1, 2, "Sao Paulo/SP", "Santos/SP", "Sao Jose do Rio Preto/SP", "Qual é o código correto?", respostas, 4);
        questionsList.add(aux);
        int[] respostas2 = {7, 5, 6, 1};
        aux = new QuestionCfop(2, 2, "Ipatinga/MG", "Ipatinga/MG", "Rio de Janeiro/RJ", "Qual é o código correto?", respostas2, 3);
        questionsList.add(aux);
        int[] respostas3 = {2, 1, 4, 3};
        aux = new QuestionCfop(3, 1, "Aracaju/RJ", "Aracaju/RJ", "Lagarto/SE", "Qual é o código correto?", respostas3, 1);
        questionsList.add(aux);
        int[] respostas4 = {5, 9, 6, 7};
        aux = new QuestionCfop(4, 2, "Bauru/SP", "Bauru/SP", "Araguaína/TO", "Qual é o código correto?", respostas4, 3);
        questionsList.add(aux);
        int[] respostas5 = {1, 5, 3, 2};
        aux = new QuestionCfop(5, 1, "Marituba/PA", "Marituba/PA", "Campina Grande/PB", "Qual é o código correto?", respostas5, 4);
        questionsList.add(aux);
        int[] respostas6 = {5, 2, 3, 6};
        aux = new QuestionCfop(6, 2, "Colatina/ES", "Gov. Valadares/MG", "Patos/PB", "Qual é o código correto?", respostas6, 4);
        questionsList.add(aux);
        int[] respostas7 = {2, 3, 1, 4};
        aux = new QuestionCfop(7, 1, "Boa Vista/RR", "Boa Vista/RR", "Camaragibe/PE", "Qual é o código correto?", respostas7, 1);
        questionsList.add(aux);
        int[] respostas8 = {2, 1, 3, 9};
        aux = new QuestionCfop(8, 1, "Corumbá/MS", "Corumbá/MS", "Campo Grande/MS", "Qual é o código correto?", respostas8, 2);
        questionsList.add(aux);
        int[] respostas9 = {5, 3, 6, 2};
        aux = new QuestionCfop(9, 2, "Rio Claro/SP", "Rio Claro/SP", "Lages/SC", "Qual é o código correto?", respostas9, 3);
        questionsList.add(aux);
        int[] respostas10 = {6, 4, 5, 7};
        aux = new QuestionCfop(10, 2, "Passos/MG", "Passos/MG", "João Pessoa/PB", "Qual é o código correto?", respostas10, 1);
        questionsList.add(aux);
        int[] respostas11 = {1, 3, 9, 2};
        aux = new QuestionCfop(11, 1, "Crato/CE", "Crato/CE", "Umuarama/PR", "Qual é o código correto?", respostas11, 4);
        questionsList.add(aux);
        int[] respostas12 = {2, 5, 7, 6};
        aux = new QuestionCfop(12, 2, "Palmas/TO", "Palmas/TO", "Luziânia/GO", "Qual é o código correto?", respostas12, 4);
        questionsList.add(aux);
        int[] respostas13 = {6, 2, 5, 3};
        aux = new QuestionCfop(13, 2, "São José/SC", "Curitiba/PR", "Maringá/PR", "Qual é o código correto?", respostas13, 3);
        questionsList.add(aux);
        int[] respostas14 = {5, 9, 7, 6};
        aux = new QuestionCfop(14, 2, "Foz do Iguaçu/PR", "Foz do Iguaçu/PR", "Cidad Del Leste/PAR", "Qual é o código correto?", respostas14, 3);
        questionsList.add(aux);
        int[] respostas15 = {3, 2, 1, 5};
        aux = new QuestionCfop(15, 1, "Parnaíba/PI", "Parnaíba/PI", "Itajaí/SC", "Qual é o código correto?", respostas15, 2);
        questionsList.add(aux);
        int[] respostas16 = {6, 5, 3, 2};
        aux = new QuestionCfop(16, 2, "Recife/PE", "Recife/PE", "Rio Verde/GO", "Qual é o código correto?", respostas16, 1);
        questionsList.add(aux);
        int[] respostas17 = {1, 9, 3, 2};
        aux = new QuestionCfop(17, 1, "Rio Branco/AC", "Rio Branco/AC", "Breves/PA", "Qual é o código correto?", respostas17, 4);
        questionsList.add(aux);
        int[] respostas18 = {1, 5, 6, 3};
        aux = new QuestionCfop(18, 2, "Cabo Frio/RJ", "Cabo Frio/RJ", "Rio de Janeiro/RJ", "Qual é o código correto?", respostas18, 2);
        questionsList.add(aux);
        int[] respostas19 = {9, 3, 2, 1};
        aux = new QuestionCfop(19, 1, "Brasília/DF", "Goiânia/GO", "Campinas/SP", "Qual é o código correto?", respostas19, 3);
        questionsList.add(aux);
        int[] respostas20 = {2, 1, 3, 5};
        aux = new QuestionCfop(20, 1, "São Caetano do Sul/SP", "São Caetano do Sul/SP", "São Paulo/SP", "Qual é o código correto?", respostas20, 2);
        questionsList.add(aux);

    }


    @SuppressWarnings("unchecked")

    private void initComponents() {

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setLayout(null);

        titleLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/title_left.png")));
        jPanel1.add(titleLeft);
        titleLeft.setBounds(20, 20, 216, 34);

        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/botao_Jogar.png")));
        btnPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPlayMouseClicked(evt);
            }
        });
        jPanel1.add(btnPlay);
        btnPlay.setBounds(720, 520, 520, 160);

        imagemVideo.setForeground(new java.awt.Color(102, 102, 0));
        imagemVideo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/infos.png")));
        jPanel1.add(imagemVideo);
        imagemVideo.setBounds(110, 110, 1150, 570);

        madeText.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        madeText.setForeground(new java.awt.Color(255, 255, 255));
        madeText.setText("FEITO POR CESAR VERGARA");
        jPanel1.add(madeText);
        madeText.setBounds(20, 730, 260, 30);

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/botaoSairBottom.png")));
        btnSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
        });
        jPanel1.add(btnSair);
        btnSair.setBounds(1170, 710, 180, 45);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/fundo_sistema.jpg")));
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

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {
        int result = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja sair?", "Encerrar Tentativa",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            Home game = new Home(account);
            game.setLocationRelativeTo(null);
            game.setVisible(true);
            this.dispose();
        }
    }

    private void btnPlayMouseClicked(java.awt.event.MouseEvent evt) {
        JogoPergunta questionGame = new JogoPergunta(account, questionsList);
        questionGame.setLocationRelativeTo(null);
        questionGame.setVisible(true);
        this.dispose();
    }


    public static void main(String[] args) throws FileNotFoundException {


        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }



        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameInstruction().setVisible(true);
            }
        });
    }

}
