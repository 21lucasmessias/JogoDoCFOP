package screen;

import javax.swing.*;

import java.util.ArrayList;

import game.Game;
import org.jdesktop.layout.GroupLayout;
import questions.Question;
import questions.QuestionCfop;
import utils.sortContext;
import utils.questionsRandom;
import static javax.swing.JOptionPane.*;
import static utils.ImageUtils.getImage;

public class GameInstruction {
    ArrayList<Question> questionsList = new ArrayList<>();

    public GameInstruction() {
        clearGame();
        fillQuestions();

        // padrao strategy
        sortContext ctx = new sortContext();
        ctx.setOrdemStrategy(new questionsRandom());
        ctx.createList(questionsList);
    }

    private void clearGame() {
        Game currentGame = Game.getInstance();

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

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {
        int result = showConfirmDialog(null, "Você tem certeza que deseja sair?", "Encerrar Tentativa",
                YES_NO_OPTION,
                QUESTION_MESSAGE);
        if (result == YES_OPTION) {
            Screen.getScreen().setScreen("Home");
        }
    }

    private void btnPlayMouseClicked(java.awt.event.MouseEvent evt) {
        Screen.getScreen().setScreen("GameQuestion", questionsList);
    }

    public ArrayList<JComponent> getAllComponents() {
        ArrayList<JComponent> listOfComponents = new ArrayList<JComponent>();

        JDialog jDialog1 = new JDialog();
        JLabel titleLeft = new JLabel();
        JLabel btnPlay = new JLabel();
        JLabel imageVideo = new JLabel();
        JLabel madeText = new JLabel();
        JLabel btnExit = new JLabel();
        JLabel background = new JLabel();

        GroupLayout jDialog1Layout = new GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
                jDialog1Layout.createParallelGroup(GroupLayout.LEADING)
                        .add(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
                jDialog1Layout.createParallelGroup(GroupLayout.LEADING)
                        .add(0, 300, Short.MAX_VALUE)
        );

        titleLeft.setIcon(new ImageIcon(getImage("title_left.png")));
        listOfComponents.add(titleLeft);
        titleLeft.setBounds(20, 20, 216, 34);

        btnPlay.setIcon(new ImageIcon(getImage("botao_Jogar.png")));
        btnPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPlayMouseClicked(evt);
            }
        });
        listOfComponents.add(btnPlay);
        btnPlay.setBounds(720, 520, 520, 160);

        imageVideo.setForeground(new java.awt.Color(102, 102, 0));
        imageVideo.setIcon(new ImageIcon(getImage("infos.png")));
        listOfComponents.add(imageVideo);
        imageVideo.setBounds(110, 110, 1150, 570);

        madeText.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        madeText.setForeground(new java.awt.Color(255, 255, 255));
        madeText.setText("FEITO POR CESAR VERGARA");
        listOfComponents.add(madeText);
        madeText.setBounds(20, 730, 260, 30);

        btnExit.setIcon(new ImageIcon(getImage("botaoSairBottom.png")));
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
        });
        listOfComponents.add(btnExit);
        btnExit.setBounds(1170, 710, 180, 45);

        background.setIcon(new javax.swing.ImageIcon(getImage("fundo_sistema.jpg")));
        listOfComponents.add(background);
        background.setBounds(0, 0, 1366, 770);

        return listOfComponents;
    }
}

