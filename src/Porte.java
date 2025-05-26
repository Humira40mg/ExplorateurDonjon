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
     * Constructeur.
     * 
     * @param type Type de la porte.
     */
    public Porte(TypePorte type) {
        this.type = type;
        skin = type == TypePorte.ENTREE ? "[===]" : "[---]";
    }

    /**
     * Interaction avec le joueur.
     * 
     * @param joueur
     *            Le joueur qui interagit.
     * @return Si la porte est toujours active.
     */
    public boolean touch(Joueur joueur)
    {
        if (type == TypePorte.SORTIE) {
            // soigne le joueur et supprime l'instance de la salle
            joueur.heal(3);
            GenerateurSalle.destroySalle();
        }
        return false;
    }
}

