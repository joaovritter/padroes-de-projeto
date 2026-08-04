public class Gato extends Animal {

    public Gato(String nome, int idade, double peso) {
        super(nome, idade, peso);
    }

    @Override
    public void emitirSom() {
        System.out.println(getNome() + ": Miau!");
    }

    @Override
    public void movimentar() {
        System.out.println(getNome() + " está andando silenciosamente.");
    }
}
