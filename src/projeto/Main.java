package projeto;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GestorFicheiros gf = new GestorFicheiros();

        CadeiaSupermercados cad = new CadeiaSupermercados();
        if(gf.lerFichObj()){ //verificar se o ficheiro de objetos existe
            cad = gf.lerCadSup();
            if(cad == null) return;
        } else{
            List<Cliente> clientes = gf.obterClientes();
            if(clientes == null) return;
            cad.setClientes(clientes);

            List<Produto> produtos = gf.obterProdutos();
            if(produtos == null) return;
            produtos = gf.obterPromocoes(produtos);
            if(produtos == null) return;
            cad.setProdutos(produtos);
        }

        InterfaceUtilizador i = new InterfaceUtilizador(cad, gf);
        i.menu();
    }
}
