package projeto;

import java.util.Scanner;

public class Interface {

    // Método que lê um inteiro e devolve-o sem erros, se um acontecer devolve -1 (sendo considerado erro neste programa)
    public int readIntProtection(Scanner scanner){
        if(scanner.hasNextInt()) return scanner.nextInt();
        else{
            scanner.nextLine();
            return -1;
        }
    }

    public String readEmail(Scanner scanner){
        String email;
        do{
            email = scanner.nextLine();
        }while(email.isEmpty());
        return email;
    }

    public void menu(CadeiaSupermercados cad){
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        while (n != 2) {
            System.out.print("1. Iniciar Sessão\n2. Sair do Programa.\nOpção: ");
            n = readIntProtection(scanner);
            if (n == 1) {
                System.out.print("Email: ");
                Cliente c = cad.contemEmail(readEmail(scanner));
                if(c == null) System.out.println("Email inválido.");
                else System.out.printf("Login Válido...\n\nBom dia %s!\n", c.getNome());
            }
            else System.out.println("Opção inválida.");
        }
    }
}
