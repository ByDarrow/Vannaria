package cat.copernic.m03.uf4.p01.heroesofvannaria;

/**
 *
 * @author ByDarrow
 */
public interface Caos {

    /*
    *Interface amb un únic mètode, que rep uns daus i prova de realitzar un atac
    *amb una PA reduïda en un 50%. Retorna true o false en funció de si l’atac té o no èxit.
    */

    public boolean contraAtaca(Dau ... daus);
}
