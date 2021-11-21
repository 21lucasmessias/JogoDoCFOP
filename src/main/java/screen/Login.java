package screen;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.Font.PLAIN;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.SwingConstants.CENTER;
import static utils.ImageUtils.getImage;

public class Login {

    private JPasswordField inputPassword = new JPasswordField();
    private JTextField inputEmail = new JTextField();

    public Login() {
        fillLogin();
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

    private void handleExitPress(MouseEvent evt) {
        System.exit(0);
    }

    private void handleLoginPress(MouseEvent evt) {
        login();
    }

    private void handleRegisterPress(MouseEvent evt) {
        Screen.getScreen().setScreen("Register");
    }

    private void handleEnterPress(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            login();
        }
    }

    private boolean isFormValid() {
        if (inputEmail.getText().length() < 7) {
            showMessageDialog(null, "E-mail invalido.");
            return false;
        } else if (inputPassword.getPassword().length < 5) {
            showMessageDialog(null, "A senha precisa ter no mínimo 5 caracteres.");
            return false;
        }
        return true;
    }

    private void login() {
        if (isFormValid()) {
            File f = new File(inputEmail.getText() + ".txt");
            if (f.exists()) {
                try (BufferedReader in = new BufferedReader(new FileReader(inputEmail.getText() + ".txt"))) {
                    String str;
                    String[] account = {"", ""};
                    String strPass = new String(inputPassword.getPassword()).trim();

                    while ((str = in.readLine()) != null) {
                        account = str.split(",");
                    }
                    if (account[1].equals(strPass)) {
                        Game game = Game.getInstance();
                        game.setLogin(inputEmail.getText());
                        game.setAccount(account);

                        Screen.getScreen().setScreen("Home");
                    } else {
                        showMessageDialog(null, "Senha incorreta.");
                        inputPassword.setText("");
                    }
                } catch (IOException e) {
                    System.out.println("File Read Error");
                }
            } else {
                showMessageDialog(null, "Essa conta não existe.");
            }
        }
    }

    public ArrayList<JComponent> getAllComponents() {
        ArrayList<JComponent> listOfComponents = new ArrayList<JComponent>();

        JLabel titleLeft = new JLabel();
        JLabel titleLogin = new JLabel();
        JLabel titleEmail = new JLabel();
        JLabel titlePassword = new JLabel();
        JLabel btnLogin = new JLabel();
        JLabel btnRegister = new JLabel();
        JLabel btnExit = new JLabel();
        JLabel background = new JLabel();
        JLabel backgroundContent = new JLabel();

        titleLeft.setIcon(new ImageIcon(getImage("title_left.png")));
        listOfComponents.add(titleLeft);
        titleLeft.setBounds(20, 20, 216, 34);

        titleLogin.setFont(new Font("Krungthep", PLAIN, 24));
        titleLogin.setHorizontalAlignment(CENTER);
        titleLogin.setText("Login");
        listOfComponents.add(titleLogin);
        titleLogin.setBounds(610, 150, 100, 50);

        titleEmail.setFont(new Font("Krungthep", PLAIN, 18));
        titleEmail.setText("E-MAIL:");
        listOfComponents.add(titleEmail);
        titleEmail.setBounds(370, 270, 110, 24);

        inputEmail.setFont(new Font("Roboto", PLAIN, 18));
        listOfComponents.add(inputEmail);
        inputEmail.setBounds(490, 260, 480, 40);

        titlePassword.setFont(new Font("Krungthep", PLAIN, 18));
        titlePassword.setText("SENHA:");
        listOfComponents.add(titlePassword);
        titlePassword.setBounds(370, 360, 110, 24);

        inputPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                handleEnterPress(evt);
            }
        });
        listOfComponents.add(inputPassword);
        inputPassword.setBounds(490, 350, 480, 40);

        btnLogin.setIcon(new ImageIcon(getImage("botaoEntrar.png")));
        btnLogin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                handleLoginPress(evt);
            }
        });
        listOfComponents.add(btnLogin);
        btnLogin.setBounds(440, 470, 180, 50);

        btnRegister.setIcon(new ImageIcon(getImage("botaoCriarConta.png")));
        btnRegister.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                handleRegisterPress(evt);
            }
        });
        listOfComponents.add(btnRegister);
        btnRegister.setBounds(680, 470, 240, 50);

        backgroundContent.setIcon(new ImageIcon(getImage("backgroundConquistas.png")));
        listOfComponents.add(backgroundContent);
        backgroundContent.setBounds(270, 130, 784, 500);

        btnExit.setIcon(new ImageIcon(getImage("botaoSairBottom.png")));
        btnExit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                handleExitPress(evt);
            }
        });
        listOfComponents.add(btnExit);
        btnExit.setBounds(1170, 710, 180, 45);

        background.setIcon(new ImageIcon(getImage("fundo_sistema.jpg")));
        listOfComponents.add(background);
        background.setBounds(0, 0, 1366, 770);

        return listOfComponents;
    }
}
