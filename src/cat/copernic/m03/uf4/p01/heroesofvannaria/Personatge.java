
package cat.copernic.m03.uf4.p01.heroesofvannaria;

/**
 *
 * @author ByDarrow
 */

public abstract class Personatge {

    protected String nom;
    protected String classe; // Guerrer,Cavaller, Valquiria, Assassí

    // Estadístiques
    protected int str;  // Força
    protected int con;   // Constitució
    protected int vel;   // Velocitat
    protected int iq;  // Intel·ligència
    protected int sor;   // Sort


    protected Arma arma;  // Daga,Espasa,Martell

    // Estadístiques derivades
    protected int ps;    // Punts de salut
    protected int pd;    // Punts de dany
    protected int pa;    // Probabilitat atacar
    protected int pe;    // Probabilitat esquivar

    protected int niv;    // Nivell
    protected int pex;    // Punts d'experiència

    protected String devocio;


    public Personatge(String nom, String classe, int str, int con, int vel,
                      int iq, int sor,Arma arma) {

        this.setDades(nom, classe, str, con, vel, iq, sor,arma);
        this.devocio = devocio;
        calculaDerivades();
    }


    public Personatge(String nom, String classe, int str, int con, int vel,
                      int iq, int sor, Arma arma, int niv,int pex) {

        this.setDades(nom, classe, str, con, vel, iq, sor,arma);
        this.devocio = devocio;
        calculaDerivades();
    }

    private void setDades(String nom,String classe,int str,int con,int vel,
                          int iq,int sor, Arma arma) {

        this.nom    = nom;
        this.classe = classe;

        this.str   = str;
        this.con    = con;
        this.vel    = vel;
        this.iq   = iq;
        this.sor    = sor;

        this.arma   = new Arma();

        this.ps     = 0;
        this.pd     = 0;
        this.pa     = 0;
        this.pe     = 0;

        this.pex    = 0;
        this.niv    = 0;

    }


    abstract public void calculaDerivades();

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getClasse() {
        return this.classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getStr() {
        return this.str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getCon() {
        return this.con;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public int getVel() {
        return this.vel;
    }

    public void setVel(int vel) {
        this.vel = vel;
    }

    public int getIQ() {
        return this.iq;
    }

    public void setIQ(int iq) {
        this.iq = iq;
    }

    public int getSor() {
        return this.sor;
    }

    public void setSor(int sor) {
        this.sor = sor;
    }

    public Arma getArma() {
        return this.arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public int getPs() {
        return this.ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getPd() {
        return this.pd;
    }

    public void setPd(int pd) {
        this.pd = pd;
    }

    public int getPa() {
        return this.pa;
    }

    public void setPa(int pa) {
        this.pa = pa;
    }

    public int getPe() {
        return this.pe;
    }

    public void setPe(int pe) {
        this.pe = pe;
    }

    public int getPex() {
        return this.pex;
    }

    public void setPex(int pex) {
        this.pex = pex;
    }

    public int getNiv() {
        return this.niv;
    }

    public void setNiv(int niv) {
        this.niv = niv;
    }

    public String getDevocio() {
        return this.devocio;
    }

    public void setDevocio(String devocio) {
        this.devocio = devocio;
    }

    public boolean ataca(Dau...daus) {
        int tirada = 0;
        for(Dau d:daus)
            tirada += d.tirada();

        return this.pa >= tirada;
    }


    public boolean esquiva(Dau...daus) {
        int tirada = 0;
        for(Dau d:daus)
            tirada += d.tirada();

        return this.pe >= tirada;
    }


    public void restauraPs() {
        this.ps = this.con + this.str;
    }

    @Override
    public String toString() {
        return nom + "(for:" + this.str + ",con:" + this.con +
                ",vel:" + this.vel + ",inte:" + this.iq + ",sor:" + this.sor +
                ",niv:" + this.niv + ",pex: " + this.pex  + ") - "
                + arma.getNom();

    }
}