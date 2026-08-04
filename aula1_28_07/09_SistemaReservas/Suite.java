public class Suite extends Acomodacao {
    private static final double TAXA_SERVICO = 1.5;
    private static final double VALOR_SERVICOS_EXTRAS = 100;

    public Suite(int numero, double valorDiaria) {
        super(numero, valorDiaria);
    }

    @Override
    public double calcularValorReserva(int dias) {
        return (getValorDiaria() * dias * TAXA_SERVICO) + VALOR_SERVICOS_EXTRAS;
    }
}
