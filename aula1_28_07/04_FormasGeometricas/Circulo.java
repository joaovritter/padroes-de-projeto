public class Circulo extends FormaGeometrica {
    private double raio;

    public Circulo(String cor, boolean preenchida, double raio) {
        super(cor, preenchida);
        this.raio = raio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * raio * raio;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * raio;
    }

    public void redimensionar(double fator) {
        raio *= fator;
    }

    public void redimensionar(double fatorLargura, double fatorAltura) {
        raio *= (fatorLargura + fatorAltura) / 2;
    }
}
