public class Revista extends ItemBiblioteca {
    private int edicao;

    public Revista(String codigo, String titulo, int anoPublicacao, int edicao) {
        super(codigo, titulo, anoPublicacao);
        this.edicao = edicao;
    }

    @Override
    public int calcularPrazoEmprestimo() {
        return 7;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Edição: " + edicao);
    }
}
