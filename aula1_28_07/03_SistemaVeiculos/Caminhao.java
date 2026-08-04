public class Caminhao extends Veiculo {
    private static final double KM_POR_LITRO = 5;

    public Caminhao(String marca, String modelo, int ano) {
        super(marca, modelo, ano);
    }

    @Override
    public double calcularConsumo(double distancia) {
        return distancia / KM_POR_LITRO;
    }
}
