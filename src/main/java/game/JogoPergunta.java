package game;

import home.Home;
import questions.Question;
import questions.QuestionCfop;
import questions.RespostaCerta;
import questions.RespostaErrada;
import utils.Memento;
import login.Register;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author cesar
 */
public class JogoPergunta extends JFrame {

    private String[] conta = null;
    private Game jogoAtual;
    private ArrayList<Question> listaDePerguntas;
    private QuestionCfop perguntaAtual;
    private static final int TROFEU1 = 6;
    private static final int TROFEU2 = 7;
    private static final int TROFEU3 = 8;
    private static final int TROFEU4 = 9;
    private static final int TROFEU5 = 10;
    private static final int TROFEU6 = 11;
    private final ArrayList<Memento> mementos = new ArrayList<>();
    private Duration tempoResposta;
    private int qtdPerguntas;

    public JogoPergunta() {
        initComponents();
    }

    public JogoPergunta(String[] conta, ArrayList<Question> perguntas) {
        initComponents();
        this.conta = conta;
        this.listaDePerguntas = perguntas;
        this.jogoAtual = Game.getInstance();
        this.jogoAtual.setLvl(1);

        JFrame frame = new JFrame();
        String qtd = JOptionPane.showInputDialog(frame, "Digite a quantidade de Perguntas!", 8);
        this.jogoAtual.setQuestionsQuantity(Integer.parseInt(qtd));
        this.qtdPerguntas = jogoAtual.getQuestionsQuantity();
        while (qtdPerguntas < 8 || qtdPerguntas > 20) {
            JOptionPane.showMessageDialog(frame, "Minimo: 08 perguntas e Máximo: 20!");
            qtd = JOptionPane.showInputDialog(frame, "Digite a quantidade de Perguntas!", 8);
            qtdPerguntas = Integer.parseInt(qtd);
        }
        preencherDados();
    }

    private void preencherDados() {
        perguntaAtual = (QuestionCfop) listaDePerguntas.get((Integer.parseInt(levelState.getText()) - 1));
        String opera;
        if (this.perguntaAtual.getOperacao() == 1) {
            opera = "Entrada";
        } else {
            opera = "Saída";
        }

        numPerguntas.setText(String.valueOf(this.qtdPerguntas));
        operacaoInput.setText(opera);
        cidadeInput.setText(this.perguntaAtual.getEmissora());
        remetenteInput.setText(this.perguntaAtual.getRemetente());
        destinatarioInput.setText(this.perguntaAtual.getDestinatario());
        tituloPergunta.setText(this.perguntaAtual.getTitulo());
        int[] respostas = this.perguntaAtual.getRespostas();
        opcao1.setText(String.valueOf(respostas[0]));
        opcao2.setText(String.valueOf(respostas[1]));
        opcao3.setText(String.valueOf(respostas[2]));
        opcao4.setText(String.valueOf(respostas[3]));
        Memento tempoCarregou = new Memento(Instant.now());
        addMemento(tempoCarregou);
    }

    private void atualizarInfo() {
        String completou = "não";
        if (jogoAtual.getSeq() > Integer.parseInt(conta[5])) {
            conta[5] = Integer.toString(jogoAtual.getSeq());
        }
        File f = new File(conta[0] + ".txt");
        if (f.exists()) {
            try (PrintWriter out = new PrintWriter(conta[0] + ".txt")) {
                String strPass = conta[1];
                int[] conquistas = new int[6];
                for (int i = 0; i < 6; i++) {
                    conquistas[i] = Integer.parseInt(conta[6 + i]);
                }
                if (conquistas[5] == 1) {
                    completou = "sim";
                }
                out.println(conta[0] + "," + strPass + "," + conta[2] + "," + conta[3] + "," + completou + "," + conta[5] + "," + conquistas[0] + "," + conquistas[1] + "," + conquistas[2] + "," + conquistas[3] + "," + conquistas[4] + "," + conquistas[5]);
                System.out.println("Conta atualizada com sucesso.");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Erro ao atualizar conta: conta não existe.");
        }
    }

    private void verificarTrofeus() {
        int mudancas = 0;
        // trofeu iniciou um jogo
        if (conta[TROFEU1].equals("0")) {
            conta[TROFEU1] = "1";
            mudancas++;
        }
        // trofeu ligeirinho
        if (conta[TROFEU2].equals("0")) {
            if (tempoResposta.getSeconds() < 5) {
                conta[TROFEU2] = "1";
                mudancas++;
            }
        }
        // trofeu 5 acertos seguidos
        if (conta[TROFEU3].equals("0")) {
            if (jogoAtual.getSeq() >= 5) {
                conta[TROFEU3] = "1";
                mudancas++;
            }
        }
        // trofeu 8 acertos seguidos
        if (conta[TROFEU4].equals("0")) {
            if (jogoAtual.getSeq() >= 8) {
                conta[TROFEU4] = "1";
                mudancas++;
            }
        }
        // trofeu completar a fase sem errar
        if (conta[TROFEU5].equals("0")) {
            if (jogoAtual.getRightAnswers() == qtdPerguntas) {
                conta[TROFEU5] = "1";
                mudancas++;
            }
        }
        // trofeu completou a primeira fase
       /* if (conta[TROFEU6].equals("0")) {
            if (jogoAtual.getPerguntas() == 20) {
                conta[TROFEU6] = "1";
                mudancas++;
            }
        }*/
        if (mudancas > 0) {
            atualizarInfo();
        }
    }

    public void desabRespostas() {
        opcao1.setEnabled(false);
        opcao2.setEnabled(false);
        opcao3.setEnabled(false);
        opcao4.setEnabled(false);
    }

    private void responderPergunta(int opcao) {
        if (opcao >= 1 && opcao <= 4) {
            tempoResposta = Duration.between(getMemento().getActionTime(), mementos.get(0).getActionTime());
            System.out.println("pergunta.resposta: " + opcao + " Resposta Certa: " + perguntaAtual.getRespostaCerta() + " Tempo levado: " + tempoResposta.toSeconds() + " segundos.");
            if (perguntaAtual.getRespostaCerta() == opcao) {
                jogoAtual.setRightAnswers(jogoAtual.getRightAnswers() + 1);
                jogoAtual.setScore(jogoAtual.getScore() + 80);
                jogoAtual.setSeq(jogoAtual.getSeq() + 1);
                RespostaCerta feedback;
                if (jogoAtual.getQuestions() < 19 && jogoAtual.getWrongAnswers() < 3) {
                    feedback = new RespostaCerta(false, conta);
                } else {
                    feedback = new RespostaCerta(true, conta);
                    feedback.setLocationRelativeTo(null);
                }
                feedback.setDefaultCloseOperation(RespostaCerta.DO_NOTHING_ON_CLOSE);
                feedback.setVisible(true);
            } else {
                jogoAtual.setWrongAnswers(jogoAtual.getWrongAnswers() + 1);
                jogoAtual.setSeq(0);
                int vidas = atualizarVidas();
                RespostaErrada feedback;
                if (jogoAtual.getQuestions() < 19 && jogoAtual.getWrongAnswers() < 3) {
                    feedback = new RespostaErrada(false, conta, vidas);
                } else {
                    feedback = new RespostaErrada(true, conta, vidas);
                    feedback.setLocationRelativeTo(null);
                }
                feedback.setDefaultCloseOperation(RespostaErrada.DO_NOTHING_ON_CLOSE);
                feedback.setVisible(true);
            }
            if (jogoAtual.getQuestions() >= 21 || jogoAtual.getWrongAnswers() == 3) {
                menuPrincipal();
            }

            // atualiza o level
            jogoAtual.setQuestions(jogoAtual.getQuestions() + 1);
            if (jogoAtual.getQuestions() <= 21) {
                levelState.setText(Integer.toString(jogoAtual.getQuestions()));
                if (jogoAtual.getQuestions() == qtdPerguntas + 1) {
                    btnProxFase.setEnabled(true);
                    desabRespostas();
                }

            } else {
                levelState.setText("?");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Resposta Inválida.");
        }
        try {
            Thread.sleep(500);
            verificarTrofeus();
            preencherDados();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Situacao: " + jogoAtual.getStatus() + " Pontuacao: " + jogoAtual.getScore() + " Perguntas: " + jogoAtual.getQuestions() + " Acertos: " + jogoAtual.getRightAnswers() + " Erros: " + jogoAtual.getWrongAnswers() + " Seq: " + jogoAtual.getSeq());
    }

    public void menuPrincipal() {
        Home novaTentativa = new Home(conta);
        novaTentativa.setLocationRelativeTo(null);
        novaTentativa.setVisible(true);
        this.dispose();
    }

    public int atualizarVidas() {
        int vidas = 3 - jogoAtual.getWrongAnswers();
        ImageIcon vida = new ImageIcon(getClass().getResource("/resources/" + vidas + "vidas.png"));
        lifes.setIcon(vida);
        return vidas;
    }

    public void addMemento(Memento m) {
        mementos.add(0, m);
    }

    public Memento getMemento() {
        return mementos.get(1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPanel jPanel1 = new JPanel();
        JLabel titleLeft = new JLabel();
        lifes = new JLabel();
        numPerguntas = new JLabel();
        JLabel jLabel1 = new JLabel();
        JLabel truckIcon = new JLabel();
        JLabel tituloDados = new JLabel();
        JLabel fundoTituloDados = new JLabel();
        JLabel operacaoTitulo = new JLabel();
        operacaoInput = new JLabel();
        JLabel cidadeTitulo = new JLabel();
        cidadeInput = new JLabel();
        JLabel remetenteTitulo = new JLabel();
        remetenteInput = new JLabel();
        JLabel destinatarioTitulo = new JLabel();
        destinatarioInput = new JLabel();
        JLabel fundoDados = new JLabel();
        levelInput = new JLabel();
        levelState = new JLabel();
        JLabel fundoLevel = new JLabel();
        JLabel trofeu = new JLabel();
        tituloPergunta = new JLabel();
        JLabel fundoPergunta = new JLabel();
        opcao1 = new JButton();
        opcao2 = new JButton();
        opcao3 = new JButton();
        opcao4 = new JButton();
        JLabel madeText = new JLabel();
        JLabel btnSair = new JLabel();
        btnProxFase = new JLabel();
        JLabel background = new JLabel();
        JLabel jLabel2 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setLayout(null);

        titleLeft.setIcon(new ImageIcon(getClass().getResource("/resources/title_left.png"))); // NOI18N
        jPanel1.add(titleLeft);
        titleLeft.setBounds(20, 20, 216, 34);

        lifes.setIcon(new ImageIcon(getClass().getResource("/resources/3vidas.png"))); // NOI18N
        jPanel1.add(lifes);
        lifes.setBounds(930, 10, 170, 50);

        numPerguntas.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        numPerguntas.setForeground(new java.awt.Color(255, 255, 255));
        numPerguntas.setText("20");
        jPanel1.add(numPerguntas);
        numPerguntas.setBounds(1250, 10, 40, 50);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("/");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(1230, 10, 14, 50);

        truckIcon.setIcon(new ImageIcon(getClass().getResource("/resources/truck.png"))); // NOI18N
        jPanel1.add(truckIcon);
        truckIcon.setBounds(330, 160, 80, 60);

        tituloDados.setFont(new java.awt.Font("Krungthep", 0, 14)); // NOI18N
        tituloDados.setForeground(new java.awt.Color(255, 255, 255));
        tituloDados.setText("DADOS DA NOTA");
        jPanel1.add(tituloDados);
        tituloDados.setBounds(320, 230, 118, 20);

        fundoTituloDados.setIcon(new ImageIcon(getClass().getResource("/resources/fundoTituloDados.png"))); // NOI18N
        jPanel1.add(fundoTituloDados);
        fundoTituloDados.setBounds(290, 230, 158, 23);

        operacaoTitulo.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        operacaoTitulo.setForeground(new java.awt.Color(9, 0, 108));
        operacaoTitulo.setText("OPERAÇÃO");
        jPanel1.add(operacaoTitulo);
        operacaoTitulo.setBounds(140, 290, 190, 24);

        operacaoInput.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        operacaoInput.setForeground(new java.awt.Color(142, 142, 142));
        operacaoInput.setHorizontalAlignment(SwingConstants.RIGHT);
        operacaoInput.setText("OPERAÇÃO");
        operacaoInput.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                operacaoInputComponentAdded(evt);
            }
        });
        jPanel1.add(operacaoInput);
        operacaoInput.setBounds(342, 290, 240, 24);

        cidadeTitulo.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        cidadeTitulo.setForeground(new java.awt.Color(9, 0, 108));
        cidadeTitulo.setText("CIDADE EMISSORA");
        jPanel1.add(cidadeTitulo);
        cidadeTitulo.setBounds(140, 360, 190, 20);

        cidadeInput.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        cidadeInput.setForeground(new java.awt.Color(142, 142, 142));
        cidadeInput.setHorizontalAlignment(SwingConstants.RIGHT);
        cidadeInput.setText("CIDADE/UF");
        cidadeInput.setToolTipText("");
        jPanel1.add(cidadeInput);
        cidadeInput.setBounds(342, 360, 240, 24);

        remetenteTitulo.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        remetenteTitulo.setForeground(new java.awt.Color(9, 0, 108));
        remetenteTitulo.setText("REMETENTE");
        jPanel1.add(remetenteTitulo);
        remetenteTitulo.setBounds(140, 470, 180, 20);

        remetenteInput.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        remetenteInput.setForeground(new java.awt.Color(142, 142, 142));
        remetenteInput.setHorizontalAlignment(SwingConstants.RIGHT);
        remetenteInput.setText("CIDADE/UF");
        remetenteInput.setToolTipText("");
        jPanel1.add(remetenteInput);
        remetenteInput.setBounds(342, 470, 240, 24);

        destinatarioTitulo.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        destinatarioTitulo.setForeground(new java.awt.Color(9, 0, 108));
        destinatarioTitulo.setText("DESTINATÁRIO");
        jPanel1.add(destinatarioTitulo);
        destinatarioTitulo.setBounds(140, 540, 180, 20);

        destinatarioInput.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        destinatarioInput.setForeground(new java.awt.Color(142, 142, 142));
        destinatarioInput.setHorizontalAlignment(SwingConstants.RIGHT);
        destinatarioInput.setText("CIDADE/UF");
        destinatarioInput.setToolTipText("");
        jPanel1.add(destinatarioInput);
        destinatarioInput.setBounds(342, 540, 240, 24);

        fundoDados.setIcon(new ImageIcon(getClass().getResource("/resources/fundoDados.png"))); // NOI18N
        jPanel1.add(fundoDados);
        fundoDados.setBounds(100, 140, 520, 500);

        levelInput.setBackground(new java.awt.Color(255, 255, 255));
        levelInput.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        levelInput.setForeground(new java.awt.Color(255, 255, 255));
        levelInput.setHorizontalAlignment(SwingConstants.CENTER);
        levelInput.setText("LEVEL:");
        jPanel1.add(levelInput);
        levelInput.setBounds(1120, 10, 110, 50);

        levelState.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        levelState.setForeground(new java.awt.Color(255, 255, 255));
        levelState.setHorizontalAlignment(SwingConstants.CENTER);
        levelState.setText("1");
        levelState.setToolTipText("");
        jPanel1.add(levelState);
        levelState.setBounds(1190, 10, 70, 50);

        fundoLevel.setIcon(new ImageIcon(getClass().getResource("/resources/fundoLevel.png"))); // NOI18N
        jPanel1.add(fundoLevel);
        fundoLevel.setBounds(1120, 10, 170, 46);

        trofeu.setIcon(new ImageIcon(getClass().getResource("/resources/trofeu.png"))); // NOI18N
        jPanel1.add(trofeu);
        trofeu.setBounds(1310, 10, 46, 50);

        tituloPergunta.setFont(new java.awt.Font("Krungthep", 0, 24)); // NOI18N
        tituloPergunta.setForeground(new java.awt.Color(255, 255, 255));
        tituloPergunta.setHorizontalAlignment(SwingConstants.CENTER);
        tituloPergunta.setText("TITULO PERGUNTA");
        jPanel1.add(tituloPergunta);
        tituloPergunta.setBounds(730, 160, 550, 32);

        fundoPergunta.setIcon(new ImageIcon(getClass().getResource("/resources/tituloPergunta.png"))); // NOI18N
        jPanel1.add(fundoPergunta);
        fundoPergunta.setBounds(730, 150, 550, 50);

        opcao1.setBackground(new java.awt.Color(255, 255, 255));
        opcao1.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        opcao1.setText("opcao1");
        opcao1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcao1ActionPerformed(evt);
            }
        });
        jPanel1.add(opcao1);
        opcao1.setBounds(740, 240, 540, 60);

        opcao2.setBackground(new java.awt.Color(255, 255, 255));
        opcao2.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        opcao2.setText("opcao2");
        opcao2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcao2ActionPerformed(evt);
            }
        });
        jPanel1.add(opcao2);
        opcao2.setBounds(740, 320, 540, 60);

        opcao3.setBackground(new java.awt.Color(255, 255, 255));
        opcao3.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        opcao3.setText("opcao3");
        opcao3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcao3ActionPerformed(evt);
            }
        });
        jPanel1.add(opcao3);
        opcao3.setBounds(740, 400, 540, 60);

        opcao4.setBackground(new java.awt.Color(255, 255, 255));
        opcao4.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        opcao4.setText("opcao4");
        opcao4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcao4ActionPerformed(evt);
            }
        });
        jPanel1.add(opcao4);
        opcao4.setBounds(740, 480, 540, 60);

        madeText.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        madeText.setForeground(new java.awt.Color(255, 255, 255));
        madeText.setText("FEITO POR CESAR VERGARA");
        jPanel1.add(madeText);
        madeText.setBounds(20, 730, 260, 30);

        btnSair.setIcon(new ImageIcon(getClass().getResource("/resources/botaoSairBottom.png"))); // NOI18N
        btnSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSairMouseClicked(evt);
            }
        });
        jPanel1.add(btnSair);
        btnSair.setBounds(1170, 710, 180, 45);

        btnProxFase.setIcon(new ImageIcon(getClass().getResource("/resources/botaoResultadoe.png"))); // NOI18N
        btnProxFase.setText("btnProxiFase");
        btnProxFase.setEnabled(false);
        btnProxFase.setName(""); // NOI18N
        btnProxFase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProxFaseMouseClicked(evt);
            }
        });
        jPanel1.add(btnProxFase);
        btnProxFase.setBounds(870, 550, 280, 80);
        btnProxFase.getAccessibleContext().setAccessibleName("");
        btnProxFase.getAccessibleContext().setAccessibleDescription("");

        background.setIcon(new ImageIcon(getClass().getResource("/resources/fundo_sistema.jpg"))); // NOI18N
        background.setText("sdaa");
        jPanel1.add(background);
        background.setBounds(0, 0, 1392, 770);
        background.getAccessibleContext().setAccessibleName("");

        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(1180, 100, 34, 14);

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
        int result = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja sair?", "Encerrar Tentativa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            menuPrincipal();
        }
    }//GEN-LAST:event_btnSairMouseClicked

    private void opcao2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcao2ActionPerformed
        Memento tempoRespondeu = new Memento(Instant.now());
        addMemento(tempoRespondeu);
        responderPergunta(2);
    }//GEN-LAST:event_opcao2ActionPerformed

    private void opcao1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcao1ActionPerformed
        Memento tempoRespondeu = new Memento(Instant.now());
        addMemento(tempoRespondeu);
        responderPergunta(1);
    }//GEN-LAST:event_opcao1ActionPerformed

    private void opcao3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcao3ActionPerformed
        Memento tempoRespondeu = new Memento(Instant.now());
        addMemento(tempoRespondeu);
        responderPergunta(3);
    }//GEN-LAST:event_opcao3ActionPerformed

    private void opcao4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcao4ActionPerformed
        Memento tempoRespondeu = new Memento(Instant.now());
        addMemento(tempoRespondeu);
        responderPergunta(4);
    }//GEN-LAST:event_opcao4ActionPerformed

    private void operacaoInputComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_operacaoInputComponentAdded
    }//GEN-LAST:event_operacaoInputComponentAdded

    private void btnProxFaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProxFaseMouseClicked
        // TODO add your handling code here:
        if (jogoAtual.getQuestions() == qtdPerguntas + 1) {
            levelState.setText(Integer.toString(jogoAtual.getQuestions()));
            JogoResultado resultado = new JogoResultado(conta);
            resultado.setLocationRelativeTo(null);
            resultado.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnProxFaseMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JogoPergunta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    JLabel btnProxFase;
    JLabel cidadeInput;
    JLabel destinatarioInput;
    JLabel levelInput;
    JLabel levelState;
    JLabel lifes;
    JLabel numPerguntas;
    JButton opcao1;
    JButton opcao2;
    JButton opcao3;
    JButton opcao4;
    JLabel operacaoInput;
    JLabel remetenteInput;
    JLabel tituloPergunta;
    // End of variables declaration//GEN-END:variables

}
