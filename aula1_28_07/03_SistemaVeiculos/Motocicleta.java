public class Motocicleta extends Veiculo {
    private static final double KM_POR_LITRO = 25;

    public Motocicleta(String marca, String modelo, int ano) {
        super(marca, modelo, ano);
    }

    @Override
    public double calcularConsumo(double distancia) {
        return distancia / KM_POR_LITRO;
    }
}
