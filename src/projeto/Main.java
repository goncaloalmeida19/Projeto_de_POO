package projeto;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GestorFicheiros gf = new GestorFicheiros();
        CadeiaSupermercados cad = new CadeiaSupermercados();
        InterfaceUtilizador i = new InterfaceUtilizador(cad);
        cad.setClientes(gf.obterClientes());
        List<Produto> produtos = gf.obterPromocoes(gf.obterProdutos());
        cad.setProdutos(produtos);
        i.menu();
        gf.escreverCadSup(cad);
    }
}
