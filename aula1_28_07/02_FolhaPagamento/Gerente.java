public class Gerente extends Funcionario {
    private double gratificacao;

    public Gerente(String nome, int matricula, double salarioBase, double gratificacao) {
        super(nome, matricula, salarioBase);
        this.gratificacao = gratificacao;
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() + gratificacao;
    }
}
