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

    public Operator(int operatorID){
        thread = new Thread(this, "operator" + operatorID);
        this.operatorID = operatorID;
        thread.start();
    }

    public int getOperatorID(){
        return operatorID;
    }
    public ObservableList<Request> getRequestQueueObservableList(){
        return requestQueueObservableList;
    }


    public void addListener(){
        ListChangeListener listChangeListener = new ListChangeListener<Request>() {
            @Override
            public void onChanged(Change<? extends Request> c) {
                if(requestQueueObservableList.size()>0){
                    requestQueueObservableList.get(requestQueueObservableList.size()-1).setTime("10000");
                    requestQueueObservableList.get(requestQueueObservableList.size()-1).setStatus("обработан");
                    notifyAll();
                }
            }
        };
        requestQueueObservableList.addListener(listChangeListener);
    }

    public synchronized void doNotifyRequest(){
        if (requestQueueObservableList.size()>0){
            //requestQueueObservableList.remove(requestQueueObservableList.size()-1);
           notifyAll();
        }
        else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


    public synchronized void doProcessRequest(){
        if(requestQueueObservableList.size()==0){
            try {
                System.out.println("wait");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println(Thread.currentThread().getName());
        }
    }

    @Override
    public void run() {
        addListener();
        while(true) {
            doProcessRequest();
        }

    }

}
