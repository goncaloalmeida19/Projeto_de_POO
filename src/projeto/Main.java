package projeto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CadeiaSupermercados cad = new CadeiaSupermercados();
        Scanner scanner = new Scanner(System.in);
        InterfaceUtilizador i = new InterfaceUtilizador(cad, scanner);
        i.menu();
    }
}
