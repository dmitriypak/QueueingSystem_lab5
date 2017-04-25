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
    private int free;
    public synchronized int getFree() {
        return free;
    }

    public synchronized void setFree(int free) {
        this.free = free;
    }

    public Operator(int operatorID){
        thread = new Thread(this, "operator" + operatorID);
        thread.start();
        this.free = 1;
    }

    public synchronized ObservableList<Request> getRequestQueueObservableList(){
        return requestQueueObservableList;
    }

    public void addListener(){
        ListChangeListener listChangeListener = new ListChangeListener<Request>() {
            @Override
            public void onChanged(Change<? extends Request> c) {
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
