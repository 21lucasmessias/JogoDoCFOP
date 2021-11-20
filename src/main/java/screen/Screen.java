package screen;

import org.jdesktop.layout.GroupLayout;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    private static Screen screen = null;
    private JPanel mainPanel = new JPanel();

    private Screen(){
        setTitle("Jogo do Atendente Fiscal");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static Screen getScreen() {
        if(screen == null)
            screen = new Screen();
        return screen;
    }

    public void setScreen(String nameOfScreen) {
        this.mainPanel.removeAll();
        this.mainPanel.setLayout(null);

        switch (nameOfScreen) {
            case "Login": {
                Login loginScreen = new Login();

                loginScreen.getAllComponents()
                        .forEach(component -> this.mainPanel.add(component));

                break;
            }
            default: {
                throw new IllegalStateException("Unexpected value: " + nameOfScreen);
            }
        }

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(mainPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1366, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(mainPanel, GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE));

        this.pack();
    }

    private void createUIComponents() {

    }
}
