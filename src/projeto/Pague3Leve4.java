package projeto;

public class Pague3Leve4 extends Promocao{
    public Pague3Leve4(Data inicio, Data fim) {
        super(inicio, fim);
    }

    public double desconto(int quantidade, double precoUni){
        return precoUni * (int)(quantidade / 4);
    }

    @Override
    public String toString() {
        return "Pague3Leve4";
    }
}
