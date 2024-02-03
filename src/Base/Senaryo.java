/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

public class Senaryo {

    private int customerCount;
    private int priority;
    private long creationTime;

    public Senaryo(int customerCount, int priority) {
        this.customerCount = customerCount;
        this.priority = priority;
        creationTime = System.currentTimeMillis();
    }

    public long getElapsedSeconds() {
        // Calculate elapsed time in seconds
        long currentTime = System.currentTimeMillis();
        long elapsedTimeInMillis = currentTime - creationTime;
        return elapsedTimeInMillis / 1000; // Convert milliseconds to seconds
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

}
