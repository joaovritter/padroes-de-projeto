public abstract class Acomodacao {
    private int numero;
    private double valorDiaria;
    private boolean disponivel;

    public Acomodacao(int numero, double valorDiaria) {
        this.numero = numero;
        this.valorDiaria = valorDiaria;
        this.disponivel = true;
    }

    public abstract double calcularValorReserva(int dias);

    public void reservar(String hospede, int dias) {
        reservar(hospede, dias, 1, "Nenhum");
    }

    public void reservar(String hospede, int dias, int numeroHospedes) {
        reservar(hospede, dias, numeroHospedes, "Nenhum");
    }

    public void reservar(String hospede, int dias, int numeroHospedes, String servicosAdicionais) {
        if (!disponivel) {
            System.out.println("Acomodação " + numero + " indisponível para " + hospede + ".");
            return;
        }
        disponivel = false;
        System.out.println("Reserva confirmada para " + hospede + " (" + numeroHospedes + " hóspede(s), "
                + dias + " dia(s), serviços: " + servicosAdicionais + "). Valor: R$ "
                + String.format("%.2f", calcularValorReserva(dias)));
    }

    public int getNumero() {
        return numero;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }
}
