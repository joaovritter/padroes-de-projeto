public class Arvore {
    // estado extrínseco (único de cada árvore)
    private final int x;
    private final int y;
    private final TipoArvore tipo;

    public Arvore(int x, int y, TipoArvore tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
    }

    public void desenhar() {
        tipo.desenhar(x, y);
    }
}
