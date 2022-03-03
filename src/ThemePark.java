import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ThemePark implements Runnable {

     Scanner in;

     Random rand;
     Thread thread = new Thread();
     JFrame frame = new JFrame();
     JPanel panel = new JPanel();
     JLabel labl = new JLabel();
     JLabel inSession = new JLabel();
     JLabel tempWords = new JLabel();
     JLabel imSeeing;
     JLabel trulyInSession = new JLabel();
     JLabel waitingLine = new JLabel();
     TextArea waiting1;
    TextArea waiting2;
     public Ride ride1 = new Ride("Fiona");
     public Ride ride2 = new Ride("Ella");
     public Ride ride3 = new Ride("Estrella");
     public Ride ride4 = new Ride("Ariel");
     public Ride ride5 = new Ride("Jazmine");
     public Ride ride6 = new Ride("Shrek");
     public Ride ride7 = new Ride("Charming");
     public Ride ride8 = new Ride("Sol");
     public Ride ride9  = new Ride("Eric");
     public Ride ride10 = new Ride("Alladin");


    public int rides = 10;
    int space;
    public int theClock = 0;
    public int numOfRides;
    public String name;
    public ArrayList<TextArea> theWaiting = new ArrayList<>();
    public ArrayList<TextArea> theWaiting2 = new ArrayList<>();
    public ArrayList<String> names = new ArrayList<>();
    public ArrayList<JLabel> reallySee = new ArrayList<>();

    public Ride[] theRides = {ride1,ride2,ride3,ride4,ride5,ride6,ride7,ride8,ride9,ride10};

    public ThemePark() {
        frame.setVisible(true);
        frame.setSize(1100,700);
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.setVisible(true);
        panel.setLayout(null);
        panel.setSize(1100,700);
        panel.add(labl);
        labl.setVisible(true);
        labl.setLocation(10,0);
        labl.setText("Number of Rides: 10");
        labl.setSize(152,33);
        panel.add(inSession);
        inSession.setSize(150,15);
        inSession.setText("Rides in Session: ");
        inSession.setLocation(10,60);
        panel.add(tempWords);
        tempWords.setVisible(true);
        tempWords.setSize(100,30);
        tempWords.setLocation(1070,0);
        panel.add(trulyInSession);
        trulyInSession.setText("People Riding:");
        trulyInSession.setVisible(true);
        trulyInSession.setSize(100,30);
        trulyInSession.setLocation(10,90);
        panel.add(waitingLine);
        waitingLine.setText("Waiting in Line:");
        waitingLine.setVisible(true);
        waitingLine.setSize(100,30);
        waitingLine.setLocation(10,250);
        toSee();
        thread.start();
        run();
        theWait();
    }

    public void toSee() {
        int yLoc = 60;
        int xLoc = 160;
        int tYLoc = 100;
        int tXLoc = 120;
        int tYLoc1 = 280;
        int tXLoc1 = 120;
        for(int i = 0; i < rides; i++) {
            imSeeing = new JLabel(theRides[i].getRideName());
            panel.add(imSeeing);
            imSeeing.setLocation(xLoc,yLoc);
            imSeeing.setVisible(true);
            imSeeing.setSize(150,15);
            xLoc += 110;
        }
        for(int i = 0; i < rides; i++) {
            waiting1 = new TextArea();
            theWaiting.add(waiting1);
            panel.add(theWaiting.get(i));
            theWaiting.get(i).setVisible(true);
            theWaiting.get(i).setText("");
            theWaiting.get(i).setSize(80,150);
            theWaiting.get(i).setLocation(tXLoc,tYLoc);
            tXLoc += 100;
        }
        for(int i = 0; i < rides; i++) {
            waiting2 = new TextArea();
            theWaiting2.add(waiting2);
            panel.add(theWaiting2.get(i));
            theWaiting2.get(i).setVisible(true);
            theWaiting2.get(i).setText("");
            theWaiting2.get(i).setSize(80,250);
            theWaiting2.get(i).setLocation(tXLoc1,tYLoc1);
            tXLoc1 += 100;
        }
    }

    public void theWait() {
        space =0;
        while(theClock < 60) {
            try {
                thread.sleep(500);
                tempWords.setText(String.valueOf(theClock));
                for(int i = 0; i < rides; i++) {
                    double prob1 = theProb();
                    if(prob1 > 0.10 && prob1 < 0.50) {
                        double prob2 = theProb();
                        String theName = addNames(rand.nextInt(40));
                        theRides[i].addFastWait(theName);
                        theWaiting2.get(i).append("\n");
                        theWaiting2.get(i).append(theName);
                        if (prob2 < 0.25) {
                            theRides[i].addFastWait(theName);

                            if(space == theRides[i].getSize()) {
                                String hey = theRides[i].sendSwiftly();
                                theWaiting.get(i).append("\n");
                                theWaiting.get(i).append(hey);
                                space = 0;
                            }
                            space++;
                        } else {
                            theRides[i].addSlowWait(theName);
                            if(space == theRides[i].getSize()) {
                                String hey = theRides[i].sendSwiftly();
                                theWaiting.get(i).append("\n");
                                theWaiting.get(i).append(hey);
                                space = 0;
                            }
                            space++;
                        }
                    } else if(prob1 < 0.10) {
                        //two people will come
                        double prob2 = theProb();
                        String theName = addNames(rand.nextInt());
                        if (prob2 < 0.25) {
                            theRides[i].addFastWait(theName);
                            if(space == theRides[i].getSize()) {
                                String hey = theRides[i].sendSwiftly();
                                theWaiting.get(i).append("\n");
                                theWaiting.get(i).append(hey);
                                space = 0;
                            }
                            space++;
                        } else {
                            theRides[i].addSlowWait(theName);
                        }
                        double prob3 = theProb();
                        if (prob3 < 0.25) {
                            theRides[i].addFastWait(theName);
                        } else {
                            theRides[i].addSlowWait(theName);
                        }

                    }
                }
                theClock++;
            } catch (Exception e) {}
        }
    }


    public  double theProb() {
        rand = new Random();
        double factor = (double) Math.pow(10,2);
        double val = rand.nextDouble();
        val = val * factor;
        double temp = Math.round(val);
        return temp / factor;
    }

    public String addNames(int i) {
        try {
            in = new Scanner(new FileReader("Riders.txt"));
            while (in.hasNextLine()) {
                names.add(in.nextLine());
            }
            return names.get(i);
        } catch (FileNotFoundException e) {

        }
        return "nothing";
    }

    public static void main(String[] args) {
        new ThemePark();
    }

    @Override
    public void run() {
        System.out.println("Thread is running");
    }
}
