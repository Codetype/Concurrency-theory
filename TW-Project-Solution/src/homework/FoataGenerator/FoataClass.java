package homework.FoataGenerator;

import homework.Productions.Production;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class FoataClass {
    public ArrayList<Production> productionList = new ArrayList<Production>();
    private int whichFoataClass = 0;
    private String path;

    public FoataClass() {
        this.path = "./src/output/foata.txt";

        try {
            File file = new File(path);
            FileOutputStream fos = new FileOutputStream(file);
            PrintStream ps = new PrintStream(fos);
            System.setOut(ps);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void push(Production production) {
        this.productionList.add(production);
    }

    public void execute() throws IOException, InterruptedException {

        if(this.productionList.size() < 1) {
            return;
        }

        this.whichFoataClass += 1;

        System.out.println(this.whichFoataClass + " foata class:");

        System.out.print("| ");
        Iterator<Production> iter = productionList.iterator();

        while(iter.hasNext()) {
            Production p = iter.next();
            String prod = p.getClass().getSimpleName();
            System.out.print(prod + "_" + p.getID());
            if(prod.equals("S")) {
                System.out.print(", ");
            } else {
                System.out.print(" | ");
            }
            p.start();
        }

        System.out.print("|");

        iter = productionList.iterator();
        while (iter.hasNext()) {
            Production p = iter.next();
            p.join();
        }

        System.out.println();

        productionList.clear();
    }
}
