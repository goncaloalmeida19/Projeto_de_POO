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
        int op = -1;
        if(scanner.hasNextInt())
            op = scanner.nextInt();
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
                        System.out.println("\n\n1. Realizar uma compra.\n"
                                + "2. Consultar as compras realizadas.\n"
                                + "3. Mudar data atual.\n"
                                + "4. Terminar sessão.\n");
                        System.out.print("Opção: ");
                        if(scanner.hasNextInt())
                            op = scanner.nextInt();
                        switch (op) {
                            case 1:
                                menuCompra(d, scanner);
                                break;
                            case 2:
                                imprimirComprasRealizadas(c, d);
                                break;
                            case 3:
                                d = readData(scanner);
                                break;
                            default:
                                if (op != 4) System.out.println("Opção inválida.");
                                break;
                        }
                    }
                }
            }
            else if(n != 2) System.out.println("Opção inválida.");
        }
    }
}
