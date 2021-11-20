package projeto;

import java.util.List;
import java.util.Scanner;

public class InterfaceUtilizador {
    private final CadeiaSupermercados cad;
    private final Scanner scanner;

    public InterfaceUtilizador(CadeiaSupermercados cad, Scanner scanner){
        this.cad = cad;
        this.scanner = scanner;
    }

    public String readString(){
        String s;
        do{
            s = scanner.nextLine();
        }while(s.isEmpty());
        return s;
    }

    // Método que lê um inteiro positivo e devolve-o sem erros, se um acontecer devolve -1 (sendo considerado erro neste programa)
    public int readIntProtection(){
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
    public Data readData(){
        Data d = null;
        while(d == null){
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
            d = new Data(dat[0], dat[1], dat[2]);
            if(!d.eValida()){
                d = null;
                System.out.println("Data inválida");
            }
        }
        return d;
    }

    public void imprimirComprasRealizadas(Cliente cliente, Data data){
        List<Compra> compras = cliente.getCompras();
        if(compras.size() == 0) System.out.println("\nNão foi encontrada nenhuma compra até " + data);
        else{
            System.out.println("\nCompras realizadas até " + data + ":\n");
            for(Compra v: cliente.getCompras()){
                System.out.print("Venda do dia " + v.getData() + ":" + v);
            }
        }
    }

    public void adicionarItemCarrinho(Compra compra, List<Compra> compras){
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
                default -> System.out.println(adiciona + " " + produto.getNome() + " adicionados ao carrinho.");
            }
        }
    }

    public void realizarCompra(Compra compra){
        int op = 1;
        List<Compra> compras = cad.obterCompras();
        while(op != 2) {
            String catalogo = cad.obterCatalogo(compras, compra);
            System.out.println("\nCatálogo:" + catalogo);
            System.out.println("\n1. Adicionar produto do carrinho.\n2. Voltar para o menu de compra.");
            System.out.print("Opção: ");
            op = readIntProtection();
            switch(op){
                case 1 -> adicionarItemCarrinho(compra, compras);
                case 2 -> {}
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    public void removerItemCarrinho(Compra compra){
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

    public void verCarrinho(Compra compra){
        int op = 1;
        while(op != 3){
            List<Item> carrinho = compra.getCarrinho();
            System.out.print("\nCarrinho: " + compra);
            double preco = compra.precoSemEnvio();
            System.out.println("\nPreço sem portes: " + String.format("%.2f",preco) + "\n");

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
                    case 2 -> compra.clear();
                    case 3 -> {}
                    default -> System.out.println("Opção inválida.");
                }
            }
        }
    }

    public boolean printFinal(Compra compra, Cliente cliente){
        cad.confirmaCompra(compra, cliente);
        System.out.println("\nEncomenda enviada para " + cliente.getMorada() +
                           "em nome de " + cliente.getNome() +
                           "\nObrigado por fazer compras com a SONAI, " +
                           "a melhor cadeia de supermercados da Península Ibérica");
        return true;
    }

    public void alterarDados(Cliente cliente){
        int op = -1;
        String[] alteracoes = {"Nome", "Morada", "Email", "Telemóvel", "Data de nascimento"};
        while(op != 6){
            System.out.println("""

                                1. Alterar nome.
                                2. Alterar morada.
                                3. Alterar email.
                                4. Alterar telemóvel.
                                5. Alterar data de nascimento.
                                6. Continuar
                                """);
            System.out.print("Opção: ");
            op = readIntProtection();
            if (op != 6) {
                if(op >= 1 && op <= 5){
                    if(op != 5){
                        System.out.print(alteracoes[op - 1] + ": ");
                        String altera = readString();
                        cliente.setters(altera, op);
                    }
                    else cliente.setDataNascimento(readData());

                    System.out.println("Os seus dados" + cliente);
                }
                else System.out.println("Opção inválida!");
            }
        }
    }

    public boolean opcaoPagamento(Compra compra, Cliente cliente, double precoFinal){
        int op = -1;
        while(op != 3){
            System.out.println("\nPreço final a pagar: " + String.format("%.2f",precoFinal) + "€");
            System.out.println("\nMétodos de pagamento disponíveis:");
            System.out.println("\t1.Multibanco\n\t2.Paypal\n\t3.Voltar para o menu de pagamento");
            System.out.print("Opção: ");
            op = readIntProtection();

            switch (op) {
                case 1 -> {
                    System.out.print("Número do cartão (XXXX XXXX XXXX XXXX): ");
                    String numCartao = readString();
                    System.out.print("Data de validade (MM/AA): ");
                    String dataValidade = readString();
                    System.out.print("CVV: ");
                    String cVV = readString();
                    if (cad.cartaoEValido(numCartao, dataValidade, cVV, compra.getData()))
                        return printFinal(compra, cliente);
                    else System.out.println("Cartão inválido");
                }
                case 2 -> {
                    System.out.print("Email: ");
                    String email = readString();
                    if (cad.paypalEValido(email, cliente.getEmail())) return printFinal(compra, cliente);
                    else System.out.println("Email inválido.");
                }
                case 3 -> {}
                default -> System.out.println("Opção inválida.");
            }
        }
        return false;
    }

    public boolean pagarCompra(Compra compra, Cliente cliente, double precoFinal){
        int op = -1;
        boolean comp = true;
        while(op != 2){
            System.out.println("\nOs seus dados estão corretos?:\n" + cliente);
            System.out.println("1. Sim\n2. Não");
            System.out.print("Opção: ");
            op = readIntProtection();
            switch (op) {
                case 1 -> {
                    comp = opcaoPagamento(compra, cliente, precoFinal);
                    return comp;
                }
                case 2 -> {
                    alterarDados(cliente);
                    comp = opcaoPagamento(compra, cliente, precoFinal);
                }
                default -> System.out.println("Opção inválida.");
            }
        }
        return comp;
    }

    public void confirmarCompra(Compra compra, Cliente cliente){
        if(compra.getCarrinho().size() == 0) System.out.println("O carrinho encontra-se vazio.");
        else{
            System.out.println("\nCarrinho final:" + compra);
            double preco = compra.precoSemEnvio();
            System.out.println("\nPreço com desconto (s/ portes): " + String.format("%.2f",preco) + "€");
            double precoFinal = compra.precoDeEnvioTotal(cliente, preco) + preco;
            System.out.println("Preço com desconto (c/ portes): " + String.format("%.2f",precoFinal) + "€\n");
            int op = 1;
            boolean comp = true;
            while(op != 2){
                System.out.println("1. Prosseguir com o pagamento.\n2. Voltar para o menu de compra.");
                System.out.print("Opção: ");
                op = readIntProtection();
                switch(op){
                    case 1 -> comp = pagarCompra(compra, cliente, precoFinal);
                    case 2 -> {}
                    default -> System.out.println("Opção inválida.");
                }
                System.out.println();
                if(!comp) return;
            }
        }
    }

    public void menuCompra(Data data, Cliente cliente) {
        int op = -1;
        Compra v = new Compra(data);
        while(op != 4) {
            System.out.println("""

                    1. Ver Catálogo e comprar.
                    2. Ver carrinho.
                    3. Fazer pagamento.
                    4. Fechar menu de compra.""");
            System.out.print("Opção: ");
            op = readIntProtection();
            switch (op) {
                case 1 -> realizarCompra(v);
                case 2 -> verCarrinho(v);
                case 3 -> confirmarCompra(v, cliente);
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
                    while(op2 != 5) {
                        System.out.println("""

                                1. Realizar uma compra.
                                2. Consultar as compras realizadas.
                                3. Alterar dados pessoais
                                4. Mudar data atual.
                                5. Terminar sessão.
                                """);
                        System.out.print("Opção: ");
                        op2 = readIntProtection();
                        switch (op2) {
                            case 1 -> menuCompra(d, c);
                            case 2 -> imprimirComprasRealizadas(c, d);
                            case 3 -> alterarDados(c);
                            case 4 -> d = readData();
                            case 5 -> {}
                            default -> System.out.println("Opção inválida.");
                        }
                    }
                }
            }
            else if(op != 2) System.out.println("Opção inválida.");
        }
    }
}
