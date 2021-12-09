package projeto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que gere leituras e escritas em ficheiros.
 */
public class GestorFicheiros {
    //diretório e nomes dos ficheiros
    private final String fPath;
    private final String fClientes;
    private final String fProdutos;
    private final String fPromocoes;
    private final String fCadSup;

    /**
     * Construtor da classe GestorFicheiros
     */
    public GestorFicheiros(String fPath, String fClientes, String fProdutos, String fPromocoes, String fCadSup){
        this.fPath = fPath;
        this.fClientes = fClientes;
        this.fProdutos = fProdutos;
        this.fPromocoes = fPromocoes;
        this.fCadSup = fCadSup;
    }

    /**
     * Método para ler o ficheiro dos clientes
     * @return Lista de clientes presentes no ficheiro
     */
    public List<Cliente> obterClientes(){
        int linha_erro = 0;
        File f = new File(fPath + fClientes);
        try{
            List<Cliente> clientes = new ArrayList<>();
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String l;
            while((l = br.readLine()) != null){
                linha_erro++;
                String[] sp = l.split(";");
                if(sp.length != 6){
                    System.out.println("Erro no formato do ficheiro " + fClientes + " na linha " + linha_erro);
                    return null;
                }

                String[] data = sp[4].split("/");
                if(data.length != 3){
                    System.out.println("Erro no formato do ficheiro " + fClientes + " na linha " + linha_erro);
                    return null;
                }

                Data nasc = new Data(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]));
                if(!nasc.eValida()) {
                    System.out.println("Data inválida no ficheiro " +  fPromocoes + " na linha " + linha_erro);
                    return null;
                }

                if(sp[5].equals("regular"))
                    clientes.add(new Regular(sp[0],sp[1],sp[2],Integer.parseInt(sp[3]), nasc));
                else if(sp[5].equals("frequente"))
                    clientes.add(new Frequente(sp[0],sp[1],sp[2],Integer.parseInt(sp[3]), nasc));
                    //else throw new NumberFormatException();
                else{
                    System.out.println("Erro no formato do ficheiro " + fClientes + " na linha " + linha_erro);
                    return null;
                }
            }
            br.close();
            return clientes;
        } catch(IOException ex){
            System.out.println("Erro ao ler ficheiro " + fClientes);
        } catch(ArrayIndexOutOfBoundsException ex) {
            System.out.println("Erro no formato do ficheiro " + fClientes + " na linha " + linha_erro);
        } catch(NumberFormatException ex) {
            System.out.println("Erro ao ler um número do ficheiro " + fClientes + " na linha " + linha_erro);
        }
        return null;
    }

    /**
     * Método para ler o ficheiro dos produtos
     * @return Lista de produtos presentes no ficheiro
     */
    public List<Produto> obterProdutos(){
        int linha_erro = 0;
        File f = new File(fPath + fProdutos);
        try{
            List<Produto> produtos = new ArrayList<>();
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String l;
            while((l = br.readLine()) != null){
                linha_erro++;
                String[] sp = l.split(";");
                switch (sp[0]) {
                    case "alimentar" -> {
                        if(sp.length != 7){
                            System.out.println("Erro no formato do ficheiro " + fProdutos + " na linha " + linha_erro);
                            return null;
                        }
                        produtos.add(new Alimentar(sp[1], sp[2], Double.parseDouble(sp[3]), Integer.parseInt(sp[4]),
                                Double.parseDouble(sp[5]), Integer.parseInt(sp[6])));
                    }
                    case "limpeza" -> {
                        if(sp.length != 6){
                            System.out.println("Erro no formato do ficheiro " + fProdutos + " na linha " + linha_erro);
                            return null;
                        }
                        produtos.add(new Limpeza(sp[1], sp[2], Double.parseDouble(sp[3]), Integer.parseInt(sp[4]),
                                Integer.parseInt(sp[5])));
                    }
                    case "mobiliario" -> {
                        if(sp.length != 9){
                            System.out.println("Erro no formato do ficheiro " + fProdutos + " na linha " + linha_erro);
                            return null;
                        }
                        Dimensao dim = new Dimensao(Double.parseDouble(sp[5]), Double.parseDouble(sp[6]), Double.parseDouble(sp[7]));
                        produtos.add(new Mobiliario(sp[1], sp[2], Double.parseDouble(sp[3]), Integer.parseInt(sp[4]),
                                dim, Integer.parseInt(sp[8])));
                    }
                    default -> {
                        System.out.println("Erro no formato do ficheiro " + fProdutos + " na linha " + linha_erro);
                        return null;
                    }
                }
            }
            br.close();
            return produtos;
        } catch(IOException ex){
            System.out.println("Erro ao ler ficheiro " + fProdutos);
        } catch(ArrayIndexOutOfBoundsException ex) {
            System.out.println("Erro no formato do ficheiro " + fProdutos + " na linha " + linha_erro);
        } catch(NumberFormatException ex) {
            System.out.println("Erro ao ler um número do ficheiro " + fProdutos + " na linha " + linha_erro);
        }
        return null;
    }



    /**
     * Método para ler o ficheiro de texto que contém as promoções dos produtos
     * @param produtos Lista de produtos a ser atualizada com as promoções presentes no ficheiro
     * @return Lista de produtos atualizada com as promoções presentes no ficheiro
     */
    public List<Produto> obterPromocoes(List<Produto> produtos){
        int linha_erro = 0;
        File f = new File(fPath + fPromocoes);
        try{
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String l;
            while((l = br.readLine()) != null){
                linha_erro++;
                String[] sp = l.split(";");
                if(sp.length != 4){
                    System.out.println("Erro no formato do ficheiro " + fPromocoes + " na linha " + linha_erro);
                    return null;
                }
                String[] dataIni = sp[2].split("/");
                if(dataIni.length != 3){
                    System.out.println("Erro no formato do ficheiro " + fPromocoes + " na linha " + linha_erro);
                    return null;
                }
                Data dIni = new Data(Integer.parseInt(dataIni[0]),Integer.parseInt(dataIni[1]),Integer.parseInt(dataIni[2]));
                if(!dIni.eValida()) {
                    System.out.println("Data inválida no ficheiro " +  fPromocoes + " na linha " + linha_erro);
                    return null;
                }

                String[] dataFim = sp[3].split("/");
                if(dataFim.length != 3){
                    System.out.println("Erro no formato do ficheiro " + fPromocoes + " na linha " + linha_erro);
                    return null;
                }
                Data dFim = new Data(Integer.parseInt(dataFim[0]),Integer.parseInt(dataFim[1]),Integer.parseInt(dataFim[2]));
                if(!dFim.eValida()) {
                    System.out.println("Data inválida no ficheiro " +  fPromocoes + " na linha " + linha_erro);
                    return null;
                }

                Promocao prom;
                if(sp[0].equals("paguemenos"))
                    prom = new PagueMenos(dIni, dFim);
                else if(sp[0].equals("pague3leve4"))
                    prom = new Pague3Leve4(dIni, dFim);
                else{
                    System.out.println("Erro no formato do ficheiro " + fPromocoes + " na linha " + linha_erro);
                    return null;
                }

                //procurar o produto correspondente à promoção
                boolean encontrou = false;
                for(Produto p: produtos){
                    if(p.getIdentificador().equals(sp[1])){
                        encontrou = true;
                        p.adicionarPromocoes(prom);
                    }
                }
                if(!encontrou){
                    System.out.println("Produto não encontrado no ficheiro " + fPromocoes + " na linha " + linha_erro);
                    return null;
                }
            }
            return produtos;

        } catch(IOException ex){
            System.out.println("Erro ao ler ficheiro " + fPromocoes);
        } catch(ArrayIndexOutOfBoundsException ex) {
            System.out.println("Erro no formato do ficheiro " + fPromocoes + " na linha " + linha_erro);
        }catch(NumberFormatException ex) {
            System.out.println("Erro ao ler um número do ficheiro " + fPromocoes + " na linha " + linha_erro);
        }

        return null;
    }

    /**
     * Método para escrever os dados da cadeia de supermercados num ficheiro de objetos
     * @param cad Cadeia de supermercados cujos dados irão ser registados
     */
    public void escreverCadSup(CadeiaSupermercados cad){
        try {
            File f = new File(fPath + fCadSup);
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cad);
            oos.close();
        } catch(IOException ex) {
            System.out.println("Erro ao escrever no ficheiro " + fCadSup);
        } catch(NumberFormatException ex) {
            System.out.println("Erro no formato do ficheiro " + fCadSup);
        }
    }

    /**
     * Método para ler os dados da cadeia de supermercados do ficheiro de objetos
     * @return Cadeia de supermercados lida
     */
    public CadeiaSupermercados lerCadSup(){
        try {
            File f = new File(fPath + fCadSup);
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            CadeiaSupermercados cad = (CadeiaSupermercados) ois.readObject();
            ois.close();
            return cad;
        } catch(IOException ex) {
            System.out.println("Erro ao ler o ficheiro " + fCadSup);
        } catch(NumberFormatException ex) {
            System.out.println("Erro no formato do ficheiro "  + fCadSup);
        } catch (ClassNotFoundException e) {
            System.out.println("Classe não encontrada no ficheiro " + fCadSup);
        }
        return null;
    }

    /**
     * Método para verificar se o ficheiro de objetos já foi criado
     * @return true, se o ficheiro de objetos já foi criado, ou false, caso contrário
     */
    public boolean lerFichObj(){
        File f = new File(fPath + fCadSup);
        return f.exists();
    }
}
