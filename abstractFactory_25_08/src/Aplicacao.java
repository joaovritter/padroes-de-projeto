public class Aplicacao {

    private final Botao botao;
    private final Menu menu;
    private final Checkbox checkbox;

    public Aplicacao(FabricaInterface fabrica) {
        this.botao = fabrica.criarBotao();
        this.menu = fabrica.criarMenu();
        this.checkbox = fabrica.criarCheckbox();
    }

    public void executar() {
        botao.exibir();
        menu.exibir();
        checkbox.exibir();
    }
}
