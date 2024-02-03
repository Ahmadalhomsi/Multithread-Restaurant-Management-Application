package OBJ;

public class Masa {

    private final int masaNo;
    private boolean available;
    private Musteri currentOccupant;
    private boolean orderTook;

    public Masa(int masaNo) {
        this.masaNo = masaNo;
        this.available = true;
        this.currentOccupant = null;
    }

    public int getMasaNo() {
        return masaNo;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Musteri getCurrentOccupant() {
        return currentOccupant;
    }

    public void setCurrentOccupant(Musteri currentOccupant) {
        this.currentOccupant = currentOccupant;
    }

    public boolean isOrderTook() {
        return orderTook;
    }

    public void setOrderTook(boolean orderTook) {
        this.orderTook = orderTook;
    }
    
    

}
