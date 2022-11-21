package tree.avltree;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ReadData {

    public static void readData() throws Exception {
        java.io.File file = new java.io.File("testfil.txt");

        System.out.println("TEST AV READDATA FRA FIL");

        // kontroller antall rader i fil
        Scanner kontroller = new Scanner(file);
        int antallRader = 0;
        while (kontroller.hasNextLine()) {
            antallRader++;
            kontroller.nextLine();
        }
        kontroller.close();
        System.out.println("Antall rader i fil: " + antallRader);

        // kontroller antall rader i fil
        Scanner kontrollerAntallord = new Scanner(file);
        int antallOrd = 0;
        while (kontrollerAntallord.hasNext()) {
            antallOrd++;
            kontrollerAntallord.next();
        }
        kontroller.close();
        System.out.println("Antall ORD i fil: " + antallOrd);


        // kontroller antall ord pr rad i fil
        System.out.println("Antall ord pr rad i fil: "+antallOrd/antallRader);


        // opprette skanner
        Scanner input = new Scanner(file);

        // les data fra fil, plukk først ut eventuell Header:

        Object verdi1 = input.nextLine(); // hele rad 1
        Object verdi2 = input.nextLine(); // hele rad 2
        System.out.println("rad 1: " +verdi1);
        System.out.println("rad 2: " +verdi2);

        // Så kan man finne resten av verdiene:
        while (input.hasNext()) {
            Object verdi4 = input.next(); // ord fra rad 4
            Object verdi5 = input.next(); // ord fra rad 4
            Object verdi6 = input.next(); // ord fra rad 4
            Object verdi7 = input.next(); // ord fra rad 4


        //    System.out.println("rad 2: " +verdi2);
        //    System.out.println("rad 3: " +verdi3);
            System.out.println("ord på rad 4: " +verdi4);
            System.out.println("ord på rad 4: " +verdi5);
            System.out.println("ord på rad 4: " +verdi6);
            System.out.println("ord på rad 4: " +verdi7);
          //  System.out.println("v1: "+verdi1+" v2: "+verdi2+" v3: "+verdi3+" v4: "+verdi4);
        }

        input.close(); // lukker skanner
    }

}
