package projeto;

import java.util.ArrayList;
import java.util.List;

public class CadeiaSupermercados {
    List<Cliente> clientes;
    List<Produto> produtos;

    public CadeiaSupermercados() {
        this.clientes = new ArrayList<>();

        //APAGAR ISTO ANTES DE SUBMETER
        clientes.add(new Regulares("nome", "morada", "email", 123456789, new Data(1, 1, 1)));
        clientes.add(new Frequentes("n", "m", "e", 987654321, new Data(2, 2, 2)));

        this.produtos = new ArrayList<>();

        //APAGAR ISTO ANTES DE SUBMETER
        produtos.add(new Alimentares("identifcador", "nome", 1.1, 2, 3.1, 7));
        produtos.add(new Limpeza("identif", "no", 1.1, 2, 2));
        produtos.add(new Mobiliario("i", "n", 0.1, 2, new Dimensao(1,1,1), 1));
        produtos.get(0).addPromocoes(new Pague3Leve4(new Data(3, 4, 4), new Data(7, 4, 4)));
        produtos.get(2).addPromocoes(new PagueMenos(new Data(3, 4, 4), new Data(9, 4, 4)));
    }

    public Cliente contemEmail(String email){
        for(Cliente c: clientes){
            if(c.getEmail().equals(email)){
                return c;
            }
        }
        return null;
    }

    public void confirmaCompra(Compra compra, Cliente cliente){
        cliente.addCompra(compra);
    }

    public Produto obterProduto(String nomeProduto){
        for(Produto p: produtos){
            if(p.getNome().equalsIgnoreCase(nomeProduto)){
                return p;
            }
        }
        return null;
    }

    public List<Compra> obterCompras(){
        List<Compra> compras = new ArrayList<>();
        for(Cliente c: clientes){
            compras.addAll(c.getCompras());
        }
        return compras;
    }

    public String obterCatalogo(List<Compra> compras, Compra c){
        Data data = c.getData();
        String catalogo = "\n";
        for (Produto p : produtos) {
            int stock = p.obterStockAtual(data, compras) - c.obterQuantidade(p);
            if(stock < 0) stock = 0;

            Promocao promo = p.obterPromocao(data);
            catalogo = catalogo.concat(p + "€ (Stock: " + stock);
            if(promo != null) catalogo = catalogo.concat(" Promoção: " + promo);
            catalogo = catalogo.concat(")\n");
        }
        return catalogo;
    }

    public boolean cartaoEValido(String numCartao, String dataValidade, String cVV, Data dataCompra){
        int tamanho = numCartao.length();
        if(tamanho != 19) return false;
        for(int i = 0; i < cVV.length(); i++){
            if(cVV.charAt(i) < '0' || cVV.charAt(i) > '9') return false;
        }

        String[] dataV = dataValidade.split("/");
        Data dataFinal = new Data(1, Integer.parseInt(dataV[0]), Integer.parseInt(dataV[1]));
        if(dataFinal.compareTo(dataCompra) < 0) return false;

        for(int i = 0; i < numCartao.length(); i++){
            if(!(((i + 1) % 5 == 0 && numCartao.charAt(i) == ' ') || (numCartao.charAt(i) >= '0' && numCartao.charAt(i) <= '9'))) return false;
        }
        return true;
    }

    public boolean paypalEValido(String email, String emailCliente){
        return email.equals(emailCliente);
    }
}
