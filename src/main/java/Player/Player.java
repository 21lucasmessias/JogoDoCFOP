package Player;

import game.Game;

public class Player {

    private Game currentGame;
    private String email;
    private String password;
    private String name;
    private String role;  //cargo
    private boolean gameFinished;  //completou o jogo
    private int bestSequence;
    private int[] Achievements;

    public Player(String email, String password, String name, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.bestSequence = 0;
        this.Achievements = new int[6];

        System.out.println(email);
        System.out.println(name);
        System.out.println(password);
        System.out.println(role);
        System.out.println(currentGame.getRightAnswers());
        System.out.println(currentGame.getWrongAnswers());
        System.out.println(currentGame.getQuestions());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public int getBestSequence() {
        return bestSequence;
    }

    public void setBestSequence(int bestSequence) {
        this.bestSequence = bestSequence;
    }

    public int[] getAchievements() {
        return Achievements;
    }

    public void setAchievements(int[] achievements) {
        this.Achievements = achievements;
    }
}
