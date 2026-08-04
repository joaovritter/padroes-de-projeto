import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        QuartoSimples quarto101 = new QuartoSimples(101, 150);
        QuartoLuxo quarto201 = new QuartoLuxo(201, 300);
        Suite suite301 = new Suite(301, 500);

        quarto101.reservar("Joao", 3);
        quarto201.reservar("Fabio", 2, 2);
        suite301.reservar("Carlos", 4, 2, "Café da manhã e spa");

        List<Acomodacao> acomodacoes = new ArrayList<>();
        acomodacoes.add(quarto101);
        acomodacoes.add(quarto201);
        acomodacoes.add(suite301);

        System.out.println("----- Acomodações -----");
        for (Acomodacao acomodacao : acomodacoes) {
            System.out.println("Quarto " + acomodacao.getNumero() + " (diária: R$ "
                    + String.format("%.2f", acomodacao.getValorDiaria()) + ")");
            System.out.println("Disponível: " + (acomodacao.isDisponivel() ? "Sim" : "Não"));
            System.out.println("------");
        }
    }
}
