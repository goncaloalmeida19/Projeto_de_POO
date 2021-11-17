package projeto;

import java.util.Scanner;

public class Interface {

    // Método que lê um inteiro positivo e devolve-o, sendo -1 em caso de erro
    public int readIntProtection(Scanner scanner){
        if(scanner.hasNextInt()) return scanner.nextInt();
        else{
            scanner.nextLine();
            return -1;
        }
    }

    public String readString(Scanner scanner){
        String s;
        do{
            s = scanner.nextLine();
        }while(s.isEmpty());
        return s;
    }

    // Método que lê uma data e devolve-a, sendo null em caso de erro
    public Data readData(Scanner scanner){
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
                scanner.next();
            }
        }
        Data d = new Data(dat[0], dat[1], dat[2]);
        if(d.eValida()) return d;
        else return null;
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

    public void verCarrinho(Venda v){
        System.out.println("Carrinho:" + v);
    }

    public void menuCompra(Data d, Scanner scanner) {
        Venda v = new Venda(d);
        System.out.println("\n1. Ver Catálogo e comprar.\n"
                +"2. Ver carrinho.\n"
                +"3. Fazer pagamento.\n"
                +"4. Fechar menu de compra.");
        System.out.print("Opção: ");
        int op = readIntProtection(scanner);
        switch (op) {
            case 1:
                realizarCompra(scanner, v);
                break;
            case 2: 
                verCarrinho(v);
                break;
            case 3: 
                //confirmarCompra();
                break;
            default:
                if (op != 4) System.out.println("Opção inválida.");
                break;
        }
    }

    public void menu(CadeiaSupermercados cad){
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        while (n != 2) {
            System.out.print("1. Iniciar Sessão\n2. Sair do Programa.\nOpção: ");
            n = readIntProtection(scanner);
            if (n == 1) {
                System.out.print("Email: ");
                Cliente c = cad.contemEmail(readString(scanner));
                if(c == null) System.out.println("Email inválido.");
                else {
                    System.out.printf("Login Válido...\n\nBom dia %s!\n", c.getNome());
                    int op = 0;
                    while(op != 3) {
                        System.out.println("\nData:");
                        Data d = readData(scanner);
                        System.out.println();
                        if (d == null) System.out.println("Data inválida");
                        else {
                            System.out.println("\n\n1. Realizar uma compra.\n"
                                    +"2. Consultar as compras realizadas.\n"
                                    +"3. Terminar sessão.\n");
                            System.out.print("Opção: ");
                            op = readIntProtection(scanner);
                            switch (op) {
                                case 1:
                                    menuCompra(d, scanner);
                                    break;
                                case 2:
                                    imprimirComprasRealizadas(c, d);
                                    break;
                                default:
                                    if (op != 3) System.out.println("Opção inválida.");
                                    break;
                            }
                        }
                    }
                }
            }
            else System.out.println("Opção inválida.");
        }
    }
}
