import java.util.List;

/**
 * Classe qui gere les deplacement et interaction entre le joueur et les elements
 */
public class Salle {
    //Instance de joueur
    private Joueur plr;

    //liste des elements du donjon
    private GroupeDonjon groupeElem;

    //la matrice de la salle.
    private char[][] map;

    //Constructeur de la classe
    public Salle(GroupeDonjon elem, char[][] matrice)
    {
        groupeElem = elem;
        map = matrice;
        plr = Joueur.getInstance();
    }

    /**
     * afficherSalle, affiche la salle dans le terminal.
     */
    public void afficherSalle()
    {
        for (int x = 0; x < map.length; x++)
        {
            for (int y = 0; y < map[x].length; y++)
            {
                System.out.print(map[x][y]);
            }
            System.out.print("\n");
        }
    }

    /**
     * setPositionJoueur, place le joueur au coordonnées passée en parametres.
     *
     * @params int x la coordonnée X;
     * @params int y la coordonnée Y;
     * @params char bloquant, le char a la position visée.
     * @return bool true si le placement est un succes false sinon.
     */
    private boolean setPositionJoueur(int x, int y, char bloquant)
    {
        switch (bloquant)
        {
            case ' ' :
                //deplace le joueur.
                for (int i = 0; i < plr.getSkin().length(); i++)
                {
                    map[x][y + i] = plr.getSkin().charAt(i);
                }
                plr.setX(x);
                plr.setY(y);
                return true;

            case '#' :
                return false;

            default:
                //trouver de quel element il s'agit.
                ElementDonjon elem = groupeElem.getElementFromPosition(x, y);

                //active l'effet de l'element puis le detruit.
                if (elem != null && elem.touch(plr))
                {
                    destroyElement(elem);

                    //deplace le joueur
                    for (int i = 0; i < plr.getSkin().length(); i++)
                    {
                        map[x][y + i] = plr.getSkin().charAt(i);
                    }
                    plr.setX(x);
                    plr.setY(y);
                    return true;
                }
                return false;
        }
    }


    /**
     * setPositionElem, place l'element entré en parametre au coordonnées passée en parametres.
     *
     * @params ElementDonjon element;
     * @params int x la coordonnée X;
     * @params int y la coordonnée Y;
     * @return true si le placement est un succes false sinon.
     */
    private boolean setPositionElem(ElementDonjon element, int x, int y, char bloquant)
    {
        switch (bloquant)
        {
            case ' ' :
                //deplace l'element.
                for (int i = 0; i < element.getSkin().length(); i++)
                {
                    map[x][y + i] = element.getSkin().charAt(i);
                }
                return true;

            case '#' :
                //le faire partir dans l'autre sens si c'est un monstre (il y'a donc un temps d'arret si obstacle).
                if (element instanceof Monstre)
                    ((Monstre) element).inverserDirection();
                return false;


            default:
                //verifier si c'est le joueur
                if (plr.isAt(x, y))
                {
                    //deplace l'element.
                    for (int i = 0; i < element.getSkin().length(); i++)
                    {
                        map[x][y + i] = element.getSkin().charAt(i);
                    }
                    element.setX(x);
                    element.setY(y);
                    return true;
                }

                //le faire partir dans l'autre sens si c'est un monstre (il y'a donc un temps d'arret si obstacle).
                if (element instanceof Monstre)
                    ((Monstre) element).inverserDirection();
                return false;
        }
    }

    /**
     * deplacerJoueur, deplace de 1 la position du joueur.
     *
     * @params String z, q, s ou d en soit la direction prise.
     * @return bool true si le deplacement est un succes false sinon.
     */
    public boolean deplacerJoueur(String choix)
    {
        char dir = choix.toCharArray()[0];
        char nextPosChar = ' ';

        switch (dir)
        {
            case 'z' :
                //verifie si il y'a un obstacle a la prochaine position
                for (int i = plr.getY(); i < plr.getY() + plr.getSkin().length(); i++ )
                {
                    nextPosChar = checkPositionLibre(plr.getX() - 1, i);
                    if (nextPosChar != ' ') break;
                }

                //modifie officiellement la position
                return setPositionJoueur(plr.getX() - 1, plr.getY(), nextPosChar);

            case 'q' :
                //verifie si il y'a un obstacle a la prochaine position
                nextPosChar = checkPositionLibre(plr.getX(), plr.getY() - 1);

                //modifie officiellement la position
                return setPositionJoueur(plr.getX(), plr.getY() - 1, nextPosChar);

            case 's' :
                //verifie si il y'a un obstacle a la prochaine position
                for (int i = plr.getY(); i < plr.getY() + plr.getSkin().length(); i++ )
                {
                    nextPosChar = checkPositionLibre(plr.getX() + 1, i);
                    if (nextPosChar != ' ') break;
                }

                //modifie officiellement la position
                return setPositionJoueur(plr.getX() + 1, plr.getY(), nextPosChar);

            case 'd' :
                //verifie si il y'a un obstacle a la prochaine position
                nextPosChar = checkPositionLibre(plr.getX(), plr.getY() + 1);

                //modifie officiellement la position
                return setPositionJoueur(plr.getX(), plr.getY() + 1, nextPosChar);

            default:
                //le joueur ne bouge pas :
                nextPosChar = checkPositionLibre(plr.getX(), plr.getY());
                return setPositionJoueur(plr.getX(), plr.getY(), nextPosChar);
        }
    }

    /**
     * deplacerElem, deplace de 1 la position d'un element.
     * A EXECUTER AVANT deplacerJoueur.
     *
     * @params Monstre
     * @return bool true si le deplacement est un succes false sinon.
     */
    public boolean deplacerElem(Monstre elem)
    {
        int[] direction = elem.getDirection();
        char nextPosChar = ' ';

        //verifie si il y'a un obstacle a la prochaine position
        if (direction[0] != 0)
        {
            for (int i = elem.getY(); i < elem.getY() + elem.getSkin().length(); i++) {
                nextPosChar = checkPositionLibre(elem.getX() + direction[0], i);
                if (nextPosChar != ' ') break;
            }
        }
        else nextPosChar = checkPositionLibre(elem.getX(), elem.getY() + direction[1]);

        return setPositionElem(elem, elem.getX() + direction[0], elem.getY() + direction[1], nextPosChar);
    }

    /**
     * checkPositionLibre, verifie si les positions sont libres dans la portée visée.
     *
     * @params int x;
     * @params int y;
     * @return ' ' si c'est vide, le charactere bloquant sinon.
     */
    private char checkPositionLibre(int x, int y)
    {
        return map[x][y];
    }

    /**
     * destroyElement, efface un element de la carte, detruit l'instance de cet element.
     *
     * @params ElementDonjon
     */
    private void destroyElement(ElementDonjon elem)
    {
        for (int i = elem.getY(); i < elem.getSkin().length(); i++)
        {
            map[elem.getX()][i] = ' ';
        }
        groupeElem.retirer(elem);
    }
}