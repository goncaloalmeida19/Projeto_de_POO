package projeto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que contém a lista de clientes e produtos ligados à cadeia de supermercados.
 * Além disso, possui também métodos implementados na classe Main e na classe InterfaceUtilizador.
 */
public class CadeiaSupermercados implements Serializable {
    private List<Cliente> clientes;
    private List<Produto> produtos;

    /**
     * Construtor da classe CadeiaSupermercados
     */
    public CadeiaSupermercados() {
    }

    /**
     * Método para definir a lista de clientes
     * @param clientes Lista de clientes a ser definida
     */
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * Método para definir a lista de produtos
     * @param produtos Lista de produtos a ser definida
     */
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    /**
     * Método para verificar a igualdade de um email com o de um cliente
     * @param email Email a comparar
     * @return cliente cujo email seja igual ao dado a comparar ou null, caso esse cliente não exista
     */
    public Cliente contemEmail(String email){
        for(Cliente c: clientes){
            if(c.getEmail().equals(email)){
                return c;
            }
        }
        return null;
    }

    /**
     * Método para obter um produto que possua nome igual à String passada como parâmetro
     * @param nomeProduto String a comparar
     * @return produto cujo nome seja igual à String dada a comparar ou null, caso esse produto não exista
     */
    public Produto obterProduto(String nomeProduto){
        for(Produto p: produtos){
            if(p.getNome().equalsIgnoreCase(nomeProduto)){
                return p;
            }
        }
        return null;
    }

    /**
     * Método para obter uma lista de compras realizadas por todos os clientes
     * @return Lista de compras realizadas por todos os clientes
     */
    public List<Compra> obterCompras(){
        List<Compra> compras = new ArrayList<>();
        for(Cliente c: clientes){
            compras.addAll(c.getCompras());
        }
        return compras;
    }

    /**
     * Método para obter um catálogo dos produtos com as suas descrições no formato String
     * @param compras Lista de compras realizadas por todos os clientes
     * @param c Carrinho de um cliente (Compra a ser realizada)
     * @return Catálogo dos produtos com as suas descrições no formato String
     */
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
