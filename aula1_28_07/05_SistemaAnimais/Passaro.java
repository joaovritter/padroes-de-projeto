public class Passaro extends Animal {

    public Passaro(String nome, int idade, double peso) {
        super(nome, idade, peso);
    }

    @Override
    public void emitirSom() {
        System.out.println(getNome() + ": Piu piu!");
    }

    @Override
    public void movimentar() {
        System.out.println(getNome() + " está voando.");
    }
}
