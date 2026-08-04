import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Cachorro rex = new Cachorro("Rex", 3, 18);
        Gato mimi = new Gato("Mimi", 2, 4);
        Passaro piu = new Passaro("Piu", 1, 0.3);

        rex.alimentar("Ração");
        mimi.alimentar("Ração úmida", 100);
        piu.alimentar("Sementes", 10, "08:00");

        List<Animal> animais = new ArrayList<>();
        animais.add(rex);
        animais.add(mimi);
        animais.add(piu);

        System.out.println("----- Animais -----");
        for (Animal animal : animais) {
            System.out.println(animal.getNome() + " (" + animal.getIdade() + " anos, " + animal.getPeso() + "kg)");
            animal.emitirSom();
            animal.movimentar();
            System.out.println("------");
        }
    }
}
