public class Computador {

    private final String processador;
    private final int memoriaRAM;
    private final int armazenamento;
    private final String placaVideo;
    private final String sistemaOperacional;
    private final boolean wifi;
    private final boolean bluetooth;

    private Computador(Builder builder) {
        this.processador = builder.processador;
        this.memoriaRAM = builder.memoriaRAM;
        this.armazenamento = builder.armazenamento;
        this.placaVideo = builder.placaVideo;
        this.sistemaOperacional = builder.sistemaOperacional;
        this.wifi = builder.wifi;
        this.bluetooth = builder.bluetooth;
    }

    public void exibirConfiguracao() {
        System.out.println("Processador: " + processador);
        System.out.println("Memória RAM: " + memoriaRAM + " GB");
        System.out.println("Armazenamento: " + armazenamento + " GB");
        System.out.println("Placa de vídeo: " + (placaVideo != null ? placaVideo : "Não informada"));
        System.out.println("Sistema operacional: " + (sistemaOperacional != null ? sistemaOperacional : "Não informado"));
        System.out.println("Wi-Fi: " + (wifi ? "Sim" : "Não"));
        System.out.println("Bluetooth: " + (bluetooth ? "Sim" : "Não"));
        System.out.println("-----------------------------------");
    }

    public static class Builder {
        private String processador;
        private int memoriaRAM;
        private int armazenamento;
        private String placaVideo;
        private String sistemaOperacional;
        private boolean wifi;
        private boolean bluetooth;

        public Builder processador(String processador) {
            this.processador = processador;
            return this;
        }

        public Builder memoriaRAM(int memoria) {
            this.memoriaRAM = memoria;
            return this;
        }

        public Builder armazenamento(int armazenamento) {
            this.armazenamento = armazenamento;
            return this;
        }

        public Builder placaVideo(String placaVideo) {
            this.placaVideo = placaVideo;
            return this;
        }

        public Builder sistemaOperacional(String sistema) {
            this.sistemaOperacional = sistema;
            return this;
        }

        public Builder wifi(boolean wifi) {
            this.wifi = wifi;
            return this;
        }

        public Builder bluetooth(boolean bluetooth) {
            this.bluetooth = bluetooth;
            return this;
        }

        public Computador build() {
            if (processador == null || processador.trim().isEmpty()) {
                throw new IllegalStateException("O processador é obrigatório e não pode ser vazio.");
            }
            if (memoriaRAM <= 0) {
                throw new IllegalStateException("A quantidade de memória RAM deve ser maior que zero.");
            }
            if (armazenamento <= 0) {
                throw new IllegalStateException("O armazenamento deve ser maior que zero.");
            }
            return new Computador(this);
        }
    }
}
