public class EstudanteCursoTecnico extends Estudante {

    public EstudanteCursoTecnico(String nome, int matricula) {
        super(nome, matricula);
    }

    @Override
    public double calcularMedia() {
        if (getNotas().isEmpty()) {
            return 0;
        }
        double somaPonderada = 0;
        double somaPesos = 0;
        for (int i = 0; i < getNotas().size(); i++) {
            somaPonderada += getNotas().get(i) * getPesos().get(i);
            somaPesos += getPesos().get(i);
        }
        return somaPonderada / somaPesos;
    }

    @Override
    public String verificarSituacao() {
        return calcularMedia() >= 6 ? "Aprovado" : "Reprovado";
    }
}
