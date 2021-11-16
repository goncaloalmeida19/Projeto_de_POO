package projeto;

public class PagueMenos extends Promocao{
    private int desconto;

    public PagueMenos(Data inicio, Data fim) {
        super(inicio, fim);
        this.desconto = 0;
    }

    public void addDesconto() {
        this.desconto += 5;
    }
}
