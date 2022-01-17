
package listaencadeada;

public class Lista {
    private No inicio;
    private No fim;

    public Lista() {
        this.inicio = null;
        this.fim = null;
    }

    public void inicializa()
    {
        inicio = fim = null;
    }
    
    public Lista criaListaAux(Lista l1)
    {
        Lista l2 = new Lista();
        No aux = l1.inicio;
        while(aux!= null)
        {
            l2.inserirNoFinal(aux.getInfo());
            aux = aux.getProx();
        }
        return l2;

    }
    
    public void selecao_direta()
    {
        No pi, pposmenor, pj;
        int menor;
        pi = inicio;
        while(pi.getProx()!= null)
        {
            menor=pi.getInfo();
            pposmenor=pi;
            pj=pi.getProx();
            while(pj != null)
            {
                if(pj.getInfo()<menor)
                {
                    menor = pj.getInfo();
                    pposmenor = pj;
                }
                pj = pj.getProx();
            }
            pposmenor.setInfo(pi.getInfo());
            pi.setInfo(menor);
            pi = pi.getProx();
        }
    }
    
    public void insercao_direta()
    {
        No pos, pi;
        int aux;
        pi = inicio.getProx();
        while(pi != null)
        {
            pos = pi;
            aux = pi.getInfo();
            while(pos.getAnt() != null && aux < pos.getAnt().getInfo())
            {
                pos.setInfo(pos.getAnt().getInfo());
                pos = pos.getAnt();
            }
            pos.setInfo(aux);
            pi = pi.getProx();
        }
    }
    

    
    public int busca_binaria(int chave, int tam)
    {
        int ini=0, fim = tam-1, meio, valor_meio;
        meio = fim/2;
        
        No auxcont;////
        int cont=0;
        auxcont= inicio;
        while(auxcont != null && cont != meio)
        {
            auxcont = auxcont.getProx();
            cont++;
        }
        valor_meio = auxcont.getInfo();
        
        while(ini<fim && chave != valor_meio)
        {
            if(chave > valor_meio)
                ini = meio + 1;
            else
                fim = meio - 1;
            meio = (ini+fim)/2;
            cont=0;
            auxcont= inicio;
            while(auxcont != null && cont != meio)
            {
                cont++;
                auxcont = auxcont.getProx();
            }
            valor_meio = auxcont.getInfo();
        }
        if(chave > valor_meio)
            return meio + 1;
        return meio;
    }
    
    
    public void insercao_binaria()
    {
        int aux, pos, i;
        int cont;
        No npos=inicio;
        No pi = inicio;
        No pj = inicio;
        for(i=1; i<getTamanho();i++)
        {
            pi = posicionaDoInicio(pi, i);
            aux = pi.getInfo();
            pos = busca_binaria(aux, i);
            
            for(int j = i; j> pos; j--)
            {
                pj = posicionaDoInicio(pj, j);
                pj.setInfo(pj.getAnt().getInfo());
            }
            npos = posicionaDoInicio(npos, pos);
            npos.setInfo(aux);
        }
    }
    
    public void bubble_sort()
    {
        No pfim = fim;
        No pi;
        int aux;
        
        while(pfim != inicio)
        {
            pi = inicio;
            while(pi != pfim)
            {
                if(pi.getInfo() > pi.getProx().getInfo())
                {
                    aux = pi.getInfo();
                    pi.setInfo(pi.getProx().getInfo());
                    pi.getProx().setInfo(aux);
                }
                pi = pi.getProx();
            }
            pfim = pfim.getAnt();
        }
        
        
        
    }
    
    
    public void shake_sort()
    {
        No pfim = fim;
        No pi, pj;
        No pini = inicio;
        int aux;
        
        while(pfim != pini)//
        {
            //ida
            pi = pini;
            while(pi != pfim)
            {
                if(pi.getInfo() > pi.getProx().getInfo())
                {
                    aux = pi.getInfo();
                    pi.setInfo(pi.getProx().getInfo());
                    pi.getProx().setInfo(aux);
                }
                pi = pi.getProx();
            }
            pfim = pfim.getAnt();
            //volta
            if(pfim != pini)
            {
                pj = pfim;
                while (pj != pini) {
                    if (pj.getInfo() < pj.getAnt().getInfo()) {
                        aux = pj.getInfo();
                        pj.setInfo(pj.getAnt().getInfo());
                        pj.getAnt().setInfo(aux);
                    }
                    pj = pj.getAnt();
                }
                pini = pini.getProx();
            }
        }
        
    }
    
    public void heap()
    {
        int TL = getTamanho();
        int TL2 = TL, pai, FE, FD, maiorF, aux;
        No noE=inicio, noD=inicio;
        No noMaior = inicio;
        No noPai = inicio;
        No noUlt=inicio;
        while(TL2>1)
        {
            for(pai=TL2/2-1;pai>=0; pai--)
            {
                FE = 2* pai+1; 
                maiorF = FE;
                noMaior = posicionaDoInicio(noMaior, maiorF);
                FD = 2* pai+2;
                
                if(FD<TL2)
                {
                    noD = posicionaDoInicio(noD, FD);
                    if(noD.getInfo() > noMaior.getInfo())
                    {   
                        maiorF = FD;
                        noMaior = posicionaDoInicio(noMaior, maiorF);
                    }
                }

                noPai = posicionaDoInicio(noPai, pai);
                if(noMaior.getInfo() > noPai.getInfo())
                {
                    aux = noPai.getInfo();
                    noPai.setInfo(noMaior.getInfo());
                    noMaior.setInfo(aux);
                }
            }
            noUlt = posicionaDoInicio(noUlt, TL2-1);
            aux = inicio.getInfo();
            inicio.setInfo(noUlt.getInfo());//
            noUlt.setInfo(aux);
            TL2--;
        }
    }
    
    
    
    public void testaPOS()
    {
        No aux = inicio;
        aux = posicionaDoInicio(aux, 5);
        System.out.println("Posicionou em: "+aux.getInfo());
    }
    
    public No posicionaDoInicio(No n, int pos)
    {
        n = inicio;
        while(n!=null && pos>0)
        {
            n = n.getProx();
            pos--;
        }
        return n;
    }
    
    public void shell_sort()
    {
        int TL = getTamanho();
        int aux, dist = 4, i, j, k;
        No pi = inicio;
        No pj = inicio;
        No pk = inicio;
        No pk2 = inicio;
        while(dist>0)
        {
           for(i=0; i<dist; i++)
           {
               j=i;
               while((j+dist) < TL)
               {
                   pi = posicionaDoInicio(pi, j);
                   pj = posicionaDoInicio(pj, j+dist);
                   //System.out.println("Comparando "+pi.getInfo()+"com "+pj.getInfo());
                   if(pi.getInfo() > pj.getInfo())
                   {
                       aux = pi.getInfo();
                       pi.setInfo(pj.getInfo());
                       pj.setInfo(aux);
                       k=j;/////////
                       
                       pk = posicionaDoInicio(pk, k);
                       if(k-dist >=i)
                           pk2 = posicionaDoInicio(pk2, k-dist);
                       while(k-dist >= i && pk.getInfo() < pk2.getInfo())
                       {
                           aux = pk.getInfo();
                           pk.setInfo(pk2.getInfo());
                           pk2.setInfo(aux);
                           k = k-dist;
                           pk = posicionaDoInicio(pk, k);
                           pk2 = posicionaDoInicio(pk2, k-dist);
                       }
                   }
                   j = j+dist;
               }
           }
           dist /=2;
        }
    }
    
    public void quick_sempivo()
    {
        QuickSP(0, (getTamanho()-1));
        //System.out.println("Passou com tam "+(getTamanho()-1));
    }
    
    public void QuickSP(int ini, int fin)
    {
        int i = ini, j = fin, aux;
        No pi = inicio;
        No pj = fim;
        while(i<j)
        {
            pi = pi = posicionaDoInicio(pi, i);
            pj = posicionaDoInicio(pj, j);
            while(i<j && pi.getInfo() <= pj.getInfo())
            {
                i++;
                pi = posicionaDoInicio(pi, i);
            }
            aux = pi.getInfo();
            pi.setInfo(pj.getInfo());
            pj.setInfo(aux);
            while(i<j && pj.getInfo() >= pi.getInfo())
            {
                j--;
                pj = posicionaDoInicio(pj, j);
            }
            aux = pi.getInfo();
            pi.setInfo(pj.getInfo());
            pj.setInfo(aux);
        }
        if(ini<i-1)
            QuickSP(ini, i-1);
        if(j+1<fin)
            QuickSP(j+1, fin);
    }
    
    
    public void quick_compivo()
    {
        QuickCP(0, (getTamanho()-1));
        //System.out.println("Passou com tam "+(getTamanho()-1));
    }
    
    public void QuickCP(int ini, int fin)
    {

        No pi=inicio, pj=inicio;
        int aux, pivo, i=ini, j=fin;
        No nopivo = inicio;
        nopivo = posicionaDoInicio(nopivo, (ini+fin)/2);
        pivo =nopivo.getInfo();
        
        while(i < j){
            pi = posicionaDoInicio(pi, i);
            pj = posicionaDoInicio(pj, j);
            while(pi.getInfo() < pivo){
                i++;
                pi =pi.getProx();
            }
            
            while(pj.getInfo() > pivo){
                j--;
                pj =pj.getAnt();
            }
            if(i <= j){
                aux = pi.getInfo();
                pi.setInfo(pj.getInfo());
                pj.setInfo(aux);
                i++;
                j--;
            }
        }
        if(ini < j)
            QuickCP(ini, j);
        if(i < fin)
            QuickCP(i, fin);
        
        
    }
    
    public void merge_sort1()
    {
        int seq =1;
        int TL = getTamanho();
        Lista L1 = new Lista();
        L1.inicializa();
        Lista L2 = new Lista();
        L2.inicializa();
        while(seq < TL)
        {
            particao(L1, L2);
            fusao(L1, L2, seq);
            seq = seq*2;
        }
    }
    
    public void particao(Lista L1, Lista L2)
    {
        int tam = getTamanho()/2;
        No aux=inicio;
        No auxLista1, auxLista2;
        if(L1.inicio!=null && L2.inicio!=null)
        {
            auxLista1 = L1.inicio;
            auxLista2 = L2.inicio;
            for(int i=0; i<tam; i++)
            {
                aux = posicionaDoInicio(aux, i);
                auxLista1.setInfo(aux.getInfo());
                auxLista1 = auxLista1.getProx();
                aux = posicionaDoInicio(aux, i+tam);
                auxLista2.setInfo(aux.getInfo());
                auxLista2 = auxLista2.getProx();
            }
        }
        else
        {
            for(int i=0; i< tam; i++)
            {
                aux = posicionaDoInicio(aux, i);
                L1.inserirNoFinal(aux.getInfo());
                aux = posicionaDoInicio(aux, i+tam);
                L2.inserirNoFinal(aux.getInfo());
            }
        }
    }
    
    public void fusao(Lista L1, Lista L2, int seq)
    {
        int TL = getTamanho();
        No aux1=L1.getInicio(), aux2=L2.getInicio();
        No auxL = inicio;
        int auxS = seq, k=0, i=0, j=0;
        
        while(k< TL)
        {
            while(i<seq && j<seq)
            {
                aux1=L1.posicionaDoInicio(aux1, i);
                aux2=L2.posicionaDoInicio(aux2, j);
                auxL = posicionaDoInicio(auxL, k);
                if(aux1.getInfo() < aux2.getInfo())
                {
                    auxL.setInfo(aux1.getInfo());
                    i++;
                }
                else
                {
                    auxL.setInfo(aux2.getInfo());
                    j++;
                }
                k++;
            }
            
            aux1=L1.posicionaDoInicio(aux1, i);
            aux2=L2.posicionaDoInicio(aux2, j);
            while(i<seq)
            {
                auxL = posicionaDoInicio(auxL, k);
                auxL.setInfo(aux1.getInfo());
                i++;
                k++;
                aux1 = aux1.getProx();
            }
            while(j<seq)
            {
                auxL = posicionaDoInicio(auxL, k);
                auxL.setInfo(aux2.getInfo());
                j++;
                k++;
                aux2 = aux2.getProx();
            }
            seq = seq + auxS;
        }
    }
    
    public void Counting()
    {
        int TL = getTamanho();
        int maior = getMaior();
        int menor = getMenor();
        int range = maior - menor +1;
        int contador[] = new int[range];
        int vetFinal[] = new int[TL];
        int i;
        No pi = inicio;
        for(i=0; i<TL; i++)
        {
            contador[pi.getInfo()-menor]++;
            pi = pi.getProx();
        }    
        
        for(i=1; i<range; i++)
        {
            contador[i] = contador[i] + contador[i-1];
        }
        
        for(i=TL-1;i>=0; i-- )
        {
            pi = posicionaDoInicio(pi, i);
            vetFinal[contador[pi.getInfo()-menor]-1] = pi.getInfo();
            contador[pi.getInfo()-menor]--;
        }
        
        pi = inicio;
        for(i=0; i<TL; i++)
        {
            pi.setInfo(vetFinal[i]);
            pi = pi.getProx();
        }
        
        
        
        
    }
    
    public void counting_Sort(int exp)//para o radix
    {
        int TL = getTamanho();
        No pi = inicio;
        int contador[]= new int[10];
        int vetFinal[] = new int[TL]; 
        int i;
        for(i=0; i<10; i++)
            contador[i]=0;
        
        while(pi!= null)
        {
            contador[(pi.getInfo()/exp) % 10]++;
            pi = pi.getProx();
        }
        
        for(i =1; i<10; i++)
            contador[i] = contador[i] + contador[i-1];
        
        for(i = TL-1; i>=0; i--)
        {
            pi = posicionaDoInicio(pi, i);
            vetFinal[contador[(pi.getInfo()/exp)%10]-1] = pi.getInfo();
            contador[(pi.getInfo()/exp)%10]--;
        }
        
        pi = inicio;
        for(i=0; i<TL;i++)
        {
            pi.setInfo(vetFinal[i]);
            pi = pi.getProx();
        }
        
    }
    
    public int getMaior()
    {
        int maior = inicio.getInfo();
        No aux=inicio.getProx();
        
        while(aux!= null)
        {
            if(aux.getInfo() > maior)
                maior = aux.getInfo();
            aux = aux.getProx();
        }
        return maior;
    }
    
    public int getMenor()
    {
        int menor= inicio.getInfo();
        No aux=inicio.getProx();
        
        while(aux!= null)
        {
            if(aux.getInfo() < menor)
                menor = aux.getInfo();
            aux = aux.getProx();
        }
        return menor;
    }
    
    public void radix_Sort()
    {
        int maior = this.getMaior();
        int exp;
        for(exp=1; maior/exp>0; exp = exp*10)
            counting_Sort(exp);
    }
    
    
    public void comb_Sort()
    {
        int TL = getTamanho();
        int aux, dist= TL;
        boolean trocou = true;
        No pi=inicio, pj=inicio;
        while( dist >1 || trocou==true)
        {
            if(dist > 1)
                dist = (int) (dist/1.247330950103979);
            trocou = false;
            for(int i=0; i+dist < TL;i++)
            {
                pi = posicionaDoInicio(pi, i);
                pj = posicionaDoInicio(pj, i+dist);
                if(pi.getInfo()> pj.getInfo())
                {
                    aux = pi.getInfo();
                    pi.setInfo(pj.getInfo());
                    pj.setInfo(aux);
                    trocou = true;
                }
            }
        }
    }
    
    public void gnome_Sort()
    {
        int TL = getTamanho();
        No pi=inicio, pj=inicio;
        int i=0;
        int aux;
        
        while(i<TL)
        {
            if(i==0)
                i++;
            pi = posicionaDoInicio(pi, i);
            pj = posicionaDoInicio(pj, i-1);
            if(pi.getInfo() >= pj.getInfo())
                i++;
            else
            {
                aux = pi.getInfo();
                pi.setInfo(pj.getInfo());
                pj.setInfo(aux);
                i--;  
            }
        }
    }
    
    public int getMenorValor(int a, int b)//para o  tim
    {
        if(a<b)
            return a;
        else
            return b;
    }
    
    public void insercao_diretaTim(int esq, int dir)
    {
        No pi=inicio, pj = inicio;
        int j;
        
        if(pi!= null){
            for(int i = esq+1; i<= dir; i++)
            {
                pi = posicionaDoInicio(pi, i);
                int aux = pi.getInfo();
                j= i-1;
                pj = posicionaDoInicio(pj, j);
                while(j>= esq && pj.getInfo()>aux)
                {
                    pj.getProx().setInfo(pj.getInfo());
                    j--;
                    pj = pj.getAnt();//
                }
                pj = posicionaDoInicio(pj, j+1);
                pj.setInfo(aux);
            }
            
        }
    }
    
    public void merge_Tim(int esq, int dir, int meio)
    {
        int TL1 = meio - esq +1;
        int TL2 = dir - meio;
        int i;
        Lista L1 = new Lista();
        Lista L2 = new Lista();
        No pi = inicio;
        No auxE=L1.inicio, auxD = L2.inicio;
        
        for(i=0; i<TL1;i++)
        {
            pi = posicionaDoInicio(pi,esq+i);
            L1.inserirNoFinal(pi.getInfo());
        }
        for(i=0; i<TL2;i++)
        {
            pi = posicionaDoInicio(pi,meio+1+i);
            L2.inserirNoFinal(pi.getInfo());
        }
            
        i=0;
        int j=0, k=1;
        auxE = L1.posicionaDoInicio(auxE, i);
        auxD = L2.posicionaDoInicio(auxD, j);
        while(i < TL1 && j < TL2)
        {
            pi = posicionaDoInicio(pi, k);
            if(auxE.getInfo() < auxD.getInfo())
            {
                pi.setInfo(auxE.getInfo());
                i++;
                auxE = L1.posicionaDoInicio(auxE, i);
            }
            else
            {
                pi.setInfo(auxD.getInfo());
                j++;
                auxD = L2.posicionaDoInicio(auxD, j);
            }
            k++;
        }
        
        while(i<TL1)
        {
            pi = posicionaDoInicio(pi, k);
            pi.setInfo(auxE.getInfo());
            i++;
            k++;
            auxE = L1.posicionaDoInicio(auxE, i);
        }
        
        while(j<TL1)
        {
            pi = posicionaDoInicio(pi, k);
            pi.setInfo(auxD.getInfo());
            j++;
            k++;
            auxD = L2.posicionaDoInicio(auxD, i);
        }
        
    }
    
    public void tim_Sort()
    {
        int bloco = 32, i, tam, meio, fim;
        int esq, dir;
        int TL = getTamanho();
        
        for(i=0; i<TL; i = i+bloco)
            insercao_diretaTim(i, getMenorValor((i+bloco-1), (TL-1)));
        
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
    
    
    public void bucket_sort() {
        int qtdBaldes = 5;
        int max = (getMaior() - 1) / qtdBaldes;
        int i, aux;
        No pi;
        
        Lista[] buckets = new Lista[max];
        for (i = 0; i < max; i++) {
            buckets[i] = new Lista();
        }
        
        pi = inicio;
        while (pi != null) {
            aux = pi.getInfo();
            buckets[(aux - 1) / (max + 1)].InserirNoInicio(aux);
            pi = pi.getProx();
        }
        for (i = 0; i < max; i++) {
            ordena_Bucket(buckets[i]);
        }

        No pBuck;
        pi = inicio;
        i=0;
        for (int j = 0; j < max; j++) {
            pBuck = buckets[j].inicio;
            while (pBuck != null) {
                pi= posicionaDoInicio(pi, i);
                pi.setInfo(pBuck.getInfo());
                i++;
                pBuck = pBuck.getProx();
            }
        }
    }

    public void ordena_Bucket(Lista bucket)
    {
        No pi, pposmenor, pj;
        int menor;
        pi = bucket.inicio;
        if(pi!= null){
            while(pi.getProx()!= null)
            {
                menor=pi.getInfo();
                pposmenor=pi;
                pj=pi.getProx();
                while(pj != null)
                {
                    if(pj.getInfo()<menor)
                    {
                        menor = pj.getInfo();
                        pposmenor = pj;
                    }
                    pj = pj.getProx();
                }
                pposmenor.setInfo(pi.getInfo());
                pi.setInfo(menor);
                pi = pi.getProx();
            }
        }
    }
    
    void testaposiciona()
    {
        No p = inicio;
        
        p = posiciona(p, 5);
        //System.out.println("P agora em: "+p.getInfo());
    }
    
    public No posiciona(No p, int dist)
    {
        if(dist<0)
            while(dist <0)
            {
                p = p.getAnt();
                dist++;
            }
        else
            while(dist>0)
            {
                p = p.getProx();
                dist--;
            }
        return p;
    }
    
    public void InserirNoInicio(int info)
    {
        No nova = new No(null, inicio, info);
        if(inicio==null)
            inicio = fim = nova;
        else
        {
            inicio.setAnt(nova);
            inicio = nova;
        }
    }
    
    public void inserirNoFinal(int info)
    {
        No nova;
        if(inicio==null)
        {    
            nova = new No(null, inicio, info);
            inicio = fim = nova;
        }
        else
        {
            nova = new No(fim, null, info);
            fim.setProx(nova);
            fim = nova;
        }
    }
    
    public int getTamanho()
    {
        No aux = inicio;
        int i=0;
        while(aux!= null)
        {
            i++;
            aux= aux.getProx();
        }
        //System.out.println("Tamanho: "+i);
        return i;
    }
    
    public void exibir()
    {
        No aux = inicio;
        while(aux!= null)
        {
            System.out.print(aux.getInfo()+", ");
            aux = aux.getProx();
        }
    }
    
    public No busca_exaustiva(int info)
    {
        No aux= inicio;
        while(aux != null && info != aux.getInfo())
        {
            aux = aux.getProx();
        }
        
        return aux;
    }
    
    public void remover(int info)
    {
        if(inicio.getInfo()== info)
        {
            inicio = inicio.getProx();
            inicio.setAnt(null);
        }
        else
        if(fim.getInfo()== info)
        {
            fim = fim.getAnt();
            fim.setProx(null);
        }
        else
        {
            No aux = inicio;
            No ant=null;
            while(aux!= null && aux.getInfo() != info)
            {
                ant = aux;
                aux = aux.getProx();
            }
            if(aux.getInfo()== info)
            {
                ant.setProx(aux.getProx());
                aux.getProx().setAnt(ant);
            }
        }
    }

    public No getInicio() {
        return inicio;
    }

    public void setInicio(No inicio) {
        this.inicio = inicio;
    }

    public No getFim() {
        return fim;
    }

    public void setFim(No fim) {
        this.fim = fim;
    }
    
    
}
