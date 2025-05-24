/**
 * Classe représentant un piège dans le donjon.
 * 
 * @author NGUYEN Alexandre et Nathan Milllot
 *
 */
public class Piege extends ElementDonjon {
    /**
     * Type du piège (ex: "flèches", "arbalète", etc.).
     */
    private String type;
    /**
     * Nombre de dégâts infligés par le piège.
     */
    private int degats;
    /**
     * Apparence visuelle du piège.
     */
    private String skin = "(X)";

    /**
     * Constructeur.
     * 
     * @param type Type du piège.
     * @param degats Nombre de dégâts infligés.
     */
    public Piege(String type, int degats) {
        this.type = type;
        this.degats = degats;
    }

    /**
     * Retourne le type du piège.
     * 
     * @return Type du piège.
     */
    public String getType() {
        return type;
    }

    /**
     * Retourne le nombre de dégâts infligés par le piège.
     * 
     * @return Nombre de dégâts infligés.
     */
    public int getDegats() {
        return degats;
    }

    /**
     * Méthode qui inflige les dégâts du piège au joueur.
     * 
     * @param joueur Joueur qui entre en contact avec le piège.
     */
    public void touch(Joueur joueur) {
        if (joueur.isAlive())return; 
        joueur.takeDamage(degats);
    }

    /**
     * Retourne une chaîne de caractère représentant le piège.
     * 
     * @return Chaîne de caractère représentant le piège.
     */
    @Override
    public String toString() {
        return estActif()
            ? "Piège: " + type + " (Dégâts: " + degats + ") " + skin
            : "[Piège désactivé]";
    }
}

