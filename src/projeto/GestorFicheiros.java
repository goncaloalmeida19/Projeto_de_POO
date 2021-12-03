package projeto;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestorFicheiros {
    public GestorFicheiros(){
    }

    //constantes
    private static final String fClientes = "src/projeto/clientes.txt";
    private static final String fProdutos = "src/projeto/produtos.txt";
    private static final String fPromocoes = "src/projeto/promocoes.txt";
    private static final String fCadSup = "src/projeto/cadSup.obj";

    /**
     * Ler o ficheiro dos clientes
     * @return lista de clientes
     */
    public List<Cliente> obterClientes(){
        int linha_erro = 0;
        File f = new File(fClientes);
        try{
            List<Cliente> clientes = new ArrayList<>();
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
                    System.out.println("Erro no formato do ficheiro " + fClientes + " na linha " + linha_erro);
                    return null;
                }
            }
            br.close();
            return clientes;
        } catch(IOException ex){
            System.out.println("Erro ao ler ficheiro " + fClientes);
        } catch(NumberFormatException ex) {
            //verificar se o erro ocorreu numa linha específica do ficheiro de texto
            if(linha_erro != 0)
                System.out.println("Erro no formato do ficheiro " + fClientes + " na linha " + linha_erro);
            else
                System.out.println("Erro no formato do ficheiro " + fClientes);
        }
        return null;
    }

    /**
     * Ler o ficheiro dos produtos
     * @return lista de produtos
     */
    public List<Produto> obterProdutos(){
        int linha_erro = 0;
        File f = new File(fProdutos);
        try{
            List<Produto> produtos = new ArrayList<>();
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
                        System.out.println("Erro no formato do ficheiro " + fProdutos + " na linha " + linha_erro);
                        return null;
                    }
                }
            }
            br.close();
            return produtos;
        } catch(IOException ex){
            System.out.println("Erro ao ler ficheiro " + fProdutos);
        } catch(NumberFormatException ex) {
            //verificar se o erro ocorreu numa linha específica do ficheiro de texto
            if(linha_erro != 0)
                System.out.println("Erro no formato do ficheiro " + fProdutos + " na linha " + linha_erro);
            else
                System.out.println("Erro no formato do ficheiro " + fProdutos);
        }
        return null;
    }



    /**
     * Ler o ficheiro de texto que contem as promoções dos produtos
     * @param produtos lista de produtos a ser atualizada com promoções
     * @return lista de produtos atualizada com as promoções
     */
    public List<Produto> obterPromocoes(List<Produto> produtos){
        int linha_erro = 0;
        File f = new File(fPromocoes);
        try{
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String l;
            while((l = br.readLine()) != null){
                linha_erro++;
                String[] sp = l.split(";");
                String[] dataIni = sp[2].split("/");
                Data dIni = new Data(Integer.parseInt(dataIni[0]),Integer.parseInt(dataIni[1]),Integer.parseInt(dataIni[2]));
                String[] dataFim = sp[3].split("/");
                Data dFim = new Data(Integer.parseInt(dataFim[0]),Integer.parseInt(dataFim[1]),Integer.parseInt(dataFim[2]));
                Promocao prom;
                if(sp[0].equals("paguemenos"))
                    prom = new PagueMenos(dIni, dFim);
                else if(sp[0].equals("pague3leve4"))
                    prom = new Pague3Leve4(dIni, dFim);
                else{
                    System.out.println("Erro no formato do ficheiro " + fPromocoes + " na linha " + linha_erro);
                    return null;
                }

                boolean encontrou = false;
                for(Produto p: produtos){
                    if(p.getIdentificador().equals(sp[1])){
                        encontrou = true;
                        p.addPromocoes(prom);
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
        } catch(NumberFormatException ex) {
            //verificar se o erro ocorreu numa linha específica do ficheiro de texto
            if(linha_erro != 0)
                System.out.println("Erro no formato do ficheiro " + fPromocoes + " na linha " + linha_erro);
            else
                System.out.println("Erro no formato do ficheiro " + fPromocoes);
        }
        return null;
    }

    /**
     * Escreve os dados da cadeia de supermercados num ficheiro de objetos
     * @param cad cadeia de supermercados cujos dados irão ser registados
     */
    public void escreverCadSup(CadeiaSupermercados cad){
        try {
            File f = new File(fCadSup);
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cad);
            oos.close();
        } catch(IOException ex) {
            System.out.println("Erro ao ler ficheiro " + fCadSup);
        } catch(NumberFormatException ex) {
            System.out.println("Erro no formato do ficheiro" + fCadSup);
        }
    }
}
