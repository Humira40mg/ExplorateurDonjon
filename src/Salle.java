import java.util.List;

public class Salle {
    //Instance de joueur
    private Joueur plr;

    //liste des elements du donjon
    private List<ElementDonjon> elements;

    //la matrice de la salle.
    private char[][] map;

    //Constructeur de la classe
    public Salle(List<ElementDonjon> elem, char[][] matrice)
    {
        elements = elem;
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
     * initPositionJoueur, place le joueur au coordonnées passée en parametres.
     *
     * @params int x la coordonnée X;
     * @params int y la coordonnée Y;
     * @params char bloquant, le char a la position visée.
     * @return bool true si le placement est un succes false sinon.
     */
    private boolean initPositionJoueur(int x, int y, char bloquant)
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
                ElementDonjon elem = null;
                for (int i = 0; i < elements.size(); i++)
                {
                    if (elements.get(i).isAt(x, y))
                    {
                        elem = elements.get(i);
                        break;
                    }
                }

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
     * initPositionElem, place l'element entré en parametre au coordonnées passée en parametres.
     *
     * @params ElementDonjon element;
     * @params int x la coordonnée X;
     * @params int y la coordonnée Y;
     * @return true si le placement est un succes false sinon.
     */
    private boolean initPositionElem(ElementDonjon element, int x, int y, char bloquant)
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
                //le faire partir dans l'autre sens (il y'a donc un temps d'arret si obstacle).
                element.inverserDirection();
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

                //le faire partir dans l'autre sens (il y'a donc un temps d'arret si obstacle).
                element.inverserDirection();
                return false;
        }
    }

    /**
     * deplacerJoueur, deplace de 1 la position du joueur.
     *
     * @params String z, q, s ou d en soit la direction prise.
     * @return bool true si le deplacement est un succes false sinon.
     */

    /**
     * deplacerElem, deplace de 1 la position d'un element.
     *
     * @params ElementDonjon
     * @params int X : 1 ou -1 ou 0.
     * @params int Y : 1 ou -1 ou 0.
     * @return bool true si le deplacement est un succes false sinon.
     */

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
        elem.despawn();
    }
}