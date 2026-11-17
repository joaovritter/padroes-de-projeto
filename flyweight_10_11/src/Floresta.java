import java.util.ArrayList;
import java.util.List;

public class Floresta {
    private final List<Arvore> arvores = new ArrayList<>();

    public void plantarArvore(int x, int y, String especie, String imagem, String cor) {
        TipoArvore tipo = FabricaTipoArvore.getTipo(especie, imagem, cor);
        arvores.add(new Arvore(x, y, tipo));
    }

    public void desenhar() {
        arvores.forEach(Arvore::desenhar);
    }

    public int quantidadeArvores() {
        return arvores.size();
    }
}
