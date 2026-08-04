

public class ContaCorrente extends ContaBancaria {
    
    public ContaCorrente(int numeroConta, String titular, double saldo) {
        super(numeroConta, titular, saldo);
    }

    @Override
    public double calcularTarifa(double valor) {
        return valor * 0.10; // 10% de taxa sobre o valor sacado
    }

    //metodo para cobrar 10% encima de cada saque realizado, vai ser inserido no metodo de saque
    public void cobrarTaxa(double valorSaque) {
        double taxa = calcularTarifa(valorSaque);
        if (validarOperacao(taxa)) {
            super.depositar(-taxa); // Subtrai a taxa do saldo
            System.out.println("Taxa de R$ " + String.format("%.2f", taxa) + " cobrada.");
        }
    }


    public void sacar(double valor) {
        if (validarOperacao(valor)) {
            super.depositar(-valor); 
            System.out.println("Saque de R$ " + String.format("%.2f", valor) + " realizado com sucesso.");
            cobrarTaxa(valor); 
        }
    }

    
}
