public class Main {
    public static void main(String[] args) {
        ConfiguracaoJogo instancia1 = ConfiguracaoJogo.getInstance();
        instancia1.setVolume(10);

        ConfiguracaoJogo instancia2 = ConfiguracaoJogo.getInstance();
        System.out.println("Volume: " + instancia2.getVolume());

        System.out.println("instancia1 == instancia2: " + (instancia1 == instancia2));
    }
}
