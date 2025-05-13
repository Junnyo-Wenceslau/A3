public abstract class Carta {
    protected boolean virada;
    protected boolean encontrada;

    public Carta() {
        this.virada = false;
        this.encontrada = false;
    }

    public void virar() {
        this.virada = !this.virada;
    }

    public boolean isVirada() {
        return this.virada;
    }

    public boolean isEncontrada() {
        return this.encontrada;
    }

    public void setEncontrada(boolean encontrada) {
        this.encontrada = encontrada;
    }

    public abstract String getNome();
}