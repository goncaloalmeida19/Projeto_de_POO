package projeto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorFicheiros {
    public GestorFicheiros(){
    }

    /**
     * Ler o ficheiro dos clientes
     * @return lista de clientes
     */
    public List<Cliente> obterClientes(){
        int linha_erro = 0;
        try{
            File f = new File("clientes.obj");
            List<Cliente> clientes = new ArrayList<>();
            if(f.exists()){
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                boolean end = false;
                while(!end){
                    Cliente c = (Cliente)ois.readObject();
                    if(c != null)
                        clientes.add(c);
                    else
                        end = true;
                }
                ois.close();
                return clientes;
            } else{
                f = new File("clientes.txt");
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String l;
                while((l = br.readLine()) != null){
                    linha_erro++;
                    String[] sp = l.split(";");
                    String[] data = sp[4].split("/");
                    Data nasc = new Data(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]));
                    clientes.add(new Regular(sp[0],sp[1],sp[2],Integer.parseInt(sp[3]), nasc));
                }
                br.close();
                return clientes;
            }
        } catch(IOException ex){
            System.out.println("Erro ao ler ficheiro clientes.txt");
        } catch(NumberFormatException ex) {
            System.out.println("Erro no formato do ficheiro clientes.txt na linha " + linha_erro);
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto no ficheiro clientes.txt");
        }
        return null;
    }

    /**
     * Escreve uma lista de clientes num ficheiro de objetos
     * @param clientes lista de clientes a ser escrita
     */
    public void escreverClientes(List<Cliente> clientes){
        try {
            File f = new File("clientes.obj");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Cliente c: clientes)
                oos.writeObject(c);
            oos.close();
        } catch(IOException ex) {
            System.out.println("Erro ao ler ficheiro clientes.txt");
        } catch(NumberFormatException ex) {
            System.out.println("Erro no formato do ficheiro clientes.txt");
        }
    }
}
