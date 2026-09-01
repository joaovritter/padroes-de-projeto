public class FabricaWindows implements FabricaInterface {
    @Override
    public Botao criarBotao() {
        return new BotaoWindows();
    }

    @Override
    public Menu criarMenu() {
        return new MenuWindows();
    }

    @Override
    public Checkbox criarCheckbox() {
        return new CheckboxWindows();
    }
}
