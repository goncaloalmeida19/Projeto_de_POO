package projeto;

public abstract class Promocao {
    private final Data inicio;
    private final Data fim;

    public Promocao(Data inicio, Data fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public Data getInicio() {
        return inicio;
    }

    public Data getFim() {
        return fim;
    }

    public abstract double desconto(int quantidade, double precoUni);

    @Override
    public abstract String toString();
}
