public class QuartoLuxo extends Acomodacao {
    private static final double TAXA_SERVICO = 1.2;

    public QuartoLuxo(int numero, double valorDiaria) {
        super(numero, valorDiaria);
    }

    @Override
    public double calcularValorReserva(int dias) {
        return getValorDiaria() * dias * TAXA_SERVICO;
    }
}
