public class MidiaDigital extends ItemBiblioteca {
    private String formato;

    public MidiaDigital(String codigo, String titulo, int anoPublicacao, String formato) {
        super(codigo, titulo, anoPublicacao);
        this.formato = formato;
    }

    @Override
    public int calcularPrazoEmprestimo() {
        return 3;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Formato: " + formato);
    }
}
