package projeto;

import java.util.ArrayList;
import java.util.List;

public class CadeiaSupermercados {
    private List<Cliente> clientes;
    private List<Produto> produtos;

    public CadeiaSupermercados() {
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
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
            catalogo = catalogo.concat(")\n\n");
        }
        return catalogo;
    }
}
