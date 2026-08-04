public class Cachorro extends Animal {

    public Cachorro(String nome, int idade, double peso) {
        super(nome, idade, peso);
    }

    @Override
    public void emitirSom() {
        System.out.println(getNome() + ": Au au!");
    }

    @Override
    public void movimentar() {
        System.out.println(getNome() + " está correndo.");
    }
}
