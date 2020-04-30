package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador {
    private Long id;
    private Long idTime;
    private String nome;
    private LocalDate dataNascimento;
    private Integer nivelHabilidade;
    private BigDecimal salario;

    //getters
    public Long getId() {return id;}
    public Long getIdTime() {return idTime;}
    public String getNome() {return nome;}
    public LocalDate getDataNascimento() {return dataNascimento;}
    public Integer getNivelHabilidade() {return nivelHabilidade;}
    public BigDecimal getSalario() {return salario;}

    //setters
    public void setId(Long id) {this.id = id;}
    public void setIdTime(Long idTime) {this.idTime = idTime;}
    public void setNome(String nome) {this.nome = nome;}
    public void setDataNascimento(LocalDate dataNascimento) {this.dataNascimento = dataNascimento;}
    public void setNivelHabilidade(Integer nivelHabilidade) {this.nivelHabilidade = nivelHabilidade;}
    public void setSalario(BigDecimal salario) {this.salario = salario;}

    //construtor
    public Jogador(){}
    public Jogador(Long id){this.id = id;}
    public Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario){
        this.id = id;
        this.idTime = idTime;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nivelHabilidade = nivelHabilidade;
        this.salario = salario;
    }
}
