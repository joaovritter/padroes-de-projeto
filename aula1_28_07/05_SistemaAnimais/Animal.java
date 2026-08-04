public abstract class Animal {
    private String nome;
    private int idade;
    private double peso;

    public Animal(String nome, int idade, double peso) {
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
    }

    public abstract void emitirSom();

    public abstract void movimentar();

    public void alimentar(String alimento) {
        System.out.println(nome + " comeu " + alimento + ".");
    }

    public void alimentar(String alimento, double quantidade) {
        System.out.println(nome + " comeu " + quantidade + "g de " + alimento + ".");
    }

    public void alimentar(String alimento, double quantidade, String horario) {
        System.out.println(nome + " comeu " + quantidade + "g de " + alimento + " às " + horario + ".");
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public double getPeso() {
        return peso;
    }
}
