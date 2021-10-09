package jogador;

import jogo.Jogo;

public class Jogador {

    private Jogo jogoAtual;
    private String email;
    private String senha;
    private String nome;
    private String cargo;
    private boolean completouJogo;
    private int melhorSequencia;
    private int[] conquistas;

    //private int totalErros;
    // private int totalAcertos;
    public Jogador(String email, String senha, String nome, String cargo) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.cargo = cargo;
        this.melhorSequencia = 0;
        this.conquistas = new int[6];

        System.out.println(email);
        System.out.println(nome);
        System.out.println(senha);
        System.out.println(cargo);
        System.out.println(jogoAtual.getAcertos());
        System.out.println(jogoAtual.getErros());
        System.out.println(jogoAtual.getPerguntas());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean getCompletouJogo() {
        return completouJogo;
    }

    public void setCompletouJogo(boolean completouJogo) {
        this.completouJogo = completouJogo;
    }

    public int getMelhorSequencia() {
        return melhorSequencia;
    }

    public void setMelhorSequencia(int melhorSequencia) {
        this.melhorSequencia = melhorSequencia;
    }

    public int[] getConquistas() {
        return conquistas;
    }

    public void setConquistas(int[] conquistas) {
        this.conquistas = conquistas;
    }
}
