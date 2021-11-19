package projeto;

public class PagueMenos extends Promocao{

    public PagueMenos(Data inicio, Data fim) {
        super(inicio, fim);
    }

    public double desconto(int quantidade, double precoUni){
        return precoUni * 0.05 * (quantidade - 1);
    }

    @Override
    public String toString() {
        return "PagueMenos";
    }
}
