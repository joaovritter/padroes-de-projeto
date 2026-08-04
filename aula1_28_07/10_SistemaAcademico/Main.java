import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EstudanteGraduacao joao = new EstudanteGraduacao("Joao", 1);
        EstudantePosGraduacao fabio = new EstudantePosGraduacao("Fabio", 2);
        EstudanteCursoTecnico carlos = new EstudanteCursoTecnico("Carlos", 3);

        joao.adicionarNota(7);
        joao.adicionarNota(8);

        fabio.adicionarNota(9, 1);
        fabio.adicionarNota(6, 1);

        carlos.adicionarNota(8, 3, "Prova prática");
        carlos.adicionarNota(5, 1, "Trabalho em grupo");

        List<Estudante> estudantes = new ArrayList<>();
        estudantes.add(joao);
        estudantes.add(fabio);
        estudantes.add(carlos);

        System.out.println("----- Situação acadêmica -----");
        for (Estudante estudante : estudantes) {
            System.out.println(estudante.getNome() + " (matrícula " + estudante.getMatricula() + ")");
            System.out.println("Média: " + String.format("%.2f", estudante.calcularMedia()));
            System.out.println("Situação: " + estudante.verificarSituacao());
            System.out.println("------");
        }
    }
}
