package projeto;

public class Pague3Leve4 extends Promocao{
    public Pague3Leve4(Data inicio, Data fim) {
        super(inicio, fim);
    }

    public double desconto(int quantidade, double precoUni){
        return precoUni * (quantidade % 4);
    }
}
