import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FuncionarioHorista joao = new FuncionarioHorista("Joao", 1, 160, 25);
        FuncionarioComissionado fabio = new FuncionarioComissionado("Fabio", 2, 2000, 800);
        Gerente carlos = new Gerente("Carlos", 3, 5000, 1500);

        joao.registrarDesempenho(8.5);
        fabio.registrarDesempenho(9.0, "Bateu a meta do mês");
        carlos.registrarDesempenho(9.5, "Excelente liderança", "Ana - RH");

        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(joao);
        funcionarios.add(fabio);
        funcionarios.add(carlos);

        System.out.println("----- Folha de pagamento -----");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome() + " (matrícula " + funcionario.getMatricula() + ")");
            System.out.println("Salário: R$ " + String.format("%.2f", funcionario.calcularSalario()));
            System.out.println("------");
        }
    }
}
