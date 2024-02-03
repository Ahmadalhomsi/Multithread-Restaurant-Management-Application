package OBJ;

import Base.GUI;
import java.util.List;

public class Musteri extends Thread {

    private final String name;
    private final List<Masa> masalar;
    private boolean orderPrepared;
    private GUI gui;
    private Kasa kasa;
    private int index;
    Masa selectedMasa;

    public Musteri(String name, int index , List<Masa> masalar, GUI gui, Kasa kasa) {
        this.name = name;
        this.masalar = masalar;
        this.orderPrepared = false; // delte this
        this.gui=gui;
        this.kasa=kasa;
        this.index=index;
    }

    public boolean isOrderPrepared() { // deete this 
        return orderPrepared;
    }

    public void setOrderPrepared(boolean orderPrepared) {
        this.orderPrepared = orderPrepared;
    }
    
    

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        try {
            //selectTable();
            gui.SpecificTableActUpdater(this, "Eating");
            Thread.sleep(3000); // Simulate eating time
            
            gui.SpecificTableActUpdater(this, "Paying");
            kasa.receivePayment(this, 1);
            Thread.sleep(1000); // Simulate payment time
            
            gui.SpecificTableActUpdater(this, "Left");
            
            

            
            //leaveTable();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectTable() {
        synchronized (masalar) {
            selectedMasa = findAvailableTable();
            if (selectedMasa != null) {
                selectedMasa.setAvailable(false);  // Mark the table as unavailable
                selectedMasa.setCurrentOccupant(this);  // Set the current occupant to the customer
                System.out.println(Thread.currentThread().getName() + ": " + name
                        + " is sitting at Table " + selectedMasa.getMasaNo());
            }
        }
    }

    private Masa findAvailableTable() {
        for (Masa masa : masalar) {
            if (masa.isAvailable()) {
                //gui.tablesMusteriNameUpdater(this);
                return masa;
                
            }
        }
        return null; // No available table
    }

    public void leaveTable() {
        synchronized (masalar) {
            Masa selectedMasa = findSelectedTable();
            if (selectedMasa != null) {
                selectedMasa.setAvailable(true);  // Mark the table as available
                selectedMasa.setCurrentOccupant(null);  // Set the current occupant to null
                System.out.println(Thread.currentThread().getName() + ": " + name
                        + " is leaving Table " + selectedMasa.getMasaNo());
                // Perform other actions related to leaving the table
            } else {
                System.out.println(Thread.currentThread().getName() + ": " + name
                        + " couldn't find a selected table to leave.");
            }
        }
    }

    public Masa findSelectedTable() {
        return selectedMasa;
    }

    public String getNameX() {
        return name;
    }

    public int getIndex() {
        return index;
    }
    
    
    
}
