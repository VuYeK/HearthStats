package HearthStats.model.factory.facade;

/**
 * Created by VuYeK on 03.11.2015.
 */
public class ArenaPart1 {

    int id_areny, wygrane;

    //Konstruktor z ID
    public ArenaPart1(int id_areny, int wygrane) {
        this.id_areny = id_areny;
        this.wygrane = wygrane;
    }

    //Konstruktor bez ID
    public ArenaPart1(int wygrane) {
        this.wygrane = wygrane;
    }

    /*GET*/
    public int getId_areny() {
        return id_areny;
    }
    public int getWygrane(){return wygrane;}


    /*SET*/
    public void setId_areny(int id_areny) {
        this.id_areny = id_areny;
    }
    public void setWygrane(int wygrane) {
        this.wygrane = wygrane;
    }

}
