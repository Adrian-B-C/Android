package Banco;

/**
 *
 * @author abalague
 */
class cuenta {

    private int saldo;

    public cuenta(int saldo) {
        System.out.println("Saldo de la cuenta " + saldo);
        this.saldo = saldo;
    }

    public synchronized void ingreso(int ingresar) {
        notify();
        this.saldo = saldo + ingresar;
        System.out.println("Ha realizado un ingreso de " + ingresar + "€");
        System.out.println("Saldo= " + saldo);
    }

    public synchronized void gasto(int retirar) {

        if (getSaldo() < retirar) {
            System.out.println("Aun no se puede, necesitas realizar un ingreso");
            try {
                wait();

            } catch (InterruptedException e) {
                System.out.println("Error");
            }
            notify();
            System.out.println("Va a gastar " + retirar + "€");
            this.saldo = saldo - retirar;
            System.out.println("Saldo= " + saldo);
        } else {
            System.out.println("Va a gastar " + retirar + "€");
            this.saldo = saldo - retirar;
            System.out.println("Saldo= " + saldo);

        }

    }

    public int getSaldo() {
        return saldo;
    }

}
