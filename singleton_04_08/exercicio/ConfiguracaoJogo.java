public class ConfiguracaoJogo {

    private static ConfiguracaoJogo instance;

    private int volume;
    private String idioma;

    private ConfiguracaoJogo() {
        volume = 50;
        idioma = "PT-BR";
    }

    public static ConfiguracaoJogo getInstance() {
        if (instance == null) {
            instance = new ConfiguracaoJogo();
        }
        return instance;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
