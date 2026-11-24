import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private final List<Livro> livros = new ArrayList<>();

    public void adicionar(Livro livro) {
        livros.add(livro);
    }

    public LivroIterator createIterator() {
        return new CatalogoIterator(livros);
    }
}
