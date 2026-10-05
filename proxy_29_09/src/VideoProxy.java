public class VideoProxy implements Video {

    private final String titulo;
    private final String arquivo;
    private final Perfil perfilMinimo;
    private VideoReal videoReal;

    public VideoProxy(String titulo, String arquivo, Perfil perfilMinimo) {
        this.titulo = titulo;
        this.arquivo = arquivo;
        this.perfilMinimo = perfilMinimo;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public void exibir(Usuario usuario) {
        // Proxy de proteção: barra antes de tocar no objeto real
        if (!temPermissao(usuario)) {
            System.out.println("    [Proxy] ACESSO NEGADO: '" + titulo + "' é restrito a "
                    + perfilMinimo + " (usuário " + usuario.getNome() + " é " + usuario.getPerfil() + ")");
            return;
        }

        // Proxy virtual: o vídeo real só nasce na primeira exibição autorizada
        if (videoReal == null) {
            System.out.println("    [Proxy] Primeira exibição, instanciando vídeo real...");
            videoReal = new VideoReal(titulo, arquivo);
        } else {
            System.out.println("    [Proxy] Vídeo já está em memória, reutilizando instância.");
        }

        videoReal.exibir(usuario);
    }

    private boolean temPermissao(Usuario usuario) {
        return usuario.getPerfil().ordinal() >= perfilMinimo.ordinal();
    }
}
