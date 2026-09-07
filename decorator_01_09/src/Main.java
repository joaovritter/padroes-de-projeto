public class Main {

    public static void main(String[] args) {
        Bebida cafeSimples = new CafeSimples();
        mostrar(cafeSimples);

        Bebida cafeComLeiteEChocolate = new ChocolateDecorator(new LeiteDecorator(new CafeSimples()));
        mostrar(cafeComLeiteEChocolate);

        Bebida cafeCompleto = new CarameloDecorator(
                new ChantillyDecorator(
                        new ChocolateDecorator(
                                new LeiteDecorator(new CafeSimples()))));
        mostrar(cafeCompleto);

        // Desafio adicional: mesmo ingrediente adicionado mais de uma vez
        Bebida cafeComDoisChocolates = new ChantillyDecorator(
                new ChocolateDecorator(
                        new ChocolateDecorator(new CafeSimples())));
        mostrar(cafeComDoisChocolates);
    }

    private static void mostrar(Bebida bebida) {
        System.out.println("Bebida: " + bebida.getDescricao());
        System.out.printf("Preço total: R$ %.2f%n%n", bebida.getPreco());
    }
}
