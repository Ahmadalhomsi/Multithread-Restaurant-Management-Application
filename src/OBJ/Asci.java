/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJ;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

// Asci class
public class Asci extends Thread {

    private String name;
    private Semaphore tableSemaphore;
    private volatile boolean processingOrders = true;
    private int ready=2;
    private int index;

    public Asci(String name, Semaphore tableSemaphore, int index) {
        this.name = name;
        this.tableSemaphore = tableSemaphore;
        this.index = index;
    }

    @Override
    public void run() {
        /*
        try {
            processOrders();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }

    public void stopProcessingOrders() {
        this.processingOrders = false;
    }

    private void processOrders() throws InterruptedException {
        while (processingOrders) {
            // Simulate the time it takes to prepare orders
            Thread.sleep(2000);

            // Release the permit back to the tableSemaphore
            tableSemaphore.release();

            // Simulate the time it takes to notify the garson
            Thread.sleep(1000);
        }
    }

    public int getReady() {
        return ready;
    }

    public void setReady(int ready) {
        this.ready = ready;
    }

    

    public String getNameX() {
        return name;
    }

    public void setNameX(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }
    
    
    
    
}

