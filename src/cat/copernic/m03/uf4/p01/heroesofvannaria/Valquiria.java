package cat.copernic.m03.uf4.p01.heroesofvannaria;

/**
 *
 * @author ByDarrow
 */
public class Valquiria extends Personatge{

    public Valquiria(String nom, String classe, int str, int con, int vel,
                     int iq, int sor,Arma arma){
        super(nom, classe, str, con, vel, iq, sor, arma);
    }

    public Valquiria(String nom, String classe, int str, int con, int vel,
                     int iq, int sor, Arma arma,int niv,int pex){
        super(nom,classe,str,con,vel,iq,sor,arma,niv,pex);
    }

    public void calculaDerivades(){
        this.ps = this.con+this.str;
        this.pd = (this.str+this.arma.getWPow())/4;
        this.pa = this.iq + this.sor + this.arma.getWVel();
        this.pe = this.vel + this.sor + this.iq;
    }
}
