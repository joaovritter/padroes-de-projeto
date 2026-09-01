public class Main {

    public static void main(String[] args) {

        System.out.println("=== Windows ===");
        FabricaInterface fabricaWindows = new FabricaWindows();
        Aplicacao appWindows = new Aplicacao(fabricaWindows);
        appWindows.executar();

        System.out.println("=== Linux ===");
        FabricaInterface fabricaLinux = new FabricaLinux();
        Aplicacao appLinux = new Aplicacao(fabricaLinux);
        appLinux.executar();

        System.out.println("=== Mac ===");
        FabricaInterface fabricaMac = new FabricaMac();
        Aplicacao appMac = new Aplicacao(fabricaMac);
        appMac.executar();
    }
}
