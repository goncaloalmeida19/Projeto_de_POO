package projeto;

public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public void setData(int dia, int mes, int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    /**
     * Verificar se um ano é bissexto ou não
     * @param ano Ano de uma data
     * @return true, se o ano for bissexto, e false, se não for bissexto
     */
    private boolean eBissexto(int ano){
        return (((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0));
    }


    /**
     * Verificar se a data que chama o método é válida ou não
     * @return true, se a data que chama o método for válida, e false, se for inválida
     */
    public boolean eValida(){
        // Verificação dos limites habituais do ano, mês e dia
        if(ano < 0 || mes < 1 || mes > 12 || dia < 1 || dia > 31) return false;
        // Verificação da exceção de fevereiro com ano bissexto
        if(mes == 2) {
            if(eBissexto(ano)) return dia <= 29;
            else return dia <= 28;
        }
        // Verificação dos meses com 30 dias
        if(mes == 4 || mes == 6 || mes == 9 || mes == 11) return dia <= 30;
        return true;
    }

    /**
     * Comparar duas datas
     * @param d data a comparar
     * @return -1, 0 ou 1, respetivamente, se a data for antes, igual ou depois da data a comparar
     */
    public int compareTo(Data d){
        if(ano > d.ano) return 1;
        if(ano < d.ano) return -1;
        if(mes > d.mes) return 1;
        if(mes < d.mes) return -1;
        return Integer.compare(dia, d.dia);
    }

    @Override
    public String toString() {
        return  dia + "/" + mes + "/" + ano ;
    }
}
