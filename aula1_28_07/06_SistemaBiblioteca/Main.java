import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Livro livro = new Livro("L1", "Clean Code", 2008, "Robert C. Martin");
        Revista revista = new Revista("R1", "Superinteressante", 2024, 450);
        MidiaDigital audiobook = new MidiaDigital("M1", "O Hobbit", 2012, "Audiobook");

        livro.emprestar("Joao");
        revista.emprestar("Fabio", "01/08/2026");
        audiobook.emprestar("Carlos", "01/08/2026", "04/08/2026");

        // Carlos tenta pegar o mesmo livro que o Joao já levou: deve ser negado
        livro.emprestar("Carlos");

        List<ItemBiblioteca> itens = new ArrayList<>();
        itens.add(livro);
        itens.add(revista);
        itens.add(audiobook);

        System.out.println("----- Acervo -----");
        for (ItemBiblioteca item : itens) {
            item.exibirInformacoes();
            System.out.println("Disponível: " + (item.isDisponivel() ? "Sim" : "Não"));
            System.out.println("------");
        }
    }
}
