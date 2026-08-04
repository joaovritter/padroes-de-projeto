public abstract class FormaGeometrica {
    private String cor;
    private boolean preenchida;

    public FormaGeometrica(String cor, boolean preenchida) {
        this.cor = cor;
        this.preenchida = preenchida;
    }

    public abstract double calcularArea();

    public abstract double calcularPerimetro();

    public String getCor() {
        return cor;
    }

    public boolean isPreenchida() {
        return preenchida;
    }
}
