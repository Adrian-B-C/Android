/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

/**
 *
 * @author abalague
 */
public class cuenta{
    
    private int saldo;
    
    public cuenta(int saldo) {
        System.out.println("Saldo de la cuenta "+ saldo);
        this.saldo=saldo;
    }

    public synchronized void ingreso(int ingresar){
    saldo=saldo+ingresar;
        System.out.println("Ha realizado un ingreso de "+ingresar+"€");
        System.out.println("Saldo= "+saldo);
    }
    public synchronized void gasto(int retirar){
    if(getSaldo() < retirar){
        System.out.println("Aun no se puede");
        try {
            wait();
        } catch (InterruptedException e) {
            System.out.println("Error");
        }
    }else{
        System.out.println("Va a gastar "+ retirar+"€");
    }
    }

    public int getSaldo() {
        return saldo;
    }
    
    
}

