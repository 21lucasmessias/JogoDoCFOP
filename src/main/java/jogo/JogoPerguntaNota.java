package jogo;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import menu.MenuPrincipal;
import pergunta.Pergunta;
import pergunta.PerguntaNotaFiscal;
import resposta.RespostaCerta;
import resposta.RespostaErrada;
import utils.Memento;
import utils.telaCadastro;

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
 *
 * @author cesar
 */
public class JogoPerguntaNota extends JFrame {

    private String[] conta = null;
    private Jogo jogoAtual;
    private ArrayList<Pergunta> listaDePerguntas;
    private PerguntaNotaFiscal perguntaAtual;
    private static final int TROFEU1 = 6;
    private static final int TROFEU2 = 7;
    private static final int TROFEU3 = 8;
    private static final int TROFEU4 = 9;
    private static final int TROFEU5 = 10;
    private static final int TROFEU6 = 11;
    private ArrayList<Memento> mementos = new ArrayList<>();
    private Duration tempoResposta;
    private int qtdPerguntas;
    private int fase = 2;

    public JogoPerguntaNota() {
        initComponents();
    }

    public JogoPerguntaNota(String[] conta, ArrayList<Pergunta> perguntas) {
        initComponents();
        this.conta = conta;
        this.listaDePerguntas = perguntas;
        this.jogoAtual = Jogo.getInstance();
        this.jogoAtual.setFase(2);

        JFrame frame = new JFrame();
        String qtd = JOptionPane.showInputDialog(frame, "Digite a quantidade de Perguntas!", 8);
        this.jogoAtual.setQtdPerguntas(Integer.parseInt(qtd));
        this.qtdPerguntas = jogoAtual.getQtdPerguntas();
        while (qtdPerguntas < 8 || qtdPerguntas > 20) {
            JOptionPane.showMessageDialog(null, "Minimo: 08 perguntas e Máximo: 20!");
            qtd = JOptionPane.showInputDialog(frame, "Digite a quantidade de Perguntas!", 8);
            qtdPerguntas = Integer.parseInt(qtd);
        }
        preencherDados();
    }

    private void preencherDados() {
        perguntaAtual = (PerguntaNotaFiscal) listaDePerguntas.get((Integer.parseInt(levelState.getText()) - 1));
        
        numPerguntas.setText(String.valueOf(this.qtdPerguntas));
        tituloPergunta.setText(this.perguntaAtual.getTitulo());
        uf.setText(String.valueOf(this.perguntaAtual.getUf()));
        ano.setText(String.valueOf(this.perguntaAtual.getAno()));
        mes.setText(String.valueOf(this.perguntaAtual.getMes()));
        cnpj.setText(String.valueOf(this.perguntaAtual.getCnpj()));
        modelo.setText(String.valueOf(this.perguntaAtual.getModelo()));
        serie.setText(String.valueOf(this.perguntaAtual.getSerie()));
        numero.setText(String.valueOf(this.perguntaAtual.getNumero()));
        codNumerico.setText(String.valueOf(this.perguntaAtual.getCodigoNumerico()));
        digito.setText(String.valueOf(this.perguntaAtual.getDigitoVerificador()));
        
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
                Logger.getLogger(telaCadastro.class.getName()).log(Level.SEVERE, null, ex);
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
            if (tempoResposta.getSeconds() < 3) {
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
        // trofeu 08 acertos seguidos
        if (conta[TROFEU4].equals("0")) {
            if (jogoAtual.getSeq() >= 8) {
                conta[TROFEU4] = "1";
                mudancas++;
            }
        }
        // trofeu finalizar a fase sem errar
        if (conta[TROFEU5].equals("0")) {
            if (jogoAtual.getAcertos() == qtdPerguntas) {
                conta[TROFEU5] = "1";
                mudancas++;
            }
        }
        // trofeu completou o game
        if (conta[TROFEU6].equals("0")) {
            if (jogoAtual.getPerguntas() == qtdPerguntas) {
                conta[TROFEU6] = "1";
                mudancas++;
            }
        }
        if (mudancas > 0) {
            atualizarInfo();
        }
    }
//desabilita campos respostas

    public void desabRespostas() {
        opcao1.setEnabled(false);
        opcao2.setEnabled(false);
        opcao3.setEnabled(false);
        opcao4.setEnabled(false);

    }

    private void responderPergunta(int opcao) {
        if (opcao >= 1 && opcao <= 4) {
            tempoResposta = Duration.between(getMemento().getActionTime(), mementos.get(0).getActionTime());
            System.out.println("resposta: " + opcao + " Resposta Certa: " + perguntaAtual.getRespostaCerta() + " Tempo levado: " + tempoResposta.toSeconds() + " segundos.");
            if (perguntaAtual.getRespostaCerta() == opcao) {
                jogoAtual.setAcertos(jogoAtual.getAcertos() + 1);
                jogoAtual.setPontuacao(jogoAtual.getPontuacao() + 80);
                jogoAtual.setSeq(jogoAtual.getSeq() + 1);
                RespostaCerta feedback;
                if (jogoAtual.getPerguntas() < 19 && jogoAtual.getErros() < 3) {
                    feedback = new RespostaCerta(false, conta);
                } else {
                    feedback = new RespostaCerta(true, conta);
                    feedback.setLocationRelativeTo(null);
                }
                feedback.setDefaultCloseOperation(RespostaCerta.DO_NOTHING_ON_CLOSE);
                feedback.setVisible(true);
            } else {
                jogoAtual.setErros(jogoAtual.getErros() + 1);
                jogoAtual.setSeq(0);
                int vidas = atualizarVidas();
                RespostaErrada feedback;
                if (jogoAtual.getPerguntas() < 19 && jogoAtual.getErros() < 3) {
                    feedback = new RespostaErrada(false, conta, vidas);
                } else {
                    feedback = new RespostaErrada(true, conta, vidas);
                    feedback.setLocationRelativeTo(null);
                }
                feedback.setDefaultCloseOperation(RespostaErrada.DO_NOTHING_ON_CLOSE);
                feedback.setVisible(true);
            }
            if (jogoAtual.getPerguntas() >= 21 || jogoAtual.getErros() == 3) {
                menuPrincipal();
            }
            // atualiza o level 
            jogoAtual.setPerguntas(jogoAtual.getPerguntas() + 1);
            if (jogoAtual.getPerguntas() <= 21) {
                levelState.setText(Integer.toString(jogoAtual.getPerguntas()));
                if (jogoAtual.getPerguntas() == qtdPerguntas + 1) {
                    btnResultado.setEnabled(true);
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
        System.out.println("Situacao: " + jogoAtual.getStatus() + " Pontuacao: " + jogoAtual.getPontuacao() + " Perguntas: " + jogoAtual.getPerguntas() + " Acertos: " + jogoAtual.getAcertos() + " Erros: " + jogoAtual.getErros() + " Seq: " + jogoAtual.getSeq());
    }

    public void menuPrincipal() {
        MenuPrincipal novaTentativa = new MenuPrincipal(conta);
        novaTentativa.setLocationRelativeTo(null);
        novaTentativa.setVisible(true);
        this.dispose();
    }

    public int atualizarVidas() {
        int vidas = 3 - jogoAtual.getErros();
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
        JLabel truckIcon = new JLabel();
        JLabel txtChave = new JLabel();
        JLabel jLabel4 = new JLabel();
        JLabel tituloDados = new JLabel();
        JLabel fundoTituloDados = new JLabel();
        JLabel jLabel2 = new JLabel();
        uf = new JLabel();
        ano = new JLabel();
        mes = new JLabel();
        cnpj = new JLabel();
        modelo = new JLabel();
        serie = new JLabel();
        numero = new JLabel();
        codNumerico = new JLabel();
        digito = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel fundoDados = new JLabel();
        levelInput = new JLabel();
        levelState = new JLabel();
        JLabel fundoLevel = new JLabel();
        JLabel trofeu = new JLabel();
        tituloPergunta = new JLabel();
        JLabel fundoPergunta = new JLabel();
        opcao1 = new JButton();
        opcao2 = new JButton();
        JLabel jLabel6 = new JLabel();
        opcao3 = new JButton();
        opcao4 = new JButton();
        JLabel madeText = new JLabel();
        JLabel btnSair = new JLabel();
        btnResultado = new JLabel();
        JLabel background = new JLabel();

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
        numPerguntas.setBounds(1240, 10, 30, 50);

        truckIcon.setIcon(new ImageIcon(getClass().getResource("/resources/truck.png"))); // NOI18N
        jPanel1.add(truckIcon);
        truckIcon.setBounds(290, 180, 80, 60);

        txtChave.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtChave.setText("CHAVE");
        jPanel1.add(txtChave);
        txtChave.setBounds(290, 430, 60, 40);

        jLabel4.setIcon(new ImageIcon(getClass().getResource("/resources/codBarras.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(110, 370, 440, 50);

        tituloDados.setFont(new java.awt.Font("Krungthep", 0, 14)); // NOI18N
        tituloDados.setForeground(new java.awt.Color(255, 255, 255));
        tituloDados.setText("CHAVE DE ACESSO DA NOTA");
        jPanel1.add(tituloDados);
        tituloDados.setBounds(230, 260, 300, 20);

        fundoTituloDados.setIcon(new ImageIcon(getClass().getResource("/resources/fundoTituloDados.png"))); // NOI18N
        jPanel1.add(fundoTituloDados);
        fundoTituloDados.setBounds(180, 260, 160, 23);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("/");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(1230, 14, 10, 40);

        uf.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        uf.setText("21");
        uf.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        uf.setEnabled(false);
        jPanel1.add(uf);
        uf.setBounds(70, 490, 23, 30);

        ano.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ano.setText("16");
        ano.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        ano.setEnabled(false);
        jPanel1.add(ano);
        ano.setBounds(100, 490, 23, 30);

        mes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        mes.setText("03");
        mes.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        mes.setEnabled(false);
        jPanel1.add(mes);
        mes.setBounds(120, 490, 23, 30);

        cnpj.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cnpj.setText("07100000039412");
        cnpj.setBorder(BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        cnpj.setEnabled(false);
        jPanel1.add(cnpj);
        cnpj.setBounds(150, 490, 150, 30);

        modelo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        modelo.setText("03");
        modelo.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        modelo.setEnabled(false);
        jPanel1.add(modelo);
        modelo.setBounds(300, 490, 23, 30);

        serie.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        serie.setText("532");
        serie.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        serie.setEnabled(false);
        jPanel1.add(serie);
        serie.setBounds(330, 490, 35, 30);

        numero.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        numero.setText("036000039");
        numero.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        numero.setEnabled(false);
        jPanel1.add(numero);
        numero.setBounds(370, 490, 97, 30);

        codNumerico.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        codNumerico.setText("126353203");
        codNumerico.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        codNumerico.setEnabled(false);
        jPanel1.add(codNumerico);
        codNumerico.setBounds(470, 490, 97, 30);

        digito.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        digito.setText("6");
        digito.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        digito.setEnabled(false);
        jPanel1.add(digito);
        digito.setBounds(570, 490, 15, 30);

        jLabel3.setIcon(new ImageIcon(getClass().getResource("/resources/fundoTituloDados.png"))); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(320, 260, 196, 23);

        fundoDados.setIcon(new ImageIcon(getClass().getResource("/resources/fundoDados.png"))); // NOI18N
        jPanel1.add(fundoDados);
        fundoDados.setBounds(70, 150, 520, 500);

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
        tituloPergunta.setBounds(610, 160, 740, 32);

        fundoPergunta.setIcon(new ImageIcon(getClass().getResource("/resources/tituloPergunta.png"))); // NOI18N
        jPanel1.add(fundoPergunta);
        fundoPergunta.setBounds(600, 150, 250, 50);

        opcao1.setBackground(new java.awt.Color(255, 255, 255));
        opcao1.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        opcao1.setText("opcao1");
        opcao1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcao1ActionPerformed(evt);
            }
        });
        jPanel1.add(opcao1);
        opcao1.setBounds(720, 240, 540, 60);

        opcao2.setBackground(new java.awt.Color(255, 255, 255));
        opcao2.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        opcao2.setText("opcao2");
        opcao2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcao2ActionPerformed(evt);
            }
        });
        jPanel1.add(opcao2);
        opcao2.setBounds(720, 320, 540, 60);

        jLabel6.setIcon(new ImageIcon(getClass().getResource("/resources/tituloPergunta.png"))); // NOI18N
        jPanel1.add(jLabel6);
        jLabel6.setBounds(810, 150, 560, 50);

        opcao3.setBackground(new java.awt.Color(255, 255, 255));
        opcao3.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        opcao3.setText("opcao3");
        opcao3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcao3ActionPerformed(evt);
            }
        });
        jPanel1.add(opcao3);
        opcao3.setBounds(720, 400, 540, 60);

        opcao4.setBackground(new java.awt.Color(255, 255, 255));
        opcao4.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        opcao4.setText("opcao4");
        opcao4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcao4ActionPerformed(evt);
            }
        });
        jPanel1.add(opcao4);
        opcao4.setBounds(720, 480, 540, 60);

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

        btnResultado.setIcon(new ImageIcon(getClass().getResource("/resources/botaoResultadoe.png"))); // NOI18N
        btnResultado.setText("btnProxiFase");
        btnResultado.setEnabled(false);
        btnResultado.setName(""); // NOI18N
        btnResultado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResultadoMouseClicked(evt);
            }
        });
        jPanel1.add(btnResultado);
        btnResultado.setBounds(850, 550, 280, 80);
        btnResultado.getAccessibleContext().setAccessibleDescription("");

        background.setIcon(new ImageIcon(getClass().getResource("/resources/fundo_sistema.jpg"))); // NOI18N
        background.setText("sdaa");
        jPanel1.add(background);
        background.setBounds(0, 0, 1392, 770);

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

    private void btnResultadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResultadoMouseClicked
        // TODO add your handling code here:        
        if (jogoAtual.getPerguntas() == qtdPerguntas + 1) {
            levelState.setText(Integer.toString(jogoAtual.getPerguntas()));
            JogoResultado resultado = new JogoResultado(conta);
            resultado.setLocationRelativeTo(null);
            resultado.setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_btnResultadoMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
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
    JLabel ano;
    JLabel btnResultado;
    JLabel cnpj;
    JLabel codNumerico;
    JLabel digito;
    JLabel levelInput;
    JLabel levelState;
    JLabel lifes;
    JLabel mes;
    JLabel modelo;
    JLabel numPerguntas;
    JLabel numero;
    JButton opcao1;
    JButton opcao2;
    JButton opcao3;
    JButton opcao4;
    JLabel serie;
    JLabel tituloPergunta;
    JLabel uf;
    // End of variables declaration//GEN-END:variables

}
