public class Pessoa {
    String nome;
    int idade;
    String mensagem;
    int repeticoes;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    
    public String trabalhar(){
        return "Trabalhando...";
    }

    public String falar(String mensagem){
        return "pode falar se quiser";
    }


    public String falar (String mensagem, int repeticoes){
        for (int i = 0; i < repeticoes; i++){
            System.out.println(mensagem);
        }
        return mensagem;
    }



    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    
};


