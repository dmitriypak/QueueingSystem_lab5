package objects;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Created by HP on 22.04.2017.
 */
public class Operator implements Runnable  {
    Thread thread;

    private ObservableList<Request> requestQueueObservableList = FXCollections.observableArrayList();
    private int operatorID;
    private int free;
    private int deviceID;

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public synchronized int getFree() {
        return free;
    }

    public synchronized void setFree(int free) {
        this.free = free;
    }

    public Operator(int operatorID){
        thread = new Thread(this, "operator" + operatorID);
        this.operatorID = operatorID;
        thread.start();
        this.free = 1;
    }

    public int getOperatorID(){
        return operatorID;
    }
    public synchronized ObservableList<Request> getRequestQueueObservableList(){
        return requestQueueObservableList;
    }

    public void addListener(){
        ListChangeListener listChangeListener = new ListChangeListener<Request>() {
            @Override
            public void onChanged(Change<? extends Request> c) {
                if(requestQueueObservableList.size()>0){
//                    requestQueueObservableList.get(requestQueueObservableList.size()-1).setTime("10000");
//                    requestQueueObservableList.get(requestQueueObservableList.size()-1).setStatus("в обработке");
//                    try {
//                        Thread.currentThread().sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        };
        requestQueueObservableList.addListener(listChangeListener);
    }
    @Override
    public void run() {
        addListener();

//        OperatorTask operatorTask = new OperatorTask(operatorID);
//        Timer operatorTimer = new Timer(true);
//        operatorTimer.scheduleAtFixedRate(operatorTask,0,10);

//        if(operatorID==1 || operatorID==3){
//            Device device = new Device();
//            DeviceTask deviceTask = new DeviceTask();
//            Timer deviceTimer = new Timer(true);
//            deviceTimer.scheduleAtFixedRate(deviceTask,0,10);
//        }


    }

}
