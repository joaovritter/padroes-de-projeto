import java.util.ArrayList;
import java.util.List;

public abstract class Estudante {
    private String nome;
    private int matricula;
    private List<Double> notas = new ArrayList<>();
    private List<Double> pesos = new ArrayList<>();

    public Estudante(String nome, int matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public abstract double calcularMedia();

    public abstract String verificarSituacao();

    public void adicionarNota(double nota) {
        adicionarNota(nota, 1.0, "Avaliação");
    }

    public void adicionarNota(double nota, double peso) {
        adicionarNota(nota, peso, "Avaliação");
    }

    public void adicionarNota(double nota, double peso, String descricao) {
        notas.add(nota);
        pesos.add(peso);
        System.out.println(nome + " recebeu nota " + nota + " (peso " + peso + ") em: " + descricao);
    }

    protected List<Double> getNotas() {
        return notas;
    }

    protected List<Double> getPesos() {
        return pesos;
    }

    public String getNome() {
        return nome;
    }

    public int getMatricula() {
        return matricula;
    }
}
