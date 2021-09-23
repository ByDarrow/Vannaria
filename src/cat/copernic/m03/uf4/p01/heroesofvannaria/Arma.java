package cat.copernic.m03.uf4.p01.heroesofvannaria;

/**
 *
 * @author ByDarrow
 */
public class Arma {

    private int wpow;   // Poder de l'arma
    private int wvel;   // Velocitat de l'arma

    private String nom; // Daga, Espasa, Martell

    public Arma() {}

    public Arma(int wpow,int wvel,String nom) {
        this.wpow = wpow;
        this.wvel = wvel;
        this.nom  = nom;
    }


    public int getWPow() {
        return this.wpow;
    }

    public void setWPow(int wpow) {
        this.wpow = wpow;
    }

    public int getWVel() {
        return this.wvel;
    }

    public void setWVel(int wvel) {
        this.wvel = wvel;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}