public abstract class Veiculo {
    private String marca;
    private String modelo;
    private int ano;
    private double velocidadeAtual;

    public Veiculo(String marca, String modelo, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.velocidadeAtual = 0;
    }

    public abstract double calcularConsumo(double distancia);

    public void acelerar() {
        velocidadeAtual += 10;
        System.out.println(modelo + " acelerou para " + velocidadeAtual + " km/h.");
    }

    public void acelerar(double kmh) {
        velocidadeAtual += kmh;
        System.out.println(modelo + " acelerou " + kmh + " km/h, agora a " + velocidadeAtual + " km/h.");
    }

    public void acelerar(double kmh, double tempoSegundos) {
        velocidadeAtual += kmh;
        System.out.println(modelo + " acelerou " + kmh + " km/h em " + tempoSegundos + "s, agora a " + velocidadeAtual + " km/h.");
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public double getVelocidadeAtual() {
        return velocidadeAtual;
    }
}
