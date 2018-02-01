package objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by HP on 22.04.2017.
 */
public class Device implements Runnable{
    private int ID;
    private int capacity;

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    private int serviceTime;
    Thread thread;

    public int getCapacity() {
        return capacity;
    }

    public Device(int ID, int capacity){
        thread = new Thread(this, "device" + Integer.toString(ID));
        this.capacity = capacity;

        this.ID = ID;
    }
    public synchronized int getID() {
        return ID;
    }

    public ObservableList<Request> requestDeviceQueueObservableList = FXCollections.observableArrayList();
    public synchronized ObservableList<Request> getRequestDeviceQueueObservableList(){
        return requestDeviceQueueObservableList;
    }

    @Override
    public void run() {

    }
}
