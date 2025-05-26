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
    public char[][] map;

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
     * @param x la coordonnée X;
     * @param y la coordonnée Y;
     * @param bloquant, le char a la position visée.
     */
    public void setPositionJoueur(int x, int y, char bloquant, ElementDonjon elem)
    {
        switch (bloquant)
        {
            case ' ' :
                plr.setX(x);
                plr.setY(y);

                //affiche sur la carte
                afficherEntite(plr);
                break;

            default:
                //active l'effet de l'element puis le detruit.
                if (elem != null && elem.touch(plr))
                {
                    destroyElement(elem);

                    plr.setX(x);
                    plr.setY(y);

                    //affiche sur la carte
                    afficherEntite(plr);

                    break;
                }

                //Permet d'empecher d'esquiver un monstres en restant sur place
                //verifie si il y'a un obstacle a la prochaine position
                for (int i = plr.getY(); i < plr.getY() + plr.getSkin().length(); i++ )
                {
                    bloquant = checkPositionLibre(plr.getX(), i);
                    if (bloquant != ' ' && bloquant != '#')
                    {//trouve l'element si la zone est occupé
                        elem = groupeElem.getElementFromPosition(plr.getX(), i);
                        break;
                    }
                }

                if (elem != null && elem.touch(plr))
                {
                    destroyElement(elem);
                }
                //affiche sur la carte
                afficherEntite(plr);
                break;
        }
    }


    /**
     * setPositionElem, place l'element entré en parametre au coordonnées passée en parametres.
     *
     * @param element;
     * @param x la coordonnée X;
     * @param y la coordonnée Y;
     */
    public void setPositionElem(ElementDonjon element, int x, int y, char bloquant)
    {
        switch (bloquant)
        {
            case ' ' :
                effacerEntite(element); //efface l'ancienne position

                element.setX(x);
                element.setY(y);

                //affiche sur la carte
                afficherEntite(element);
                break;

            default:
                //le faire partir dans l'autre sens si c'est un monstre (il y'a donc un temps d'arret si obstacle).
                if (element instanceof Monstre)
                    ((Monstre) element).inverserDirection();
                break;
        }
    }

    /**
     * deplacerJoueur, deplace de 1 la position du joueur.
     *
     * @params String z, q, s ou d en soit la direction prise.
     */
    public void deplacerJoueur(String choix)
    {
        char dir = choix.toCharArray()[0];
        char nextPosChar = ' ';
        ElementDonjon elem = null;

        effacerEntite(plr); //efface l'ancienne position du joueur

        deplacerTousMonstres(); // deplace les monstres avant le joueur. IMPORTANT dans la logique du code

        switch (dir)
        {
            case 'z' :
                //verifie si il y'a un obstacle a la prochaine position
                for (int i = plr.getY(); i < plr.getY() + plr.getSkin().length(); i++ )
                {
                    nextPosChar = checkPositionLibre(plr.getX() - 1, i);
                    if (nextPosChar != ' ' && nextPosChar != '#')
                    {//trouve l'element si la zone est occupé
                        elem = groupeElem.getElementFromPosition(plr.getX() - 1, i);
                        break;
                    }
                }

                //modifie officiellement la position
                setPositionJoueur(plr.getX() - 1, plr.getY(), nextPosChar, elem);
                break;

            case 'q' :
                //verifie si il y'a un obstacle a la prochaine position
                nextPosChar = checkPositionLibre(plr.getX(), plr.getY() - 1);

                //trouve l'element si la zone est occupée
                if (nextPosChar != ' ' && nextPosChar != '#')
                    elem = groupeElem.getElementFromPosition(plr.getX(), plr.getY() - 1);

                //modifie officiellement la position
                setPositionJoueur(plr.getX(), plr.getY() - 1, nextPosChar, elem);
                break;

            case 's' :
                //verifie si il y'a un obstacle a la prochaine position
                for (int i = plr.getY(); i < plr.getY() + plr.getSkin().length(); i++ )
                {
                    nextPosChar = checkPositionLibre(plr.getX() + 1, i);

                    //trouve l'element si la zone est occupée
                    if (nextPosChar != ' ' && nextPosChar != '#')
                    {
                        elem = groupeElem.getElementFromPosition(plr.getX() + 1, i);
                        break;
                    }
                }

                //modifie officiellement la position
                setPositionJoueur(plr.getX() + 1, plr.getY(), nextPosChar, elem);
                break;

            case 'd' :
                //verifie si il y'a un obstacle a la prochaine position
                nextPosChar = checkPositionLibre(plr.getX(), plr.getY() + plr.getSkin().length());

                //trouve l'element si la zone est occupée
                if (nextPosChar != ' ' && nextPosChar != '#')
                    elem = groupeElem.getElementFromPosition(plr.getX(), plr.getY() + plr.getSkin().length());

                //modifie officiellement la position
                setPositionJoueur(plr.getX(), plr.getY() + 1, nextPosChar, elem);
                break;

            default:
                //le joueur ne bouge pas :
                //verifie si il y'a un obstacle a la prochaine position
                for (int i = plr.getY(); i < plr.getY() + plr.getSkin().length(); i++ )
                {
                    nextPosChar = checkPositionLibre(plr.getX(), i);
                    if (nextPosChar != ' ' && nextPosChar != '#')
                    {//trouve l'element si la zone est occupé
                        elem = groupeElem.getElementFromPosition(plr.getX(), i);
                        break;
                    }
                }
                setPositionJoueur(plr.getX(), plr.getY(), nextPosChar, elem);
        }
    }

    /**
     * deplacerElem, deplace de 1 la position d'un element.
     * A EXECUTER AVANT deplacerJoueur.
     *
     * @param elem
     */
    private void deplacerElem(Monstre elem)
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
        else
        {
            if (direction[1] == 1)
                nextPosChar = checkPositionLibre(elem.getX(), elem.getY() + elem.getSkin().length()-1 + direction[1]);
            else
                nextPosChar = checkPositionLibre(elem.getX(), elem.getY() + direction[1]);
        }

        setPositionElem(elem, elem.getX() + direction[0], elem.getY() + direction[1], nextPosChar);
    }

    /**
     * deplace tous les monstres de la carte dans la direction qu'ils suivent
     */
    private void deplacerTousMonstres()
    {
        ElementDonjon grp = groupeElem.getGroupeFromInstance(Monstre.class);

        if (grp == null) return;

        for (ElementDonjon monstre : ((GroupeDonjon) grp).getChildren())
        {
            deplacerElem((Monstre) monstre);
        }
    }

    /**
     * checkPositionLibre, verifie si les positions sont libres dans la portée visée.
     *
     * @params int x;
     * @params int y;
     * @return ' ' si c'est vide, le charactere bloquant sinon.
     */
    public char checkPositionLibre(int x, int y)
    {
        return map[x][y];
    }

    /**
     * affiche sur la carte l'entite donnée
     *
     * @param entite
     */
    private void afficherEntite(Entite entite)
    {
        //affiche l'entite.
        for (int i = 0; i < entite.getSkin().length(); i++)
        {
            map[entite.getX()][entite.getY() + i] = entite.getSkin().charAt(i);
        }
    }

    /**
     * efface de la carte l'entite donnée.
     *
     * @param entite un joueur ou element
     */
    private void effacerEntite(Entite entite)
    {
        for (int i = 0; i < entite.getSkin().length(); i++)
        {
            map[entite.getX()][entite.getY() + i] = ' ';
        }
    }

    /**
     * destroyElement, efface un element de la carte, detruit l'instance de cet element.
     *
     * @param elem element a detruire
     */
    private void destroyElement(ElementDonjon elem)
    {
        effacerEntite(elem);
        groupeElem.retirer(elem);
        elem = null;
    }
}