// Kasa class
package OBJ;

import Base.GUI;

public class Kasa extends Thread {

    private int totalRevenue; // Gelirim
    GUI gui;

    public Kasa(GUI gui) {
        this.totalRevenue = 0;
        this.gui = gui;
    }

    public synchronized void receivePayment(Musteri musteri, int amount) {
        System.out.println(musteri.getNameX() + " has paid.");
        totalRevenue += amount;
        gui.kazancNumberUpdater(totalRevenue);
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }
}
