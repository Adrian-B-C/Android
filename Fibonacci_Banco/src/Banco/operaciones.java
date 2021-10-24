
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
    private cuenta Cuenta;

    public operaciones(cuenta Cuenta, int index, String operacion, int cantidad) {
        this.index = index;
        this.cantidad = cantidad;
        this.operacion = operacion;
        this.Cuenta = Cuenta;
    }

    @Override
    public void run() {
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        System.out.println("operaciones " + index + " " + operacion + " " + " " + cantidad + "€");
        if (operacion.equals("ingreso")) {
            this.Cuenta.ingreso(cantidad);
        } else {
            this.Cuenta.gasto(cantidad);
        }
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Introduce saldo inicial de la cuenta: ");
        cuenta Cuenta = new cuenta(s.nextInt());
        s.nextLine();

        operaciones h1 = new operaciones(Cuenta, 1, "ingreso", 500);
        operaciones h2 = new operaciones(Cuenta, 2, "gasto", 1000);
        operaciones h3 = new operaciones(Cuenta, 3, "gasto", 250);
        operaciones h4 = new operaciones(Cuenta, 3, "gasto", 100);
        operaciones h5 = new operaciones(Cuenta, 3, "gasto", 300);
        operaciones h6 = new operaciones(Cuenta, 3, "gasto", 400);
        operaciones h7 = new operaciones(Cuenta, 3, "gasto", 150);
        operaciones h8 = new operaciones(Cuenta, 3, "gasto", 2000);
        operaciones h9 = new operaciones(Cuenta, 3, "gasto", 3000);
        operaciones h10 = new operaciones(Cuenta, 3, "ingreso", 200000);

            h2.start();
            h1.start();
            h4.start();
            h5.start();
            h6.start();
            h7.start();
            h8.start();
            h9.start();
            h10.start();
            //h2.setPriority(MAX_PRIORITY);
            h3.start();
           
            
            
           
        

    }
}
