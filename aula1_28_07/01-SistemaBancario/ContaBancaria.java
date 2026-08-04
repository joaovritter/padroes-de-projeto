
public abstract class ContaBancaria {
    private int numeroConta;
    private String titular;
    private double saldo;
    
    public ContaBancaria(int numeroConta, String titular, double saldo) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void depositar (double valor, String descricao){
        saldo += valor;
        System.out.println("Depósito de R$ " + String.format("%.2f", valor) + " realizado com sucesso. Descrição: " + descricao);

    }

    public void depositar (double valor, String descricao, String data){
        saldo += valor;
        System.out.println("Depósito de R$ " + String.format("%.2f", valor) + " realizado com sucesso. Descrição: " + descricao + ". Data: " + data);
    }
   
    public String consultarSaldo() {
        return "Saldo atual: R$ " + String.format("%.2f", saldo);
    }

    public boolean validarOperacao(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido. A operação não pode ser realizada.");
            return false;
        }
        if (valor > saldo) {
            System.out.println("Saldo insuficiente. A operação não pode ser realizada.");
            return false;
        }
        return true;
    }

    public abstract double calcularTarifa(double valor);




    public int getNumeroConta() {
        return numeroConta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }
}
