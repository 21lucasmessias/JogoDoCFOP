package questions;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eder e Raimundo Osvaldo
 */
public class PerguntaNotaFiscal extends Question {

    private String uf;
    private String ano;
    private String mes;
    private String cnpj;
    private String modelo;
    private String numero;
    private String serie;
    private String codigoNumerico;
    private String digitoVerificador;

    public PerguntaNotaFiscal(int num, String uf, String ano, String mes, String cnpj, String modelo, String serie, String numero, String codigoNumerico, String digitoVerificador, String titulo, int opcoes[], int respostaCerta) {
        super(num, titulo, opcoes, respostaCerta);
        this.uf = uf;
        this.ano = ano;
        this.mes = mes;
        this.cnpj = cnpj;
        this.modelo = modelo;
        this.serie = serie;
        this.numero = numero;
        this.codigoNumerico = codigoNumerico;
        this.digitoVerificador = digitoVerificador;
    }

    public String getUf() {
        return uf;
    }

    public String getAno() {
        return ano;
    }

    public String getMes() {
        return mes;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getModelo() {
        return modelo;
    }

    public String getSerie() {
        return serie;
    }

    public String getNumero() {
        return numero;
    }

    public String getCodigoNumerico() {
        return codigoNumerico;
    }

    public String getDigitoVerificador() {
        return digitoVerificador;
    }

}
