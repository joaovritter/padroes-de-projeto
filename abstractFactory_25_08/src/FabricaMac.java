public class FabricaMac implements FabricaInterface {
    @Override
    public Botao criarBotao() {
        return new BotaoMac();
    }

    @Override
    public Menu criarMenu() {
        return new MenuMac();
    }

    @Override
    public Checkbox criarCheckbox() {
        return new CheckboxMac();
    }
}
