/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonacci;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abalague
 */
public class Fibonacci extends Thread {

    @Override
    public void run() {
        Scanner s = new Scanner(System.in);
        System.out.println("Introduce una posición de la sucesión de Fibonacci");
        int pos = 0;
        int a = Fibonacci(pos = s.nextInt());
        s.nextLine();
        System.out.println("El numero de la posición " + pos + " es " + a);

    }

    public static int Fibonacci(int n) {
        if (n > 1) {
            return Fibonacci(n - 1) + Fibonacci(n - 2);
        } else if (n == 1) {
            return 1;
        } else if (n == 0) {
            return 0;
        } else {
            System.out.println("debes ingresar un número mayor o igual a 1");
            return -1;
        }
    }

    public static void main(String[] args) {

        Fibonacci h1 = new Fibonacci();
        Fibonacci h2 = new Fibonacci();
        Fibonacci h3 = new Fibonacci();
        Fibonacci h4 = new Fibonacci();

        try {
            h1.start();
            h1.join(NORM_PRIORITY);
            h2.start();
            h3.start();
            h3.join(MIN_PRIORITY);
            h4.start();
            h4.join(MAX_PRIORITY);
        } catch (InterruptedException ex) {
            Logger.getLogger(Fibonacci.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
