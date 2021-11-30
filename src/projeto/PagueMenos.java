package projeto;

public class PagueMenos extends Promocao{

    public PagueMenos(Data inicio, Data fim) {
        super(inicio, fim);
    }

    public double desconto(int quantidade, double precoUni){
        double desc = 0;
        for (int i = 0; i < quantidade; i++) {
            if(i*0.05 < 0.5) //verificar se o desconto é inferior a 50%
                desc += precoUni * i*0.05;
            else
                desc += precoUni * 0.5;
        }
        return desc;
    }

    @Override
    public String toString() {
        return "PagueMenos";
    }
}
