package projeto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CadeiaSupermercados cad = new CadeiaSupermercados();
        Interface i = new Interface();
        i.menu(cad);
    }
}
