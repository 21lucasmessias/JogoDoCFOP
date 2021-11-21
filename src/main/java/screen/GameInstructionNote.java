package screen;

import org.jdesktop.layout.GroupLayout;
import questions.FiscalNoteQuestion;
import questions.Question;

import javax.swing.*;
import java.util.ArrayList;

import static utils.ImageUtils.getImage;

public class GameInstructionNote extends GameInstruction {
    public GameInstructionNote() {
        super();
    }

    @Override
    public void fillQuestions() {
        int[] answer = {94, 53, 21, 36};
        Question aux = new FiscalNoteQuestion(1, "21", "17", "11", "07100000039412", "65", "532", "136000394", "126353203", "7", "Qual é o código da UF na Chave?", answer, 3);
        questionsList.add(aux);
        int[] answer2 = {2021, 2003, 2006, 2020};
        aux = new FiscalNoteQuestion(1, "41", "20", "03", " 92441794000147", "65", "532", "553440439", "146453723", "9", "Qual é o ano na Chave?", answer2, 4);
        questionsList.add(aux);
        int[] answer3 = {06, 03, 12, 07};
        aux = new FiscalNoteQuestion(1, "32", "17", "06", "36177489000119", "65", "532", "807960222", "631253320", "6", "Qual é o mês na Chave?", answer3, 1);
        questionsList.add(aux);
        int[] answer4 = {1719, 7160, 6354,5444};
        aux = new FiscalNoteQuestion(1, "17", "19", "12", "63542898000140", "65", "532", "716058793", "615444203", "2", "Qual sequência faz parte do CNPJ da Empresa na Chave?", answer4, 3);
        questionsList.add(aux);
        int[] answer5 = {21, 03, 17, 06};
        aux = new FiscalNoteQuestion(1, "16", "16", "05", "20380424000136", "03", "532", "226607654", "301835322", "8", "Qual é o número do modelo do documento fiscal na Chave? ", answer5, 2);
        questionsList.add(aux);
        int[] answer6 = {17, 071, 532, 21};
        aux = new FiscalNoteQuestion(1, "50", "21", "02", "48781293000118", "65", "532", "199917792", "213635032", "1", "Qual é a serie do documento fiscal na Chave?", answer6, 3);
        questionsList.add(aux);
        int[] answer7 = {495, 359, 532, 164};
        aux = new FiscalNoteQuestion(1, "31", "20", "09", "95935972000184", "65", "532", "495518160", "164625341", "2", "Qual sequência faz parte do Número do Documento Fiscal na chave?", answer7, 1);
        questionsList.add(aux);
        int[] answer8 = {281208, 503419, 645900, 222622};
        aux = new FiscalNoteQuestion(1, "28", "12", "08", "50341905000101", "65", "532", "583645900", "222622603", "1", "Qual sequência faz parte do Código Numérico para formar a Chave de Acesso?", answer8, 4);
        questionsList.add(aux);
        int[] answer9 = {41, 52, 21, 35};
        aux = new FiscalNoteQuestion(1, "11", "14", "10", "24262022000198", "65", "532", "927252165", "845679333", "4", "Qual código não pertence ao estado do Paraná?", answer9, 1);
        questionsList.add(aux);
        int[] answer10 = {42, 23, 35, 55};
        aux = new FiscalNoteQuestion(1, "51", "18", "01", "65451859000162", "65", "532", "674776013", "145864203", "6", "Qual código pertence ao estado de São Paulo?", answer10, 3);
        questionsList.add(aux);
        int[] answer11 = {11, 41, 23, 35};
        aux = new FiscalNoteQuestion(1, "33", "21", "04", "96119442999129", "65", "532", "128420096", "302200112", "8", "Qual código pertence a região Sudeste?", answer11, 4);
        questionsList.add(aux);
        int[] answer12 = {21, 13, 42, 55};
        aux = new FiscalNoteQuestion(1, "43", "20", "02", "85044851000199", "65", "532", "791381971", "141582990", "9", "Qual código pertence a região Norte?", answer12, 2);
        questionsList.add(aux);
        int[] answer13 = {33, 21, 14, 43};
        aux = new FiscalNoteQuestion(1, "24", "15", "03", "26339003000148", "65", "532", "844231176", "990202111", "2", "Qual código pertence a região Sul?", answer13, 4);
        questionsList.add(aux);
        int[] answer14 = {11, 20, 14, 15};
        aux = new FiscalNoteQuestion(1, "12", "17", "10", "00544610000156", "65", "532", "416270355", "332644892", "5", "Quantos dígitos são reservados para o CNPJ do Emitente?", answer14, 3);
        questionsList.add(aux);
        int[] answer15 = {4, 9, 5, 7};
        aux = new FiscalNoteQuestion(1, "43", "10", "02", "16078779000106", "65", "532", "522381503", "273244887", "7", "Quantos dígitos são reservados para o Código de Acesso?", answer15, 2);
        questionsList.add(aux);
        int[] answer16 = {42, 43, 44, 45};
        aux = new FiscalNoteQuestion(1, "52", "16", "12", "28444457000104", "65", "532", "835337288", "973244541", "5", "A chave de acesso é composta por quantos números?", answer16, 3);
        questionsList.add(aux);
        int[] answer17 = {9, 5, 7, 8};
        aux = new FiscalNoteQuestion(1, "42", "17", "11", "87926854000181", "03", "532", "972435155", "111265702", "2", " Quantos dígitos são reservados para o Número do Documento Fiscal?", answer17, 1);
        questionsList.add(aux);
        int[] answer18 = {5, 3, 4, 2};
        aux = new FiscalNoteQuestion(1, "35", "21", "07", "20091163000134", "65", "532", "640566050", "223343880", "1", "Quantos digitos são reservados para o Modelo do Documento Fiscal?", answer18, 4);
        questionsList.add(aux);
        int[] answer19 = {0, 1, 2, 3};
        aux = new FiscalNoteQuestion(1, "26", "20", "01", "05984764000182", "65", "532", "258927211", "717715430", "3", "Quantos dígitos são reservados para o cDV na chave?", answer19, 2);
        questionsList.add(aux);
        int[] answer20 = {6, 7, 8, 9};
        aux = new FiscalNoteQuestion(1, "13", "14", "05", "40639751000177", "65", "532", "001390436", "513624063", "6", "Quantos grupos ou campos formam a Chave de Acesso?", answer20, 4);
        questionsList.add(aux);
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

        titleLeft.setIcon(new javax.swing.ImageIcon(getImage("title_left.png"))); // NOI18N
        listOfComponents.add(titleLeft);
        titleLeft.setBounds(20, 20, 216, 34);

        btnPlay.setIcon(new javax.swing.ImageIcon(getImage("botao_Jogar.png"))); // NOI18N
        btnPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPlayMouseClicked(evt);
            }
        });
        listOfComponents.add(btnPlay);
        btnPlay.setBounds(720, 520, 520, 160);

        imageVideo.setIcon(new javax.swing.ImageIcon(getImage("infosNota.png"))); // NOI18N
        listOfComponents.add(imageVideo);
        imageVideo.setBounds(110, 110, 1150, 570);

        madeText.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        madeText.setForeground(new java.awt.Color(255, 255, 255));
        madeText.setText("FEITO POR CESAR VERGARA");
        listOfComponents.add(madeText);
        madeText.setBounds(20, 730, 260, 30);

        btnExit.setIcon(new javax.swing.ImageIcon(getImage("botaoSairBottom.png"))); // NOI18N
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
        });
        listOfComponents.add(btnExit);
        btnExit.setBounds(1170, 710, 180, 45);

        background.setIcon(new javax.swing.ImageIcon(getImage("fundo_sistema.jpg"))); // NOI18N
        listOfComponents.add(background);
        background.setBounds(0, 0, 1366, 770);

        return listOfComponents;
    }

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {
        int result = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja sair?", "Encerrar Tentativa",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            Screen.getScreen().setScreen("Home");
        }
    }

    private void btnPlayMouseClicked(java.awt.event.MouseEvent evt) {
        Screen.getScreen().setScreen("GameQuestionNote", questionsList);
    }
}
