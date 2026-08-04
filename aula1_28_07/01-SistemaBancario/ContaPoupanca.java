
public class ContaPoupanca extends ContaBancaria {
    
    public ContaPoupanca(int numeroConta, String titular, double saldo) {
        super(numeroConta, titular, saldo);
    }

    @Override
    public double calcularTarifa(double valor) {
        return getSaldo() * 0.01; // 1% de rendimento sobre o saldo
    }

    //conta com percentual de rendimento de 1% ao mes, vai ser inserido no metodo de rendimento
    public void rendimento(){
        double rendimento = calcularTarifa(0);
        super.depositar(rendimento);
        System.out.println("Rendimento de R$ " + String.format("%.2f", rendimento) + " adicionado à conta.");
    }
}