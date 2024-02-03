/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author xandros
 */
public class ProfitOptimizer { // (herSaniye, musteriSayisi, oncelikliMusteri, sure);

    private int herSaniye;
    private int musteriSayisi;
    private int oncelikliMusteri;
    private int sure;

    public ProfitOptimizer(int herSaniye, int musteriSayisi, int oncelikliMusteri, int sure) {
        this.herSaniye = herSaniye;
        this.musteriSayisi = musteriSayisi;
        this.oncelikliMusteri = oncelikliMusteri;
        this.sure = sure;
    }

    public void Calc() {

        int fullTablesCount = 0;
        int totalCustomers = 0;
        int departingCustomers = 0; // Ayrilmis olan
        int tablesCount = 6;
        List<Musteri2> musteriList = new ArrayList<>();
        List<Musteri2> finalMusteriList = new ArrayList<>();
        for (int i = 0; i < sure; i++) {
            if (i % herSaniye == 0) {
                for (int j = 0; j < musteriSayisi; j++) {
                    System.out.println("Normal");
                    musteriList.add(new Musteri2(i));

                }

                for (int j = 0; j < oncelikliMusteri; j++) {
                    musteriList.add(new Musteri2(i, true));
                    System.out.println("Onecelikli");

                }

                Collections.sort(musteriList, Comparator.comparing(Musteri2::isOncelikli).reversed());
            }

            while (fullTablesCount < tablesCount) {
                System.out.println("MUSTERILIST SIZE:" + musteriList.size());
                if (musteriList.size() == 0) {
                    break;
                }
                
                finalMusteriList.add(musteriList.get(0));
                musteriList.remove(0);

                fullTablesCount++;

                /*
                if (musteriList.size() > 25) {
                    tablesCount++;
                }
                */
            }

            if ((i + 1) % 9 == 0) {
                fullTablesCount--;
            }

            for (int j = 0; j < musteriList.size(); j++) {
                if ((i - musteriList.get(j).getCreationTime()) > 20) {
                    musteriList.remove(j);
                    departingCustomers++;
                    tablesCount++;
                }

            }
        }

        System.out.println("Ayrilimis Musteri Sayisi: " + departingCustomers);
        System.out.println("Musteri Sayisi: " + finalMusteriList.size());
        System.out.println("Masa Sayisi : " + tablesCount);

    }
    
    public void CalcSimple()
    {
        int toplamMusteri = (sure/herSaniye)*(musteriSayisi+oncelikliMusteri);
        int ayrilanMusteri = (int) (toplamMusteri*0.1);
        int masaCount = (int) (toplamMusteri*0.2);
        int garsonCount = (int) (masaCount/3);
        int asciCount = (int) (garsonCount/4);
        toplamMusteri-=ayrilanMusteri;
        
        System.out.println("Toplam musteri sayisi: " + (toplamMusteri+ayrilanMusteri));
        System.out.println("Ayrilimis Musteri Sayisi: " + ayrilanMusteri);
        System.out.println("Giren Musteri Sayisi: " + toplamMusteri);
        System.out.println("Masa Sayisi : " + masaCount);
        System.out.println("Garson Sayisi : " + garsonCount);
        System.out.println("Asci Sayisi : " + asciCount);
        int enIyiKazanc = toplamMusteri-masaCount-garsonCount-asciCount;
        System.out.println("En iyi kazanc: " + toplamMusteri + "-" + masaCount + "-" + garsonCount + "-" + asciCount + "=" + enIyiKazanc);
    }

}

class Musteri2 {

    private int creationTime;
    private boolean oncelikli = false;

    public Musteri2(int creationTime) {
        this.creationTime = creationTime;
    }

    public Musteri2(int creationTime, boolean oncelikli) {
        this.creationTime = creationTime;
        this.oncelikli = oncelikli;
    }

    public boolean isOncelikli() {
        return oncelikli;
    }

    public int getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(int creationTime) {
        this.creationTime = creationTime;
    }

}
