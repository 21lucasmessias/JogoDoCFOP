package jogo;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import home.Conquistas;
import home.Home;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/*
 * @author cesar
 */
public class JogoResultado extends javax.swing.JFrame {

    private Jogo jogo;
    String[] conta;
    private static final int TROFEU1 = 6;
    private static final int TROFEU2 = 7;
    private static final int TROFEU3 = 8;
    private static final int TROFEU4 = 9;
    private static final int TROFEU5 = 10;
    private static final int TROFEU6 = 11;
    private String arqNome;

    /**
     * Creates new form menu.MenuPrincipal
     */
    public JogoResultado() {
        initComponents();
    }

    /**
     * Creates new form menu.MenuPrincipal
     */
    public JogoResultado(String[] conta) {
        initComponents();
        this.conta = conta;
        this.jogo = Jogo.getInstance();
        preencherConquistas();
        //arquivoEscrita();
        //arquivoLeitura();
        ranking();
        if (jogo.getFase() == 2) {
            preencherIcone();
        }
    }

    //verifica o ranking dos jogadores
    public void ranking() {
        File rank1 = new File("rankingFase1.txt");
        File rank2 = new File("rankingFase2.txt");
        if (rank1.exists() && rank2.exists()) {
            try {
                if (jogo.getFase() == 1) {
                    this.arqNome = "rankingFase1.txt";
                } else {
                    this.arqNome = "rankingFase2.txt";
                }
                //le o arquivo conforme email de entrada (login)
                FileReader lerArquivo = new FileReader(jogo.getLogin() + ".txt");
                //  FileReader lerArquivo = new FileReader("jogador.txt");
                BufferedReader lerLinha = new BufferedReader(lerArquivo);
                String linha = lerLinha.readLine();
                String email = linha.split(",")[0];
                String nome = linha.split(",")[2];

                //ler o arquivo ranking.txt e verificar maior pontuacao                                                
                FileReader lerR = new FileReader(arqNome);
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
                FileWriter escreverArquivo = new FileWriter(arqNome, true);
                BufferedWriter escreverLinha = new BufferedWriter(escreverArquivo);

                if (jogo.getFase() == 1) {
                    escreverLinha.write(1 + "," + email + "," + nome + "," + jogo.getPontuacao());
                } else {
                    escreverLinha.write(2 + "," + email + "," + nome + "," + jogo.getPontuacao());
                }
                escreverLinha.newLine();
                escreverLinha.close();
                escreverArquivo.close();

                //ler o arquivo ranking
                //FileReader lerArqRanking = new FileReader("ranking.txt");
                FileReader lerArqRanking = new FileReader(arqNome);
                BufferedReader lerRanking = new BufferedReader(lerArqRanking);
                String rank;//  = lerRanking.readLine();

                //retorna total linhas arquivo ranking                
                LineNumberReader contaLinhas = new LineNumberReader(new FileReader(arqNome));
                contaLinhas.skip(Long.MAX_VALUE);
                int qtdLinhas = contaLinhas.getLineNumber() + 4;

                // array  pontuacao, jogador e login(email) com tamanho definido pelo total de linhas do arquivo
                Integer[] listaPontosJogador = new Integer[qtdLinhas];
                String[] listaNomeJogador = new String[qtdLinhas];
                String[] listaLoginJogador = new String[qtdLinhas];
                System.out.println(qtdLinhas);

                //escreve valores nos vetores pontuacao e jogadores                                      
                int i = 0;
                while ((rank = lerRanking.readLine()) != null && i <= qtdLinhas) {
                    String fase = rank.split(",")[0];
                    String login = rank.split(",")[1];
                    String nomeJogador = rank.split(",")[2];
                    String pontos = rank.split(",")[3];

                    listaLoginJogador[i] = login;
                    listaNomeJogador[i] = nomeJogador;
                    listaPontosJogador[i] = Integer.valueOf(pontos);
                    i++;
                }

                //algoritimo bubble sort ordenar arrays 5 primeiros colocados jogador e pontuacao
                for (int a = 0; a <= qtdLinhas; a++) {
                    for (int b = 1; b <= (qtdLinhas - 1); b++) {
                        if (listaPontosJogador[b] == null) {
                            listaPontosJogador[b] = 0;
                        }
                        if (listaPontosJogador[b - 1] < listaPontosJogador[b]) {
                            int temp = listaPontosJogador[b - 1];
                            listaPontosJogador[b - 1] = listaPontosJogador[b];
                            listaPontosJogador[b] = temp;

                            String flagNome = listaNomeJogador[b - 1];
                            listaNomeJogador[b - 1] = listaNomeJogador[b];
                            listaNomeJogador[b] = flagNome;

                            String flagLogin = listaLoginJogador[b - 1];
                            listaLoginJogador[b - 1] = listaLoginJogador[b];
                            listaLoginJogador[b] = flagLogin;
                        }
                    }
                }

                // recebe os valores dos arrays de pontuacao e jogadores
                int priLugar = listaPontosJogador[0];
                int segLugar = listaPontosJogador[1];
                int terLugar = listaPontosJogador[2];
                int quaLugar = listaPontosJogador[3];
                int quiLugar = listaPontosJogador[4];

                String jogPrimeiroNome = listaNomeJogador[0];
                String jogSegundoNome = listaNomeJogador[1];
                String jogTerceiroNome = listaNomeJogador[2];
                String jogQuartoNome = listaNomeJogador[3];
                String jogQuintoNome = listaNomeJogador[4];

                String jogPrimeiroLogin = listaLoginJogador[0];
                String jogSegundoLogin = listaLoginJogador[1];
                String jogTerceiroLogin = listaLoginJogador[2];
                String jogQuartoLogin = listaLoginJogador[3];
                String jogQuintoLogin = listaLoginJogador[4];

                //cria hash mape dos arrays de jogador e pontos
                HashMap<String, Integer> map = new HashMap<>();
                map.put(jogPrimeiroNome, priLugar);
                map.put(jogSegundoNome, segLugar);
                map.put(jogTerceiroNome, terLugar);
                map.put(jogQuartoNome, quaLugar);
                map.put(jogQuintoNome, quiLugar);

                Stream<Map.Entry<String, Integer>> sorteio = map.entrySet().stream().sorted(Map.Entry.comparingByValue());
                sorteio.forEach(System.out::println);

                //imprime jogado pontuacao
                pontosPrimeiroLugar.setText(String.valueOf(priLugar));
                pontosSegundoLugar.setText(String.valueOf(segLugar));
                pontosTerceiroLugar.setText(String.valueOf(terLugar));
                pontosQuartoLugar.setText(String.valueOf(quaLugar));
                pontosQuintoLugar.setText(String.valueOf(quiLugar));

                //imprime nome jogador posicao
                nomePrimeiroLugar.setText(jogPrimeiroNome);
                nomeSegundoLugar.setText(jogSegundoNome);
                nomeTerceiroLugar.setText(jogTerceiroNome);
                nomeQuartoLugar.setText(jogQuartoNome);
                nomeQuintoLugar.setText(jogQuintoNome);

                //imprime jogador login(email)
                emailPrimeiroLugar.setText(jogPrimeiroLogin);
                emailSegundoLugar.setText(jogSegundoLogin);
                emailTerceiroLugar.setText(jogTerceiroLogin);
                emailQuartoLugar.setText(jogQuartoLogin);
                emailQuintoLugar.setText(jogQuintoLogin);

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        totalPeguntas = new javax.swing.JLabel();
        javax.swing.JLabel jogoCFOP = new javax.swing.JLabel();
        javax.swing.JLabel iconConquistas = new javax.swing.JLabel();
        javax.swing.JLabel tituloPontos = new javax.swing.JLabel();
        emailPrimeiroLugar = new javax.swing.JLabel();
        emailSegundoLugar = new javax.swing.JLabel();
        emailTerceiroLugar = new javax.swing.JLabel();
        emailQuartoLugar = new javax.swing.JLabel();
        emailQuintoLugar = new javax.swing.JLabel();
        pontosAcertos = new javax.swing.JLabel();
        pontosErros = new javax.swing.JLabel();
        star1 = new javax.swing.JLabel();
        star2 = new javax.swing.JLabel();
        star3 = new javax.swing.JLabel();
        star4 = new javax.swing.JLabel();
        star5 = new javax.swing.JLabel();
        star6 = new javax.swing.JLabel();
        totalPontos = new javax.swing.JLabel();
        tituloPerguntas = new javax.swing.JLabel();
        javax.swing.JLabel tituloAcertos = new javax.swing.JLabel();
        javax.swing.JLabel tituloErros = new javax.swing.JLabel();
        fundoTotalPontos = new javax.swing.JLabel();
        btnProxFaseConquistas = new javax.swing.JLabel();
        javax.swing.JLabel btnVoltar = new javax.swing.JLabel();
        nomePrimeiroLugar = new javax.swing.JLabel();
        javax.swing.JLabel fundoPontos = new javax.swing.JLabel();
        javax.swing.JLabel madeText = new javax.swing.JLabel();
        javax.swing.JLabel primeiroLugar = new javax.swing.JLabel();
        pontosPrimeiroLugar = new javax.swing.JLabel();
        javax.swing.JLabel trofeu = new javax.swing.JLabel();
        pontosSegundoLugar = new javax.swing.JLabel();
        pontosTerceiroLugar = new javax.swing.JLabel();
        pontosQuintoLugar = new javax.swing.JLabel();
        pontosQuartoLugar = new javax.swing.JLabel();
        nomeSegundoLugar = new javax.swing.JLabel();
        nomeTerceiroLugar = new javax.swing.JLabel();
        nomeQuartoLugar = new javax.swing.JLabel();
        nomeQuintoLugar = new javax.swing.JLabel();
        javax.swing.JLabel quartoLugar = new javax.swing.JLabel();
        javax.swing.JLabel tituloRanking = new javax.swing.JLabel();
        javax.swing.JLabel quintoLugar = new javax.swing.JLabel();
        javax.swing.JLabel segundoLugar = new javax.swing.JLabel();
        javax.swing.JLabel terceiroLugar = new javax.swing.JLabel();
        javax.swing.JLabel fundoPrimeiroLugar = new javax.swing.JLabel();
        javax.swing.JLabel fundoSegiundoLugar = new javax.swing.JLabel();
        javax.swing.JLabel fundoTerceiroLugar = new javax.swing.JLabel();
        javax.swing.JLabel fundoQuartoLugar = new javax.swing.JLabel();
        javax.swing.JLabel fundoQuintoLugar = new javax.swing.JLabel();
        javax.swing.JLabel fundoRanking = new javax.swing.JLabel();
        javax.swing.JLabel background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setLayout(null);

        totalPeguntas.setFont(new java.awt.Font("Tahoma", 1, 38)); // NOI18N
        totalPeguntas.setForeground(new java.awt.Color(221, 87, 144));
        jPanel1.add(totalPeguntas);
        totalPeguntas.setBounds(310, 430, 60, 50);

        jogoCFOP.setIcon(new ImageIcon(getClass().getResource("/resources/title_left.png"))); // NOI18N
        jPanel1.add(jogoCFOP);
        jogoCFOP.setBounds(20, 20, 216, 34);

        iconConquistas.setIcon(new ImageIcon(getClass().getResource("/resources/conquistasIcone.png"))); // NOI18N
        jPanel1.add(iconConquistas);
        iconConquistas.setBounds(900, 120, 80, 90);

        tituloPontos.setBackground(new java.awt.Color(51, 51, 255));
        tituloPontos.setFont(new java.awt.Font("Ink Free", 1, 36)); // NOI18N
        tituloPontos.setForeground(new java.awt.Color(221, 87, 144));
        tituloPontos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloPontos.setText("PONTOS");
        tituloPontos.setToolTipText("");
        jPanel1.add(tituloPontos);
        tituloPontos.setBounds(230, 190, 190, 50);
        tituloPontos.getAccessibleContext().setAccessibleName("RESULTADO");

        emailPrimeiroLugar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailPrimeiroLugar.setForeground(new java.awt.Color(255, 193, 7));
        emailPrimeiroLugar.setText("JLabel");
        jPanel1.add(emailPrimeiroLugar);
        emailPrimeiroLugar.setBounds(790, 280, 200, 40);

        emailSegundoLugar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailSegundoLugar.setForeground(new java.awt.Color(255, 195, 7));
        emailSegundoLugar.setText("jLabel4");
        jPanel1.add(emailSegundoLugar);
        emailSegundoLugar.setBounds(810, 334, 180, 40);

        emailTerceiroLugar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailTerceiroLugar.setForeground(new java.awt.Color(255, 195, 7));
        emailTerceiroLugar.setText("jLabel4");
        jPanel1.add(emailTerceiroLugar);
        emailTerceiroLugar.setBounds(830, 400, 130, 20);

        emailQuartoLugar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailQuartoLugar.setForeground(new java.awt.Color(255, 195, 7));
        emailQuartoLugar.setText("jLabel4");
        jPanel1.add(emailQuartoLugar);
        emailQuartoLugar.setBounds(850, 447, 140, 30);

        emailQuintoLugar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailQuintoLugar.setForeground(new java.awt.Color(255, 195, 7));
        emailQuintoLugar.setText("jLabel4");
        jPanel1.add(emailQuintoLugar);
        emailQuintoLugar.setBounds(870, 504, 160, 30);

        pontosAcertos.setFont(new java.awt.Font("Krungthep", 1, 36)); // NOI18N
        pontosAcertos.setForeground(new java.awt.Color(51, 153, 0));
        pontosAcertos.setText("Iniciou o jogo uma vez");
        jPanel1.add(pontosAcertos);
        pontosAcertos.setBounds(150, 330, 50, 40);

        pontosErros.setFont(new java.awt.Font("Krungthep", 1, 36)); // NOI18N
        pontosErros.setForeground(new java.awt.Color(255, 0, 0));
        pontosErros.setText("Acertou uma pergunta rápido");
        jPanel1.add(pontosErros);
        pontosErros.setBounds(380, 330, 40, 40);

        star1.setIcon(new ImageIcon(getClass().getResource("/resources/starGray.png"))); // NOI18N
        jPanel1.add(star1);
        star1.setBounds(360, 150, 46, 44);

        star2.setIcon(new ImageIcon(getClass().getResource("/resources/starGray.png"))); // NOI18N
        jPanel1.add(star2);
        star2.setBounds(240, 150, 46, 44);

        star3.setIcon(new ImageIcon(getClass().getResource("/resources/starGray.png"))); // NOI18N
        jPanel1.add(star3);
        star3.setBounds(300, 140, 46, 44);

        star4.setIcon(new ImageIcon(getClass().getResource("/resources/starGray.png"))); // NOI18N
        jPanel1.add(star4);
        star4.setBounds(310, 430, 46, 44);

        star5.setIcon(new ImageIcon(getClass().getResource("/resources/starGray.png"))); // NOI18N
        jPanel1.add(star5);
        star5.setBounds(140, 330, 46, 44);

        star6.setIcon(new ImageIcon(getClass().getResource("/resources/starGray.png"))); // NOI18N
        jPanel1.add(star6);
        star6.setBounds(370, 330, 46, 44);

        totalPontos.setFont(new java.awt.Font("Krungthep", 1, 48)); // NOI18N
        totalPontos.setForeground(new java.awt.Color(51, 204, 0));
        totalPontos.setText("Acertou 5 perguntas seguidas");
        jPanel1.add(totalPontos);
        totalPontos.setBounds(290, 230, 110, 80);

        tituloPerguntas.setFont(new java.awt.Font("Krungthep", 1, 24)); // NOI18N
        tituloPerguntas.setForeground(new java.awt.Color(255, 193, 7));
        tituloPerguntas.setText("Total de Perguntas");
        tituloPerguntas.setToolTipText("");
        jPanel1.add(tituloPerguntas);
        tituloPerguntas.setBounds(230, 400, 230, 40);

        tituloAcertos.setFont(new java.awt.Font("Krungthep", 1, 24)); // NOI18N
        tituloAcertos.setForeground(new java.awt.Color(255, 193, 7));
        tituloAcertos.setText("Acertos");
        tituloAcertos.setToolTipText("");
        jPanel1.add(tituloAcertos);
        tituloAcertos.setBounds(210, 330, 220, 40);

        tituloErros.setFont(new java.awt.Font("Krungthep", 1, 24)); // NOI18N
        tituloErros.setForeground(new java.awt.Color(255, 193, 7));
        tituloErros.setText("Erros");
        jPanel1.add(tituloErros);
        tituloErros.setBounds(440, 330, 100, 40);

        fundoTotalPontos.setFont(new java.awt.Font("Krungthep", 0, 14)); // NOI18N
        fundoTotalPontos.setIcon(new ImageIcon(getClass().getResource("/resources/fundoLevel.png"))); // NOI18N
        fundoTotalPontos.setText("Total de pontos:");
        jPanel1.add(fundoTotalPontos);
        fundoTotalPontos.setBounds(240, 240, 170, 60);

        btnProxFaseConquistas.setFont(new java.awt.Font("Krungthep", 1, 24)); // NOI18N
        btnProxFaseConquistas.setForeground(new java.awt.Color(255, 193, 7));
        btnProxFaseConquistas.setIcon(new ImageIcon(getClass().getResource("/resources/botaoProxFase.png"))); // NOI18N
        btnProxFaseConquistas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProxFaseConquistasMouseClicked(evt);
            }
        });
        jPanel1.add(btnProxFaseConquistas);
        btnProxFaseConquistas.setBounds(800, 580, 290, 50);

        btnVoltar.setIcon(new ImageIcon(getClass().getResource("/resources/botaoVoltar.png"))); // NOI18N
        btnVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVoltarMouseClicked(evt);
            }
        });
        jPanel1.add(btnVoltar);
        btnVoltar.setBounds(220, 580, 210, 45);

        nomePrimeiroLugar.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        nomePrimeiroLugar.setForeground(new java.awt.Color(0, 204, 102));
        nomePrimeiroLugar.setText("teste");
        jPanel1.add(nomePrimeiroLugar);
        nomePrimeiroLugar.setBounds(780, 260, 260, 40);

        fundoPontos.setIcon(new ImageIcon(getClass().getResource("/resources/backgroundConquistas.png"))); // NOI18N
        jPanel1.add(fundoPontos);
        fundoPontos.setBounds(110, 100, 440, 570);

        madeText.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        madeText.setForeground(new java.awt.Color(255, 255, 255));
        madeText.setText("FEITO POR CESAR VERGARA");
        jPanel1.add(madeText);
        madeText.setBounds(20, 730, 260, 30);

        primeiroLugar.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        primeiroLugar.setForeground(new java.awt.Color(255, 255, 255));
        primeiroLugar.setText("1");
        jPanel1.add(primeiroLugar);
        primeiroLugar.setBounds(730, 260, 50, 60);

        pontosPrimeiroLugar.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        pontosPrimeiroLugar.setForeground(new java.awt.Color(255, 255, 255));
        pontosPrimeiroLugar.setEnabled(false);
        jPanel1.add(pontosPrimeiroLugar);
        pontosPrimeiroLugar.setBounds(1090, 270, 70, 40);

        trofeu.setIcon(new ImageIcon(getClass().getResource("/resources/trofeu.png"))); // NOI18N
        jPanel1.add(trofeu);
        trofeu.setBounds(1040, 270, 46, 40);

        pontosSegundoLugar.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        pontosSegundoLugar.setForeground(new java.awt.Color(255, 255, 255));
        pontosSegundoLugar.setEnabled(false);
        jPanel1.add(pontosSegundoLugar);
        pontosSegundoLugar.setBounds(1090, 320, 70, 40);

        pontosTerceiroLugar.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        pontosTerceiroLugar.setForeground(new java.awt.Color(255, 255, 255));
        pontosTerceiroLugar.setEnabled(false);
        jPanel1.add(pontosTerceiroLugar);
        pontosTerceiroLugar.setBounds(1090, 380, 70, 40);

        pontosQuintoLugar.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        pontosQuintoLugar.setForeground(new java.awt.Color(255, 255, 255));
        pontosQuintoLugar.setEnabled(false);
        jPanel1.add(pontosQuintoLugar);
        pontosQuintoLugar.setBounds(1090, 490, 70, 40);

        pontosQuartoLugar.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        pontosQuartoLugar.setForeground(new java.awt.Color(255, 255, 255));
        pontosQuartoLugar.setEnabled(false);
        jPanel1.add(pontosQuartoLugar);
        pontosQuartoLugar.setBounds(1090, 430, 70, 40);

        nomeSegundoLugar.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        nomeSegundoLugar.setForeground(new java.awt.Color(0, 204, 102));
        nomeSegundoLugar.setText("teste");
        nomeSegundoLugar.setToolTipText("");
        jPanel1.add(nomeSegundoLugar);
        nomeSegundoLugar.setBounds(800, 320, 290, 30);

        nomeTerceiroLugar.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        nomeTerceiroLugar.setForeground(new java.awt.Color(0, 204, 102));
        nomeTerceiroLugar.setText("teste");
        jPanel1.add(nomeTerceiroLugar);
        nomeTerceiroLugar.setBounds(820, 375, 270, 30);

        nomeQuartoLugar.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        nomeQuartoLugar.setForeground(new java.awt.Color(0, 204, 102));
        nomeQuartoLugar.setText("teste");
        jPanel1.add(nomeQuartoLugar);
        nomeQuartoLugar.setBounds(840, 430, 250, 30);

        nomeQuintoLugar.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        nomeQuintoLugar.setForeground(new java.awt.Color(0, 204, 102));
        nomeQuintoLugar.setText("teste");
        jPanel1.add(nomeQuintoLugar);
        nomeQuintoLugar.setBounds(860, 490, 230, 25);

        quartoLugar.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        quartoLugar.setForeground(new java.awt.Color(255, 255, 255));
        quartoLugar.setText("4");
        jPanel1.add(quartoLugar);
        quartoLugar.setBounds(790, 430, 30, 44);

        tituloRanking.setFont(new java.awt.Font("Ink Free", 1, 36)); // NOI18N
        tituloRanking.setText("Ranking");
        tituloRanking.setToolTipText("RANKING");
        jPanel1.add(tituloRanking);
        tituloRanking.setBounds(880, 210, 150, 40);

        quintoLugar.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        quintoLugar.setForeground(new java.awt.Color(255, 255, 255));
        quintoLugar.setText("5");
        jPanel1.add(quintoLugar);
        quintoLugar.setBounds(810, 490, 30, 40);

        segundoLugar.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        segundoLugar.setForeground(new java.awt.Color(255, 255, 255));
        segundoLugar.setText("2");
        jPanel1.add(segundoLugar);
        segundoLugar.setBounds(750, 330, 23, 30);

        terceiroLugar.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        terceiroLugar.setForeground(new java.awt.Color(255, 255, 255));
        terceiroLugar.setText("3");
        jPanel1.add(terceiroLugar);
        terceiroLugar.setBounds(770, 380, 30, 30);

        fundoPrimeiroLugar.setIcon(new ImageIcon(getClass().getResource("/resources/tituloPergunta.png"))); // NOI18N
        fundoPrimeiroLugar.setText("jLabel3");
        jPanel1.add(fundoPrimeiroLugar);
        fundoPrimeiroLugar.setBounds(720, 260, 440, 60);

        fundoSegiundoLugar.setIcon(new ImageIcon(getClass().getResource("/resources/tituloPergunta.png"))); // NOI18N
        jPanel1.add(fundoSegiundoLugar);
        fundoSegiundoLugar.setBounds(740, 310, 420, 70);

        fundoTerceiroLugar.setIcon(new ImageIcon(getClass().getResource("/resources/tituloPergunta.png"))); // NOI18N
        jPanel1.add(fundoTerceiroLugar);
        fundoTerceiroLugar.setBounds(760, 370, 400, 60);

        fundoQuartoLugar.setIcon(new ImageIcon(getClass().getResource("/resources/tituloPergunta.png"))); // NOI18N
        jPanel1.add(fundoQuartoLugar);
        fundoQuartoLugar.setBounds(780, 430, 380, 50);

        fundoQuintoLugar.setIcon(new ImageIcon(getClass().getResource("/resources/tituloPergunta.png"))); // NOI18N
        jPanel1.add(fundoQuintoLugar);
        fundoQuintoLugar.setBounds(800, 480, 360, 60);

        fundoRanking.setIcon(new ImageIcon(getClass().getResource("/resources/backgroundConquistas.png"))); // NOI18N
        fundoRanking.setText("jLabel2");
        jPanel1.add(fundoRanking);
        fundoRanking.setBounds(720, 100, 440, 570);

        background.setIcon(new ImageIcon(getClass().getResource("/resources/fundo_sistema.jpg"))); // NOI18N
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

    private void btnVoltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoltarMouseClicked
        Home jogo = new Home(conta);
        jogo.setLocationRelativeTo(null);
        jogo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarMouseClicked

    private void btnProxFaseConquistasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProxFaseConquistasMouseClicked
        // TODO add your handling code here:
        if (jogo.getFase() == 1) {
            JogoInstrucaoNota jogo = new JogoInstrucaoNota(conta);
            jogo.setLocationRelativeTo(null);
            jogo.setVisible(true);
            this.dispose();
        } else {
            Conquistas conquistas = new Conquistas(conta);
            conquistas.setLocationRelativeTo(null);
            conquistas.setVisible(true);
        }
    }//GEN-LAST:event_btnProxFaseConquistasMouseClicked
    public void preencherIcone() {
        ImageIcon img = new ImageIcon(getClass().getResource("/resources/botaoVerConquistas.png"));
        btnProxFaseConquistas.setIcon(img);

    }
//preenche os campos de acertos, erros, pontuacao

    private void preencherConquistas() {
        totalPeguntas.setText(String.valueOf(jogo.getPerguntas() - 1));
        pontosErros.setText(String.valueOf(jogo.getErros()));
        pontosAcertos.setText(String.valueOf(jogo.getAcertos()));
        totalPontos.setText(String.valueOf(jogo.getPontuacao()));
        ImageIcon estrela = new ImageIcon(getClass().getResource("/resources/starIcon.png"));
        if (conta[TROFEU1].equals("1")) {
            star1.setIcon(estrela);
        }
        if (conta[TROFEU2].equals("1")) {
            star2.setIcon(estrela);
        }
        if (conta[TROFEU3].equals("1")) {
            star3.setIcon(estrela);
        }
        if (conta[TROFEU4].equals("1")) {
            star4.setIcon(estrela);
        }
        if (conta[TROFEU5].equals("1")) {
            star5.setIcon(estrela);
        }
        if (conta[TROFEU6].equals("1")) {
            star6.setIcon(estrela);
        }
    }

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
                new JogoResultado().setVisible(true);

            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JLabel btnProxFaseConquistas;
    javax.swing.JLabel emailPrimeiroLugar;
    javax.swing.JLabel emailQuartoLugar;
    javax.swing.JLabel emailQuintoLugar;
    javax.swing.JLabel emailSegundoLugar;
    javax.swing.JLabel emailTerceiroLugar;
    javax.swing.JLabel fundoTotalPontos;
    javax.swing.JLabel nomePrimeiroLugar;
    javax.swing.JLabel nomeQuartoLugar;
    javax.swing.JLabel nomeQuintoLugar;
    javax.swing.JLabel nomeSegundoLugar;
    javax.swing.JLabel nomeTerceiroLugar;
    javax.swing.JLabel pontosAcertos;
    javax.swing.JLabel pontosErros;
    javax.swing.JLabel pontosPrimeiroLugar;
    javax.swing.JLabel pontosQuartoLugar;
    javax.swing.JLabel pontosQuintoLugar;
    javax.swing.JLabel pontosSegundoLugar;
    javax.swing.JLabel pontosTerceiroLugar;
    javax.swing.JLabel star1;
    javax.swing.JLabel star2;
    javax.swing.JLabel star3;
    javax.swing.JLabel star4;
    javax.swing.JLabel star5;
    javax.swing.JLabel star6;
    javax.swing.JLabel tituloPerguntas;
    javax.swing.JLabel totalPeguntas;
    javax.swing.JLabel totalPontos;
    // End of variables declaration//GEN-END:variables

}
