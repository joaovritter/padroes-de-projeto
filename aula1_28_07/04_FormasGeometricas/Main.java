import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Circulo circulo = new Circulo("Azul", true, 5);
        Retangulo retangulo = new Retangulo("Verde", false, 4, 8);
        Triangulo triangulo = new Triangulo("Vermelho", true, 6, 4, 5);

        circulo.redimensionar(2);
        retangulo.redimensionar(1.5, 2);

        List<FormaGeometrica> formas = new ArrayList<>();
        formas.add(circulo);
        formas.add(retangulo);
        formas.add(triangulo);

        System.out.println("----- Formas geométricas -----");
        for (FormaGeometrica forma : formas) {
            System.out.println(forma.getClass().getSimpleName() + " (cor: " + forma.getCor() + ")");
            System.out.println("Area: " + String.format("%.2f", forma.calcularArea()));
            System.out.println("Perímetro: " + String.format("%.2f", forma.calcularPerimetro()));
            System.out.println("------");
        }
    }
}
