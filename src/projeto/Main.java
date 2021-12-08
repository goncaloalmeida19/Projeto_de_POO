package projeto;

import java.util.List;

/**
 * Classe Main do projeto.
 */
public class Main {
    private static final String fPath= "src/projeto/";
    private static final String fClientes = "clientes.txt";
    private static final String fProdutos = "produtos.txt";
    private static final String fPromocoes = "promocoes.txt";
    private static final String fCadSup = "cadSup.obj";

    public static void main(String[] args) {
        GestorFicheiros gf = new GestorFicheiros(fPath, fClientes, fProdutos, fPromocoes, fCadSup);

        CadeiaSupermercados cad = new CadeiaSupermercados();
        if(gf.lerFichObj()){ //Verificar se o ficheiro de objetos com os dados da cadeia de supermercados existe
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
