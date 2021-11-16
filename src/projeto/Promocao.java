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
}
