package screen;

import org.jdesktop.layout.GroupLayout;
import questions.Question;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Screen extends JFrame {
    private static Screen screen = null;
    private JPanel mainPanel;

    private Screen(){
        setTitle("Jogo do Atendente Fiscal");
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        setResizable(true);
        this.setLocation(0, 0);
        setVisible(true);
    }

    public static Screen getScreen() {
        if(screen == null)
            screen = new Screen();
        return screen;
    }

    public void setScreen(String nameOfScreen, Object argument) {
        this.mainPanel.removeAll();

        switch (nameOfScreen) {
            case "GameQuestion": {
                GameQuestion gameQuestionScreen = new GameQuestion((ArrayList<Question>) argument);
                gameQuestionScreen.getAllComponents().forEach(component -> this.mainPanel.add(component));
                break;
            }

            case "GameQuestionNote": {
                GameQuestionNote gameQuestionNoteScreen = new GameQuestionNote((ArrayList<Question>) argument);
                gameQuestionNoteScreen.getAllComponents().forEach(component -> this.mainPanel.add(component));
                break;
            }
            case "GameResult": {
                GameResult gameResultScreen = new GameResult((boolean) argument);
                gameResultScreen.getAllComponents().forEach(component -> this.mainPanel.add(component));
                break;
            }

            default: {
                throw new IllegalStateException("Unexpected value: " + nameOfScreen);
            }
        }

        this.showScreen();
    }

    public void setScreen(String nameOfScreen) {
        this.mainPanel.removeAll();

        switch (nameOfScreen) {
            case "Login": {
                Login loginScreen = new Login();
                loginScreen.getAllComponents().forEach(component -> this.mainPanel.add(component));
                break;
            }

            case "Home": {
                Home homeScreen = new Home();
                homeScreen.getAllComponents().forEach(component -> this.mainPanel.add(component));
                break;
            }

            case "Register": {
                Register registerScreen = new Register();
                registerScreen.getAllComponents().forEach(component -> this.mainPanel.add(component));
                break;
            }

            case "GameInstruction": {
                GameInstruction gameInstructionScreen = new GameInstruction();
                gameInstructionScreen.getAllComponents().forEach(component -> this.mainPanel.add(component));
                break;
            }

            case "GameInstructionNote": {
                GameInstructionNote gameInstructionNoteScreen = new GameInstructionNote();
                gameInstructionNoteScreen.getAllComponents().forEach(component -> this.mainPanel.add(component));
                break;
            }

            case "Achievements": {
                Achievements achievementsScreen = new Achievements();
                achievementsScreen.getAllComponents().forEach(component -> this.mainPanel.add(component));
                break;
            }

            case "GameResult": {
                GameResult gameResultScreen = new GameResult();
                gameResultScreen.getAllComponents().forEach(component -> this.mainPanel.add(component));
                break;
            }

            default: {
                throw new IllegalStateException("Unexpected value: " + nameOfScreen);
            }
        }

        this.showScreen();
    }

    private void showScreen() {
        GroupLayout layout = new GroupLayout(this.getContentPane());

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.LEADING)
                .add(mainPanel, GroupLayout.DEFAULT_SIZE, 1366, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.LEADING)
                .add(mainPanel, GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE));

        this.getContentPane().setLayout(layout);

        this.pack();
    }

    private void createUIComponents() {

    }
}
