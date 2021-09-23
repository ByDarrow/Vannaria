package cat.copernic.m03.uf4.p01.heroesofvannaria;

/**
 *
 * @author ByDarrow
 */

public class Dau {

    private int cares;

    public Dau(int cares) {
        this.cares = cares;
    }

    public int tirada() {
        return (int)(Math.random()*cares+1);
    }
}
