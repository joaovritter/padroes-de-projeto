public abstract class Funcionario {
    private String nome;
    private int matricula;
    private double salarioBase;

    public Funcionario(String nome, int matricula, double salarioBase) {
        this.nome = nome;
        this.matricula = matricula;
        this.salarioBase = salarioBase;
    }

    public abstract double calcularSalario();

    public void registrarDesempenho(double nota) {
        System.out.println(nome + " avaliado com nota " + nota);
    }

    public void registrarDesempenho(double nota, String comentario) {
        System.out.println(nome + " avaliado com nota " + nota + ". Comentário: " + comentario);
    }

    public void registrarDesempenho(double nota, String comentario, String avaliador) {
        System.out.println(nome + " avaliado com nota " + nota + ". Comentário: " + comentario + ". Avaliador: " + avaliador);
    }

    public String getNome() {
        return nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public double getSalarioBase() {
        return salarioBase;
    }
}
