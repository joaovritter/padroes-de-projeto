public class Morador implements ComponenteEdificio {

    private final String nome;
    private final int idade;

    public Morador(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    @Override
    public void exibirDetalhes(String prefixo) {
        System.out.println(prefixo + "Morador: " + nome + " (" + idade + " anos)");
    }
}
