package objects;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import static controllers.Controller.deviceArrayList;

/**
 * Created by HP on 22.04.2017.
 */
public class Operator implements Runnable  {
    Thread thread;

    private ObservableList<Request> requestQueueObservableList = FXCollections.observableArrayList();
//    private int operatorID;
    private int free;
//    private int deviceID;

//    public int getDeviceID() {
//        return deviceID;
//    }
//
//    public void setDeviceID(int deviceID) {
//        this.deviceID = deviceID;
//    }
    //    private Device device;

//    public Device getDevice() {
//        return device;
//    }
//
//    public void setDevice(Device device) {
//        this.device = device;
//    }

    public synchronized int getFree() {
        return free;
    }

    public synchronized void setFree(int free) {
        this.free = free;
    }

    public Operator(int operatorID){
        thread = new Thread(this, "operator" + operatorID);
       // this.operatorID = operatorID;
        thread.start();
        this.free = 1;
    }

 //   public int getOperatorID(){
//        return operatorID;
//    }
    public synchronized ObservableList<Request> getRequestQueueObservableList(){
        return requestQueueObservableList;
    }

    public void addListener(){
        ListChangeListener listChangeListener = new ListChangeListener<Request>() {
            @Override
            public void onChanged(Change<? extends Request> c) {
               // System.out.println(requestQueueObservableList.get(requestQueueObservableList.size()-1).getId());
                int index = requestQueueObservableList.size() - 1;
                int operatorID = Integer.parseInt(requestQueueObservableList.get(index).getOperatorID());
                Request request = new Request(requestQueueObservableList.get(index).getId(),"","в обработке",Integer.toString(operatorID));
                if(operatorID==0||operatorID==1){
                    deviceArrayList.get(0).getRequestDeviceQueueObservableList().add(request);
                }
                else{
                    deviceArrayList.get(1).getRequestDeviceQueueObservableList().add(request);
                }
            }
        };
        requestQueueObservableList.addListener(listChangeListener);
    }
    @Override
    public void run() {
        addListener();
    }

}
