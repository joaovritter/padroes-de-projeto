package aula1_28_07.exercicio3;

public class ContaCorrente extends ContaBancaria {
    public ContaCorrente(double saldo) {
        super(saldo);
    }

    @Override
    public double calcularSaldo() {
        return saldo - 10;
    }
}
