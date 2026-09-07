public class CarameloDecorator extends BebidaDecorator {

    public CarameloDecorator(Bebida bebida) {
        super(bebida);
    }

    @Override
    public String getDescricao() {
        return bebida.getDescricao() + " + Caramelo";
    }

    @Override
    public double getPreco() {
        return bebida.getPreco() + 1.50;
    }
}
