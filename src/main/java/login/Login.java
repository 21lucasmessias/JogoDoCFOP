package login;

import jogo.Jogo;
import menu.MenuPrincipal;
import utils.ImageUtils;
import utils.telaCadastro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Login extends JFrame {

    private JPasswordField inputPassword;
    private JTextField inputEmail;
    private JPanel mainPanel;

    public Login() {
        setTitle("Jogo do CFOP");

        createUIComponents();
        fillLogin();
    }

    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    private void fillLogin() {
        File f = new File("settings.ini");

        if (f.exists()) {
            try (BufferedReader in = new BufferedReader(new FileReader("settings.ini"))) {
                String str;
                String[] account = {"", ""};

                while ((str = in.readLine()) != null) {
                    account = str.split(",");
                }

                inputEmail.setText(account[0]);
                inputPassword.setText(account[1]);
            } catch (IOException e) {
                System.out.println("File Read Error");
            }
        }
    }

    private void btnSairMouseClicked(java.awt.event.MouseEvent evt) {
        System.exit(0);
    }

    private void btnEntrarMouseClicked(java.awt.event.MouseEvent evt) {
        realizarLogin();
    }

    private void btnCadastrarContaMouseClicked(java.awt.event.MouseEvent evt) {
        telaCadastro novaConta = new telaCadastro();
        novaConta.setLocationRelativeTo(null);
        novaConta.setVisible(true);
        this.dispose();
    }

    private void inputSenhaKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            realizarLogin();
        }
    }

    private boolean validarDados() {
        if (inputEmail.getText().length() < 7) {
            JOptionPane.showMessageDialog(null, "E-mail invalido.");
            return false;
        } else if (inputPassword.getPassword().length < 5) {
            JOptionPane.showMessageDialog(null, "A senha precisa ter no mínimo 5 caracteres.");
            return false;
        }
        return true;
    }

    private void realizarLogin() {
        if (validarDados()) {
            File f = new File(inputEmail.getText() + ".txt");
            if (f.exists()) {
                try (BufferedReader in = new BufferedReader(new FileReader(inputEmail.getText() + ".txt"))) {
                    String str;
                    String[] conta = null;
                    String strPass = new String(inputPassword.getPassword()).trim();
                    while ((str = in.readLine()) != null) {
                        conta = str.split(",");
                        System.out.println(str);
                    }
                    if (conta[1].equals(strPass)) {
                        Jogo jogoAtual = Jogo.getInstance();
                        jogoAtual.setLogin(inputEmail.getText());
                        MenuPrincipal jogo = new MenuPrincipal(conta);
                        jogo.setLocationRelativeTo(null);
                        jogo.setVisible(true);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Senha incorreta.");
                        inputPassword.setText("");
                    }
                } catch (IOException e) {
                    System.out.println("File Read Error");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Essa conta não existe.");
            }
        }
    }

    private void createUIComponents() {
        JPanel jPanel1 = new JPanel();
        JLabel titleLeft = new JLabel();
        JLabel tituloLogin = new JLabel();
        JLabel tituloEmail = new JLabel();
        inputEmail = new JTextField();
        JLabel tituloSenha = new JLabel();
        inputPassword = new JPasswordField();
        JLabel btnEntrar = new JLabel();
        JLabel btnCadastrarConta = new JLabel();
        JLabel fundoLogin = new JLabel();
        JLabel madeText = new JLabel();
        JLabel btnSair = new JLabel();
        JLabel background = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setLayout(null);

        titleLeft.setIcon(new ImageIcon(ImageUtils.getImage("title_left.png")));
        jPanel1.add(titleLeft);
        titleLeft.setBounds(20, 20, 216, 34);

        tituloLogin.setFont(new java.awt.Font("Krungthep", 0, 24)); // NOI18N
        tituloLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloLogin.setText("Login");
        jPanel1.add(tituloLogin);
        tituloLogin.setBounds(610, 150, 100, 50);

        tituloEmail.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        tituloEmail.setText("E-MAIL:");
        jPanel1.add(tituloEmail);
        tituloEmail.setBounds(370, 270, 110, 24);

        inputEmail.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jPanel1.add(inputEmail);
        inputEmail.setBounds(490, 260, 480, 40);

        tituloSenha.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        tituloSenha.setText("SENHA:");
        jPanel1.add(tituloSenha);
        tituloSenha.setBounds(370, 360, 110, 24);

        inputPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                inputSenhaKeyPressed(evt);
            }
        });
        jPanel1.add(inputPassword);
        inputPassword.setBounds(490, 350, 480, 40);

        btnEntrar.setIcon(new ImageIcon(ImageUtils.getImage("botaoEntrar.png")));
        btnEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEntrarMouseClicked(evt);
            }
        });
        jPanel1.add(btnEntrar);
        btnEntrar.setBounds(440, 470, 180, 50);

        btnCadastrarConta.setIcon(new ImageIcon(ImageUtils.getImage("botaoCriarConta.png")));
        btnCadastrarConta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCadastrarContaMouseClicked(evt);
            }
        });
        jPanel1.add(btnCadastrarConta);
        btnCadastrarConta.setBounds(680, 470, 240, 50);

        fundoLogin.setIcon(new ImageIcon(ImageUtils.getImage("backgroundConquistas.png")));
        jPanel1.add(fundoLogin);
        fundoLogin.setBounds(270, 130, 784, 500);

        madeText.setFont(new java.awt.Font("Krungthep", 0, 18)); // NOI18N
        madeText.setForeground(new java.awt.Color(255, 255, 255));
        madeText.setText("FEITO POR CESAR VERGARA");
        jPanel1.add(madeText);
        madeText.setBounds(20, 730, 260, 30);

        btnSair.setIcon(new ImageIcon(ImageUtils.getImage("botaoSairBottom.png")));
        btnSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSairMouseClicked(evt);
            }
        });
        jPanel1.add(btnSair);
        btnSair.setBounds(1170, 710, 180, 45);

        background.setIcon(new ImageIcon(ImageUtils.getImage("fundo_sistema.jpg")));
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
}
