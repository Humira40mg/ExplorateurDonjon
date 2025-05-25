/**
 * Classe mere originelle avec le systeme de coordonées necessaire a tout ce qui integrera le donjon.
 */
abstract class Entite
{
    protected static int x; //coordonée x
    protected static int y; //coordonée y
    protected static String skin; //apparence visuelle de l'entitée

    /**
     * isAt verifie que l'entitée est dans la portée de la position indiquée
     *
     * @params int co_x
     * @params int co_y
     * @return true si l'entitée est a la position indiquée. sinon false
     */
    public boolean isAt(int co_x, int co_y)
    {
        for (int i = 0; i < skin.length(); i++)
        {
            if (x == co_x && y+i == co_y) return true;
        }
        return false;
    }

    //setters
    public void setX(int i)
    {
        x = i;
    }

    public void setY(int i)
    {
        y = i;
    }

    //getters
    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public String getSkin()
    {
        return skin;
    }
}