/**
 * Classe abstraite représentant un élément du donjon.
 * Elle contient des méthodes qui permettent de positionner l'élément sur le donjon, de le déplacer, de le supprimer...
 * Elle est abstraite car les éléments du donjon sont très différents (pièges, portes, créatures, etc.) et qu'il est donc impossible de définir une classe qui les représente toutes.
 * @author NGUYEN Alexandre et  MILLOT Nathan
 */
public abstract class ElementDonjon extends Entite {

    /**
     * Méthode qui permet de récupérer l'élément qui est à une certaine position.
     * @param posx la position x de l'élément que l'on cherche.
     * @param posy la position y de l'élément que l'on cherche.
     * @return l'élément qui est à la position (posx, posy) ou null si il n'y a pas d'élément.
     */
    public ElementDonjon getElementFromPosition(int posx, int posy) {
        if (this.isAt(posx, posy)) return this;
        return null;
    }

    /**
     * Méthode qui permet de récupérer le composant parent d'un type d'instance.
     * @param type le type d'instance que l'on cherche.
     * @return le composant parent qui est de type type ou null si il n'y a pas de composant parent de ce type.
     */
    public ElementDonjon getGroupeFromInstance(Class<?> type)
    {
        if (type.isInstance(this)) return this;
        return null;
    }

    /**
     * Méthode qui permet de reagire et activer les effets voulu si l'élément est touché par un joueur.
     * @param joueur le joueur en contact avec l'élément.
     * @return true si l'element doit disparaitre
     */
    public boolean touch(Joueur joueur)
    {
        return false;
    }
}
