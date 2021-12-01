package projeto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorFicheiros {
    public GestorFicheiros(){
    }

    //constantes
    private static final String fClientes = "clientes";
    private static final String fProdutos = "produtos";
    private static final String fPromocoes = "promocoes";

    /**
     * Ler o ficheiro dos clientes
     * @return lista de clientes
     */
    public List<Cliente> obterClientes(){
        int linha_erro = 0;
        File f = new File(fClientes + ".obj");
        try{
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
                f = new File(fClientes + ".txt");
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String l;
                while((l = br.readLine()) != null){
                    linha_erro++;
                    String[] sp = l.split(";");
                    String[] data = sp[4].split("/");
                    Data nasc = new Data(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]));
                    if(sp[5].equals("regular"))
                        clientes.add(new Regular(sp[0],sp[1],sp[2],Integer.parseInt(sp[3]), nasc));
                    else if(sp[5].equals("frequente"))
                        clientes.add(new Frequente(sp[0],sp[1],sp[2],Integer.parseInt(sp[3]), nasc));
                    //else throw new NumberFormatException();
                    else{
                        System.out.println("Erro no formato do ficheiro " + fClientes + ".txt na linha " + linha_erro);
                        return null;
                    }
                }
                br.close();
                return clientes;
            }
        } catch(IOException ex){
            System.out.println("Erro ao ler ficheiro" + f.getName());
        } catch(NumberFormatException ex) {
            //verificar se o erro ocorreu numa linha específica do ficheiro de texto
            if(linha_erro != 0)
                System.out.println("Erro no formato do ficheiro " + f.getName() + " na linha " + linha_erro);
            else
                System.out.println("Erro no formato do ficheiro " + f.getName());
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto no ficheiro " + f.getName());
        }
        return null;
    }

    /**
     * Escreve uma lista de clientes num ficheiro de objetos
     * @param clientes lista de clientes a ser escrita
     */
    public void escreverClientes(List<Cliente> clientes){
        try {
            File f = new File(fClientes + ".obj");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Cliente c: clientes)
                oos.writeObject(c);
            oos.close();
        } catch(IOException ex) {
            System.out.println("Erro ao ler ficheiro " + fClientes + ".obj");
        } catch(NumberFormatException ex) {
            System.out.println("Erro no formato do ficheiro " + fClientes + ".obj");
        }
    }

    /**
     * Ler o ficheiro dos produtos
     * @return lista de produtos
     */
    public List<Produto> obterProdutos(){
        int linha_erro = 0;
        File f = new File(fProdutos + ".obj");
        try{
            List<Produto> produtos = new ArrayList<>();
            if(f.exists()){
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                boolean end = false;
                while(!end){
                    Produto p = (Produto)ois.readObject();
                    if(p != null)
                        produtos.add(p);
                    else
                        end = true;
                }
                ois.close();
                return produtos;
            } else{
                f = new File(fProdutos + ".txt");
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String l;
                while((l = br.readLine()) != null){
                    linha_erro++;
                    String[] sp = l.split(";");
                    switch (sp[0]) {
                        case "alimentar" -> produtos.add(new Alimentar(sp[1], sp[2], Double.parseDouble(sp[3]), Integer.parseInt(sp[4]),
                                Double.parseDouble(sp[5]), Integer.parseInt(sp[6])));
                        case "limpeza" -> produtos.add(new Limpeza(sp[1], sp[2], Double.parseDouble(sp[3]), Integer.parseInt(sp[4]),
                                Integer.parseInt(sp[5])));
                        case "mobiliario" -> {
                            Dimensao dim = new Dimensao(Double.parseDouble(sp[5]), Double.parseDouble(sp[6]), Double.parseDouble(sp[7]));
                            produtos.add(new Mobiliario(sp[1], sp[2], Double.parseDouble(sp[3]), Integer.parseInt(sp[4]),
                                    dim, Integer.parseInt(sp[8])));
                        }
                        default -> {
                            System.out.println("Erro no formato do ficheiro " + fProdutos + ".txt na linha " + linha_erro);
                            return null;
                        }
                    }
                }
                br.close();
                return produtos;
            }
        } catch(IOException ex){
            System.out.println("Erro ao ler ficheiro " + f.getName());
        } catch(NumberFormatException ex) {
            //verificar se o erro ocorreu numa linha específica do ficheiro de texto
            if(linha_erro != 0)
                System.out.println("Erro no formato do ficheiro " + f.getName() + " na linha " + linha_erro);
            else
                System.out.println("Erro no formato do ficheiro " + f.getName());

        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto no ficheiro clientes.txt");
        }
        return null;
    }

    /**
     * Escreve uma lista de produtos num ficheiro de objetos
     * @param produtos lista de produtos a ser escrita
     */
    public void escreverProdutos(List<Produto> produtos){
        try {
            File f = new File(fProdutos + ".obj");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Produto p: produtos)
                oos.writeObject(p);
            oos.close();
        } catch(IOException ex) {
            System.out.println("Erro ao ler ficheiro " + fProdutos + ".obj");
        } catch(NumberFormatException ex) {
            System.out.println("Erro no formato do ficheiro" + fProdutos + ".obj");
        }
    }
}
