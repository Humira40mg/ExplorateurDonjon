/**
 * Classe singleton representant le joueur (sous classe de entite)
 */
public class Joueur extends Entite
{
    private static Joueur instance;
    private int pv;
    private int score;

    private Joueur()
    {
        pv = 20;
        score = 0;
        skin = "('u')"; //apparence visuelle du joueur
    }

    /**
     * @return L'instance unique du Joueur
     */
    public static Joueur getInstance()
    {
        if (instance == null)
        {
            instance = new Joueur();
        }
        return instance;
    }

    /**
     * Fait perdre de la vie au joueur (il en a 20 de base).
     *
     * @params damage (le nombre de degat prit)
     * @return true si le joueur n'a plus de points de vie.
     */
    public boolean takeDamage(int damage)
    {
        pv -= damage;
        if (pv <= 0) return true;
        return false;
    }

    /**
     * incremente de x le score du joueur
     *
     * @params x un entier positif
     */
    public void incrementScore(int x)
    {
        score += x;
    }

    /**
     * Verifie si le joueur est en vie
     * @return true si le joueur est en vie
     */
    public boolean isAlive()
    {
        return pv > 0;
    }

    //getters
    public int getPv()
    {
        return pv;
    }

    public int getScore()
    {
        return score;
    }
}