public class Retangulo extends FormaGeometrica {
    private double largura;
    private double altura;

    public Retangulo(String cor, boolean preenchida, double largura, double altura) {
        super(cor, preenchida);
        this.largura = largura;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return largura * altura;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * (largura + altura);
    }

    public void redimensionar(double fator) {
        largura *= fator;
        altura *= fator;
    }

    public void redimensionar(double fatorLargura, double fatorAltura) {
        largura *= fatorLargura;
        altura *= fatorAltura;
    }
}
