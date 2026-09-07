public class ChantillyDecorator extends BebidaDecorator {

    public ChantillyDecorator(Bebida bebida) {
        super(bebida);
    }

    @Override
    public String getDescricao() {
        return bebida.getDescricao() + " + Chantilly";
    }

    @Override
    public double getPreco() {
        return bebida.getPreco() + 2.50;
    }
}
