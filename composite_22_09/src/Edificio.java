import java.util.ArrayList;
import java.util.List;

public class Edificio implements ComponenteEdificio {

    private final String nome;
    private final List<ComponenteEdificio> apartamentos = new ArrayList<>();

    public Edificio(String nome) {
        this.nome = nome;
    }

    public void adicionar(ComponenteEdificio apartamento) {
        apartamentos.add(apartamento);
    }

    public void remover(ComponenteEdificio apartamento) {
        apartamentos.remove(apartamento);
    }

    @Override
    public void exibirDetalhes(String prefixo) {
        System.out.println(prefixo + "Edifício " + nome + ":");
        for (ComponenteEdificio apartamento : apartamentos) {
            apartamento.exibirDetalhes(prefixo + "  ");
        }
    }
}
