import java.util.Scanner;

/**
 * Classe qui s'occupera d'afficher le necessaire dans le terminal
 */
public class View
{
    private static Scanner reader = new Scanner(System.in);
    /**
     * Efface ce qui est affiché sur le terminal
     */
    public static void clearScreen()
    {
        //imitation d'un clear car autres methodes non fonctionnel partout.
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * Affiche le menu principale
     */
    public static void menuPrincipal()
    {
        clearScreen();

        System.out.println(">---------------<| ExploDon |>---------------<\n");

        //TODO: afficher le meilleurs score

        System.out.println("  <| Appuyez sur 'Entrer' pour commencer  |>\n");
        System.out.println("     Entrez une autre touche pour quitter\n");
        System.out.println(">--------------------------------------------<");

        String choix = reader.nextLine();

        if (choix.equals("")) startGame();
    }

    /**
     * Methode qui affiche le jeu dans une boucle while, correlant les input utilisateurs
     * avec les element et la carte.
     */
    private static void startGame()
    {
        Joueur player = Joueur.getInstance();
        String lastinput = "d";
        String input;

        while (player.isAlive())
        {
            //TODO
            clearScreen();

            //Afficher la carte

            //Afficher les info joueur.
            afficherInfoJoueur(player);

            //recuperer le prochain input
            input = reader.nextLine();

            if (input.equals("")) input = lastinput;

            //sortie controlée de la partie.
            else if (input.equals("exit"))
            {
                gameOver(player);
                break;
            }

            //deplacer le joueur.
        }

        //mort du joueur, retour au menu principal.
        menuPrincipal();
    }

    /**
     * Affiche les infos concernant le joueur.
     * @param Joueur
     */
    private static void afficherInfoJoueur(Joueur plr)
    {
        System.out.println(">-------------------------------------------------<");
        System.out.println("Score : " + Integer.toString(plr.getScore()));
        System.out.println("PV : [" + "#".repeat(plr.getPv()) + "-".repeat(20-plr.getPv()) + "]");
        System.out.println("zqsd pour choisir une direction (juste 'entrer' pour repeter l'input precedent) ou taper exit pour quitter.");
    }

    /**
     * methode qui execute le necessaire a la fin d'une partie.
     * (Enregistrement du meilleur score ou suppression du singleton du joueur par exemple).
     * @param Joueur
     */
    private static void gameOver(Joueur plr)
    {
        //enregistrer le meilleur score.

        //suppression de l'instance du singleton.
        plr = null;
    }
}