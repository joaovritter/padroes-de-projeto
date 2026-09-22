import java.util.ArrayList;
import java.util.List;

public class Apartamento implements ComponenteEdificio {

    private final String numero;
    private final List<ComponenteEdificio> moradores = new ArrayList<>();

    public Apartamento(String numero) {
        this.numero = numero;
    }

    public void adicionar(ComponenteEdificio morador) {
        moradores.add(morador);
    }

    public void remover(ComponenteEdificio morador) {
        moradores.remove(morador);
    }

    @Override
    public void exibirDetalhes(String prefixo) {
        System.out.println(prefixo + "Apartamento " + numero + ":");
        for (ComponenteEdificio morador : moradores) {
            morador.exibirDetalhes(prefixo + "  ");
        }
    }
}
