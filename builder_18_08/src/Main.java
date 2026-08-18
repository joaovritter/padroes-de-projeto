public class Main {

    public static void main(String[] args) {

        Computador gamer = new Computador.Builder()
                .processador("Intel Core i9")
                .memoriaRAM(32)
                .armazenamento(2000)
                .placaVideo("RTX 5070")
                .sistemaOperacional("Windows 11")
                .wifi(true)
                .bluetooth(true)
                .build();

        Computador escritorio = new Computador.Builder()
                .processador("Intel Core i5")
                .memoriaRAM(16)
                .armazenamento(512)
                .wifi(true)
                .build();

        Computador servidor = new Computador.Builder()
                .processador("AMD EPYC 7763")
                .memoriaRAM(128)
                .armazenamento(4000)
                .sistemaOperacional("Linux Ubuntu Server")
                .build();

        System.out.println("=== Computador Gamer ===");
        gamer.exibirConfiguracao();

        System.out.println("=== Computador Escritório ===");
        escritorio.exibirConfiguracao();

        System.out.println("=== Computador Servidor ===");
        servidor.exibirConfiguracao();

        // validacaow
        try {
            Computador invalido = new Computador.Builder()
                    .processador("")
                    .memoriaRAM(0)
                    .armazenamento(256)
                    .build();
        } catch (IllegalStateException e) {
            System.out.println("Erro ao criar computador inválido: " + e.getMessage());
        }
    }
}
