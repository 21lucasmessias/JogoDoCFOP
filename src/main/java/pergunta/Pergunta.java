package pergunta;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import java.util.Arrays;
/**
 *
 * @author cesar, eder e raimundo
 */
public abstract class Pergunta {

    private int num;
    private String titulo;
    private int[] opcoes;
    private int respostaCerta;

    public Pergunta(int num, String titulo, int respostas[], int respostaCerta) {
        this.num = num;
        this.titulo = titulo;
        this.opcoes = respostas;
        this.respostaCerta = respostaCerta;
    }

    /**
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the respostas
     */
    public int[] getRespostas() {
        return opcoes;
    }

    /**
     * @param respostas the respostas to set
     */
    public void setRespostas(int[] respostas) {
        this.opcoes = respostas;
    }

    /**
     * @return the respostaCerta
     */
    public int getRespostaCerta() {
        return respostaCerta;
    }

    /**
     * @param respostaCerta the respostaCerta to set
     */
    public void setRespostaCerta(int respostaCerta) {
        this.respostaCerta = respostaCerta;
    }

}
