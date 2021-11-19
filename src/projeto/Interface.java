package projeto;

import java.util.List;
import java.util.Scanner;

public class Interface {
    public String readString(Scanner scanner){
        String s;
        do{
            s = scanner.nextLine();
        }while(s.isEmpty());
        return s;
    }

    // Método que lê um inteiro e devolve-o sem erros, se um acontecer devolve -1 (sendo considerado erro neste programa)
    public int readIntProtection(Scanner scanner){
        if(scanner.hasNextInt()) return scanner.nextInt();
        else{
            scanner.nextLine();
            return -1;
        }
    }

    // Método que lê uma data e devolve-a, sendo null em caso de erro
    public Data readData(Scanner scanner){
        Data d = null;
        while(d == null){
            System.out.println("\nData");
            int i = 0;
            int[] dat = new int[3];
            for (; i < 3; i++) {
                if(i == 0) System.out.print("\tDia: ");
                else if(i == 1) System.out.print("\tMês: ");
                else System.out.print("\tAno: ");
                // Se o scanner ler um inteiro, guarda o valor na tabela
                dat[i] = readIntProtection(scanner);
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

    public void imprimirComprasRealizadas(Cliente c, Data d){
        List<Venda> vendas = c.getVendas();
        if(vendas.size() == 0) System.out.println("\nNão foi encontrada nenhuma compra até " + d);
        else{
            System.out.println("\nCompras realizadas até " + d + ":\n");
            for(Venda v: c.getVendas()){
                System.out.print("Venda do dia " + v.getData() + ":" + v);
            }
        }
    }

    public void adicionarItemCarrinho(Scanner scanner, Venda v, List<Venda> vendas, CadeiaSupermercados cad){
        System.out.print("Produto a adicionar: ");
        String nomeProduto = readString(scanner);
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

            int adiciona = v.addCarrinho(produto, quantidade, vendas);

            if(adiciona == -2) {
                if(quantidade == 1) System.out.println(produto.getNome() + " adicionado ao carrinho.");
                else System.out.println(quantidade + " " + produto.getNome() + " adicionados ao carrinho.");
            }
            else if(adiciona == -1) System.out.println("Não existe stock para o produto requerido.");
            else System.out.println(adiciona + " " + produto.getNome() + " adicionados ao carrinho.");
        }
    }

    public void realizarCompra(Scanner scanner, Venda v, CadeiaSupermercados cad){
        int op = 1;
        List<Venda> vendas = cad.obterVendas();
        while(op != 2) {
            System.out.println("\nCatálogo:");
            for (Produto p : cad.produtos) {
                Data data = v.getData();
                int stock = p.obterStockAtual(data, vendas) - v.obterQuantidade(p);
                if(stock < 0) stock = 0;

                Promocao promo = p.obterPromocao(data);
                String print = "\t" + p.getNome() + " a " + p.getPrecoUni() + "€ (Stock: " + stock;
                if(promo != null) print += " Promoção: " + promo;
                System.out.println(print+ ")");
            }
            System.out.println("\n1. Adicionar produto do carrinho.\n2. Voltar para o menu de compra.");
            System.out.print("Opção: ");
            op = readIntProtection(scanner);
            if (op == 1) adicionarItemCarrinho(scanner, v, vendas, cad);
            else {
                if (op != 2) System.out.println("Opção inválida.");
            }
        }
    }

    public void removerItemCarrinho(Scanner scanner, Venda v, CadeiaSupermercados cad){
        System.out.print("Produto a remover: ");
        String nomeProduto = readString(scanner);
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

            int remove = v.removeCarrinho(produto, quantidade);
            if(remove == 0) System.out.println("Produto inserido não está no carrinho.");
            // Se remover com sucesso ou o stock total, apresentar esta mensagem
            else System.out.println("Produto removido do carrinho.");
        }
    }

    public void verCarrinho(Scanner scanner, Venda v, CadeiaSupermercados cad){
        int op = 1;
        while(op != 3){
            List<Item> carrinho = v.getCarrinho();
            System.out.print("\nCarrinho: " + v);

            double preco = v.precoSemEnvio();
            System.out.println("\nPreço sem portes: " + String.format("%.2f",preco) + "\n");

            System.out.println("""

                    1. Remover produto do carrinho.
                    2. Remover todos os produtos do carrinho
                    3. Voltar para o menu de compra.""");
            System.out.print("Opção: ");
            op = readIntProtection(scanner);
            boolean validSize = carrinho.size() != 0;
            if (op == 2 && validSize) v.clear();
            else if(op == 1 && validSize) removerItemCarrinho(scanner, v, cad);
            else {
                if(op == 1 || op == 2) System.out.println("Não existem produtos para remover.");
                else if (op != 3) System.out.println("Opção inválida.");
            }
        }
    }

    public boolean printFinal(Venda v, Cliente cliente, CadeiaSupermercados cad){
        cad.confirmaCompra(v, cliente);
        System.out.println("\nEncomenda enviada para " + cliente.getMorada() +
                           "em nome de " + cliente.getNome() +
                           "\nObrigado por fazer compras com a SONAI, " +
                           "a melhor cadeia de supermercados da Península Ibérica");
        return true;
    }

    public void alterarDados(Cliente c, Scanner scanner){
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
            op = readIntProtection(scanner);
            Data d;
            if (op != 6) {
                if(op >= 1 && op <= 5){
                    if(op != 5){
                        System.out.print(alteracoes[op - 1] + ": ");
                        d = new Data(0, 0, 0);
                    }
                    else d = readData(scanner);

                    String altera = readString(scanner);
                    c.setters(altera, op, d);
                    System.out.println("Os seus dados" + c);
                }
                else System.out.println("Opção inválida!");
            }
        }
    }

    public boolean opcaoPagamento(Scanner scanner, Venda v, Cliente cliente, double precoFinal, CadeiaSupermercados cad){
        int op = -1;
        while(op != 3){
            System.out.println("\nPreço final a pagar: " + String.format("%.2f",precoFinal) + "€");
            System.out.println("\nMétodos de pagamento disponíveis:");
            System.out.println("\t1.Multibanco\n\t2.Paypal\n\t3.Voltar para o menu de pagamento");
            System.out.print("Opção: ");
            op = readIntProtection(scanner);

            if (op == 1) {
                System.out.print("Número do cartão (XXXX XXXX XXXX XXXX): ");
                String numCartao = readString(scanner);
                System.out.print("Data de validade (MM/AA): ");
                String dataValidade = readString(scanner);
                System.out.print("CVV: ");
                String cVV = readString(scanner);
                if(cad.cartaoEValido(numCartao, dataValidade, cVV, v.getData())) return printFinal(v, cliente, cad);
                else System.out.println("Cartão inválido");
            }
            else if(op == 2){
                System.out.print("Email: ");
                String email = readString(scanner);
                if(cad.paypalEValido(email, cliente.getEmail())) return printFinal(v, cliente, cad);
                else System.out.println("Email inválido.");
            }
            else{
                if(op != 3) System.out.println("Opção inválida.");
            }
        }
        return false;
    }

    public boolean pagarCompra(Scanner scanner, Venda v, Cliente cliente, double precoFinal, CadeiaSupermercados cad){
        int op = -1;
        boolean comp = true;
        while(op != 2){
            System.out.println("\nOs seus dados estão corretos?:\n" + cliente);
            System.out.println("1. Sim\n2. Não");
            System.out.print("Opção: ");
            op = readIntProtection(scanner);
            if (op == 1) {
                comp = opcaoPagamento(scanner, v, cliente, precoFinal, cad);
                return comp;
            }
            else if (op == 2) {
                alterarDados(cliente, scanner);
                comp = opcaoPagamento(scanner, v, cliente, precoFinal, cad);
            }
            else System.out.println("Opção inválida.");
        }
        return comp;
    }

    public void confirmarCompra(Scanner scanner, Venda v, Cliente cliente, CadeiaSupermercados cad){
        if(v.getCarrinho().size() == 0) System.out.println("O carrinho encontra-se vazio.");
        else{
            System.out.println("\nCarrinho final:" + v);
            double preco = v.precoSemEnvio();
            System.out.println("\nPreço com desconto (s/ portes): " + String.format("%.2f",preco) + "€");
            double precoFinal = v.precoDeEnvioTotal(cliente, preco) + preco;
            System.out.println("Preço com desconto (c/ portes): " + String.format("%.2f",precoFinal) + "€\n");
            int op = 1;
            boolean comp = true;
            while(op != 2){
                System.out.println("1. Prosseguir com o pagamento.\n2. Voltar para o menu de compra.");
                System.out.print("Opção: ");
                op = readIntProtection(scanner);
                if (op == 1) comp = pagarCompra(scanner, v, cliente, precoFinal, cad);
                else {
                    if (op != 2) System.out.println("Opção inválida.");
                }
                System.out.println();
                if(!comp) op = 2;
            }
        }
    }

    public void menuCompra(Data d, Scanner scanner, CadeiaSupermercados cad, Cliente c) {
        int op = -1;
        Venda v = new Venda(d);
        while(op != 4) {
            System.out.println("""

                    1. Ver Catálogo e comprar.
                    2. Ver carrinho.
                    3. Fazer pagamento.
                    4. Fechar menu de compra.""");
            System.out.print("Opção: ");
            op = readIntProtection(scanner);
            switch (op) {
                case 1 -> realizarCompra(scanner, v, cad);
                case 2 -> verCarrinho(scanner, v, cad);
                case 3 -> confirmarCompra(scanner, v, c, cad);
                default -> {
                    if (op != 4) System.out.println("Opção inválida.");
                }
            }
        }
    }

    public void menu(CadeiaSupermercados cad){
        Scanner scanner = new Scanner(System.in);
        int n = -1;
        while (n != 2) {
            if(n != -1) System.out.println(); // Espaçamento na consola para uma melhor leitura
            System.out.print("1. Iniciar Sessão.\n2. Sair do Programa.\nOpção: ");
            n = readIntProtection(scanner);
            if (n == 1) {
                System.out.print("Email: ");
                Cliente c = cad.contemEmail(readString(scanner));
                if(c == null) System.out.println("Email inválido.");
                else {
                    System.out.printf("Login Válido...\n\nBom dia %s!\n", c.getNome());
                    Data d = readData(scanner);
                    int op = -1;
                    while(op != 5) {
                        System.out.println("""

                                1. Realizar uma compra.
                                2. Consultar as compras realizadas.
                                3. Alterar dados pessoais
                                4. Mudar data atual.
                                5. Terminar sessão.
                                """);
                        System.out.print("Opção: ");
                        op = readIntProtection(scanner);
                        switch (op) {
                            case 1 -> menuCompra(d, scanner, cad, c);
                            case 2 -> imprimirComprasRealizadas(c, d);
                            case 3 -> alterarDados(c, scanner);
                            case 4 -> d = readData(scanner);
                            default -> {if (op != 5) System.out.println("Opção inválida.");}
                        }
                    }
                }
            }
            else if(n != 2) System.out.println("Opção inválida.");
        }
    }
}
