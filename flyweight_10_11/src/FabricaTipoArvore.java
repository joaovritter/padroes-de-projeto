import java.util.HashMap;
import java.util.Map;

public class FabricaTipoArvore {
    private static final Map<String, TipoArvore> tipos = new HashMap<>();

    public static TipoArvore getTipo(String especie, String imagem, String cor) {
        String chave = especie + "|" + imagem + "|" + cor;
        return tipos.computeIfAbsent(chave, k -> new TipoArvore(especie, imagem, cor));
    }

    public static int quantidadeTipos() {
        return tipos.size();
    }
}
