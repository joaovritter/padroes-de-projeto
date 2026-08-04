public class Funcionario extends Pessoa {
    String cargo;
    double salario;


    public Funcionario(String nome, int idade, String cargo, double salario) {
        super(nome, idade);
        this.cargo = cargo;
        this.salario = salario;
    }

    @Override
    public String trabalhar() {
        return "Trabalhando como " + cargo + "...";
    }
}
