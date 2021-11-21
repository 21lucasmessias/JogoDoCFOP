package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import game.Game;
import screen.Login;

import static java.awt.Font.PLAIN;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.SwingConstants.CENTER;
import static utils.ImageUtils.getImage;

public class Register extends JFrame {

    private final JTextField inputRole = new JTextField();
    private final JTextField inputEmail = new JTextField();
    private final JTextField inputName = new JTextField();
    private final JPasswordField inputPassword = new JPasswordField();

    private void handleExitPress(MouseEvent evt) {
        Screen.getScreen().setScreen("Login");
    }

    private boolean isFormValid() {
        if (inputEmail.getText().length() < 7) {
            showMessageDialog(null, "E-mail invalido.");
            return false;
        } else if (inputPassword.getPassword().length < 5) {
            showMessageDialog(null, "A senha precisa ter no mínimo 5 caracteres.");
            return false;
        } else if (inputName.getText().length() < 5) {
            showMessageDialog(null, "O nome precisa ter no mínimo 5 caracteres.");
            return false;
        } else if (inputRole.getText().length() < 5) {
            showMessageDialog(null, "O cargo precisa ter no mínimo 5 caracteres.");
            return false;
        }
        return true;
    }

    private void createAccount() {
        if (isFormValid()) {
            File f = new File(inputEmail.getText() + ".txt");
            if (f.exists()) {
                showMessageDialog(null, "Esse e-mail já possui uma conta vinculada.");
            } else {
                try (PrintWriter out = new PrintWriter(inputEmail.getText() + ".txt")) {
                    String strPass = new String(inputPassword.getPassword()).trim();
                    int[] achievements = new int[6];
                    for (int i = 0; i < 6; i++) {
                        achievements[i] = 0;
                    }
                    out.println(inputEmail.getText() + "," + strPass + ","
                            + inputName.getText() + "," + inputRole.getText() + ",0,0,"
                            + achievements[0] + "," + achievements[1] + ","
                            + achievements[2] + "," + achievements[3] + ","
                            + achievements[4] + "," + achievements[5]);

                    showMessageDialog(null, "Conta criada com sucesso.");

                    String[] account = {inputEmail.getText(), strPass};
                    Game.getInstance().setAccount(account);

                    Screen.getScreen().setScreen("Home");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void handleCreateAccountPress(MouseEvent evt) {
        createAccount();
    }

    private void handleEnterPress(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            createAccount();
    }

    public ArrayList<JComponent> getAllComponents() {
        ArrayList<JComponent> listOfComponents = new ArrayList<JComponent>();

        JLabel titleLeft = new JLabel();
        JLabel titleLogin = new JLabel();
        JLabel titleEmail = new JLabel();
        JLabel titlePassword = new JLabel();
        JLabel titleName = new JLabel();
        JLabel titleRole = new JLabel();
        JLabel background = new JLabel();
        JLabel backgroundContent = new JLabel();
        JLabel btnCreateAccount = new JLabel();
        JLabel btnExit = new JLabel();

        titleLeft.setIcon(new ImageIcon(getImage("title_left.png")));
        listOfComponents.add(titleLeft);
        titleLeft.setBounds(20, 20, 216, 34);

        titleLogin.setFont(new Font("Krungthep", PLAIN, 24));
        titleLogin.setHorizontalAlignment(CENTER);
        titleLogin.setText("Cadastro");
        listOfComponents.add(titleLogin);
        titleLogin.setBounds(270, 150, 780, 50);

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
        titlePassword.setBounds(370, 330, 110, 24);
        listOfComponents.add(inputPassword);
        inputPassword.setBounds(490, 320, 480, 40);

        titleName.setFont(new Font("Krungthep", PLAIN, 18));
        titleName.setText("NOME:");
        listOfComponents.add(titleName);
        titleName.setBounds(370, 390, 110, 24);

        inputName.setFont(new Font("Roboto", PLAIN, 18));
        listOfComponents.add(inputName);
        inputName.setBounds(490, 380, 480, 40);

        titleRole.setFont(new Font("Krungthep", PLAIN, 18));
        titleRole.setText("CARGO:");
        listOfComponents.add(titleRole);
        titleRole.setBounds(370, 450, 110, 24);

        inputRole.setFont(new Font("Roboto", PLAIN, 18));
        inputRole.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                handleEnterPress(evt);
            }
        });
        listOfComponents.add(inputRole);
        inputRole.setBounds(490, 440, 480, 40);

        btnCreateAccount.setIcon(new ImageIcon(getImage("botaoCriarCadastro.png")));
        btnCreateAccount.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                handleCreateAccountPress(evt);
            }
        });
        listOfComponents.add(btnCreateAccount);
        btnCreateAccount.setBounds(550, 530, 290, 50);

        backgroundContent.setIcon(new ImageIcon(getImage("backgroundConquistas.png")));
        listOfComponents.add(backgroundContent);
        backgroundContent.setBounds(270, 130, 784, 500);

        btnExit.setIcon(new ImageIcon(getImage("botaoVoltar.png")));
        btnExit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                handleExitPress(evt);
            }
        });
        listOfComponents.add(btnExit);
        btnExit.setBounds(1140, 710, 210, 45);

        background.setIcon(new ImageIcon(getImage("fundo_sistema.jpg")));
        listOfComponents.add(background);
        background.setBounds(0, 0, 1366, 770);

        return listOfComponents;
    }
}
