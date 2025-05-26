import javax.sound.sampled.Port;
import java.util.Random;

/**
 * Classe qui generera les salles et ce quelles contiennent.
 */
public class GenerateurSalle {

    //l'instance de Salle utilisée actuellement
    private static Salle salle;

    //Le generateur de nombres aleatoires
    private static Random rand = new Random();

    //retourne l'instance de la salle actuel.
    public static Salle getSalle()
    {
        if (salle == null)
            salle = genererSalle();
        return salle;
    }

    /**
     * genererSalle, genere une salle et les elements qui la compose aleatoirement.
     *
     * @return Salle
     */
    public static Salle genererSalle()
    {
        char[][] map = new char[30][60];
        GroupeDonjon racine = new GroupeDonjon();
        GroupeDonjon portes = new GroupeDonjon();
        GroupeDonjon tresors = new GroupeDonjon();
        GroupeDonjon monstres = new GroupeDonjon();
        GroupeDonjon pieges = new GroupeDonjon();

        racine.ajouter(portes);
        racine.ajouter(portes);
        racine.ajouter(tresors);
        racine.ajouter(monstres);
        racine.ajouter(pieges);

        int largeurSalle = rand.nextInt(31) + 30;
        int longueurSalle = rand.nextInt(16) + 15;

        int centrerX = (int) Math.floor((double) (30 - longueurSalle)/2);
        int centrerY = (int) Math.floor((double) (60 - largeurSalle)/2);

        //construction des murs
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 60; j++)
            {
                if ((i == centrerX && j > centrerY && j < centrerY + largeurSalle-1) || (j == centrerY && i > centrerX && i < centrerX + longueurSalle - 1) || (i == centrerX + longueurSalle - 1 && j > centrerY && j < centrerY + largeurSalle - 1) || (j == centrerY + largeurSalle-1 && i > centrerX && i < centrerX + longueurSalle - 1 ))
                    map[i][j] = '#';
                else map[i][j] = ' ';
            }
        }

        //initialisation des portes
        Porte entree = new Porte(Porte.TypePorte.ENTREE);
        Porte sortie = new Porte(Porte.TypePorte.SORTIE);

        portes.ajouter(entree);
        portes.ajouter(sortie);

        //calcule des coordonnées de l'entrée
        entree.setX(centrerX + longueurSalle - 1);
        entree.setY(rand.nextInt(largeurSalle - 6) + centrerY + 1);

        System.out.println(Integer.toString(entree.getX()));
        System.out.println(entree.getSkin());

        //calcule des coordonnées de la sortie
        sortie.setX(centrerX);
        sortie.setY(rand.nextInt(largeurSalle - 6) + centrerY + 1);

        System.out.println(Integer.toString(sortie.getX()));

        forcePlace(entree, map);
        forcePlace(sortie, map);

        //Placement du joueur.
        Joueur plr = Joueur.getInstance();
        plr.setX(entree.getX() - 1);
        plr.setY(entree.getY());

        forcePlace(plr, map);

        //Creation de l'objet Salle
        Salle laSalle = new Salle(racine, map);

        //generation des tresors (nombre aleatoire entre 0 et 2
        for (int i = 0; i < rand.nextInt(3); i++)
        {
            //valeur aleatoire
            Tresor tresor = new Tresor(rand.nextInt(3) + 1);

            //postionne le coffre
            positionner(tresor, tresors, laSalle, longueurSalle, largeurSalle, centrerX, centrerY);
        }

        //generation des monstres en fonction du score. 5 max.
        int n = (int) Math.round((double) plr.getScore()/5);
        if (n>5) n = 5;

        for (int i = 0; i < rand.nextInt(n + 1); i++)
        {
            //initialisation d'une direction aleatoire
            int[] direction = {0, 0};
            if (rand.nextInt(2) == 0)
                direction[rand.nextInt(2)] = 1;
            else direction[rand.nextInt(2)] = -1;

            //creation du monstre
            Monstre monstre = new Monstre(0, 0, direction);

            //postionne le monstre
            positionner(monstre, monstres, laSalle, longueurSalle, largeurSalle, centrerX, centrerY);
        }

        //generation des pieges en fonction du score. max 18.
        n = (int) Math.round((double) plr.getScore()/5);
        if (n>18) n = 18;

        for (int i = 0; i < rand.nextInt(n + 1); i++)
        {
            //creation du piege
            Piege piege = new Piege();

            //postionne le piege
            positionner(piege, pieges, laSalle, longueurSalle, largeurSalle, centrerX, centrerY);
        }

        return laSalle;
    }

    /**
     * forcePlace, force le placement d'une entite a une position sans se souciée de ce qui est deja present.
     *
     * @param elem
     * @param map
     */
    private static void forcePlace(Entite elem, char[][] map)
    {
        //TODO System.out.println(Integer.toString(elem.getX()));
        for (int i = 0; i < elem.getSkin().length(); i++)
        {
            map[elem.getX()][elem.getY() + i] = elem.getSkin().charAt(i);
        }
    }

    /**
     * essaye a 5 reprise de positionner un element dans le donjon aleatoirement. Si c'est un succes
     * alors l'element est ajouté a l'arborescence de GroupeDonjon.
     *
     * @param elem
     * @param composant
     * @param laSalle
     * @param longueurSalle
     * @param largeurSalle
     * @param centrerX
     * @param centrerY
     */
    private static void positionner(ElementDonjon elem, GroupeDonjon composant, Salle laSalle, int longueurSalle, int largeurSalle, int centrerX, int centrerY)
    {
        //declarations des variables de positionnement
        int x = 0;
        int y = 0;
        char nextPos = ' ';

        //4 essais de positionnement aleatoire
        for (int essai = 0; essai < 5; essai++)
        {
            x = rand.nextInt(longueurSalle - 1) + centrerX;
            y = rand.nextInt(largeurSalle - elem.getSkin().length()) + centrerY;

            //verification position libre
            for (int j = 0; j < elem.getSkin().length(); j++) {
                nextPos = laSalle.checkPositionLibre(x, y + j);
                if (nextPos != ' ') break;
            }
            if (nextPos == ' ') break; //sortie de la boucle si position libre
        }
        if (nextPos != ' ') //si au bout des 5 essais le positionnement a echoué : supprimer l'element.
            elem = null;
        else
        { //sinon le placer dans la salle.
            elem.setX(x);
            elem.setY(y);
            forcePlace(elem, laSalle.map);
            composant.ajouter(elem);
        }
    }

    /**
     * supprime l'instance de salle.
     */
    public static void destroySalle()
    {
        salle = null;
    }
}
