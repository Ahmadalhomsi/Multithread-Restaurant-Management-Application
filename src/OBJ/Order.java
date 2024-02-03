/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJ;

/**
 *
 * @author xandros
 */
public class Order {

    private String customerName;
    private int tableNumber;
    private String garsonName;

    private final Object orderLock = new Object(); // Object for synchronization

    public Order(String customerName, int tableNumber, String garsonName) {
        this.customerName = customerName;
        this.tableNumber = tableNumber;
        this.garsonName = garsonName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public String getGarsonName() {
        return garsonName;
    }

    public void notifyGarson() {
        synchronized (orderLock) {
            orderLock.notify();
        }
    }
}

