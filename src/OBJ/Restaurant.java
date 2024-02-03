// Restaurant class
package OBJ;

import Base.AsciTask;
import Base.GUI;
import Base.Senaryo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Restaurant {

    private List<Masa> masalar;
    private Kasa kasa;
    private List<Garson> garsons; // Change from array to ArrayList

    private Semaphore tableSemaphore;
    private Semaphore waiterSemaphore;
    private Semaphore chefSemaphore;

    Asci[] asciArray;
    GUI gui;

    public Restaurant(List<Masa> masalar, Kasa kasa, Semaphore tableSemaphore, GUI gui) {
        this.masalar = masalar;
        this.kasa = kasa;
        this.tableSemaphore = tableSemaphore;

        this.gui = gui;

        asciArray = new Asci[]{
            new Asci("Asci 1", chefSemaphore, 1),
            new Asci("Asci 2", chefSemaphore, 2)
        };

        this.garsons = new ArrayList<>();

        // Initialize the chefs
        this.chefSemaphore = new Semaphore(2);

        // Initialize semaphores for waiters and chefs
        waiterSemaphore = new Semaphore(garsons.size());
    }

    public List<Garson> getGarsons() {
        return garsons;
    }

    public Semaphore getChefSemaphore() {
        return chefSemaphore;
    }

    public void runSenaryo(Senaryo senaryo, int stepNumber) throws InterruptedException {
        List<Musteri> musteriList = new ArrayList<>();

        for (int i = 1; i <= senaryo.getCustomerCount(); i++) {
            Musteri musteri = new Musteri("Musteri " + i, i, masalar, gui, kasa);
            musteriList.add(musteri);
            musteri.selectTable();
            //musteri.start();
        }

        gui.tablesStatusUpdater(musteriList);

        ArrayList<Musteri> remainingMusteris = new ArrayList<Musteri>();
        ArrayList<AsciTask> asciTasks = new ArrayList<AsciTask>();

        // Order taking logic
        for (int i = 0; i < 2; i++) {

            garsons.add(new Garson("Garson 1", 1, masalar, kasa, tableSemaphore, this, gui));
            garsons.add(new Garson("Garson 2", 2, masalar, kasa, tableSemaphore, this, gui));
            garsons.add(new Garson("Garson 3", 3, masalar, kasa, tableSemaphore, this, gui));

            int k = 0 + (i * 3);
            System.out.println("MUSTERI LIST SIZE: " + musteriList.size() + ", K: " + k);
            Musteri musteri = musteriList.get(k);

            Musteri musteri2;
            musteri2 = (k + 1 < musteriList.size()) ? musteriList.get(k + 1) : null;

            Musteri musteri3;
            musteri3 = (k + 2 < musteriList.size()) ? musteriList.get(k + 2) : null;

            gui.SpecificTableActUpdater(musteri, "Waiting the garson");
            if (musteri2 != null) {
                gui.SpecificTableActUpdater(musteri2, "Waiting the garson");
            }
            if (musteri3 != null) {
                gui.SpecificTableActUpdater(musteri3, "Waiting the garson");
            }

            // Find an available garson
            Garson availableGarson = findAvailableGarson();
            Garson availableGarson2 = null;
            Garson availableGarson3 = null;
            if (musteri2 != null) {
                availableGarson2 = findAvailableGarson();
            }
            if (musteri3 != null) {
                availableGarson3 = findAvailableGarson();
            }

            if (availableGarson != null) {
                // Assign the order to the available garson
                availableGarson.setMusteriAndAsci(musteri, asciArray);
                availableGarson.start();

                gui.SpecificTableActUpdater(musteri, "Waiting the order");
            } else {
                System.out.println("No available garson to take the order of " + musteri.getNameX());
                remainingMusteris.add(musteri);
            }
            if (availableGarson2 != null) {
                availableGarson2.setMusteriAndAsci(musteri2, asciArray);
                availableGarson2.start();

                gui.SpecificTableActUpdater(musteri2, "Waiting the order");
            }
            if (availableGarson3 != null) {
                availableGarson3.setMusteriAndAsci(musteri3, asciArray);
                availableGarson3.start();

                gui.SpecificTableActUpdater(musteri3, "Waiting the order");
            }

            // Now that all orders are sent to chefs, wait for garson threads to finish
            for (Garson garson : garsons) {
                try {
                    garson.join();
                    asciTasks.add(garson.getAsciTask());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            asciArray[0].setReady(2);
            asciArray[1].setReady(2);
            chefSemaphore = new Semaphore(2);

        }

        for (AsciTask asciTask : asciTasks) {
            try {
                asciTask.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*
        // Ascı processes
        for (Asci asci : asciArray) {
            asci.start();
        }

        // Wait for all asci threads to finish preparing orders
        for (Asci asci : asciArray) {
            try {
                asci.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
         */
        // Wait for all customer threads to finish eating
        for (Musteri musteri : musteriList) {

            try {
                musteri.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        // Perform additional actions (e.g., printing tables) after ascı processes
        printTables(gui);

        // Clean up and signal customers to leave && Wait for all customer threads to finish eating
        for (Musteri musteri : musteriList) {

            musteri.leaveTable();

        }

        // clear the orderTook variable in masalar
        for (int i = 0; i < masalar.size(); i++) {
            masalar.get(i).setOrderTook(false);
        }

        //System.out.println("Executing step " + stepNumber);
        gui.tablesStatusRemover(musteriList);

        asciArray = new Asci[]{
            new Asci("Asci 1", chefSemaphore, 1),
            new Asci("Asci 2", chefSemaphore, 2)
        };
        asciTasks.clear();
    }

    public void serveFood() {
        System.out.println(Thread.currentThread().getName() + ": Serving food at tables");

        // Simulate serving food to each table
        for (Masa masa : masalar) {
            if (!masa.isAvailable()) {
                System.out.println(Thread.currentThread().getName() + ": Serving food at Table " + masa.getMasaNo());

                // Simulate the time it takes to serve food
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void printTables(GUI gui) {
        System.out.println("Current Table Status:");

        // Create a 2D array to represent the table grid
        char[][] tableGrid = new char[2][3];

        // Initialize the grid with 'O' for empty
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                tableGrid[i][j] = 'O';
            }
        }

        // Mark occupied tables with 'X'
        for (Masa masa : masalar) {
            if (!masa.isAvailable()) {
                int row = (masa.getMasaNo() - 1) / 3;  // Adjust index
                int col = (masa.getMasaNo() - 1) % 3;  // Adjust index
                tableGrid[row][col] = 'X';
            }
        }

        // Print the table grid
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tableGrid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Add this method to your Restaurant class
    public int countAvailableTables() {
        int count = 0;
        for (Masa masa : masalar) {
            if (masa.isAvailable()) {
                count++;
            }
        }
        return count;
    }

    public Semaphore getTableSemaphore() {
        return tableSemaphore;
    }

    private Garson findAvailableGarson() {
        for (Garson garson : garsons) {
            if (garson.isAvailable()) {
                garson.setAvailable(false);
                return garson;
            }
        }
        return null;

    }
}
