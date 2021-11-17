package projeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadeiaSupermercados {

    List<Cliente> clientes = new ArrayList<>();

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
        if(email.indexOf('@') != -1) return email;
        return null;
    }

    public Cliente contemEmail(String email){
        for(Cliente c: clientes){
            if(c.getEmail().equals(email)){
                return c;
            }
        }
        return null;
    }
}
