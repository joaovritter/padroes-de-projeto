public class Livro extends ItemBiblioteca {
    private String autor;

    public Livro(String codigo, String titulo, int anoPublicacao, String autor) {
        super(codigo, titulo, anoPublicacao);
        this.autor = autor;
    }

    @Override
    public int calcularPrazoEmprestimo() {
        return 14;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Autor: " + autor);
    }
}
