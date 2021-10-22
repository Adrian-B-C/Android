/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import java.util.Scanner;

/**
 *
 * @author abalague
 */
public class operaciones extends Thread {

    private int index;
    private int cantidad;
    private String operacion;

    public operaciones() {
    }

    public operaciones(int index, int cantidad, String operacion) {
        this.index = index;
        this.cantidad = cantidad;
        this.operacion = operacion;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    @Override
    public void run() {
        Scanner s = new Scanner(System.in);
        System.out.println("Introduce saldo inicial de la cuenta: ");
        cuenta Cuenta = new cuenta(s.nextInt());
        s.nextLine();
        
        System.out.println("operaciones " + index++ + " " + operacion + " " + " " + cantidad);

        if (operacion.equals("ingreso")) {
            Cuenta.ingreso(cantidad);
        } else {
            Cuenta.gasto(cantidad);
        }
    }

    public static void main(String[] args) {
        operaciones h1 = new operaciones();
        h1.start();
    }
}
