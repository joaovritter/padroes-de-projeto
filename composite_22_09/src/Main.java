public class Main {
    public static void main(String[] args) {
        Edificio edificio = new Edificio("Residencial Franciscana");

        Apartamento apto101 = new Apartamento("101");
        apto101.adicionar(new Morador("João Ritter", 21));
        apto101.adicionar(new Morador("Maria Souza", 45));

        Apartamento apto102 = new Apartamento("102");
        apto102.adicionar(new Morador("Carlos Lima", 33));

        Apartamento apto201 = new Apartamento("201");
        apto201.adicionar(new Morador("Ana Paula", 27));
        apto201.adicionar(new Morador("Pedro Alves", 29));
        apto201.adicionar(new Morador("Beatriz Rocha", 5));

        edificio.adicionar(apto101);
        edificio.adicionar(apto102);
        edificio.adicionar(apto201);

        edificio.exibirDetalhes("");
    }
}
