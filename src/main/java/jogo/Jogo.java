package jogo;

public class Jogo {

    private int status;
    private int pontuacao;
    private int perguntas;
    private int acertos;
    private int erros;
    private int seq;
    private int qtdPerguntas;
    private String login;
    private static Jogo instancia;
    private int fase;

    private Jogo() {
        this.status = 1;
        this.pontuacao = 0;
        this.perguntas = 1;
        this.acertos = 0;
        this.erros = 0;
        this.seq = 0;
        this.qtdPerguntas = 0;
    }

    public static synchronized Jogo getInstance() {
        if (instancia == null) {
            instancia = new Jogo();
        }
        return instancia;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getPerguntas() {
        return this.perguntas;
    }

    public void setPerguntas(int perguntas) {
        this.perguntas = perguntas;
    }

    public int getAcertos() {
        return this.acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public int getErros() {
        return this.erros;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }

    public int getQtdPerguntas() {
        return this.qtdPerguntas;
    }

    public void setQtdPerguntas(int qtdPerguntas) {
        this.qtdPerguntas = qtdPerguntas;
    }

    public int getSeq() {
        return seq;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    public int getFase() {
        return this.fase;
    }
}
