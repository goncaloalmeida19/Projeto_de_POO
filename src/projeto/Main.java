package projeto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean log = true;
        CadeiaSupermercados cad = new CadeiaSupermercados();
        Scanner scanner = new Scanner(System.in);
        while (log) {
            System.out.print("1. Iniciar Sessão\n2. Sair do Programa.\nOpção: ");
            int n = cad.readIntProtection(scanner);
            if (n == 1) {
                System.out.print("Email: ");
                String email = cad.readEmail(scanner);
                Cliente c = cad.contemEmail(email);
                if(c == null) System.out.println("Email inválido.");
                else {
                    System.out.printf("Login Válido...\n\nBom dia %s!\n", c.getNome());
                }
            }
            else if(n == 2){
                log = false;
                System.exit(0);
            }
            else System.out.println("Opção inválida.");
        }
    }
}
