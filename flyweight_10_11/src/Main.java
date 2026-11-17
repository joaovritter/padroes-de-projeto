public class Main {
    public static void main(String[] args) {
        Floresta floresta = new Floresta();

        for (int i = 0; i < 5; i++) {
            floresta.plantarArvore(10 + i * 7, 20 + i * 3, "Pinheiro", "pinheiro.png", "Verde escuro");
            floresta.plantarArvore(50 + i * 5, 80 - i * 4, "Carvalho", "carvalho.png", "Marrom");
            floresta.plantarArvore(100 + i * 9, 15 + i * 6, "Palmeira", "palmeira.png", "Verde claro");
        }

        floresta.desenhar();

        System.out.println("\nQuantidade total de árvores criadas: " + floresta.quantidadeArvores());
        System.out.println("Quantidade de objetos TipoArvore criados: " + FabricaTipoArvore.quantidadeTipos());
    }
}
