public class Main {
    public static void main(String[] args) {
        Funcionario funcionario = new Funcionario("João", 30, "Desenvolvedor", 5000.0);
        System.out.println("Nome: " + funcionario.getNome());
        System.out.println("Idade: " + funcionario.getIdade());
        System.out.println("Cargo: " + funcionario.cargo);
        System.out.println("Salário: " + funcionario.salario);
        System.out.println("Trabalhando: " + funcionario.trabalhar());
    }
}
