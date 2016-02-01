package HearthStats.model.factory.facade;

/**
 * Created by VuYeK on 03.11.2015.
 */
public class ArenaPart2 {

    int id_areny;
    String klasa;

    //Konstruktor z ID
    public ArenaPart2(int id_areny, String klasa) {
        this.id_areny = id_areny;
        this.klasa = klasa;
    }

    //Konstruktor bez ID
    public ArenaPart2(String klasa) {
        this.klasa = klasa;
    }

    /*GET*/
    public int getId_areny() {
        return id_areny;
    }
    public String getKlasa() {
        return klasa;
    }


    /*SET*/
    public void setId_areny(int id_areny) {
        this.id_areny = id_areny;
    }
    public void setKlasa(String klasa) {
        this.klasa = klasa;
    }

}
