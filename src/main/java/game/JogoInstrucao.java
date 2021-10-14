package game;

import home.Home;
import pergunta.Pergunta;
import pergunta.PerguntaCfop;
import utils.ordenarContexto;
import utils.perguntasAleatorio;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class JogoInstrucao extends JFrame {

    ArrayList<Pergunta> listaDePerguntas = new ArrayList<>();
    protected String[] conta = null;
    private Game jogoAtual;
    private JPanel mainPanel;
    private JLabel labelTest;

    public JogoInstrucao() {
        inicializarComponentes();
    }

    public JogoInstrucao(String[] conta) {
        this.conta = conta;
        this.jogoAtual = Game.getInstance();

        inicializarComponentes();
        limparJogo();
        preencherPerguntas();

        // padrao strategy
        ordenarContexto ctx = new ordenarContexto();
        ctx.setOrdemStrategy(new perguntasAleatorio());
        ctx.criarLista(listaDePerguntas);
    }

    public void inicializarComponentes() {
        initComponents();
    }

    private void limparJogo() {
        jogoAtual.setRightAnswers(0);
        jogoAtual.setWrongAnswers(0);
        jogoAtual.setQuestions(1);
        jogoAtual.setScore(0);
        jogoAtual.setSeq(0);
        jogoAtual.setStatus(1);
    }

    public void preencherPerguntas() {

        int[] respostas = {6, 3, 2, 5};
        Pergunta aux = new PerguntaCfop(1, 2, "Sao Paulo/SP", "Santos/SP", "Sao Jose do Rio Preto/SP", "Qual é o código correto?", respostas, 4);
        listaDePerguntas.add(aux);
        int[] respostas2 = {7, 5, 6, 1};
        aux = new PerguntaCfop(2, 2, "Ipatinga/MG", "Ipatinga/MG", "Rio de Janeiro/RJ", "Qual é o código correto?", respostas2, 3);
        listaDePerguntas.add(aux);
        int[] respostas3 = {2, 1, 4, 3};
        aux = new PerguntaCfop(3, 1, "Aracaju/RJ", "Aracaju/RJ", "Lagarto/SE", "Qual é o código correto?", respostas3, 1);
        listaDePerguntas.add(aux);
        int[] respostas4 = {5, 9, 6, 7};
        aux = new PerguntaCfop(4, 2, "Bauru/SP", "Bauru/SP", "Araguaína/TO", "Qual é o código correto?", respostas4, 3);
        listaDePerguntas.add(aux);
        int[] respostas5 = {1, 5, 3, 2};
        aux = new PerguntaCfop(5, 1, "Marituba/PA", "Marituba/PA", "Campina Grande/PB", "Qual é o código correto?", respostas5, 4);
        listaDePerguntas.add(aux);
        int[] respostas6 = {5, 2, 3, 6};
        aux = new PerguntaCfop(6, 2, "Colatina/ES", "Gov. Valadares/MG", "Patos/PB", "Qual é o código correto?", respostas6, 4);
        listaDePerguntas.add(aux);
        int[] respostas7 = {2, 3, 1, 4};
        aux = new PerguntaCfop(7, 1, "Boa Vista/RR", "Boa Vista/RR", "Camaragibe/PE", "Qual é o código correto?", respostas7, 1);
        listaDePerguntas.add(aux);
        int[] respostas8 = {2, 1, 3, 9};
        aux = new PerguntaCfop(8, 1, "Corumbá/MS", "Corumbá/MS", "Campo Grande/MS", "Qual é o código correto?", respostas8, 2);
        listaDePerguntas.add(aux);
        int[] respostas9 = {5, 3, 6, 2};
        aux = new PerguntaCfop(9, 2, "Rio Claro/SP", "Rio Claro/SP", "Lages/SC", "Qual é o código correto?", respostas9, 3);
        listaDePerguntas.add(aux);
        int[] respostas10 = {6, 4, 5, 7};
        aux = new PerguntaCfop(10, 2, "Passos/MG", "Passos/MG", "João Pessoa/PB", "Qual é o código correto?", respostas10, 1);
        listaDePerguntas.add(aux);
        int[] respostas11 = {1, 3, 9, 2};
        aux = new PerguntaCfop(11, 1, "Crato/CE", "Crato/CE", "Umuarama/PR", "Qual é o código correto?", respostas11, 4);
        listaDePerguntas.add(aux);
        int[] respostas12 = {2, 5, 7, 6};
        aux = new PerguntaCfop(12, 2, "Palmas/TO", "Palmas/TO", "Luziânia/GO", "Qual é o código correto?", respostas12, 4);
        listaDePerguntas.add(aux);
        int[] respostas13 = {6, 2, 5, 3};
        aux = new PerguntaCfop(13, 2, "São José/SC", "Curitiba/PR", "Maringá/PR", "Qual é o código correto?", respostas13, 3);
        listaDePerguntas.add(aux);
        int[] respostas14 = {5, 9, 7, 6};
        aux = new PerguntaCfop(14, 2, "Foz do Iguaçu/PR", "Foz do Iguaçu/PR", "Cidad Del Leste/PAR", "Qual é o código correto?", respostas14, 3);
        listaDePerguntas.add(aux);
        int[] respostas15 = {3, 2, 1, 5};
        aux = new PerguntaCfop(15, 1, "Parnaíba/PI", "Parnaíba/PI", "Itajaí/SC", "Qual é o código correto?", respostas15, 2);
        listaDePerguntas.add(aux);
        int[] respostas16 = {6, 5, 3, 2};
        aux = new PerguntaCfop(16, 2, "Recife/PE", "Recife/PE", "Rio Verde/GO", "Qual é o código correto?", respostas16, 1);
        listaDePerguntas.add(aux);
        int[] respostas17 = {1, 9, 3, 2};
        aux = new PerguntaCfop(17, 1, "Rio Branco/AC", "Rio Branco/AC", "Breves/PA", "Qual é o código correto?", respostas17, 4);
        listaDePerguntas.add(aux);
        int[] respostas18 = {1, 5, 6, 3};
        aux = new PerguntaCfop(18, 2, "Cabo Frio/RJ", "Cabo Frio/RJ", "Rio de Janeiro/RJ", "Qual é o código correto?", respostas18, 2);
        listaDePerguntas.add(aux);
        int[] respostas19 = {9, 3, 2, 1};
        aux = new PerguntaCfop(19, 1, "Brasília/DF", "Goiânia/GO", "Campinas/SP", "Qual é o código correto?", respostas19, 3);
        listaDePerguntas.add(aux);
        int[] respostas20 = {2, 1, 3, 5};
        aux = new PerguntaCfop(20, 1, "São Caetano do Sul/SP", "São Caetano do Sul/SP", "São Paulo/SP", "Qual é o código correto?", respostas20, 2);
        listaDePerguntas.add(aux);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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

        titleLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/title_left.png"))); // NOI18N
        jPanel1.add(titleLeft);
        titleLeft.setBounds(20, 20, 216, 34);

        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/botao_Jogar.png"))); // NOI18N
        btnPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPlayMouseClicked(evt);
            }
        });
        jPanel1.add(btnPlay);
        btnPlay.setBounds(720, 520, 520, 160);

        imagemVideo.setForeground(new java.awt.Color(102, 102, 0));
        imagemVideo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/infos.png"))); // NOI18N
        jPanel1.add(imagemVideo);
        imagemVideo.setBounds(110, 110, 1150, 570);

        madeText.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        madeText.setForeground(new java.awt.Color(255, 255, 255));
        madeText.setText("FEITO POR CESAR VERGARA");
        jPanel1.add(madeText);
        madeText.setBounds(20, 730, 260, 30);

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/botaoSairBottom.png"))); // NOI18N
        btnSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSairMouseClicked(evt);
            }
        });
        jPanel1.add(btnSair);
        btnSair.setBounds(1170, 710, 180, 45);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/fundo_sistema.jpg"))); // NOI18N
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

    private void btnSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseClicked
        int result = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja sair?", "Encerrar Tentativa",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            Home jogo = new Home(conta);
            jogo.setLocationRelativeTo(null);
            jogo.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnSairMouseClicked

    private void btnPlayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayMouseClicked
        JogoPergunta perguntaJogo = new JogoPergunta(conta, listaDePerguntas);
        perguntaJogo.setLocationRelativeTo(null);
        perguntaJogo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPlayMouseClicked


    public static void main(String[] args) throws FileNotFoundException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */

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
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JogoInstrucao().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
