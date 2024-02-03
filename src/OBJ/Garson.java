// Garson class
package OBJ;

import Base.AsciTask;
import Base.GUI;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Garson extends Thread {

    private String name;
    private List<Masa> masalar;
    private Kasa kasa;
    private Semaphore tableSemaphore;
    private Restaurant restaurant;
    private boolean available;
    private GUI gui;
    private int index;
    Musteri musteri;
    Asci[] asciArray;
    AsciTask asciTask;


    public Garson(String name, int index, List<Masa> masalar, Kasa kasa, Semaphore tableSemaphore, Restaurant restaurant, GUI gui) {
        this.name = name;
        this.masalar = masalar;
        this.kasa = kasa;
        this.tableSemaphore = tableSemaphore;
        this.restaurant = restaurant;
        this.available = true;

        this.gui = gui;
        this.index = index;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Musteri getMusteri() {
        return musteri;
    }

    public void setMusteriAndAsci(Musteri musteri, Asci[] asciArray) {
        this.musteri = musteri;
        this.asciArray = asciArray;
    }

    public AsciTask getAsciTask() {
        return asciTask;
    }
    
    

    @Override
    public void run() {
        try {
            Masa selectedMasa = takeOrder(musteri);
            gui.GarsonlarUpdate(getIndex(), "Table:" + selectedMasa.getMasaNo());
            // Simulate the time it takes to take an order
            Thread.sleep(2000);
            sendOrdersToAsci(asciArray, selectedMasa, musteri);

            //setAvailable(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendOrdersToAsci(Asci[] asciArray, Masa masa, Musteri musteri) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": " + name + " is sending orders to the chefs.");

        // Iterate over tables to find the first available table
        outerloop:
        if ((!masa.isAvailable()) && (!masa.isOrderTook())) {
            if (musteri != null) {

                // Acquire a permit from the chefSemaphore before sending an order to the asci
                //restaurant.getChefSemaphore().acquire();

                // Create an AsciTask (runnable) and start a new thread for each order
                System.out.println("X Masa: " + masa.getMasaNo());

                boolean asciFound = false;
                while (!asciFound) {
                    for (int i = 0; i < asciArray.length; i++) {
                        if( (!restaurant.getChefSemaphore().tryAcquire()) && (i==0))
                        {
                            i++;
                        }
                        else if( (!restaurant.getChefSemaphore().tryAcquire()) && (i==1) )
                        {
                            i--;
                        }
                        Asci asci = asciArray[i];
                        if (asci.getReady() > 0) {
                            asciFound = true;
                            gui.GarsonlarUpdate(getIndex(), "Chef: " + asci.getIndex());
                            
                            asciTask = new AsciTask(musteri, name, masa.getMasaNo(), restaurant, asci, gui);
                            asciTask.start();
                            // GUI ascilar
                            
                            //asciTask.join();
                            
                            
                            masa.setOrderTook(true);
                            // Break the loop after processing the first available table
                            return;
                            //break outerloop;
                        }
                    }
                }

            }

        }
    }

    public Masa takeOrder(Musteri musteri) throws InterruptedException {
        Masa selectedMasa = musteri.findSelectedTable();
        if (selectedMasa != null) {
            System.out.println(Thread.currentThread().getName() + ": " + name
                    + " took the order of " + musteri.getNameX() + " at Table " + selectedMasa.getMasaNo());
            return selectedMasa;
        }
        return null;

    }

    // Add this method to get an available garson
    private Garson getAvailableGarson() {
        for (Garson garson : restaurant.getGarsons()) {
            if (garson.isAvailable()) {
                garson.setAvailable(false); // Set the garson as not available
                return garson;
            }
        }
        return null;
    }

    public int getIndex() {
        return index;
    }

}
