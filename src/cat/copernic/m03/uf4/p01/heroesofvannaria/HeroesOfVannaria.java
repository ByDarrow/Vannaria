package cat.copernic.m03.uf4.p01.heroesofvannaria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ByDarrow
 */
public class HeroesOfVannaria {
    private final Scanner in = new Scanner(System.in);

    //private Personatge[] personatges;
    private ArrayList<Personatge> personatges;
    private Arma[] armes;
    // Punts d'experiència necessaries per passar de nivell
    private static int[] nivells = {100,200,500,1000,2000};

    HeroesOfVannaria(){
        //personatges = new Personatge[100];
        personatges = new ArrayList();
        armes = new Arma[3];
        crearArmes();
    }

    private void crearArmes() {
        armes[0] = new Arma(5,15,"Daga");
        armes[1] = new Arma(10,10,"Espasa");
        armes[2] = new Arma(15,5,"Martell de combat");
    }


    private void crearPersonatge(){
        String nom, classe;
        int str,con,vel,iq,sor;
        str = con = vel = iq = sor = -1;

        final int PUNTS_MINIM = 3,PUNTS_MAXIM = 18;
        int totalPunts = 60;

        System.out.println("" +
                "╔═╗╦═╗╔═╗╔═╗  ╔═╗╦    ╔╦╗╔═╗╦ ╦  ╔═╗╔═╗╦═╗╔═╗╔═╗╔╗╔╔═╗╔╦╗╔═╗╔═╗\n" +
                "║  ╠╦╝║╣ ╠═╣  ║╣ ║     ║ ║╣ ║ ║  ╠═╝║╣ ╠╦╝╚═╗║ ║║║║╠═╣ ║ ║ ╦║╣ \n" +
                "╚═╝╩╚═╚═╝╩ ╩  ╚═╝╩═╝   ╩ ╚═╝╚═╝  ╩  ╚═╝╩╚═╚═╝╚═╝╝╚╝╩ ╩ ╩ ╚═╝╚═╝\n"+
                "------------------------------------------------");

        System.out.print("Introdueix el nom d'un personatge: ");
        nom = in.nextLine();
        System.out.print("Introdueix la classe del personatge: \n");
        System.out.println("1- Assassi\n"+
                "2- Cavaller\n" +
                "3- Guerrer\n" +
                "4- Valquiria");
        System.out.println("------------------------------------------------");
        char opcio;
        opcio = in.next().charAt(0);
        in.nextLine();

        switch (opcio){
            case '1':
                classe = "Assassi";
                break;
            case '2':
                classe = "Cavaller";
                break;
            case '3':
                classe = "Guerrer";
                break;
            case '4':
                classe = "Valquiria";
                break;
        }

        str = Utils.llegeixEnterRang("Punts de força",
                PUNTS_MINIM,Math.min(totalPunts, PUNTS_MAXIM));
        totalPunts -= str;

        con = Utils.llegeixEnterRang("Punts de constitució",
                PUNTS_MINIM,Math.min(totalPunts, PUNTS_MAXIM));
        totalPunts -= con;

        vel = Utils.llegeixEnterRang("Punts de velocitat",
                PUNTS_MINIM,Math.min(totalPunts, PUNTS_MAXIM));
        totalPunts -= vel;

        iq = Utils.llegeixEnterRang("Punts d'intel·ligència",
                PUNTS_MINIM,Math.min(Math.min(totalPunts, totalPunts-3*1), PUNTS_MAXIM));
        totalPunts -= iq;

        sor = Utils.llegeixEnterRang("Punts de sort",
                PUNTS_MINIM,Math.min(totalPunts, PUNTS_MAXIM));
        

        // Falta triar arma
        Arma arma = triarArma();

        Personatge personatge;
        switch(opcio){
            case '1':
                classe = "Assassi";
                personatge = new Assassi(nom, classe, str, con, vel, iq, sor, arma);
                personatges.add(personatge);
                break;
            case '2':
                classe = "Cavaller";
                personatge = new Cavaller(nom, classe, str, con, vel, iq, sor, arma);
                personatges.add(personatge);
                break;
            case '3':
                classe = "Guerrer";
                personatge = new Guerrer(nom, classe, str, con, vel, iq, sor, arma);
                personatges.add(personatge);
                break;
            case '4':
                classe = "Valquiria";
                personatge = new Valquiria(nom, classe, str, con, vel, iq, sor, arma);
                personatges.add(personatge);
                break;
        }

        System.out.println("");
    }

    /*private void afegirPersonatge(Personatge p) {
        for(int i=0;i<personatges.length;i++) {
            if(personatges[i]==null) {
                personatges[i] = p;
                break;
            }
        }
    }*/

    private Arma triarArma() {

        boolean opcions = false;
        int resultat=-1;
        
        
        while (!opcions) {
            System.out.println("" +
                    "╔╦╗╦═╗╦╔═╗  ╔═╗╦    ╔═╗╦═╗╔╦╗╔═╗\n" +
                    " ║ ╠╦╝║╠═╣  ║╣ ║    ╠═╣╠╦╝║║║╠═╣\n" +
                    " ╩ ╩╚═╩╩ ╩  ╚═╝╩═╝  ╩ ╩╩╚═╩ ╩╩ ╩\n"+
                    "------------------------------------------------");
            for(int i=0;i<this.armes.length;i++)
                System.out.println((i+1) + ".- " + this.armes[i].getNom());
            
            System.out.print("\nTria una opció: ");
            String opcio = in.nextLine();
            if (opcio.length()>1) {
                System.out.println("Opció incorrecta!");
                continue;
            }


            try {
                resultat = Integer.parseInt(opcio);
                if(resultat<1 || resultat>this.armes.length) {
                    System.out.println("L'entrada no és vàlida!");
                    resultat = -1;
                } else
                    opcions = true;
            } catch (NumberFormatException e) {
                System.out.println("L'entrada no és vàlida!");
                resultat = -1;
            }
        }
        return this.armes[resultat-1];
    }

    private void modificarPersonatge(ArrayList<Personatge> personatges){
        int str,con,vel,iq,sor;
        str = con = vel = iq = sor = 1;

        int puntsInicials = 45;
        System.out.println("Selecciona un personatge per modificar");
        for(int i=0;i< personatges.size();i++){
            Personatge personatge = personatges.get(i);
            System.out.println((i+1) + " " + personatge.getNom());
            System.out.println("");
        }
        int opcio = Utils.llegeixEnterRang("",0,personatges.size()-1);
        System.out.println("Nou nom per " + personatges.get(opcio).getNom());
        String nom = in.nextLine();
        System.out.println("ESTADISTIQUES:");
        System.out.printf("Raparte %d puntos entre...\n",puntsInicials);
        System.out.println("1 - Força");
        System.out.println("2 - Constitució");
        System.out.println("3 - Velocitat");
        System.out.println("4 - Intel·ligència");
        System.out.println("5 - Sort");

        System.out.println("(Escriu [nombre atribut] [ENTER] [nombre punts])");
        System.out.println("");
        do{
            int num = Utils.llegeixEnterRang("",1,5);

            switch(num){
                case 1:
                    str = 3 + Utils.llegeixEnterRang("Força --> ", 0, Math.min(15, puntsInicials));
                    System.out.println("Resten " + (puntsInicials  -= str + 3)+" punts");
                    break;
                case 2:
                    str = 3 + Utils.llegeixEnterRang("Constitució --> ", 0, Math.min(15, puntsInicials));
                    System.out.println("Resten " + (puntsInicials  -= str + 3)+" punts");
                    break;
                case 3:
                    str = 3 + Utils.llegeixEnterRang("Velocitat --> ", 0, Math.min(15, puntsInicials));
                    System.out.println("Resten " + (puntsInicials  -= str + 3)+" punts");
                    break;
                case 4:
                    str = 3 + Utils.llegeixEnterRang("Intel·ligència --> ", 0, Math.min(15, puntsInicials));
                    System.out.println("Resten " + (puntsInicials  -= str + 3)+" punts");
                    break;
                case 5:
                    str = 3 + Utils.llegeixEnterRang("Sort --> ", 0, Math.min(15, puntsInicials));
                    System.out.println("Resten " + (puntsInicials  -= str + 3)+" punts");
                    break;
            }
        }while(puntsInicials > 0);

        personatges.get(opcio).setNom(nom);
        personatges.get(opcio).setStr(str);
        personatges.get(opcio).setCon(con);
        personatges.get(opcio).setIQ(iq);
        personatges.get(opcio).setSor(sor);
        personatges.get(opcio).setVel(vel);

        System.out.println("");
        System.out.println("Guardant canvis");
        System.out.println("...");
        System.out.println("...");
        System.out.println("...");
        System.out.println("Fet!");
        System.out.println("");
    }

    private void combat() {

        // Ha d'haver-hi 2 combatents

        // combatent1: Mostrar llista personatges i deixar que trii combatent1
        for(int i=0;i<personatges.size();i++)
            System.out.printf("%d.- %s\n",i+1,personatges.get(i));
        int opcio = Utils.llegeixEnterRang("Selecciona el primer combatent", 1, personatges.size());
        Personatge combatent1 = personatges.get(opcio-1);

        ArrayList<Personatge> personatges2 = (ArrayList<Personatge>)personatges.clone();
        personatges2.remove(opcio-1);
        // combatent2: Mostrar llista personatges i deixar que trii
        //combatent2 (que no sigui l'anterior)

        for(int i=0;i<personatges2.size();i++) {
            System.out.printf("%d.- %s\n",i+1,personatges2.get(i));
        }
        opcio = Utils.llegeixEnterRang("Selecciona el segon combatent", 1, personatges2.size());
        Personatge combatent2 = personatges2.get(opcio-1);


        // Qui comença atacant?
        //Llencem una moneda: CARA (<0.5): combatent1, CREU (>=0.5):combatent2.
        Personatge atacant,defensor;
        if(Math.random()<0.5) {
            atacant  = combatent1;
            defensor = combatent2;
        } else {
            atacant  = combatent2;
            defensor = combatent1;
        }

        System.out.println("" +
                "╦╔╗╔╦╔═╗╦  ╔╦╗╔═╗╦    ╔═╗╔═╗╔╦╗╔╗ ╔═╗╔╦╗\n" +
                "║║║║║║  ║   ║║║╣ ║    ║  ║ ║║║║╠╩╗╠═╣ ║ \n" +
                "╩╝╚╝╩╚═╝╩  ═╩╝╚═╝╩═╝  ╚═╝╚═╝╩ ╩╚═╝╩ ╩ ╩ \n"+
                "------------------------------------------------");
        System.out.println(atacant.getNom() + " vs " + defensor.getNom());

        boolean finalCombat = false;
        int ronda = 1;
        while (!finalCombat) {
            System.out.println("RONDA: " + ronda);

            // Suposem que tenim 3 daus de 25 cares i llancem els tres.
            //la suma és la puntuació de cada personatge
            Dau dau1 = new Dau(25);
            Dau dau2 = new Dau(25);
            Dau dau3 = new Dau(25);
            if(atacant.ataca(dau1,dau2,dau3)) {
                System.out.println(atacant.getNom() + " ataca.");
                // Nova tirada per veure si el defensor esquiva l'atac.
                dau1 = new Dau(25);dau2 = new Dau(25);dau3 = new Dau(25);
                if(defensor.esquiva(dau1,dau2,dau3))
                    System.out.println(defensor.getNom() + " esquiva l'atac.");
                else {
                    System.out.println(defensor.getNom() + " no pot esquivar "
                            + "l'atac.");
                    defensor.setPs(defensor.getPs()-atacant.getPd());
                    System.out.println(defensor.getNom() + " rep " +
                            atacant.getPd() + " punts de danys i els seus PS" +
                            " baixen a " + defensor.getPs());
                }

            } else
                System.out.println("Ha fallat l'atac de " + atacant.getNom());

            // Comprobem si el defensor a esgotat els seus PS
            if (defensor.getPs() <=0 ) {
                System.out.println(atacant.getNom() + " guanya el combat!!");
                finalCombat = true;
            } else {
                Personatge temp = atacant;
                atacant = defensor;
                defensor = temp;
            }
            ronda++;
        }


        // Restaurem els punts de salut dels combatents
        atacant.restauraPs();
        defensor.restauraPs();

        // Sumem als PEX del guanyador els PS inicials del perdedor
        atacant.setPex(atacant.getPex()+defensor.getPs());
        // Ha de pujar de nivell?
        if(atacant.getPex()>nivells[atacant.getNiv()]) {
            atacant.setStr(atacant.getStr()+1);
            atacant.setCon(atacant.getCon()+1);
            atacant.setVel(atacant.getVel()+1);
            atacant.setIQ(atacant.getIQ()+1);
            atacant.setSor(atacant.getSor()+1);
            atacant.setNiv(atacant.getNiv()+1);
            atacant.calculaDerivades();
        }
        System.out.println("Pulsa una tecla per tonar al menú principal...");
        Utils.pauseUntilKey();
    }

    /*
     *Metode el qual llegira les dades dels personatges desde un arxiu csv
     */
    public void llegirDades(String nomArxiu) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(nomArxiu));
            String linea;
            while ((linea = in.readLine()) != null) {
                String[] dades = linea.split(";");

                int indexArma = -1;
                for (int i = 0; i < armes.length; i++) {
                    if (armes[i].getNom().equals(dades[7])) {
                        indexArma = i;
                        break;
                    }
                }

                Personatge personatge;
                String nomClasse = dades[1];

                switch (nomClasse){
                    case "Assassi":
                        personatge = new Assassi(dades[0], dades[1],
                                Integer.parseInt(dades[2]), Integer.parseInt(dades[3]),
                                Integer.parseInt(dades[4]), Integer.parseInt(dades[5]),
                                Integer.parseInt(dades[6]), armes[indexArma],
                                Integer.parseInt(dades[8]), Integer.parseInt(dades[9]));
                        this.personatges.add(personatge);
                        break;
                    case "Cavaller":
                        personatge = new Cavaller(dades[0], dades[1],
                                Integer.parseInt(dades[2]), Integer.parseInt(dades[3]),
                                Integer.parseInt(dades[4]), Integer.parseInt(dades[5]),
                                Integer.parseInt(dades[6]), armes[indexArma],
                                Integer.parseInt(dades[8]), Integer.parseInt(dades[9]));
                        this.personatges.add(personatge);
                        break;
                    case "Guerrer":
                        personatge = new Guerrer(dades[0], dades[1],
                                Integer.parseInt(dades[2]), Integer.parseInt(dades[3]),
                                Integer.parseInt(dades[4]), Integer.parseInt(dades[5]),
                                Integer.parseInt(dades[6]), armes[indexArma],
                                Integer.parseInt(dades[8]), Integer.parseInt(dades[9]));
                        this.personatges.add(personatge);
                        break;
                    case "Valquiria":
                        personatge = new Valquiria(dades[0], dades[1],
                                Integer.parseInt(dades[2]), Integer.parseInt(dades[3]),
                                Integer.parseInt(dades[4]), Integer.parseInt(dades[5]),
                                Integer.parseInt(dades[6]), armes[indexArma],
                                Integer.parseInt(dades[8]), Integer.parseInt(dades[9]));
                        this.personatges.add(personatge);
                        break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Error de lectura del fitxer.");
        } finally {
            try {
                in.close();
            } catch (Exception e) {}
        }
    }
    
    private void reescriuCSV(ArrayList<Personatge> personatges){
            
       String ruta = null;
        try{
            FileWriter path = new FileWriter(ruta);
            BufferedWriter out = new BufferedWriter(path);

            for(int i=0;i< personatges.size();i++){            
                out.write(personatges.get(i).getNom() + ";" + personatges.get(i).getClasse() + ";" + personatges.get(i).getStr()
                        + ";" + personatges.get(i).getCon() + ";" + personatges.get(i).getVel() + ";"
                        + personatges.get(i).getIQ() + ";" + personatges.get(i).getSor() + ";" +
                        personatges.get(i).getArma().getNom() + ";" + personatges.get(i).getNiv() + ";" + personatges.get(i).getPex() + ";");
                out.newLine();
            }
            out.close();
        }catch (IOException ex){
            System.out.println("No se puede escribir el archivo");
        }
    }

    public void jugar() {

        boolean sortir = false;
        
        
        while (!sortir) {
            Utils.clearScreen();
            System.out.println("" +
                    "╦ ╦╔═╗╦═╗╔═╗╔═╗╔═╗  ╔═╗╔═╗  ╦  ╦╔═╗╔╗╔╔╗╔╔═╗╦═╗╦╔═╗  \n" +
                    "╠═╣║╣ ╠╦╝║ ║║╣ ╚═╗  ║ ║╠╣   ╚╗╔╝╠═╣║║║║║║╠═╣╠╦╝║╠═╣  \n" +
                    "╩ ╩╚═╝╩╚═╚═╝╚═╝╚═╝  ╚═╝╚     ╚╝ ╩ ╩╝╚╝╝╚╝╩ ╩╩╚═╩╩ ╩  \n" +
                    "------------------------------------------------");
            System.out.println("1 - Crear personatge \n"+
                    "2 - Iniciar combat \n" +
                    "3 - Editar personatatge \n" +
                    "X - Sortir");
            System.out.println("------------------------------------------------");
            System.out.println("Tria l'opció del menú:");
            String opcio = in.nextLine();
            if (opcio.length()>1) {
                System.out.println("Opció incorrecta!");
                continue;
            }
            switch (opcio.toUpperCase()) {
                case "1":
                    Utils.clearScreen();
                    crearPersonatge();
                    break;
                case "2":
                    Utils.clearScreen();
                    combat();
                    break;
                case "3":
                    Utils.clearScreen();
                    modificarPersonatge(personatges);
                    break;
                case "X":
                case "x":
                    System.out.println("Sortint...");
                    System.out.println("Guardant dades...");
                    reescriuCSV(personatges);
                    sortir = true;
                    break;
                default:
                    System.out.println("ERROR: opció incorrecta");
                    System.out.println("Premi ENTER per continuar");
                    in.nextLine();
                    break;
            }
        }
    }

    private void mostrarPersonatge(ArrayList<Personatge> personatges) {
        for(int i=0;i< personatges.size();i++){
            Personatge personatge = personatges.get(i);
            System.out.println((i+1) + " " + personatge);
            System.out.println("");
        }
        System.out.println("");
        
        System.out.println("Pulsa una tecla per tonar al menú principal...");
        Utils.pauseUntilKey();
    }
    
    // java -cp build\classes;lib\jline-3.9.0.jar;lib\jlayer-1.0.1.jar;lib\jansi-1.18.jar cat.copernic.m03.uf4.p01.heroesofvannaria.HeroesOfVannaria personatges.csv
    public static void main(String[] args){
//        if (args.length != 1) {
//            System.out.println("Falta indicar el nombre del archivo de datos.");
//        }else{
//            HeroesOfVannaria partida = new HeroesOfVannaria(args[0]);
//            partida.llegirDades(args[0]);
//            partida.jugar();
//            partida.reesciuCSV(args[0]);
//        }

        if (args.length != 1) {
            System.out.println("Falta indicar el nombre del archivo de datos.");
            //HeroesOfVannaria partida = new HeroesOfVannaria("E:/DAW/M03 - PROGRAMACION/UF4/UF4P01_HeroesOfVannaria/personatges.csv");
            //partida.jugar();
        } else {
            HeroesOfVannaria partida = new HeroesOfVannaria();
            partida.llegirDades(args[0]);
            partida.jugar();
            //Es salvaran els estats dels personatges al fitxer personatges.csv quan sortim del joc.
            
        }
    }
}

