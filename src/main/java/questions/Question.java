package questions;

import java.util.Arrays;

public abstract class Question {

    private int num;
    private String title;
    private int[] options;
    private int rightAnswer;

    public Question(int num, String title, int[] answers, int rightAnswer) {
        this.num = num;
        this.title = title;
        this.options = answers;
        this.rightAnswer = rightAnswer;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int[] getAnswers() {
        return options;
    }

    public void setAnswers(int[] answers) {
        this.options = answers;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public String toString() {
        return "num: " + this.num + " title: " + this.title + " options " + Arrays.toString(this.options) + "  rightAnswer: " + this.rightAnswer;
    }
}
