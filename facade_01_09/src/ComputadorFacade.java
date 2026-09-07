public class ComputadorFacade {

    private final Fonte fonte;
    private final Processador processador;
    private final Memoria memoria;
    private final Disco disco;
    private final SistemaOperacional sistemaOperacional;

    public ComputadorFacade() {
        this.fonte = new Fonte();
        this.processador = new Processador();
        this.memoria = new Memoria();
        this.disco = new Disco();
        this.sistemaOperacional = new SistemaOperacional();
    }

    public void ligarComputador() {
        fonte.ligar();
        processador.iniciar();
        memoria.carregar();
        disco.iniciar();
        sistemaOperacional.carregar();
        System.out.println("Computador pronto para uso.");
    }

    public void desligarComputador() {
        sistemaOperacional.encerrar();
        disco.parar();
        memoria.liberar();
        processador.parar();
        fonte.desligar();
        System.out.println("Computador desligado.");
    }
}
