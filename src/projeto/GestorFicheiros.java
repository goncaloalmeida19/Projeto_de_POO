package projeto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorFicheiros {
    public GestorFicheiros(){
    }

    public List<Cliente> obterRegulares(){
        try{
            File f = new File("regulares.obj");
            List<Cliente> regulares = new ArrayList<>();
            if(f.exists()){
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                boolean end = false;
                while(!end){
                    Regulares r = (Regulares)ois.readObject();
                    if(r != null)
                        regulares.add(r);
                    else
                        end = true;
                }
                ois.close();
                return regulares;
            } else{
                f = new File("regulares.txt");
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String l;
                while((l = br.readLine()) != null){
                    String[] sp = l.split(";");
                    String[] data = sp[4].split("/");
                    Data nasc = new Data(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]));
                    regulares.add(new Regulares(sp[0],sp[1],sp[2],Integer.parseInt(sp[3]), nasc));
                }
                br.close();
                return regulares;
            }
        } catch(IOException ex){
            System.out.println("Erro ao ler ficheiro regulares.txt");
        } catch(NumberFormatException ex) {
            System.out.println("Erro no formato do ficheiro regulares.txt");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto no ficheiro regulares.txt");
        }
        return null;
    }

    public List<Cliente> obterFrequentes(){
        try{
            File f = new File("frequentes.obj");
            List<Cliente> frequentes = new ArrayList<>();
            if(f.exists()){
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                boolean end = false;
                while(!end){
                    Frequentes r = (Frequentes)ois.readObject();
                    if(r != null)
                        frequentes.add(r);
                    else
                        end = true;
                }
                ois.close();
                return frequentes;
            } else{
                f = new File("regulares.txt");
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String l;
                while((l = br.readLine()) != null){
                    String[] sp = l.split(";");
                    String[] data = sp[4].split("/");
                    Data nasc = new Data(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]));
                    frequentes.add(new Frequentes(sp[0],sp[1],sp[2],Integer.parseInt(sp[3]), nasc));
                }
                br.close();
                return frequentes;
            }
        } catch(IOException ex){
            System.out.println("Erro ao ler ficheiro frequentes.txt");
        } catch(NumberFormatException ex) {
            System.out.println("Erro no formato do ficheiro frequentes.txt");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto no ficheiro frequentes.txt");
        }
        return null;
    }
}
