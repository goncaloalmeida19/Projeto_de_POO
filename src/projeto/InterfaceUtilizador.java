package projeto;

import java.util.List;
import java.util.Scanner;

public class InterfaceUtilizador {
    private final CadeiaSupermercados cad;
    private final Scanner scanner;
    private final GestorFicheiros gf;

    public InterfaceUtilizador(CadeiaSupermercados cad, GestorFicheiros gf){
        this.cad = cad;
        scanner = new Scanner(System.in);
        this.gf = gf;
    }

    private String readString(){
        String s;
        do{
            s = scanner.nextLine();
        }while(s.isEmpty());
        return s;
    }

    // Método que lê um inteiro positivo e devolve-o sem erros, se um acontecer devolve -1 (sendo considerado erro neste programa)
    private int readIntProtection(){
        if(scanner.hasNextInt()){
            int i = scanner.nextInt();
            return i <= 0 ? -1 : i;
        }
        else{
            scanner.nextLine();
            return -1;
        }
    }

    // Método que lê uma data e devolve-a, sendo null em caso de erro
    private Data readData(){
        Data d = new Data(0, 0 ,0);
        boolean valido = true;
        while(valido){
            System.out.println("\nData");
            int i = 0;
            int[] dat = new int[3];
            for (; i < 3; i++) {
                switch (i) {
                    case 0 -> System.out.print("\tDia: ");
                    case 1 -> System.out.print("\tMês: ");
                    default -> System.out.print("\tAno: ");
                }
                // Se o scanner ler um inteiro, guarda o valor na tabela
                dat[i] = readIntProtection();
                if(dat[i] < 0){
                    System.out.println("Número inválido.");
                    i--;
                }
            }
            d.setData(dat[0], dat[1], dat[2]);
            if(!d.eValida()) System.out.println("Data inválida");
            else valido = false;
        }
        return d;
    }

    private void imprimirComprasRealizadas(Cliente cliente, Data data){
        List<Compra> compras = cliente.obterComprasAteData(data);
        if(compras.size() == 0) System.out.println("\nNão foi encontrada nenhuma compra até " + data);
        else{
            System.out.println("\nCompras realizadas até " + data + ":\n");
            for(Compra c: compras){
                System.out.println("Compra do dia " + c.getData() + ":" + c);
                double preco = c.getPrecoSemEnvioFinal();
                System.out.println("\n\tPreço com promoção (s/ portes): " + String.format("%.2f",preco) + "€");
                double precoFinal = c.getPrecoComEnvioFinal();
                System.out.println("\tPreço com promoção (c/ portes): " + String.format("%.2f",precoFinal) + "€\n");
            }
        }
    }

    private void adicionarItemCarrinho(Compra compra, List<Compra> compras){
        System.out.print("Produto a adicionar: ");
        String nomeProduto = readString();
        Produto produto = cad.obterProduto(nomeProduto);

        if(produto == null) System.out.println("Produto inválido");
        else{
            int quantidade = 0;
            do{
                System.out.print("Quantidade a adicionar: ");
                boolean valido = scanner.hasNextInt();
                if(valido) quantidade = scanner.nextInt();
                if(!valido || quantidade <= 0) System.out.println("Insira uma quantidade válida.");
            }while(quantidade <= 0);

            int adiciona = compra.addCarrinho(produto, quantidade, compras);

            switch (adiciona) {
                case -1 -> System.out.println("Não existe stock para o produto requerido.");
                case -2 -> {
                    if (quantidade == 1) System.out.println(produto.getNome() + " adicionado ao carrinho.");
                    else System.out.println(quantidade + " " + produto.getNome() + " adicionados ao carrinho.");
                }
                default -> System.out.printf("A quantidade requerida é inferior à disponível. " +
                        "(Quantidade disponível: %d)\n", adiciona);
            }
        }
    }

    private void realizarCompra(Compra compra){
        int op = 1;
        List<Compra> compras = cad.obterCompras();
        while(op != 2) {
            String catalogo = cad.obterCatalogo(compras, compra);
            System.out.println("\nCatálogo:" + catalogo);
            System.out.println("1. Adicionar produto ao carrinho.\n2. Voltar para o menu de compra.");
            System.out.print("Opção: ");
            op = readIntProtection();
            switch(op){
                case 1 -> adicionarItemCarrinho(compra, compras);
                case 2 -> {}
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void removerItemCarrinho(Compra compra){
        System.out.print("Produto a remover: ");
        String nomeProduto = readString();
        Produto produto = cad.obterProduto(nomeProduto);
        if(produto == null) System.out.println("Produto inválido");
        else{
            int quantidade = 0;
            do{
                System.out.print("Quantidade a remover: ");
                boolean valido = scanner.hasNextInt();
                if(valido) quantidade = scanner.nextInt();
                if(!valido || quantidade <= 0) System.out.println("Insira uma quantidade válida.");
            }while(quantidade <= 0);

            int remove = compra.removerCarrinho(produto, quantidade);
            if(remove == 0) System.out.println("Produto inserido não está no carrinho.");
            // Se remover com sucesso ou o stock total, apresentar esta mensagem
            else System.out.println("Produto removido do carrinho.");
        }
    }

    private void verCarrinho(Compra compra, Cliente cliente){
        if(compra.getCarrinho().size() == 0) System.out.println("O carrinho encontra-se vazio.");
        else{
        int op = 1;
        while(op != 3){
            List<Item> carrinho = compra.getCarrinho();
            System.out.println("\nCarrinho: " + compra);
                double preco = compra.precoSemEnvio();
                System.out.println("\nPreço sem promoção (s/ portes): " + String.format("%.2f",preco) + "€");
                double precoFinal = compra.precoDeEnvioTotal(cliente, preco) + preco;
                System.out.println("Preço com promoção (c/ portes): " + String.format("%.2f",precoFinal) + "€");

                System.out.println("""
    
                        1. Remover produto do carrinho.
                        2. Remover todos os produtos do carrinho
                        3. Voltar para o menu de compra.""");
                System.out.print("Opção: ");
                op = readIntProtection();

                if(carrinho.size() == 0 && (op == 1 || op == 2)) System.out.println("Não existem produtos para remover.");
                else{
                    switch (op) {
                        case 1 -> removerItemCarrinho(compra);
                        case 2 -> {
                            compra.clear();
                            System.out.println("O carrinho encontra-se vazio.");
                            op = 3;
                        }
                        case 3 -> {}
                        default -> System.out.println("Opção inválida.");
                    }
                }
            }
        }
    }

    private boolean printFinal(Compra compra, Cliente cliente){
        cad.confirmaCompra(compra, cliente);
        System.out.println("\nEncomenda enviada para " + cliente.getMorada() +
                           " em nome de " + cliente.getNome());
        return true;
    }

    private boolean confirmarCompra(Compra compra, Cliente cliente){
        if(compra.getCarrinho().size() == 0) System.out.println("O carrinho encontra-se vazio.");
        else{
            System.out.println("\nCarrinho final:" + compra);
            double preco = compra.precoSemEnvio();
            compra.setPrecoSemEnvioFinal(preco);
            System.out.println("\nPreço com promoção (s/ portes): " + String.format("%.2f",preco) + "€");
            double precoFinal = compra.precoDeEnvioTotal(cliente, preco) + preco;
            compra.setPrecoComEnvioFinal(precoFinal);
            System.out.println("Preço com promoção (c/ portes): " + String.format("%.2f",precoFinal) + "€\n");
            int op = 1;
            while(op != 2){
                System.out.println("1. Confirmar a compra.\n2. Voltar para o menu de compra.");
                System.out.print("Opção: ");
                op = readIntProtection();
                switch(op){
                    case 1 -> { if(printFinal(compra, cliente)) return true; }
                    case 2 -> {}
                    default -> System.out.println("Opção inválida.");
                }
                System.out.println();
            }
        }
        return false;
    }

    private void menuCompra(Data data, Cliente cliente) {
        int op = -1;
        Compra compra = new Compra(data);
        while(op != 4) {
            System.out.println("""

                    1. Ver Catálogo e comprar.
                    2. Ver carrinho.
                    3. Fazer pagamento.
                    4. Fechar menu de compra.""");
            System.out.print("Opção: ");
            op = readIntProtection();
            switch (op) {
                case 1 -> realizarCompra(compra);
                case 2 -> verCarrinho(compra, cliente);
                case 3 -> {
                    if(confirmarCompra(compra, cliente)) {
                        gf.escreverCadSup(cad);
                        return;
                    }
                }
                case 4 -> {}
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    public void menu(){
        int op = -1;
        while (op != 2) {
            if(op != -1) System.out.println(); // Espaçamento na consola para uma melhor leitura
            System.out.print("1. Iniciar Sessão.\n2. Sair do Programa.\nOpção: ");
            op = readIntProtection();
            if (op == 1) {
                System.out.print("Email: ");
                Cliente c = cad.contemEmail(readString());
                if(c == null) System.out.println("Email inválido.");
                else {
                    System.out.printf("Login Válido...\n\nBom dia %s!\n", c.getNome());
                    Data d = readData();
                    int op2 = -1;
                    while(op2 != 4) {
                        System.out.println("""

                                1. Realizar uma compra.
                                2. Consultar as compras realizadas.
                                3. Mudar data atual.
                                4. Terminar sessão.
                                """);
                        System.out.print("Opção: ");
                        op2 = readIntProtection();
                        switch (op2) {
                            case 1 -> menuCompra(d, c);
                            case 2 -> imprimirComprasRealizadas(c, d);
                            case 3 -> d = readData();
                            case 4 -> {}
                            default -> System.out.println("Opção inválida.");
                        }
                    }
                }
            }
            else if(op != 2) System.out.println("Opção inválida.");
        }
    }
}
