/**
 * Classe représentant un Monstre dans le Donjon.
 * Hérite d'ElementDonjon.
 */
public class Monstre extends ElementDonjon {
    private int force; // Force du monstre

    private int[] direction = {0, 0}; // Direction de déplacement par défaut

    /**
     * Constructeur pour initialiser un monstre.
     *
     * @param x La coordonnée X initiale
     * @param y La coordonnée Y initiale
     * @param direction La direction initiale de déplacement
     */
    public Monstre(int x, int y, int[] direction) {
        this.force = 15;
        setX(x);
        setY(y);
        this.direction = direction;
        skin = "(°o.o°)"; // Apparence visuelle du monstre
    }

    /**
     * Inverse la direction de déplacement du monstre.
     */
    public void inverserDirection() {
        direction[0] *= -1;
        direction[1] *= -1;
    }

    /**
     * Inflige des dégâts au joueur en cas de contact.
     *
     * @param joueur Le joueur touché par le monstre
     */
    public boolean touch(Joueur joueur) {
        if (!joueur.isAlive()) return false;
        joueur.takeDamage(force);
        return true;
    }
    /**
     * Représentation textuelle du monstre.
     *
     * @return Une chaîne de caractères décrivant le monstre
     */
    @Override
    public String toString() {
        return estActif()
            ? "Monstre (" + force + ") " + skin +
              " position: (" + getX() + ", " + getY() + "), direction: (" + direction[0] + ", " + direction[1] + ")"
            : "[Monstre éliminé]";
    }

    //getters
    public int[] getDirection()
    {
        return direction;
    }
}

