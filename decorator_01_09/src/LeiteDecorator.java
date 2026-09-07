public class LeiteDecorator extends BebidaDecorator {

    public LeiteDecorator(Bebida bebida) {
        super(bebida);
    }

    @Override
    public String getDescricao() {
        return bebida.getDescricao() + " + Leite";
    }

    @Override
    public double getPreco() {
        return bebida.getPreco() + 2.00;
    }
}
