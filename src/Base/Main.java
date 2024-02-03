package Base;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import OBJ.*;
import com.formdev.flatlaf.FlatDarculaLaf;
import java.io.PrintStream;

import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {

        // Look and feel  
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf()); //FlatMacDarkLaf() FlatDarculaLaf()
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        List<Masa> masalar = new ArrayList<>();
        masalar.add(new Masa(1));
        masalar.add(new Masa(2));
        masalar.add(new Masa(3));
        masalar.add(new Masa(4));
        masalar.add(new Masa(5));
        masalar.add(new Masa(6));

        GUI gui = new GUI();

        Kasa kasa = new Kasa(gui);
        Semaphore tableSemaphore = new Semaphore(masalar.size());

        Restaurant restaurant = new Restaurant(masalar, kasa, tableSemaphore, gui);

        Scanner s = new Scanner(System.in);

        System.out.println("Problem: ");
        int problem = s.nextInt();

        /// Problem 1
        if (problem == 1) {
            System.out.println("Enter the number of steps: ");
            int kacAdim = s.nextInt();
            ArrayList<Senaryo> senaryolar = new ArrayList<>();

            for (int i = 0; i < kacAdim; i++) {
                System.out.println((i + 1) + ". step - Enter customer count: ");
                int clientCount = s.nextInt();
                System.out.println((i + 1) + ". step - Enter priority: ");
                int priority = s.nextInt();
                senaryolar.add(new Senaryo(clientCount, priority));
            }

            /*
            TextFiler textFiler = new TextFiler();
            textFiler.createFile();
            
            PrintStream fileStream = textFiler.readTerminal();
            */

            /// 65 Yas Oncelik Total
            int oncelikliMusteri = 0;

            for (int i = 0; i < senaryolar.size(); i++) {

                // if it's the first scenario (Step)
                if ((i == 0) && (senaryolar.get(0).getPriority() > 0)) {
                    while (senaryolar.get(0).getPriority() > 0) {
                        senaryolar.get(0).setCustomerCount(senaryolar.get(0).getCustomerCount() + senaryolar.get(0).getPriority());
                        senaryolar.get(0).setPriority(senaryolar.get(0).getPriority() - 1);

                        if (senaryolar.get(0).getCustomerCount() < 6) {
                            oncelikliMusteri = senaryolar.get(0).getPriority();
                            break;
                        }

                    }

                } else {
                    oncelikliMusteri += senaryolar.get(i).getPriority();
                    //senaryolar.get(i).setCustomerCount(senaryolar.get(i).getCustomerCount() - senaryolar.get(i).getPriority());
                    System.out.println("Toplam Priority Musteri: " + oncelikliMusteri);
                    senaryolar.get(i).setPriority(0);
                }

            }

            /// 65 Yas oncelik orginizer
            int k = 0; // Priority Seanryo Ekleme Indexi
            while (oncelikliMusteri > 0) {
                if (oncelikliMusteri > 6) {
                    senaryolar.add(k, new Senaryo(6, 0));
                    System.out.println("6 Priority musteri one alindi");
                    oncelikliMusteri -= 6;
                } else {
                    senaryolar.add(k, new Senaryo(oncelikliMusteri, 0));
                    System.out.println(oncelikliMusteri + " Priority musteri one alindi");
                    oncelikliMusteri = 0;
                }
                k++;

            }

            gui.setVisible(true);

            int bekleyenMusteri = 0;
            for (int i = 0; i < senaryolar.size(); i++) {
                System.out.println("Steps Loop");
                Senaryo senaryo = senaryolar.get(i);

                /*
                /// 20 saniye Bekleyen // but you need to make it musteriList
                if (senaryo.getElapsedSeconds() >= 20) {
                    i++;
                    System.out.println(senaryo.getCustomerCount() + "20 Saniye gecti ve musteri ayrildi");
                }
                */

                int availableTables = restaurant.countAvailableTables();
                int newCustomerCount = senaryo.getCustomerCount();

                // if step lower than 6 checker
                int prevCustomerCount = 0;
                while ((newCustomerCount < 6) && ((i + 1) < senaryolar.size())) {

                    int howManyCustomerAddedFromBekleyen = 0;
                    int howManyCustomerAddedFromNext = 0;
                    prevCustomerCount = newCustomerCount;
                    while (((bekleyenMusteri > 0) && (newCustomerCount < 6))) {
                        bekleyenMusteri--;
                        newCustomerCount++;
                        howManyCustomerAddedFromBekleyen++;
                    }

                    if (senaryolar.get(i + 1).getCustomerCount() > 0) {

                        newCustomerCount++;
                        howManyCustomerAddedFromNext++;
                        senaryolar.get(i + 1).setCustomerCount(senaryolar.get(i + 1).getCustomerCount() - 1);
                    }

                    if (prevCustomerCount == newCustomerCount) {
                        break;
                    } else {
                        System.out.println("(X < 6) Adding customers from another step");
                    }

                }
                // if step higher than 6 checker
                int v=i;
                while ((senaryo.getCustomerCount() > 6) && ((v + 1) < senaryolar.size())) {
                    System.out.println("(X > 6) Removing customers to another step");
                    newCustomerCount = 6;
                    bekleyenMusteri += senaryo.getCustomerCount() - 6;
                    v++;
                }
                if ((senaryo.getCustomerCount() > 6) && ((i + 1) > senaryolar.size())) {
                    senaryolar.add(new Senaryo(senaryo.getCustomerCount() - 6, 0));
                }
                //--- 

                senaryo.setCustomerCount(newCustomerCount);

                try {
                    restaurant.runSenaryo(senaryo, i + 1);
                } catch (Exception e) {
                }

                //restaurant.printTables();
                System.out.println("Executed step " + (i + 1));
                System.out.println("newCustomerCount: " + newCustomerCount);
                System.out.println("bekleyenMusteri: " + bekleyenMusteri);

                System.out.println("Tum senaryolar");
                for (int j = 0; j < senaryolar.size(); j++) {
                    System.out.println("Senaryo " + (j + 1) + ": " + senaryolar.get(j).getCustomerCount());
                }

                System.out.println("------------------------------------------------- GUI WHILE LOOP");

                while (!gui.next1) {
                    System.out.print("");
                }
                gui.StepSetter(i + 1);
                gui.next1 = false;

                
            }
        } /// Problem 2
        else if (problem == 2) {
            //System.out.println("Sabit akis modeli");

            System.out.println("Her kac saniye: ");
            int herSaniye = s.nextInt();
            System.out.println("Her " + herSaniye + " saniye musteri sayisi: ");
            int musteriSayisi = s.nextInt();
            System.out.println("Priority musteri sayisi: ");
            int oncelikliMusteri = s.nextInt();
            System.out.println("Ne kadar toplam sure (Saniye): ");
            int sure = s.nextInt();

            ProfitOptimizer PO = new ProfitOptimizer(herSaniye, musteriSayisi, oncelikliMusteri, sure);
            //PO.Calc();
            PO.CalcSimple();

        }
        

    }
}
