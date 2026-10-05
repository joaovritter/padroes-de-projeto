import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Usuario colaborador = new Usuario("João Ritter", Perfil.COLABORADOR);
        Usuario gestor = new Usuario("Maria Souza", Perfil.GESTOR);

        System.out.println("=== Carregando a página inicial (catálogo) ===");
        List<Video> catalogo = new ArrayList<>();
        catalogo.add(new VideoProxy("Integração de Novos Colaboradores", "integracao.mp4", Perfil.COLABORADOR));
        catalogo.add(new VideoProxy("Segurança da Informação", "seguranca.mp4", Perfil.COLABORADOR));
        catalogo.add(new VideoProxy("Gestão de Equipes de Alta Performance", "gestao_equipes.mp4", Perfil.GESTOR));
        catalogo.add(new VideoProxy("Avaliação de Desempenho e Remuneração", "avaliacao_desempenho.mp4", Perfil.GESTOR));

        for (Video video : catalogo) {
            System.out.println("  - " + video.getTitulo());
        }
        System.out.println("(nenhum vídeo real foi carregado em memória)");

        Video integracao = catalogo.get(0);
        Video gestaoEquipes = catalogo.get(2);

        System.out.println("\n=== Cenário 1: colaborador assiste vídeo livre (carrega sob demanda) ===");
        integracao.exibir(colaborador);

        System.out.println("\n=== Cenário 2: outro usuário assiste o mesmo vídeo (cache/reutilização) ===");
        integracao.exibir(gestor);

        System.out.println("\n=== Cenário 3: colaborador tenta vídeo restrito (acesso negado) ===");
        gestaoEquipes.exibir(colaborador);

        System.out.println("\n=== Cenário 4: gestor assiste vídeo restrito (acesso permitido) ===");
        gestaoEquipes.exibir(gestor);

        System.out.println("\n=== Cenário 5: gestor assiste novamente (reutilização) ===");
        gestaoEquipes.exibir(gestor);
    }
}
