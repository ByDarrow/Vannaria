package cat.copernic.m03.uf4.p01.heroesofvannaria;

/**
 *
 * @author ByDarrow
 */
public class Cavaller extends Personatge implements Ordre, Caos {

    public Cavaller(String nom, String classe, int str, int con, int vel,
                    int iq, int sor, Arma arma) {
        super(nom, classe, str, con, vel, iq, sor, arma);
    }

    public Cavaller(String nom, String classe, int str, int con, int vel,
                    int iq, int sor, Arma arma,int niv,int pex){
        super(nom,classe,str,con,vel,iq,sor,arma,niv,pex);
    }

    public void calculaDerivades(){
        this.ps = this.con+this.str;
        this.pd = (this.str+this.arma.getWPow())/4;
        this.pa = this.iq + this.sor + this.arma.getWVel();
        this.pe = this.vel + this.sor + this.iq;
    }

    //Implementació de la contraAtaca(), implementada amb la interficie Caos
    public boolean contraAtaca(Dau... daus) {
        int tirada = 0;
        for (int i = 0; i < daus.length; i++) {
            tirada += daus[i].tirada();
        }
        return tirada <= (pa / 2);
    }

    //Implementació de la clase recuperaParcialmentPS(), implementada amb la interficie Ordre
    public void recuperaParcialmentPS() {
        int psInicial = con + str + iq;
        int recuperacio = psInicial * 10 / 100;
        if (ps < psInicial) {
            if ((ps + recuperacio) <= psInicial) {
                ps += recuperacio;
            } else {
                ps = psInicial;
            }
        }
    }
}