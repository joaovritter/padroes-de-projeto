public class EstudanteGraduacao extends Estudante {

    public EstudanteGraduacao(String nome, int matricula) {
        super(nome, matricula);
    }

    @Override
    public double calcularMedia() {
        if (getNotas().isEmpty()) {
            return 0;
        }
        double soma = 0;
        for (double nota : getNotas()) {
            soma += nota;
        }
        return soma / getNotas().size();
    }

    @Override
    public String verificarSituacao() {
        return calcularMedia() >= 6 ? "Aprovado" : "Reprovado";
    }
}
