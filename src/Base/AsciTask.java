package Base;

import OBJ.Asci;
import OBJ.Musteri;
import OBJ.Restaurant;

public class AsciTask extends Thread {

    private Musteri musteri;
    private String garsonName;
    private int masaNo;
    private Restaurant restaurant;
    private Asci asci;
    private GUI gui;

    public AsciTask(Musteri musteri, String garsonName, int masaNo, Restaurant restaurant, Asci asci, GUI gui) {
        this.musteri = musteri;
        this.garsonName = garsonName;
        this.masaNo = masaNo;
        this.restaurant = restaurant;
        this.asci = asci;
        this.gui = gui;
    }

    @Override
    public void run() {
        gui.AscilarUpdate(asci, "Table: " + masaNo);

        System.out.println(Thread.currentThread().getName() + ": " + asci.getNameX() + " is preparing the order for "
                + musteri.getName() + " at Table " + masaNo + ". Served by " + garsonName);

        // Simulate the time it takes to prepare the order
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.err.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
        }

        gui.AscilarUpdate(asci, "Null");
        // Release the permit to signal that the order is ready
        System.out.println(Thread.currentThread().getName() + ": Order for " + musteri.getName()
                + " at Table " + masaNo + " is ready. Chef is releasing the table.");

        // Call run and start eat
        System.out.println(musteri + " is eating");
        musteri.start();

        //restaurant.getChefSemaphore().release();
    }
}
