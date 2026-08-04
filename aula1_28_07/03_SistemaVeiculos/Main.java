import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Carro carroDoJoao = new Carro("Fiat", "Argo", 2022);
        Motocicleta motoDoFabio = new Motocicleta("Honda", "CG 160", 2021);
        Caminhao caminhaoDoCarlos = new Caminhao("Volvo", "FH", 2019);

        carroDoJoao.acelerar();
        motoDoFabio.acelerar(30);
        caminhaoDoCarlos.acelerar(20, 8);

        List<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(carroDoJoao);
        veiculos.add(motoDoFabio);
        veiculos.add(caminhaoDoCarlos);

        double distanciaViagem = 150;

        System.out.println("----- Consumo da viagem de " + distanciaViagem + " km -----");
        for (Veiculo veiculo : veiculos) {
            System.out.println(veiculo.getMarca() + " " + veiculo.getModelo() + " (" + veiculo.getAno() + ")");
            System.out.println("Consumo: " + String.format("%.2f", veiculo.calcularConsumo(distanciaViagem)) + " litros");
            System.out.println("------");
        }
    }
}
