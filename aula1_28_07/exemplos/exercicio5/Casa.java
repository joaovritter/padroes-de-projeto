package aula1_28_07.exercicio5;

public class Casa {
    int tamanho;
    int quartos;

    public Casa(int tamanho, int quartos) {
        this.tamanho = tamanho;
        this.quartos = quartos;
    }

    public double calcularPreco (int tamanho) {
        return tamanho * 1000.0;
    }

    public double calcularPreco (int tamanho, int quartos) {
        return tamanho * 1000.0 + quartos * 500.0;
    }
}
