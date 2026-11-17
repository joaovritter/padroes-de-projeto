public abstract class Notificacao {
    protected final CanalNotificacao canal;

    protected Notificacao(CanalNotificacao canal) {
        this.canal = canal;
    }

    public abstract void enviar(String mensagem);
}
