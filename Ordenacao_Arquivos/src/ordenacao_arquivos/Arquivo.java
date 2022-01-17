
package ordenacao_arquivos;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Arquivo {
    private String nomearquivo;
    private RandomAccessFile arquivo;
    private int comp, mov;

    public Arquivo(String nomearquivo) throws FileNotFoundException {
        arquivo = new RandomAccessFile(nomearquivo, "rw");
        this.nomearquivo = nomearquivo;
    }

    public void copiaArquivo(RandomAccessFile arquivoOrigem) throws IOException {
        Registro reg = new Registro();
        int totRegistros = (int) arquivoOrigem.length()/ Registro.length();
        arquivoOrigem.seek(0);
        for(int i=0; i<totRegistros; i++)
        {
            reg.leDoArq(arquivoOrigem);
            seekArq(i);
            reg.gravaNoArq(arquivo);
        }
        this.truncate(totRegistros);//
        
    }

    public RandomAccessFile getFile() {
        return arquivo;
    }

    public void truncate(long pos) {
         try {
            arquivo.setLength(pos * Registro.length());
        } catch (IOException exc) {}
    }

    public void inserirRegistro(Registro r) throws IOException
    {
        seekArq(filesize());
        r.gravaNoArq(arquivo);
    }
    
    public boolean eof() throws IOException {
        if(arquivo.getFilePointer() ==arquivo.length())
        {
            return true;
        }
        return false;
    }

    public void seekArq(int pos) throws IOException {
        arquivo.seek(pos * Registro.length());
    }

    public int filesize() throws IOException {
        try{
            return (int) arquivo.length()/ Registro.length();
        }
        catch(IOException e){
            return 0;
        }
    }

    public void initComp() {
        comp=0;
    }

    public void initMov() {
        mov=0;
    }

    public int getComp() {
        return comp;
    }

    public int getMov() {
        return mov;
    }

    public void insercaoDireta() throws IOException {
        Registro reg = new Registro();
        Registro regAnt = new Registro();
        int aux, auxAnt;
        int pos;
        int TL = filesize();
        for(int i=1; i< TL; i++)
        {
            seekArq(i);
            pos =i;
            reg.leDoArq(arquivo);
            aux = reg.getNumero();
            seekArq(pos-1);
            regAnt.leDoArq(arquivo);
            auxAnt = regAnt.getNumero();
            while(pos > 0 && reg.getNumero() < regAnt.getNumero())
            {
                comp++;
                seekArq(pos);
                regAnt.gravaNoArq(arquivo);
                mov++;
                pos--;
                
                if(pos>0)
                {
                    seekArq(pos-1);
                    regAnt.leDoArq(arquivo);
                }
            }
            seekArq(pos);
            reg.gravaNoArq(arquivo);
            mov++;
        }
    }
    
    public int buscaBinaria(int chave, int tam) throws IOException
    {
        int ini=0, fim = tam-1, meio;
        int aux;
        meio = fim /2;
        Registro reg = new Registro();
        
        seekArq(meio);
        reg.leDoArq(arquivo);
        
        while(ini <fim && reg.getNumero() != chave)
        {
            comp++;
            if(chave > reg.getNumero())
                ini = meio+1;
            else
                fim = meio-1;
            comp++;
            meio = (ini + fim)/2;
            seekArq(meio);
            reg.leDoArq(arquivo);
        }
        
        comp++; // do if abaixo
        if(chave > reg.getNumero())
            return meio + 1;
        else
            return meio;
    }
    
    public void insercaoBinaria() throws IOException
    {
        int TL = filesize();
        int pos;
        Registro reg = new Registro();
        Registro regAnt = new Registro();
        for(int i=1; i< TL;i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            pos = buscaBinaria(reg.getNumero(), i);
            for(int j=i; j>pos; j--)
            {
                seekArq(j-1);
                regAnt.leDoArq(arquivo);
                seekArq(j);
                regAnt.gravaNoArq(arquivo);
                mov++;
            }
            seekArq(pos);
            reg.gravaNoArq(arquivo);
            mov++;
        }
    }
    
    public void selecaoDireta() throws IOException
    {
        int menor, posmenor;
        int TL = filesize();
        //System.out.println("entrou");
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        for(int i=0; i<TL-1;i++)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            menor = regI.getNumero();
            posmenor =i;
            for(int j=i+1; j<TL; j++)
            {
                seekArq(j);
                regJ.leDoArq(arquivo);
                if(regJ.getNumero() < menor)
                {
                    comp++;
                    menor = regJ.getNumero();
                    posmenor=j;
                }
            }
            seekArq(posmenor);
            regJ.leDoArq(arquivo);
            seekArq(posmenor);
            regI.gravaNoArq(arquivo);
            mov++;
            //System.out.println("Gravou I: "+regI.getNumero());
            seekArq(i);
            regJ.gravaNoArq(arquivo);
            mov++;
            //System.out.println("Gravou J: "+regJ.getNumero());
        }
    }
    
    public void bolha() throws IOException
    {
        Registro reg = new Registro();
        Registro regAux = new Registro();
        int aux;
        int j;
        int i=0, TL = filesize();
        
        while(TL >1)
        {
            for(i=0; i< TL-1; i++)
            {
                seekArq(i);
                reg.leDoArq(arquivo);
                seekArq(i+1);
                regAux.leDoArq(arquivo);
                if(reg.getNumero() > regAux.getNumero())
                {
                    comp++;
                    seekArq(i);
                    regAux.gravaNoArq(arquivo);
                    mov++;
                    seekArq(i+1);
                    reg.gravaNoArq(arquivo);
                    mov++;
                }

            }
            TL--;
        }
        
    }
    
    public void shake() throws IOException
    {
        int ini=0, fim = filesize();
        int i, j;
        Registro reg = new Registro();
        Registro regAux = new Registro();
        while(fim > ini)
        {
            i=ini;
            while(i< fim)
            {
                seekArq(i);
                reg.leDoArq(arquivo);
                seekArq(i+1);
                regAux.leDoArq(arquivo);
                if(reg.getNumero() > regAux.getNumero())
                {
                    comp++;
                    seekArq(i);
                    regAux.gravaNoArq(arquivo);
                    mov++;
                    seekArq(i+1);
                    reg.gravaNoArq(arquivo);
                    mov++;
                }
                i++;
            }
            fim--;
            j= fim;
            
                while(j > ini)
                {
                    seekArq(j);
                    reg.leDoArq(arquivo);
                    seekArq(j-1);
                    regAux.leDoArq(arquivo);
                    if(reg.getNumero() < regAux.getNumero())
                    {
                        comp++;
                        seekArq(j);
                        regAux.gravaNoArq(arquivo);
                        mov++;
                        seekArq(j-1);
                        reg.gravaNoArq(arquivo);
                        mov++;
                    }
                    j--;
                }
                ini++;
            
        }
                
    }
    
    public void heap() throws IOException
    {
        int TL = filesize();
        int TL2 = TL, pai, FE, FD, maiorF;
        Registro regMaior = new Registro();
        Registro regD = new Registro();
        Registro regPai = new Registro();
        Registro regAux = new Registro();
        Registro regIni = new Registro();
        
        while(TL2 > 1)
        {
            for(pai = TL2/2-1; pai>=0; pai--)
            {
                FE = 2*pai + 1;
                maiorF = FE;
                seekArq(maiorF);
                regMaior.leDoArq(arquivo);
                FD = 2*pai + 2;
                
                if(FD <TL2)
                {
                    seekArq(FD);
                    regD.leDoArq(arquivo);
                    
                    if(regD.getNumero() > regMaior.getNumero())
                    {
                        comp++;
                        maiorF = FD;
                        seekArq(maiorF);
                        regMaior.leDoArq(arquivo);
                    }
                }
                
                seekArq(pai);
                regPai.leDoArq(arquivo);
                
                if(regMaior.getNumero() > regPai.getNumero())
                {
                    comp++;
                    seekArq(pai);
                    regMaior.gravaNoArq(arquivo);
                    mov++;
                    seekArq(maiorF);
                    regPai.gravaNoArq(arquivo);
                    mov++;
                }
            }
            seekArq(TL2-1);
            regAux.leDoArq(arquivo);
            seekArq(0);
            regIni.leDoArq(arquivo);
            seekArq(0);
            regAux.gravaNoArq(arquivo);
            mov++;
            seekArq(TL2-1);
            regIni.gravaNoArq(arquivo);
            mov++;
            TL2--;
        }
    }
    
    public void shell() throws IOException
    {
        int TL = filesize();
        int dist = 4, i, j, k;
        Registro reg = new Registro();
        Registro regDist = new Registro();
        Registro aux = new Registro();
        Registro regK = new Registro();
        Registro regK2 = new Registro();
        
        while(dist>0)
        {
            for(i=0; i<dist; i++)
            {
                j =i;
                while(j+dist < TL)
                {
                    seekArq(j);
                    reg.leDoArq(arquivo);
                    seekArq(j+dist);
                    regDist.leDoArq(arquivo);
                    
                    if(reg.getNumero() > regDist.getNumero())
                    {
                        comp++;
                        seekArq(j);
                        aux.leDoArq(arquivo);//
                        seekArq(j+dist);
                        reg.leDoArq(arquivo);
                        seekArq(j);
                        reg.gravaNoArq(arquivo);
                        mov++;
                        
                        seekArq(j+dist);
                        aux.gravaNoArq(arquivo);
                        mov++;
                        k=j;
                        
                        seekArq(k);
                        regK.leDoArq(arquivo);
                        if(k-dist >= i)
                        {
                            seekArq(k-dist);
                            regK2.leDoArq(arquivo);
                        }
                        while(k-dist >= i && regK.getNumero() < regK2.getNumero())
                        {
                            comp++;
                            seekArq(k);
                            aux.leDoArq(arquivo);
                            seekArq(k-dist);
                            regK.leDoArq(arquivo);
                            seekArq(k);
                            regK.gravaNoArq(arquivo);
                                
                            seekArq(k-dist);
                            aux.gravaNoArq(arquivo);
                            mov += 2;
                            k = k- dist;
                            seekArq(k);
                            regK.leDoArq(arquivo);
                            if(k-dist >= i)
                            {
                                seekArq(k-dist);
                                regK2.leDoArq(arquivo);
                            }
                        }
                    }
                    j = j+dist;
                }
            }
            dist /=2;
        }
    }//////
    
    public void quick_sempivo() throws IOException
    {
        QuickSP(0, (filesize()-1));
    }
    
    public void QuickSP(int ini, int fim) throws IOException
    {
        int i =ini, j=fim;
        Registro regI = new Registro();
        Registro regJ = new Registro();
        while(i<j)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            seekArq(j);
            regJ.leDoArq(arquivo);
            while(i<j && regI.getNumero() <= regJ.getNumero())
            {
                comp++;
                i++;
                seekArq(i);
                regI.leDoArq(arquivo);
            }
            seekArq(i);
            regJ.gravaNoArq(arquivo);
            mov++;
            seekArq(j);
            regI.gravaNoArq(arquivo);
            mov++;
            
            seekArq(i);
            regI.leDoArq(arquivo);
            seekArq(j);
            regJ.leDoArq(arquivo);
            while(i<j && regJ.getNumero() >= regI.getNumero())
            {
                comp++;
                j--;
                seekArq(j);
                regJ.leDoArq(arquivo);
            }
            seekArq(i);
            regJ.gravaNoArq(arquivo);
            mov++;
            seekArq(j);
            regI.gravaNoArq(arquivo);
            mov++;
            
        }
        if(ini<i-1)
            QuickSP(ini, i-1);
        if(j+1<fim)
            QuickSP(j+1, fim);
    }
    
    public void quick_compivo() throws IOException
    {
        QuickCP(0, (filesize()-1));
    }
    
    public void QuickCP(int ini, int fim) throws IOException
    {
        int i=ini, j=fim;
        Registro regPivo = new Registro();
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        seekArq((ini+fim)/2);
        regPivo.leDoArq(arquivo);
        while(i<j)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            seekArq(j);
            regJ.leDoArq(arquivo);
           
            while(regI.getNumero() < regPivo.getNumero())
            {
                comp++;
                i++;
                seekArq(i);
                regI.leDoArq(arquivo);
            }
            while(regJ.getNumero() > regPivo.getNumero())
            {
                comp++;
                j--;
                seekArq(j);
                regJ.leDoArq(arquivo);
            }
            if(i<= j)////
            {
                seekArq(i);
                regJ.gravaNoArq(arquivo);
                mov++;
                seekArq(j);
                regI.gravaNoArq(arquivo);
                mov++;
                i++;
                j--;
            }
        }
        if(ini<j)
            QuickCP(ini, j);
        if(i<fim)
            QuickCP(i, fim);
    }
    
    
    
    public void merge_sort1() throws IOException
    {
        int seq =1;
        int TL = filesize();
        Arquivo ArqAuxiliarMerge1 = new Arquivo("C:\\Users\\Windows\\Documents\\ArquivosPO\\ArquivoAuxMerge1.dat");
        Arquivo ArqAuxiliarMerge2 = new Arquivo("C:\\Users\\Windows\\Documents\\ArquivosPO\\ArquivoAuxMerge2.dat");
        while(seq<TL)
        {
            particao(ArqAuxiliarMerge1, ArqAuxiliarMerge2);
            //ArqAuxiliarMerge1.exibirArquivo();
            //System.out.println("----");
            fusao(ArqAuxiliarMerge1, ArqAuxiliarMerge2, seq);
            seq = seq*2;
        }
    }
    
    public void particao(Arquivo arq1, Arquivo arq2) throws IOException
    {
        int tam = filesize()/2;
        Registro regA1 = new Registro();
        Registro regA2 = new Registro();
        
        for(int i=0; i<tam; i++)
        {
            seekArq(i);
            regA1.leDoArq(arquivo);
            arq1.seekArq(i);
            regA1.gravaNoArq(arq1.getFile());
            mov++;
            
            seekArq(i+tam);
            regA2.leDoArq(arquivo);
            arq2.seekArq(i);
            regA2.gravaNoArq(arq2.getFile());
            mov++;
        }
    }
    
    public void fusao(Arquivo arq1, Arquivo arq2, int seq) throws IOException
    {
        int TL = filesize();
        int auxS = seq, i=0, j=0, k=0;
        Registro regArq1 = new Registro();
        Registro regArq2 = new Registro();
        
        while(k<TL)
        {
            while(i<seq && j<seq)
            {
                arq1.seekArq(i);
                regArq1.leDoArq(arq1.getFile());
                arq2.seekArq(j);
                regArq2.leDoArq(arq2.getFile());
                seekArq(k);
                comp++;
                if(regArq1.getNumero() < regArq2.getNumero())
                {
                    regArq1.gravaNoArq(arquivo);
                    i++;
                }
                else
                {
                    regArq2.gravaNoArq(arquivo);
                    j++;
                }
                mov++;
                k++;
            }
            //
            while (i < seq) 
            {
                arq1.seekArq(i);
                regArq1.leDoArq(arq1.getFile());
                seekArq(k);
                regArq1.gravaNoArq(arquivo);
                mov++;
                i++;
                k++;
            }
            while (j < seq) 
            {
                arq2.seekArq(j);
                regArq2.leDoArq(arq2.getFile());
                seekArq(k);
                regArq2.gravaNoArq(arquivo);
                mov++;
                j++;
                k++;
                //arq2.seekArq(j);
                //regArq2.leDoArq(arq2.getFile());
            }
            seq = seq + auxS;
        }
    }
    
    public void Counting() throws IOException
    {
        int i, TL = filesize();
        int maior = this.getMaior();
        int menor = this.getMenor();
        Registro reg = new Registro();
        int range = maior - menor +1;
        int contador[] = new int[range];
        int vetFinal[] = new int[TL];
        
        for(i=0; i<TL; i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            contador[reg.getNumero()-menor]++;
        }    
        
        for(i=1; i<range; i++)
        {
            contador[i] = contador[i] + contador[i-1];
        }
        
        for(i=TL-1;i>=0; i-- )
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            vetFinal[contador[reg.getNumero()-menor]-1] = reg.getNumero();
            contador[reg.getNumero()-menor]--;
        }
        
        for(i=0; i<TL; i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            reg.setNumero(vetFinal[i]);
            seekArq(i);
            reg.gravaNoArq(arquivo);
            mov++;
        }
    }
    
    
    public int getMaior() throws IOException
    {
        int TL = filesize();
        int i, maior;
        Registro reg = new Registro();
        seekArq(0);
        reg.leDoArq(arquivo);
        maior = reg.getNumero();
        
        for(i=0; i<TL; i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            if(reg.getNumero() > maior)
            {
                comp++;
                maior = reg.getNumero();
            }
        }
        
        return maior;
    }
    
     public int getMenor() throws IOException
    {
        int TL = filesize();
        int i, menor;
        Registro reg = new Registro();
        seekArq(0);
        reg.leDoArq(arquivo);
        menor = reg.getNumero();
        
        for(i=0; i<TL; i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            if(reg.getNumero() < menor)
            {
                menor = reg.getNumero();
                comp++;
            }
        }
        
        return menor;
    }
    
    public void counting_Sort(int exp) throws IOException //para o radix
    {
        int TL = filesize();
        Registro reg = new Registro();
        int contador[] = new int [10];
        int vetFinal[] = new int[TL];
        int i;
        for(i=0; i<10; i++)
            contador[i]=0;
        
        for(i=0; i<TL; i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            contador[(reg.getNumero()/exp) % 10]++;
        }
        
        for(i=1; i<10;i++)
        {
            contador[i] = contador[i] + contador[i-1];
        }
        
        for(i = TL-1; i>=0; i--)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            vetFinal[contador[(reg.getNumero()/exp)%10]-1] = reg.getNumero();
            contador[(reg.getNumero()/exp)%10]--;
        }
        
        for(i=0; i<TL;i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            reg.setNumero(vetFinal[i]);
            seekArq(i);
            reg.gravaNoArq(arquivo);
            mov++;
        }
        
    }
    
    public void radix() throws IOException
    {
        int maior = this.getMaior();
        int exp;
        for(exp=1; maior/exp>0; exp = exp*10)
        {
            counting_Sort(exp);
        }
        
    }
    
    public void comb_Sort() throws IOException
    {
        int TL = filesize();
        int i, aux, dist = TL;
        boolean trocou = true;
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        while(dist > 1 || trocou==true)
        {
            if(dist>1)
                dist = (int) (dist/1.247330950103979);
            
            trocou = false;
            for(i=0; i+dist<TL;i++)
            {
                seekArq(i);
                regI.leDoArq(arquivo);
                seekArq(i+dist);
                regJ.leDoArq(arquivo);
                //comp++;
                if(regI.getNumero()> regJ.getNumero())
                {
                    comp++;
                    seekArq(i);
                    regJ.gravaNoArq(arquivo);
                    mov++;
                    seekArq(i+dist);
                    regI.gravaNoArq(arquivo);
                    mov++;
                    trocou = true;
                }
            }
        }
    }
    
    public void gnome_Sort() throws IOException
    {
        int TL = filesize();
        int i=0, aux;
        Registro regI = new Registro();
        Registro regJ = new Registro();
        
        while(i<TL)
        {
            if(i==0)
                i++;
            seekArq(i);
            regI.leDoArq(arquivo);
            seekArq(i-1);
            regJ.leDoArq(arquivo);
            comp++;
            if(regI.getNumero() >= regJ.getNumero())
                i++;
            else
            {
                seekArq(i);
                regJ.gravaNoArq(arquivo);
                mov++;
                seekArq(i-1);
                regI.gravaNoArq(arquivo);
                mov++;
                i--;
            }
        }
    }
    
    public void insercao_diretaTim(int esq, int dir) throws IOException
    {
        Registro reg = new Registro();
        Registro regAnt = new Registro();
        int aux, auxAnt;
        int pos;
        for(int i=esq+1; i<= dir; i++)
        {
            seekArq(i);
            pos =i;
            reg.leDoArq(arquivo);
            aux = reg.getNumero();
            seekArq(pos-1);
            regAnt.leDoArq(arquivo);
            auxAnt = regAnt.getNumero();
            while(pos > esq && reg.getNumero() < regAnt.getNumero())
            {
                comp++;
                seekArq(pos);
                regAnt.gravaNoArq(arquivo);
                mov++;
                pos--;
                
                if(pos>0)
                {
                    seekArq(pos-1);
                    regAnt.leDoArq(arquivo);
                }
            }
            seekArq(pos);
            reg.gravaNoArq(arquivo);
            mov++;
            
        }
    }
    
    public void merge_Tim(int esq, int dir, int meio) throws FileNotFoundException, IOException
    {
        int TL1 = meio - esq +1;
        int TL2 = dir - meio;
        int i;
        Arquivo ArqAuxMergeTim1 = new Arquivo("C:\\Users\\Windows\\Documents\\ArquivosPO\\ArqAuxMergeTim1.dat");
        Arquivo ArqAuxMergeTim2 = new Arquivo("C:\\Users\\Windows\\Documents\\ArquivosPO\\ArqAuxMergeTim2.dat");
        Registro regE = new Registro();
        Registro regD = new Registro();
        
        for(i=0; i<TL1; i++)
        {
            seekArq(esq+i);
            regE.leDoArq(arquivo);
            ArqAuxMergeTim1.seekArq(i);
            regE.gravaNoArq(ArqAuxMergeTim1.getFile());
            mov++;
        }
        
        for(i=0; i<TL2; i++)
        {
            seekArq(meio+1+i);
            regD.leDoArq(arquivo);
            ArqAuxMergeTim2.seekArq(i);
            regD.gravaNoArq(ArqAuxMergeTim2.getFile());
            mov++;
        }
        
        i=0;
        int j=0, k=esq;
        while(i<TL1 && j <TL2)
        {
            ArqAuxMergeTim1.seekArq(i);
            regE.leDoArq(ArqAuxMergeTim1.getFile());
            ArqAuxMergeTim2.seekArq(j);
            regD.leDoArq(ArqAuxMergeTim2.getFile());
            seekArq(k);
            comp++;
            if(regE.getNumero() <= regD.getNumero())
            {
                regE.gravaNoArq(arquivo);
                mov++;
                i++;
            }
            else
            {
                regD.gravaNoArq(arquivo);
                mov++;
                j++;
            }
            k++;
        }
    
        while(i<TL1)
        {
            ArqAuxMergeTim1.seekArq(i);
            regE.leDoArq(ArqAuxMergeTim1.getFile());
            seekArq(k);
            regE.gravaNoArq(arquivo);
            mov++;
            i++;
            k++;
        }
        
        while(j<TL2)
        {
            ArqAuxMergeTim2.seekArq(j);
            regD.leDoArq(ArqAuxMergeTim2.getFile());
            seekArq(k);
            regD.gravaNoArq(arquivo);
            mov++;
            j++;
            k++;
        }
        
        
    }
    
    public int getMenorValor(int a, int b)//para o tim sort
    {
        if(a<b)
            return a;
        else
            return b;
    }
    
    public void tim_Sort() throws IOException
    {
        int bloco = 32, i, tam, meio, fim;
        int esq, dir;
        int TL = filesize();
        
        for(i=0; i<TL; i= i+bloco)
        {
            insercao_diretaTim(i, getMenorValor((i+bloco-1), (TL-1)));
        }
        
        for(tam = bloco; tam<TL; tam = 2*tam)
        {
            for(esq=0; esq<TL;esq = esq + (2*tam))
            {
                meio = esq+ tam -1;
                dir = getMenorValor((esq + 2 * tam -1), (TL-1));
                
                if(meio<dir)
                    merge_Tim(esq, dir, meio);
            }
        }
        
    }
    
    public void bucket_sort() throws IOException
    {
        
        Registro reg = new Registro();
        int TL = filesize();
        int qtdBaldes = 5;
        int max = (getMaior() - 1) / qtdBaldes;
        int i, aux;
        
        ListaBucket[] buckets = new ListaBucket[max];
        for (i = 0; i < max; i++) {
            buckets[i] = new ListaBucket();
        }
        
        i=0;
        while (i < TL) {
            seekArq(i);
            reg.leDoArq(arquivo);
            aux = reg.getNumero();
            //System.out.println("Leu: "+aux);
            buckets[(aux - 1) / (max + 1)].InserirNoInicio(aux);
            i++;
        }
        for (i = 0; i < max; i++) {
            ordena_Bucket(buckets[i]);
        }

        NoBucket pBuck;
        i=0;
        for (int j = 0; j < max; j++) {
            pBuck = buckets[j].getInicio();
            while (pBuck != null) {
                seekArq(i);
                Registro regAux = new Registro(pBuck.getNumero());
                regAux.gravaNoArq(arquivo);
                mov++;
                i++;
                pBuck = pBuck.getProx();
            }
        }
        
        
    }
    
    public void ordena_Bucket(ListaBucket bucket)
    {
        NoBucket pi, pposmenor, pj;
        int menor;
        pi = bucket.getInicio();
        if(pi!= null){
            while(pi.getProx()!= null)
            {
                menor=pi.getNumero();
                pposmenor=pi;
                pj=pi.getProx();
                while(pj != null)
                {
                    if(pj.getNumero()<menor)
                    {
                        menor = pj.getNumero();
                        pposmenor = pj;
                    }
                    pj = pj.getProx();
                }
                pposmenor.setNumero(pi.getNumero());
                pi.setNumero(menor);
                pi = pi.getProx();
            }
        }
    }
    
    
    

    public void geraArquivoOrdenado() throws IOException {
        int i;
        for(i=1; i<=1024; i++)
        {
            inserirRegistro(new Registro(i));
        }
    }

    public void geraArquivoReverso() throws IOException {
        int i;
        for(i=1024; i>=1; i--)
        {
            inserirRegistro(new Registro(i));
        }
    }

    public void geraArquivoRandomico() throws IOException {
        
        Random rand = new Random();
        int num, i, j;
        List <Integer> existentes = new ArrayList();
        for(i=0; i<1024;i++)
        {
            num = 1 + (rand.nextInt(1500));
            while(existentes.contains(num))
            {
                num = 1 +  (rand.nextInt(1500));
            }
            existentes.add(num);
            inserirRegistro(new Registro(num));
                
        }

//        inserirRegistro(new Registro(23));
//        inserirRegistro(new Registro(17));
//        inserirRegistro(new Registro(8));
//        inserirRegistro(new Registro(15));
//        inserirRegistro(new Registro(9));
//        inserirRegistro(new Registro(12));
//        inserirRegistro(new Registro(19));
//        inserirRegistro(new Registro(7));
//        inserirRegistro(new Registro(25));
//        inserirRegistro(new Registro(2));
//        inserirRegistro(new Registro(5));
//        inserirRegistro(new Registro(26));
//        inserirRegistro(new Registro(30));
//        inserirRegistro(new Registro(22));
//        inserirRegistro(new Registro(42));        
        
        
  
    }
    
    public static double log(double base, double valor) {
        return Math.log(valor) / Math.log(base);
    }
    
    public double calculaCompInsDirOrd(int n){
        return n - 1;
    }
    public double calculaCompInsDirRev(int n){
        return (Math.pow(n, 2) + n - 4)/4;
    }
    public double calculaCompInsDirRand(int n){
        return (Math.pow(n, 2) + n - 2)/4;
    }
    
    public double calculaMovInsDirOrd(int n){
        return 2*(n-1);
    }
    public double calculaMovInsDirRev(int n){
        return (Math.pow(n, 2) + 3*n - 4)/2;
    }
    public double calculaMovInsDirRand(int n){
        return (Math.pow(n, 2) + 9*n - 10)/4;
    }
    
    public double calculaCompInsBin(int n){
        return n * ( Math.log((double) n) - Math.log(2.71828) + 0.5);
    }
    public double calculaMovInsBinOrd(int n){
        return 3*(n-1);
    }
    public double calculaMovInsBinRev(int n){
        return (Math.pow(n, 2) + 3*n - 4)/2;
    }
    public double calculaMovInsBinRand(int n){
        return (Math.pow(n, 2) + 9*n - 10)/4;
    }
    
    public double calculaCompSelDir(int n){
        return (Math.pow(n, 2) - n)/2;
    }
    
    public double calculaMovSelDirOrd(int n){
        return 3*(n-1);
    }
    public double calculaMovSelDirRev(int n){
        return (Math.pow(n, 2)/4) + 3 * (n-1);
    }
    public double calculaMovSelDirRand(int n){
        return n*(Math.log((double) n) + 0.577216);
    }
    
    public double calculaCompBolha(int n){
        return (Math.pow(n, 2)-n) / 2;
    }
    
    public double calculaMovBolhaOrd(int n){
        return 0.;
    }
    public double calculaMovBolhaRev(int n){
        return 3 * (Math.pow(n, 2) - n) / 4;
    }
    public double calculaMovBolhaRand(int n){
        return 3 * (Math.pow(n, 2) - n) / 2;
    }
    
    public static void gravaLinhaTabela(String nome, int compO, String equacaoCompO, int movO, String equacaoMovO, String tempoOrd, int compR, String equacaoCompR, int movR, String equacaoMovR, String tempoRev, int compRand, String equacaoCompRand, int movRand, String equacaoMovRand, String tempoRand) throws IOException
    {
        System.out.println("Ordenacao por "+nome+" finalizada.");
        FileWriter arq = new FileWriter("D:\\ArquivosPO\\tabelaArq.txt", true);
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("+---------------------------------------------------------------------------------------------------------------------+\n");
        gravarArq.printf("|  "+nome+"\t\t\t\t\t\t\t\t     |");
        gravarArq.printf("+---------------------------------------------------------------------------------------------------------------------+\n");
        
        gravarArq.printf("| Arquivo Ordenado       | "+compO+"\t               | "+equacaoCompO+"\t         | "+movO+"\t                | "+equacaoMovO+"\t      | "+tempoOrd+"\t     |\n");
        gravarArq.printf("| Arquivo Reverso          | "+compR+"\t               | "+equacaoCompR+"\t         | "+movR+"\t| "+equacaoMovR+"\t      | "+tempoRev+"\t     |\n");
        gravarArq.printf("| Arquivo Randômico     | "+compRand+"\t               | "+equacaoCompRand+"\t         | "+movRand+"\t| "+equacaoMovRand+"\t      | "+tempoRand+"\t     |\n");

        arq.close();
    }
    
    
    public void exibirArquivo() throws IOException
    {
        Registro reg = new Registro();
        seekArq(0);
        while(!eof())
        {
            reg.leDoArq(arquivo);
            reg.exibirReg();
        }
        //System.out.print(" |||| ");
    }
    
}
