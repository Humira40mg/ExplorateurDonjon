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
        pv = 60;
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
     * detruit l'instance du joueur.
     */
    public static void destroy()
    {
        instance = null;
    }

    /**
     * Fait perdre de la vie au joueur (il en a 60 de base).
     *
     * @param damage (le nombre de degat prit)
     * @return true si le joueur n'a plus de points de vie.
     */
    public boolean takeDamage(int damage)
    {
        pv -= damage;
        if (pv < 0) pv = 0;
        return pv == 0;
    }

    /**
     * heal, soigne le joueur d'un certain nombre de pv.
     *
     * @param vie
     */
    public void heal(int vie)
    {
        pv+=vie;
        if (pv > 60) pv = 60;
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
     *
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