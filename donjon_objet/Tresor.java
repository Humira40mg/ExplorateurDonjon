public class Tresor
{
    private int valeur_score;
    private String skin = "[-o-]";

    public Tresor(int valeur_score) {
        this.valeur_score = valeur_score;
    }

    public int getValeur_score() {
        return valeur_score;
    }

    @Override
    public String toString() {
        return "( Trésor avec Valeur: " + valeur_score + ") " + skin;
    }

}

