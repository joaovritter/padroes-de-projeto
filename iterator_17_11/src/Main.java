public class Main {
    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();

        catalogo.adicionar(new Livro("Dom Casmurro", "Machado de Assis"));
        catalogo.adicionar(new Livro("O Cortiço", "Aluísio Azevedo"));
        catalogo.adicionar(new Livro("Vidas Secas", "Graciliano Ramos"));
        catalogo.adicionar(new Livro("Capitães da Areia", "Jorge Amado"));
        catalogo.adicionar(new Livro("A Hora da Estrela", "Clarice Lispector"));

        LivroIterator iterator = catalogo.createIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
