public abstract class BebidaDecorator implements Bebida {

    protected Bebida bebida;

    public BebidaDecorator(Bebida bebida) {
        this.bebida = bebida;
    }

    @Override
    public String getDescricao() {
        return bebida.getDescricao();
    }

    @Override
    public double getPreco() {
        return bebida.getPreco();
    }
}
