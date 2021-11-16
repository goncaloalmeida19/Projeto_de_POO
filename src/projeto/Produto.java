package projeto;

import java.util.ArrayList;
import java.util.List;

public abstract class Produto {
    private final String identificador;
    private final String nome;
    private final double precoUni;
    private final List<Promocao> promocoes;

    public Produto(String identificador, String nome, double precoUni) {
        this.identificador = identificador;
        this.nome = nome;
        this.precoUni = precoUni;
        this.promocoes = new ArrayList();
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoUni() {
        return precoUni;
    }
    
    public void addPromocoes(Promocao p){
        promocoes.add(p);
    }
    
    public List<Promocao> getPromocoes(){
        return promocoes;
    }
    
    public double obterPreco(int quantidade, Data d){
        double desconto = 0;
        for (Promocao p: promocoes){
            if(d.compareTo(p.getInicio()) >= 0 && d.compareTo(p.getFim()) <= 0){
                desconto = p.desconto(quantidade, precoUni);
                break;
            }
        }
        return precoUni * quantidade - desconto;
    }

    public boolean igual(Produto p){
        return identificador.equals(p.identificador) && nome.equals(p.nome);
    }

    public abstract double precoDeEnvio();
}
