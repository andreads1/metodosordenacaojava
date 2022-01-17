
package ordenacao_arquivos;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Ordenacao_Arquivos {
    private Arquivo arqOrd, auxOrd, arqRev, arqRand, auxRev, auxRand;
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Ordenacao_Arquivos p = new Ordenacao_Arquivos(); //principal
        p.criaTabela();
        p.geraTabela();
        p.finalTabela();
    
        //p.printaArq();
    
        
    }
    
    public void printaArq() throws IOException
    {

        auxRand = new Arquivo("D:\\ArquivosPO\\ArquivoRandomico2.dat");
        Registro reg = new Registro();
        auxRand.seekArq(0);
        while(!(auxRand.eof()))
        {
            reg.leDoArq(auxRand.getFile());
            reg.exibirReg();
        }
    
    }
 
        public void criaTabela() throws IOException
    {
        FileWriter arq = new FileWriter("D:\\ArquivosPO\\tabelaArq.txt");
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("+---------------------------------------------------------------------------------------------------------------------+\n");
        gravarArq.printf("|  Métodos Ordenação  | Comp. Prog.  |  Comp. Equa.  | Mov. Prog.     | Mov. Equa.  | Tempo   |");
        //gravarArq.printf("+------------------------------------------------------------------------------------------------------------------+\n");
        
        arq.close();
    }
    
       public void finalTabela() throws IOException
       {
           FileWriter arq = new FileWriter("D:\\ArquivosPO\\tabelaArq.txt", true);
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("+---------------------------------------------------------------------------------------------------------------------+\n");

        
        arq.close();
       }
        
    public void geraTabela() throws FileNotFoundException, IOException
    {
       long tini, tfim, ttotalOrd, ttotalRev, ttotalRand;
       int compO, movO, compRev, movRev, compRand, movRand;
       
       
       arqOrd = new Arquivo("D:\\ArquivosPO\\ArquivoOrdenadoOriginal.dat");
       auxOrd = new Arquivo("D:\\ArquivosPO\\ArquivoOrdenado2.dat");
       arqOrd.geraArquivoOrdenado();
       //arqOrd.exibirArquivo();//
        
       arqRev =  new Arquivo("D:\\ArquivosPO\\ArquivoReversoOriginal.dat");
       auxRev = new Arquivo("D:\\ArquivosPO\\ArquivoReverso2.dat");
       arqRev.geraArquivoReverso();
       //arqRev.exibirArquivo();//
       
       arqRand = new Arquivo("D:\\ArquivosPO\\ArquivoRandomicoOriginal.dat");
       auxRand = new Arquivo("D:\\ArquivosPO\\ArquivoRandomico2.dat");
       arqRand.geraArquivoRandomico();
       //arqRand.exibirArquivo();//
        //System.out.println("\n---------------------");//
       int TL = arqRand.filesize();

       // ----------------------- INSERÇÃO DIRETA -----------------------
       //-Ordenado 
       auxOrd.copiaArquivo(arqOrd.getFile());
       auxOrd.initComp();
       auxOrd.initMov();
       tini=System.currentTimeMillis();
       auxOrd.insercaoDireta();
       tfim=System.currentTimeMillis();
       ttotalOrd=tfim-tini;
       compO=auxOrd.getComp();
       movO=auxOrd.getMov();
       
       //-Reverso
       auxRev.copiaArquivo(arqRev.getFile());
       auxRev.initComp();
       auxRev.initMov();
       tini=System.currentTimeMillis();
       auxRev.insercaoDireta();
       tfim=System.currentTimeMillis();
       ttotalRev=tfim-tini;
       compRev=auxRev.getComp();
       movRev=auxRev.getMov();
       
       
       //-Randomico
       auxRand.copiaArquivo(arqRand.getFile());
       auxRand.initComp();
       auxRand.initMov();
       tini=System.currentTimeMillis();
       auxRand.insercaoDireta();
       tfim=System.currentTimeMillis();
       ttotalRand=tfim-tini;
       compRand=auxRand.getComp();
       movRand=auxRand.getMov();
       
//       auxOrd.exibirArquivo();///
//       auxRev.exibirArquivo();//
//       auxRand.exibirArquivo();//
        //System.out.println("Tempo: "+(double)ttotalRand/1000);
       
       Arquivo.gravaLinhaTabela("Inserção Direta", compO,String.format("%.2f", auxOrd.calculaCompInsDirOrd(TL)),movO,String.format("%.2f", auxOrd.calculaMovInsDirOrd(TL)), String.format("%.2f",(double)ttotalOrd/1000), 
               compRev, String.format("%.2f", auxRev.calculaCompInsDirRev(TL)), movRev, String.format("%.2f",auxRev.calculaMovInsDirRev(TL)), String.format("%.2f", (double)ttotalRev/1000),
               compRand, String.format("%.2f",auxRand.calculaCompInsDirRand(TL)), movRand, String.format("%.2f", auxRand.calculaMovInsDirRand(TL)), String.format("%.2f", (double)ttotalRand/1000)); 
       
        // ----------------------- INSERÇÃO BINÁRIA -----------------------
        //-Ordenado
       auxOrd.copiaArquivo(arqOrd.getFile());
       auxOrd.initComp();
       auxOrd.initMov();
       tini=System.currentTimeMillis();
       auxOrd.insercaoBinaria();
       tfim=System.currentTimeMillis();
       ttotalOrd=tfim-tini;
       compO=auxOrd.getComp();
       movO=auxOrd.getMov();
       
       //-Reverso
       auxRev.copiaArquivo(arqRev.getFile());
       auxRev.initComp();
       auxRev.initMov();
       tini=System.currentTimeMillis();
       auxRev.insercaoBinaria();
       tfim=System.currentTimeMillis();
       ttotalRev=tfim-tini;
       compRev=auxRev.getComp();
       movRev=auxRev.getMov();
       
       //-Randomico
       auxRand.copiaArquivo(arqRand.getFile());
       auxRand.initComp();
       auxRand.initMov();
       tini=System.currentTimeMillis();
       auxRand.insercaoBinaria();
       tfim=System.currentTimeMillis();
       ttotalRand=tfim-tini;
       compRand=auxRand.getComp();
       movRand=auxRand.getMov();
       
//       auxOrd.exibirArquivo();///
//       auxRev.exibirArquivo();//
//       auxRand.exibirArquivo();//
       
       Arquivo.gravaLinhaTabela("Inserção Binária", compO,String.format("%.2f", auxOrd.calculaCompInsBin(TL)),movO,String.format("%.2f", auxOrd.calculaMovInsBinOrd(TL)), String.format("%.2f", (double)ttotalOrd/1000), 
               compRev, String.format("%.2f", auxRev.calculaCompInsBin(TL)), movRev, String.format("%.2f",auxRev.calculaMovInsBinRev(TL)), String.format("%.2f", (double)ttotalRev/1000),
               compRand, String.format("%.2f",auxRand.calculaCompInsBin(TL)), movRand, String.format("%.2f", auxRand.calculaMovInsBinRand(TL)), String.format("%.2f", (double)ttotalRand/1000)); 
       
        // ----------------------- SELEÇÃO DIRETA -----------------------
        //-Ordenado
       auxOrd.copiaArquivo(arqOrd.getFile());
       auxOrd.initComp();
       auxOrd.initMov();
       tini=System.currentTimeMillis();
       auxOrd.selecaoDireta();
       tfim=System.currentTimeMillis();
       ttotalOrd=tfim-tini;
       compO=auxOrd.getComp();
       movO=auxOrd.getMov();
        
       //-Reverso
       auxRev.copiaArquivo(arqRev.getFile());
       auxRev.initComp();
       auxRev.initMov();
       tini=System.currentTimeMillis();
       auxRev.selecaoDireta();
       tfim=System.currentTimeMillis();
       ttotalRev=tfim-tini;
       compRev=auxRev.getComp();
       movRev=auxRev.getMov();
       
       //-Randomico
       auxRand.copiaArquivo(arqRand.getFile());
       auxRand.initComp();
       auxRand.initMov();
       tini=System.currentTimeMillis();
       auxRand.selecaoDireta();
       tfim=System.currentTimeMillis();
       ttotalRand=tfim-tini;
       compRand=auxRand.getComp();
       movRand=auxRand.getMov();
       
//       auxOrd.exibirArquivo();///
//       auxRev.exibirArquivo();//
//       auxRand.exibirArquivo();//
       
       Arquivo.gravaLinhaTabela("Seleção Direta", compO,String.format("%.2f", auxOrd.calculaCompSelDir(TL)),movO,String.format("%.2f", auxOrd.calculaMovSelDirOrd(TL)), String.format("%.2f", (double)ttotalOrd/1000), 
               compRev, String.format("%.2f", auxRev.calculaCompSelDir(TL)), movRev, String.format("%.2f",auxRev.calculaMovSelDirRev(TL)), String.format("%.2f", (double)ttotalRev/1000),
               compRand, String.format("%.2f",auxRand.calculaCompSelDir(TL)), movRand, String.format("%.2f", auxRand.calculaMovSelDirRand(TL)), String.format("%.2f", (double)ttotalRand/1000)); 

        
       // ----------------------- BUBBLE SORT -----------------------
       //-Ordenado
       auxOrd.copiaArquivo(arqOrd.getFile());
       auxOrd.initComp();
       auxOrd.initMov();
       tini=System.currentTimeMillis();
       auxOrd.bolha();
       tfim=System.currentTimeMillis();
       ttotalOrd=tfim-tini;
       compO=auxOrd.getComp();
       movO=auxOrd.getMov();
        
       //-Reverso
       auxRev.copiaArquivo(arqRev.getFile());
       auxRev.initComp();
       auxRev.initMov();
       tini=System.currentTimeMillis();
       auxRev.bolha();
       tfim=System.currentTimeMillis();
       ttotalRev=tfim-tini;
       compRev=auxRev.getComp();
       movRev=auxRev.getMov();
       
       //-Randomico
       auxRand.copiaArquivo(arqRand.getFile());
       auxRand.initComp();
       auxRand.initMov();
       tini=System.currentTimeMillis();
       auxRand.bolha();
       tfim=System.currentTimeMillis();
       ttotalRand=tfim-tini;
       compRand=auxRand.getComp();
       movRand=auxRand.getMov();

//       auxOrd.exibirArquivo();///
//       auxRev.exibirArquivo();//
//       auxRand.exibirArquivo();//
       
       Arquivo.gravaLinhaTabela("Bubble sort", compO,String.format("%.2f", auxOrd.calculaCompBolha(TL)),movO,String.format("%.2f", auxOrd.calculaMovBolhaOrd(TL)), String.format("%.2f", (double)ttotalOrd/1000), 
               compRev, String.format("%.2f", auxRev.calculaCompBolha(TL)), movRev, String.format("%.2f",auxRev.calculaMovBolhaRev(TL)), String.format("%.2f", (double)ttotalRev/1000),
               compRand, String.format("%.2f",auxRand.calculaCompBolha(TL)), movRand, String.format("%.2f", auxRand.calculaMovBolhaRand(TL)), String.format("%.2f", (double)ttotalRand/1000)); 
       
        // ----------------------- SHAKE SORT -----------------------
       //-Ordenado
       auxOrd.copiaArquivo(arqOrd.getFile());
       auxOrd.initComp();
       auxOrd.initMov();
       tini=System.currentTimeMillis();
       auxOrd.shake();
       tfim=System.currentTimeMillis();
       ttotalOrd=tfim-tini;
       compO=auxOrd.getComp();
       movO=auxOrd.getMov();
        
       //-Reverso
       auxRev.copiaArquivo(arqRev.getFile());
       auxRev.initComp();
       auxRev.initMov();
       tini=System.currentTimeMillis();
       auxRev.shake();
       tfim=System.currentTimeMillis();
       ttotalRev=tfim-tini;
       compRev=auxRev.getComp();
       movRev=auxRev.getMov();
       
       //-Randomico
       auxRand.copiaArquivo(arqRand.getFile());
       auxRand.initComp();
       auxRand.initMov();
       tini=System.currentTimeMillis();
       auxRand.shake();
       tfim=System.currentTimeMillis();
       ttotalRand=tfim-tini;
       compRand=auxRand.getComp();
       movRand=auxRand.getMov();
       
//       auxOrd.exibirArquivo();///
//       auxRev.exibirArquivo();//
//       auxRand.exibirArquivo();//
       
       Arquivo.gravaLinhaTabela("Shake sort", compO,String.format("%.2f", auxOrd.calculaCompBolha(TL)),movO,String.format("%.2f", auxOrd.calculaMovBolhaOrd(TL)), String.format("%.2f", (double)ttotalOrd/1000), 
               compRev, String.format("%.2f", auxRev.calculaCompBolha(TL)), movRev, String.format("%.2f",auxRev.calculaMovBolhaRev(TL)), String.format("%.2f", (double)ttotalRev/1000),
               compRand, String.format("%.2f",auxRand.calculaCompBolha(TL)), movRand, String.format("%.2f", auxRand.calculaMovBolhaRand(TL)), String.format("%.2f", (double)ttotalRand/1000)); 
       
       
        //// ----------------------- SHELL SORT -----------------------
        //-Ordenado
       auxOrd.copiaArquivo(arqOrd.getFile());
       auxOrd.initComp();
       auxOrd.initMov();
       tini=System.currentTimeMillis();
       auxOrd.shell();
       tfim=System.currentTimeMillis();
       ttotalOrd=tfim-tini;
       compO=auxOrd.getComp();
       movO=auxOrd.getMov();
        
       //-Reverso
       auxRev.copiaArquivo(arqRev.getFile());
       auxRev.initComp();
       auxRev.initMov();
       tini=System.currentTimeMillis();
       auxRev.shell();
       tfim=System.currentTimeMillis();
       ttotalRev=tfim-tini;
       compRev=auxRev.getComp();
       movRev=auxRev.getMov();
       
       //-Randomico
       auxRand.copiaArquivo(arqRand.getFile());
       auxRand.initComp();
       auxRand.initMov();
       tini=System.currentTimeMillis();
       auxRand.shell();
       tfim=System.currentTimeMillis();
       ttotalRand=tfim-tini;
       compRand=auxRand.getComp();
       movRand=auxRand.getMov();

//       auxOrd.exibirArquivo();///
//       auxRev.exibirArquivo();//
//       auxRand.exibirArquivo();//
       
       Arquivo.gravaLinhaTabela("Shell sort", compO, "N/A",movO,"N/A", String.format("%.2f", (double)ttotalOrd/1000), 
               compRev, "N/A", movRev, "N/A", String.format("%.2f", (double)ttotalRev/1000),
               compRand, "N/A", movRand, "N/A", String.format("%.2f", (double)ttotalRand/1000)); 

        //// ----------------------- HEAP SORT -----------------------
        //-Ordenado
       auxOrd.copiaArquivo(arqOrd.getFile());
       auxOrd.initComp();
       auxOrd.initMov();
       tini=System.currentTimeMillis();
       auxOrd.heap();
       tfim=System.currentTimeMillis();
       ttotalOrd=tfim-tini;
       compO=auxOrd.getComp();
       movO=auxOrd.getMov();
        
       //-Reverso
       auxRev.copiaArquivo(arqRev.getFile());
       auxRev.initComp();
       auxRev.initMov();
       tini=System.currentTimeMillis();
       auxRev.heap();
       tfim=System.currentTimeMillis();
       ttotalRev=tfim-tini;
       compRev=auxRev.getComp();
       movRev=auxRev.getMov();
       
       //-Randomico
       auxRand.copiaArquivo(arqRand.getFile());
       auxRand.initComp();
       auxRand.initMov();
       tini=System.currentTimeMillis();
       auxRand.heap();
       tfim=System.currentTimeMillis();
       ttotalRand=tfim-tini;
       compRand=auxRand.getComp();
       movRand=auxRand.getMov();

//       auxOrd.exibirArquivo();///
//       auxRev.exibirArquivo();//
//       auxRand.exibirArquivo();//
       
       Arquivo.gravaLinhaTabela("Heap sort", compO, "N/A",movO,"N/A", String.format("%.2f", (double)ttotalOrd/1000), 
               compRev, "N/A", movRev, "N/A", String.format("%.2f", (double)ttotalRev/1000),
               compRand, "N/A", movRand, "N/A", String.format("%.2f", (double)ttotalRand/1000)); 
       
        // ----------------------------------- QUICK ----------------------------------------
        // ----------------------- QUICK SEM PIVÔ -----------------------
        //-Ordenado
       auxOrd.copiaArquivo(arqOrd.getFile());
       auxOrd.initComp();
       auxOrd.initMov();
       tini=System.currentTimeMillis();
       auxOrd.quick_sempivo();
       tfim=System.currentTimeMillis();
       ttotalOrd=tfim-tini;
       compO=auxOrd.getComp();
       movO=auxOrd.getMov();
        
       //-Reverso
       auxRev.copiaArquivo(arqRev.getFile());
       auxRev.initComp();
       auxRev.initMov();
       tini=System.currentTimeMillis();
       auxRev.quick_sempivo();
       tfim=System.currentTimeMillis();
       ttotalRev=tfim-tini;
       compRev=auxRev.getComp();
       movRev=auxRev.getMov();
       
       //-Randomico
       auxRand.copiaArquivo(arqRand.getFile());
       auxRand.initComp();
       auxRand.initMov();
       tini=System.currentTimeMillis();
       auxRand.quick_sempivo();
       tfim=System.currentTimeMillis();
       ttotalRand=tfim-tini;
       compRand=auxRand.getComp();
       movRand=auxRand.getMov();
        
//       auxOrd.exibirArquivo();///
//       auxRev.exibirArquivo();//
//       auxRand.exibirArquivo();//
       
       Arquivo.gravaLinhaTabela("Quick s/ pivô", compO, "N/A",movO,"N/A", String.format("%.2f", (double)ttotalOrd/1000), 
               compRev, "N/A", movRev, "N/A", String.format("%.2f", (double)ttotalRev/1000),
               compRand, "N/A", movRand, "N/A", String.format("%.2f", (double)ttotalRand/1000)); 
       // ----------------------- QUICK COM PIVÔ -----------------------
        //-Ordenado
       auxOrd.copiaArquivo(arqOrd.getFile());
       auxOrd.initComp();
       auxOrd.initMov();
       tini=System.currentTimeMillis();
       auxOrd.quick_compivo();
       tfim=System.currentTimeMillis();
       ttotalOrd=tfim-tini;
       compO=auxOrd.getComp();
       movO=auxOrd.getMov();
        
       //-Reverso
       auxRev.copiaArquivo(arqRev.getFile());
       auxRev.initComp();
       auxRev.initMov();
       tini=System.currentTimeMillis();
       auxRev.quick_compivo();
       tfim=System.currentTimeMillis();
       ttotalRev=tfim-tini;
       compRev=auxRev.getComp();
       movRev=auxRev.getMov();
       
       //-Randomico
       auxRand.copiaArquivo(arqRand.getFile());
       auxRand.initComp();
       auxRand.initMov();
       tini=System.currentTimeMillis();
       auxRand.quick_compivo();
       tfim=System.currentTimeMillis();
       ttotalRand=tfim-tini;
       compRand=auxRand.getComp();
       movRand=auxRand.getMov();
       
       //auxOrd.exibirArquivo();///
       //auxRev.exibirArquivo();//
       //auxRand.exibirArquivo();//
       
       Arquivo.gravaLinhaTabela("Quick c/ pivô", compO, "N/A",movO,"N/A", String.format("%.2f", (double)ttotalOrd/1000), 
               compRev, "N/A", movRev, "N/A", String.format("%.2f", (double)ttotalRev/1000),
               compRand, "N/A", movRand, "N/A", String.format("%.2f", (double)ttotalRand/1000)); 
       
        // ----------------------- MERGE (1ª IMPLEMENTAÇÃO) -----------------------
        //-Ordenado
       auxOrd.copiaArquivo(arqOrd.getFile());
       auxOrd.initComp();
       auxOrd.initMov();
       tini=System.currentTimeMillis();
       auxOrd.merge_sort1();
       tfim=System.currentTimeMillis();
       ttotalOrd=tfim-tini;
       compO=auxOrd.getComp();
       movO=auxOrd.getMov();
        
       //-Reverso
       auxRev.copiaArquivo(arqRev.getFile());
       auxRev.initComp();
       auxRev.initMov();
       tini=System.currentTimeMillis();
       auxRev.merge_sort1();
       tfim=System.currentTimeMillis();
       ttotalRev=tfim-tini;
       compRev=auxRev.getComp();
       movRev=auxRev.getMov();
       
       //-Randomico
       auxRand.copiaArquivo(arqRand.getFile());
       auxRand.initComp();
       auxRand.initMov();
       tini=System.currentTimeMillis();
       auxRand.merge_sort1();
       tfim=System.currentTimeMillis();
       ttotalRand=tfim-tini;
       compRand=auxRand.getComp();
       movRand=auxRand.getMov();
       
//       auxOrd.exibirArquivo();///
//       auxRev.exibirArquivo();//
//       auxRand.exibirArquivo();//
       
       Arquivo.gravaLinhaTabela("Merge 1ª Implement", compO, "N/A",movO,"N/A", String.format("%.2f", (double)ttotalOrd/1000), 
               compRev, "N/A", movRev, "N/A", String.format("%.2f", (double)ttotalRev/1000),
               compRand, "N/A", movRand, "N/A", String.format("%.2f", (double)ttotalRand/1000)); 
       
       // ----------------------- COUNTING SORT -----------------------
       //-Ordenado
       auxOrd.copiaArquivo(arqOrd.getFile());
       auxOrd.initComp();
       auxOrd.initMov();
       tini=System.currentTimeMillis();
       auxOrd.Counting();
       tfim=System.currentTimeMillis();
       ttotalOrd=tfim-tini;
       compO=auxOrd.getComp();
       movO=auxOrd.getMov();
        
       //-Reverso
       auxRev.copiaArquivo(arqRev.getFile());
       auxRev.initComp();
       auxRev.initMov();
       tini=System.currentTimeMillis();
       auxRev.Counting();
       tfim=System.currentTimeMillis();
       ttotalRev=tfim-tini;
       compRev=auxRev.getComp();
       movRev=auxRev.getMov();
       
       //-Randomico
       auxRand.copiaArquivo(arqRand.getFile());
       auxRand.initComp();
       auxRand.initMov();
       tini=System.currentTimeMillis();
       auxRand.Counting();
       tfim=System.currentTimeMillis();
       ttotalRand=tfim-tini;
       compRand=auxRand.getComp();
       movRand=auxRand.getMov();
       
//       auxOrd.exibirArquivo();///
//       auxRev.exibirArquivo();//
//       auxRand.exibirArquivo();//
//       
       Arquivo.gravaLinhaTabela("Counting", compO, "N/A",movO,"N/A", String.format("%.2f", (double)ttotalOrd/1000), 
               compRev, "N/A", movRev, "N/A", String.format("%.2f", (double)ttotalRev/1000),
               compRand, "N/A", movRand, "N/A", String.format("%.2f", (double)ttotalRand/1000)); 
       
        // ----------------------- BUCKET SORT -----------------------/////////
       //-Ordenado
       auxOrd.copiaArquivo(arqOrd.getFile());
       auxOrd.initComp();
       auxOrd.initMov();
       tini=System.currentTimeMillis();
       auxOrd.bucket_sort();
       tfim=System.currentTimeMillis();
       ttotalOrd=tfim-tini;
       compO=auxOrd.getComp();
       movO=auxOrd.getMov();
        
       //-Reverso
       auxRev.copiaArquivo(arqRev.getFile());
       auxRev.initComp();
       auxRev.initMov();
       tini=System.currentTimeMillis();
       auxRev.bucket_sort();
       tfim=System.currentTimeMillis();
       ttotalRev=tfim-tini;
       compRev=auxRev.getComp();
       movRev=auxRev.getMov();
       
       //-Randomico
       auxRand.copiaArquivo(arqRand.getFile());
       auxRand.initComp();
       auxRand.initMov();
       tini=System.currentTimeMillis();
       auxRand.bucket_sort();
       tfim=System.currentTimeMillis();
       ttotalRand=tfim-tini;
       compRand=auxRand.getComp();
       movRand=auxRand.getMov();
       
//       auxOrd.exibirArquivo();///
//       auxRev.exibirArquivo();//
//       auxRand.exibirArquivo();//
       
       Arquivo.gravaLinhaTabela("Bucket", compO, "N/A",movO,"N/A", String.format("%.2f", (double)ttotalOrd/1000), 
               compRev, "N/A", movRev, "N/A", String.format("%.2f", (double)ttotalRev/1000),
               compRand, "N/A", movRand, "N/A", String.format("%.2f", (double)ttotalRand/1000)); 
       
       // ----------------------- RADIX SORT -----------------------
       //-Ordenado
       auxOrd.copiaArquivo(arqOrd.getFile());
       auxOrd.initComp();
       auxOrd.initMov();
       tini=System.currentTimeMillis();
       auxOrd.radix();
       tfim=System.currentTimeMillis();
       ttotalOrd=tfim-tini;
       compO=auxOrd.getComp();
       movO=auxOrd.getMov();
        
       //-Reverso
       auxRev.copiaArquivo(arqRev.getFile());
       auxRev.initComp();
       auxRev.initMov();
       tini=System.currentTimeMillis();
       auxRev.radix();
       tfim=System.currentTimeMillis();
       ttotalRev=tfim-tini;
       compRev=auxRev.getComp();
       movRev=auxRev.getMov();
       
       //-Randomico
       auxRand.copiaArquivo(arqRand.getFile());
       auxRand.initComp();
       auxRand.initMov();
       tini=System.currentTimeMillis();
       auxRand.radix();
       tfim=System.currentTimeMillis();
       ttotalRand=tfim-tini;
       compRand=auxRand.getComp();
       movRand=auxRand.getMov();
       
//       auxOrd.exibirArquivo();///
//       auxRev.exibirArquivo();//
//       auxRand.exibirArquivo();//
       
       Arquivo.gravaLinhaTabela("Radix", compO, "N/A",movO,"N/A", String.format("%.2f", (double)ttotalOrd/1000), 
               compRev, "N/A", movRev, "N/A", String.format("%.2f", (double)ttotalRev/1000),
               compRand, "N/A", movRand, "N/A", String.format("%.2f", (double)ttotalRand/1000)); 
       
        // ----------------------- COMB SORT -----------------------
       //-Ordenado
       auxOrd.copiaArquivo(arqOrd.getFile());
       auxOrd.initComp();
       auxOrd.initMov();
       tini=System.currentTimeMillis();
       auxOrd.comb_Sort();
       tfim=System.currentTimeMillis();
       ttotalOrd=tfim-tini;
       compO=auxOrd.getComp();
       movO=auxOrd.getMov();
        
       //-Reverso
       auxRev.copiaArquivo(arqRev.getFile());
       auxRev.initComp();
       auxRev.initMov();
       tini=System.currentTimeMillis();
       auxRev.comb_Sort();
       tfim=System.currentTimeMillis();
       ttotalRev=tfim-tini;
       compRev=auxRev.getComp();
       movRev=auxRev.getMov();
       
       //-Randomico
       auxRand.copiaArquivo(arqRand.getFile());
       auxRand.initComp();
       auxRand.initMov();
       tini=System.currentTimeMillis();
       auxRand.comb_Sort();
       tfim=System.currentTimeMillis();
       ttotalRand=tfim-tini;
       compRand=auxRand.getComp();
       movRand=auxRand.getMov();

//       auxOrd.exibirArquivo();///
//       auxRev.exibirArquivo();//
//       auxRand.exibirArquivo();//
       
       Arquivo.gravaLinhaTabela("Comb", compO, "N/A",movO,"N/A", String.format("%.2f", (double)ttotalOrd/1000), 
               compRev, "N/A", movRev, "N/A", String.format("%.2f", (double)ttotalRev/1000),
               compRand, "N/A", movRand, "N/A", String.format("%.2f", (double)ttotalRand/1000)); 
       
        // ----------------------- GNOME SORT -----------------------
       //-Ordenado
       auxOrd.copiaArquivo(arqOrd.getFile());
       auxOrd.initComp();
       auxOrd.initMov();
       tini=System.currentTimeMillis();
       auxOrd.gnome_Sort();
       tfim=System.currentTimeMillis();
       ttotalOrd=tfim-tini;
       compO=auxOrd.getComp();
       movO=auxOrd.getMov();
        
       //-Reverso
       auxRev.copiaArquivo(arqRev.getFile());
       auxRev.initComp();
       auxRev.initMov();
       tini=System.currentTimeMillis();
       auxRev.gnome_Sort();
       tfim=System.currentTimeMillis();
       ttotalRev=tfim-tini;
       compRev=auxRev.getComp();
       movRev=auxRev.getMov();
       
       //-Randomico
       auxRand.copiaArquivo(arqRand.getFile());
       auxRand.initComp();
       auxRand.initMov();
       tini=System.currentTimeMillis();
       auxRand.gnome_Sort();
       tfim=System.currentTimeMillis();
       ttotalRand=tfim-tini;
       compRand=auxRand.getComp();
       movRand=auxRand.getMov();
       
//       auxOrd.exibirArquivo();///
//       auxRev.exibirArquivo();//
//       auxRand.exibirArquivo();//
       
       Arquivo.gravaLinhaTabela("Gnome", compO, "N/A",movO,"N/A", String.format("%.2f", (double)ttotalOrd/1000), 
               compRev, "N/A", movRev, "N/A", String.format("%.2f", (double)ttotalRev/1000),
               compRand, "N/A", movRand, "N/A", String.format("%.2f", (double)ttotalRand/1000)); 
       
        // ----------------------- TIM SORT -----------------------
       //-Ordenado
       auxOrd.copiaArquivo(arqOrd.getFile());
       auxOrd.initComp();
       auxOrd.initMov();
       tini=System.currentTimeMillis();
       auxOrd.tim_Sort();
       tfim=System.currentTimeMillis();
       ttotalOrd=tfim-tini;
       compO=auxOrd.getComp();
       movO=auxOrd.getMov();
        
       //-Reverso
       auxRev.copiaArquivo(arqRev.getFile());
       auxRev.initComp();
       auxRev.initMov();
       tini=System.currentTimeMillis();
       auxRev.tim_Sort();
       tfim=System.currentTimeMillis();
       ttotalRev=tfim-tini;
       compRev=auxRev.getComp();
       movRev=auxRev.getMov();
       
       //-Randomico
       auxRand.copiaArquivo(arqRand.getFile());
       auxRand.initComp();
       auxRand.initMov();
       tini=System.currentTimeMillis();
       auxRand.tim_Sort();
       tfim=System.currentTimeMillis();
       ttotalRand=tfim-tini;
       compRand=auxRand.getComp();
       movRand=auxRand.getMov();
       
//       auxOrd.exibirArquivo();///
//       auxRev.exibirArquivo();//
//       auxRand.exibirArquivo();//
       
       Arquivo.gravaLinhaTabela("Tim", compO, "N/A",movO,"N/A", String.format("%.2f", (double)ttotalOrd/1000), 
               compRev, "N/A", movRev, "N/A", String.format("%.2f", (double)ttotalRev/1000),
               compRand, "N/A", movRand, "N/A", String.format("%.2f", (double)ttotalRand/1000)); 
       
    }
}
