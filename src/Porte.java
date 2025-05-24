public class Porte extends ElementDonjon {
    public enum TypePorte { ENTREE, SORTIE }

    private TypePorte type;
    private String skin;
    private boolean actif = true;

    public Porte(TypePorte type) {
        this.type = type;
        this.skin = type == TypePorte.ENTREE ? "[E]" : "[S]";
    }

    public TypePorte getType() {
        return type;
    }

    @Override
    public void despawn() {
        this.actif = false;
    }

    @Override
    public boolean estActif() {
        return actif;
    }

    @Override
    public String toString() {
        return estActif()
            ? "Porte " + (type == TypePorte.ENTREE ? "d'entrée" : "de sortie") + " " + skin
            : "[Porte désactivée]";
    }
}
