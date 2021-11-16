package projeto;

public class Frequentes extends Cliente{
    public Frequentes(String nome, String morada, String email, int telefone, int data) {
        super(nome, morada, email, telefone, data);
    }

    public double precoDeEnvio(double preco){
        if(preco >= 40) return 0;
        else return 15;
    }
}
