package projeto;

public class Regulares extends Cliente{
    public Regulares(String nome, String morada, String email, int telefone, Data data) {
        super(nome, morada, email, telefone, data);
    }

    public double precoDeEnvio(double preco){
       return 20;
    }

    @Override
    public String toString() {
        return  "\tNome: " + nome + '\n' +
                "\tMorada: " + morada + '\n' +
                "\tEmail: "+ email + '\n' +
                "\tTelefone: " + telefone + '\n' +
                "\tData: " + Data + '\n';
    }

    public void setters(String altera, int op, Data d){
        if(op == 1) setNome(altera);
        if(op == 2) setMorada(altera);
        if(op == 3) setEmail(altera);
        if(op == 4) setTelefone(Integer.parseInt(altera));
        if(op == 5) setData(d);
    }
}
