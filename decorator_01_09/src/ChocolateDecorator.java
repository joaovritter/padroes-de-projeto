public class ChocolateDecorator extends BebidaDecorator {

    public ChocolateDecorator(Bebida bebida) {
        super(bebida);
    }

    @Override
    public String getDescricao() {
        return bebida.getDescricao() + " + Chocolate";
    }

    @Override
    public double getPreco() {
        return bebida.getPreco() + 3.00;
    }
}
