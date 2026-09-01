public class FabricaLinux implements FabricaInterface {
    @Override
    public Botao criarBotao() {
        return new BotaoLinux();
    }

    @Override
    public Menu criarMenu() {
        return new MenuLinux();
    }

    @Override
    public Checkbox criarCheckbox() {
        return new CheckboxLinux();
    }
}
