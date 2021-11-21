package game;

public class Game {

    private static Game instance;
    private int status;
    private int score;
    private int questions;
    private int rightAnswers;
    private int wrongAnswers;
    private int seq;
    private int questionsQuantity;
    private String login;
    private int lvl;
    private String[] account = null;

    private Game() {
        this.status = 1;
        this.score = 0;
        this.questions = 1;
        this.rightAnswers = 0;
        this.wrongAnswers = 0;
        this.seq = 0;
        this.questionsQuantity = 0;
    }

    public static synchronized Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getQuestions() {
        return this.questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }

    public int getRightAnswers() {
        return this.rightAnswers;
    }

    public void setRightAnswers(int rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public int getWrongAnswers() {
        return this.wrongAnswers;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public int getQuestionsQuantity() {
        return this.questionsQuantity;
    }

    public void setQuestionsQuantity(int questionsQuantity) {
        this.questionsQuantity = questionsQuantity;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getLvl() {
        return this.lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void setAccount(String[] account) {
        this.account = account;
    }

    public String[] getAccount() {
        return this.account;
    }
}
