package questions;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eder e Raimundo Osvaldo
 */
public class QuestionCfop extends Question {

    private int operacao;
    private String emissora;
    private String remetente;
    private String destinatario;

    public QuestionCfop(int num, int op, String emissora, String remet, String desti, String titulo, int opcoes[], int respostaCerta) {
        super(num, titulo, opcoes, respostaCerta);
        this.operacao = op;
        this.emissora = emissora;
        this.remetente = remet;
        this.destinatario = desti;
        //System.out.println("pergunta.Pergunta criada | Numero: " + this.num + " | Operacao: " + this.op + " | Cidade Emissora: " + this.emissora + " | Remetente: " + this.remet + " | Destinatário: " + this.desti + " | Titulo: " + this.titulo + " | Respostas: " + Arrays.toString(this.respostas) + " | Resposta certa: " + this.respostaCerta);
    }

    /**
     * @return the op
     */
    public int getOperacao() {
        return operacao;
    }

    /**
     * @param op the op to set
     */
    public void setOperacao(int op) {
        this.operacao = op;
    }

    /**
     * @return the emissora
     */
    public String getEmissora() {
        return emissora;
    }

    /**
     * @param emissora the emissora to set
     */
    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }

    /**
     * @return the remet
     */
    public String getRemetente() {
        return remetente;
    }

    /**
     * @param remet the remet to set
     */
    public void setRemetente(String remet) {
        this.remetente = remet;
    }

    /**
     * @return the desti
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     * @param desti the desti to set
     */
    public void setDestinatario(String desti) {
        this.destinatario = desti;
    }

}
