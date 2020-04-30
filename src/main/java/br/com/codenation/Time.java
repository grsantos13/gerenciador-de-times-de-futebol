package br.com.codenation;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Time {
    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;
    private Jogador jogadorCapitao;
    private List<Jogador> listaJogador;


    //getters
    public Long getId(){return id;}
    public String getNome(){return nome;}
    public LocalDate getDataCriacao() {return dataCriacao;}
    public String getCorUniformePrincipal() {return corUniformePrincipal;}
    public String getCorUniformeSecundario() {return corUniformeSecundario;}
    public Jogador getJogadorCapitao() {return jogadorCapitao;}
    public List<Jogador> getListaJogador(){return listaJogador;}

    //setters
    public void setId(Long id){this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setCorUniformePrincipal(String cor) {this.corUniformePrincipal = cor;}
    public void setDataCriacao(LocalDate dataCriacao) {this.dataCriacao = dataCriacao;}
    public void setCorUniformeSecundario(String cor) {this.corUniformeSecundario = cor;}
    public void setJogadorCapitao(Jogador jogadorCapitao) {this.jogadorCapitao = jogadorCapitao;}
    public void setListaJogador(Jogador jogador) {this.listaJogador.add(jogador);}

    //Construtores
    public Time(){this.listaJogador = new ArrayList<Jogador>();}
    public Time(Long id){
        this.id = id;
        this.listaJogador = new ArrayList<Jogador>();
    }
    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario){
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;
        this.listaJogador = new ArrayList<Jogador>();
    }
}
