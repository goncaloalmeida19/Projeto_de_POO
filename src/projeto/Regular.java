package projeto;

public class Regular extends Cliente{
    public Regular(String nome, String morada, String email, int telefone, Data data) {
        super(nome, morada, email, telefone, data);
    }

    public double precoDeEnvio(double preco){
       return 20;
    }
}
