public abstract class ElementDonjon extends Entite {
    protected boolean actif = true;

    // Méthodes du composite par défaut (à override dans les composites si besoin)
    public void setMonstre(Monstre monstre) {
        throw new UnsupportedOperationException();
    }

    public void setTresor(Tresor tresor) {
        throw new UnsupportedOperationException();
    }

    public void setPiege(Piege piege) {
        throw new UnsupportedOperationException();
    }

    public void setPorte(Porte porte) {
        throw new UnsupportedOperationException();
    }

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

    // Gestion du despawn
    public void despawn() {
        actif = false;
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


/* faire en sorte que les objets despawn apres etre marcher dessus   */