public class VideoReal implements Video {

    private final String titulo;
    private final String arquivo;

    public VideoReal(String titulo, String arquivo) {
        this.titulo = titulo;
        this.arquivo = arquivo;
        carregarDoDisco();
    }

    private void carregarDoDisco() {
        System.out.println("    [VideoReal] Carregando arquivo pesado '" + arquivo + "' na memória...");
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public void exibir(Usuario usuario) {
        System.out.println("    [VideoReal] Reproduzindo '" + titulo + "' para " + usuario.getNome());
    }
}
