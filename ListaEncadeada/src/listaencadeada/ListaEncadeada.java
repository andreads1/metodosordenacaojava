
package listaencadeada;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ListaEncadeada {

    public static void main(String[] args) {
        Lista L = new Lista();
        Lista aux = new Lista();
        L.inicializa();
        
        Random rand = new Random();
        int num, i, j;
        List <Integer> existentes = new ArrayList();
        for(i=0; i<32;i++)
        {
            num = 1 + (rand.nextInt(50));
            while(existentes.contains(num))
            {
                num = 1 +  (rand.nextInt(50));
            }
            existentes.add(num);
            L.inserirNoFinal(num);
                
        } 

        System.out.println("Lista original: ");
        L.exibir();
        System.out.println("\n");

        System.out.println("Lista ordenada por Seleção direta");
        aux = aux.criaListaAux(L);
        aux.selecao_direta();
        aux.exibir();
        System.out.println("\n");
        
        System.out.println("Lista ordenada por Inserção direta");
        aux = aux.criaListaAux(L);
        aux.insercao_direta();
        aux.exibir();
        System.out.println("\n");
        
        System.out.println("Lista ordenada por Inserção binária");
        aux = aux.criaListaAux(L);
        aux.insercao_binaria();
        aux.exibir();
        System.out.println("\n");
        
        System.out.println("Lista ordenada por Bubble sort");
        aux = aux.criaListaAux(L);
        aux.bubble_sort();
        aux.exibir();
        System.out.println("\n");

        System.out.println("Lista ordenada por Shake sort");
        aux = aux.criaListaAux(L);
        aux.shake_sort();
        aux.exibir();
        System.out.println("\n");
        
        System.out.println("Lista ordenada por Heap sort");
        aux = aux.criaListaAux(L);
        aux.heap();
        aux.exibir();
        System.out.println("\n");
        
        System.out.println("Lista ordenada por Shell sort");
        aux = aux.criaListaAux(L);
        aux.shell_sort();
        aux.exibir();
        System.out.println("\n");
        
        System.out.println("Lista ordenada por Quick sem pivô");
        aux = aux.criaListaAux(L);
        aux.quick_sempivo();
        aux.exibir();
        System.out.println("\n");
        
        System.out.println("Lista ordenada por Quick com pivô");
        aux = aux.criaListaAux(L);
        aux.quick_compivo();
        aux.exibir();
        System.out.println("\n"); 
        
        System.out.println("Lista ordenada por Merge 1ª implementação");
        aux = aux.criaListaAux(L);
        aux.merge_sort1();
        aux.exibir();
        System.out.println("\n");
        
        System.out.println("Lista ordenada por Radix");
        aux = aux.criaListaAux(L);
        aux.radix_Sort();
        aux.exibir();
        System.out.println("\n");
        
        System.out.println("Lista ordenada por Comb");
        aux = aux.criaListaAux(L);
        aux.comb_Sort();
        aux.exibir();
        System.out.println("\n"); 

        System.out.println("Lista ordenada por Gnome");
        aux = aux.criaListaAux(L);
        aux.gnome_Sort();
        aux.exibir();
        System.out.println("\n"); 
        
        System.out.println("Lista ordenada por Counting");
        aux = aux.criaListaAux(L);
        aux.Counting();
        aux.exibir();
        System.out.println("\n"); 
        
        System.out.println("Lista ordenada por Tim sort");
        aux = aux.criaListaAux(L);
        aux.tim_Sort();
        aux.exibir();
        System.out.println("\n"); 

        System.out.println("Lista ordenada por Bucket sort");
        aux = aux.criaListaAux(L);
        aux.bucket_sort();
        aux.exibir();
        System.out.println("\n");
       
        
        
        
    }
    
}
