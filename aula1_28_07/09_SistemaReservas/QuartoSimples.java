public class QuartoSimples extends Acomodacao {

    public QuartoSimples(int numero, double valorDiaria) {
        super(numero, valorDiaria);
    }

    @Override
    public double calcularValorReserva(int dias) {
        return getValorDiaria() * dias;
    }
}
