

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ContaCorrente cc = new ContaCorrente(1,"Joao", 2500);
        ContaPoupanca cp = new ContaPoupanca (1,"Fabio", 3000);

        cc.depositar(1000);
        cc.depositar(500, "venda feita");
        cc.depositar(12, "investimento","01/08/2026");
        cc.sacar (2000);

        cp.depositar(3000, "aporte inicial");
        cp.rendimento();

        List<ContaBancaria> contas = new ArrayList<>();
        contas.add(cc);
        contas.add(cp); 

        System.out.println("----- Extratos das contas -----");
        for (ContaBancaria contaBancaria : contas){
            System.out.println(contaBancaria.getTitular() + " (conta " + contaBancaria.getNumeroConta() + ")");
            System.out.println( contaBancaria.consultarSaldo());
            System.out.println("Rendimento: R$"+ String.format("%.2f", contaBancaria.calcularTarifa(100)));
            System.out.println("------");
         
        }
    }
}
