public class Triangulo extends FormaGeometrica {
    private double base;
    private double altura;
    private double lado;

    public Triangulo(String cor, boolean preenchida, double base, double altura, double lado) {
        super(cor, preenchida);
        this.base = base;
        this.altura = altura;
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return (base * altura) / 2;
    }

    @Override
    public double calcularPerimetro() {
        return base + (2 * lado);
    }

    public void redimensionar(double fator) {
        base *= fator;
        altura *= fator;
        lado *= fator;
    }

    public void redimensionar(double fatorBase, double fatorAltura) {
        base *= fatorBase;
        altura *= fatorAltura;
        lado *= fatorAltura;
    }
}
