/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenacao_arquivos;

/**
 *
 * @author Windows
 */
public class ListaBucket {
    private NoBucket inicio, fim;

    public ListaBucket() {
    }

    public ListaBucket(NoBucket inicio, NoBucket fim) {
        this.inicio = inicio;
        this.fim = fim;
    }
    
    public void inicializa()
    {
        inicio = null;
        fim=null;
    }

    public NoBucket getInicio() {
        return inicio;
    }

    public void setInicio(NoBucket inicio) {
        this.inicio = inicio;
    }

    public NoBucket getFim() {
        return fim;
    }

    public void setFim(NoBucket fim) {
        this.fim = fim;
    }
    
    public void InserirNoInicio(int info)
    {
        NoBucket nova = new NoBucket(info, null, inicio);
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
        NoBucket nova;
        if(inicio==null)
        {    
            nova = new NoBucket(info, fim, null);
            inicio = fim = nova;
        }
        else
        {
            nova = new NoBucket(info, fim, null);
            fim.setProx(nova);
            fim = nova;
        }
    }
    
    
    
    public void ordenar()
    {
        NoBucket pi, pposmenor, pj;
        int menor;
        pi = inicio;
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
