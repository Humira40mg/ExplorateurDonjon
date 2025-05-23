/**
 * Classe singleton representant le joueur.
 */
public class Joueur
{
    private static Joueur instance;
    private int pv;
    private int score;
    public String skin = "('u')"; //apparence visuelle du joueur

    public Joueur()
    {
        pv = 20;
        score = 0;
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
     * @param damage (le nombre de degat prit)
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
     * @param x un entier positif
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