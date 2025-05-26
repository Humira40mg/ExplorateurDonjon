/**
 * Représente un trésor qui peut être ramassé par le joueur.
 * 
 * @author NGUYEN Alexandre et MILLOT Nathan
 */
public class Tresor extends ElementDonjon {
    /**
     * Valeur du trésor en terme de score.
     */
    private int valeur_score;

    /**
     * Constructeur initialisant le trésor avec une valeur de score.
     * 
     * @param valeur_score La valeur du trésor en terme de score.
     */
    public Tresor(int valeur_score) {
        this.valeur_score = valeur_score;
        skin = "[-o-]";
    }
    
    /**
     * Lorsque le joueur touche le trésor, il ramasse le trésor et gagne le score correspondant.
     * 
     * @param joueur Le joueur qui a touché le trésor.
     */
    @Override
    public boolean touch(Joueur joueur) {
        if (!joueur.isAlive()) return false;
        joueur.incrementScore(valeur_score);
        return true;
    }
}
