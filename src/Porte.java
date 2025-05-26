/**
 * Classe représentant une Porte dans le Donjon.
 *
 * @author NGUYEN Alexandre et Nathan Milllot
 */
public class Porte extends ElementDonjon {
    /**
     * Type de porte.
     */
    public enum TypePorte {
        /**
         * Porte d'entrée.
         */
        ENTREE,
        /**
         * Porte de sortie.
         */
        SORTIE
    }

    /**
     * Type de la porte.
     */
    private TypePorte type;

    /**
     * Indique si la porte est active ou non.
     */
    private boolean actif = true;

    /**
     * Constructeur.
     * 
     * @param type Type de la porte.
     */
    public Porte(TypePorte type) {
        this.type = type;
        skin = type == TypePorte.ENTREE ? "[===]" : "[---]";
    }

    /**
     * Retourne le type de la porte.
     * 
     * @return Le type de la porte.
     */
    public TypePorte getType() {
        return type;
    }

    /**
     * Retourne si la porte est active.
     * 
     * @return Si la porte est active.
     */
    @Override
    public boolean estActif() {
        return actif;
    }
    /**
     * Interaction avec le joueur.
     * 
     * @param joueur
     *            Le joueur qui interagit.
     * @return Si la porte est toujours active.
     */
    public boolean touch(Joueur joueur) {
        if (!estActif()) return false;

        if (type == TypePorte.SORTIE) {
            // soigne le joueur et supprime l'instance de la salle
            joueur.heal(3);
            GenerateurSalle.destroySalle();
        }
        return false;
    }

    /**
     * Retourne une chaîne de caractère représentant la porte.
     * 
     * @return Une chaîne de caractère représentant la porte.
     */
    @Override
    public String toString() {
        return estActif()
            ? "Porte " + (type == TypePorte.ENTREE ? "d'entrée" : "de sortie") + " " + skin
            : "[Porte désactivée]";
    }
}

