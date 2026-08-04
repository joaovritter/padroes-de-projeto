public abstract class ItemBiblioteca {
    private String codigo;
    private String titulo;
    private int anoPublicacao;
    private boolean disponivel;

    public ItemBiblioteca(String codigo, String titulo, int anoPublicacao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.disponivel = true;
    }

    public abstract int calcularPrazoEmprestimo();

    public void exibirInformacoes() {
        System.out.println("Código: " + codigo + " | Título: " + titulo + " (" + anoPublicacao + ")");
    }

    public void emprestar(String usuario) {
        emprestar(usuario, "não informada", "não informada");
    }

    public void emprestar(String usuario, String dataRetirada) {
        emprestar(usuario, dataRetirada, "não informada");
    }

    public void emprestar(String usuario, String dataRetirada, String dataDevolucao) {
        if (!disponivel) {
            System.out.println("Item \"" + titulo + "\" indisponível. Empréstimo para " + usuario + " negado.");
            return;
        }
        disponivel = false;
        System.out.println("Item \"" + titulo + "\" emprestado para " + usuario
                + " (retirada: " + dataRetirada + ", devolução: " + dataDevolucao
                + "). Prazo: " + calcularPrazoEmprestimo() + " dias.");
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }
}
