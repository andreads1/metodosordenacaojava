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
public class NoBucket {
    private int numero;
    private NoBucket ant, prox;

    public NoBucket() {
    }

    public NoBucket(int numero, NoBucket ant, NoBucket prox) {
        this.numero = numero;
        this.ant = ant;
        this.prox = prox;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public NoBucket getAnt() {
        return ant;
    }

    public void setAnt(NoBucket ant) {
        this.ant = ant;
    }

    public NoBucket getProx() {
        return prox;
    }

    public void setProx(NoBucket prox) {
        this.prox = prox;
    }

    
    
}
