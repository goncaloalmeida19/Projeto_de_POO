package projeto;

import java.util.Scanner;

public class Interface {
    public String readString(Scanner scanner){
        String s;
        do{
            s = scanner.nextLine();
        }while(s.isEmpty());
        return s;
    }

    // Método que lê uma data e devolve-a, sendo null em caso de erro
    public Data readData(Scanner scanner){
        Data d = null;
        while(d == null){
            System.out.println("\nData:");
            d = readData(scanner);
            System.out.println();
            if (d == null) 
                continue;

            int i = 0;
            int[] dat = new int[3];
            for (; i < 3; i++) {
                if(i == 0) System.out.print("Dia: ");
                else if(i == 1) System.out.print("Mês: ");
                else System.out.print("Ano: ");
                // Se o scanner ler um inteiro, guarda o valor na tabela
                if(scanner.hasNextInt()) dat[i] = scanner.nextInt();
                else{
                    // Se o scanner não ler um inteiro o programa não termina a execução,
                    // dá outra oportunidade ao utilizador de inserir um elemento
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
        System.out.println("\nCompras realizadas até " + d);
        for(Venda v: c.getVendas()){
            System.out.print("\tVenda do dia " + v.getData() + ":");
            System.out.println(v);
        }
    }

    public void realizarCompra(Scanner scanner, Venda v){

    }

    public void confirmarCompra(){

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
            if(remove == 1) System.out.println("Produto removido do carrinho.");
            else if(remove == 0) System.out.println("Produto inserido não está no carrinho.");
            else System.out.println("Quantidade a remover do carrinho superior à existente.");
        }
    }

    public void verCarrinho(Scanner scanner, Venda v, CadeiaSupermercados cad){
        System.out.println("Carrinho:" + v);
        System.out.println("\n1. Remover produto do carrinho.\n2. Voltar para o menu de compra.");
        int op = 2;
        if(scanner.hasNextInt()) op = scanner.nextInt();
        if (op == 1) removerItemCarrinho(scanner, v, cad);
        else {
            if (op != 2) System.out.println("Opção inválida.");
        }
    }

    public void menuCompra(Data d, Scanner scanner, CadeiaSupermercados cad) {
        int op = -1;
        while(op != 4) {
            Venda v = new Venda(d);
            System.out.println("""

                    1. Ver Catálogo e comprar.
                    2. Ver carrinho.
                    3. Fazer pagamento.
                    4. Fechar menu de compra.""");
            System.out.print("Opção: ");
            if (scanner.hasNextInt())
                op = scanner.nextInt();
            switch (op) {
                case 1 -> realizarCompra(scanner, v);
                case 2 -> verCarrinho(scanner, v, cad);
                case 3 -> confirmarCompra();
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
            System.out.print("1. Iniciar Sessão\n2. Sair do Programa.\nOpção: ");
            if(scanner.hasNextInt())
                n = scanner.nextInt();
            if (n == 1) {
                System.out.print("Email: ");
                Cliente c = cad.contemEmail(readString(scanner));
                if(c == null) System.out.println("Email inválido.");
                else {
                    System.out.printf("Login Válido...\n\nBom dia %s!\n", c.getNome());
                    Data d = readData(scanner);
                    int op = -1;
                    while(op != 3) {
                        System.out.println("""

                                1. Realizar uma compra.
                                2. Consultar as compras realizadas.
                                3. Mudar data atual.
                                4. Terminar sessão.
                                """);
                        System.out.print("Opção: ");
                        if(scanner.hasNextInt())
                            op = scanner.nextInt();
                        switch (op) {
                            case 1 -> menuCompra(d, scanner, cad);
                            case 2 -> imprimirComprasRealizadas(c, d);
                            case 3 -> d = readData(scanner);
                            default -> {if (op != 4) System.out.println("Opção inválida.");}
                        }
                    }
                }
            }
            else if(n != 2) System.out.println("Opção inválida.");
        }
    }
}
