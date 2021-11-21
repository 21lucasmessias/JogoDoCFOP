package questions;

public class QuestionCfop extends Question {

    private int operation;
    private String town;
    private String sender;
    private String receiver;

    public QuestionCfop(int num, int op, String town, String sender, String receiver, String title, int options[], int rightAnswer) {
        super(num, title, options, rightAnswer);
        this.operation = op;
        this.town = town;
        this.sender = sender;
        this.receiver = receiver;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int op) {
        this.operation = op;
    }

    public String getTown() {
        return town;
    }

    public void setEmissora(String emissora) {
        this.town = emissora;
    }

    public String getSender() {
        return sender;
    }

    public void setRemetente(String remet) {
        this.sender = remet;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setDestinatario(String desti) {
        this.receiver = desti;
    }

}
