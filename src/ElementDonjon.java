public abstract class ElementDonjon extends Entite {
    protected boolean actif = true;

    public ElementDonjon getElementFromPosition(int posx, int posy) {
        if (this.isAt(posx, posy)) return this;
        return null;
    }

    // recupere le composant parent d'un type d'instance.
    public ElementDonjon getGroupeFromInstance(Class<?> type)
    {
        if (type.isInstance(this)) return this;
        return null;
    }

    public boolean estActif() {
        return actif;
    }

    public boolean touch(Joueur joueur) {
        throw new UnsupportedOperationException();
    }

    @Override
    public abstract String toString();
}