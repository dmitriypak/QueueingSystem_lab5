package objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

import java.util.Timer;

/**
 * Created by HP on 22.04.2017.
 */
public class Operator implements Runnable  {
    Thread thread;
    private OperatorTask timerTask;
    private Request request;
    private ObservableList<Request> requestQueueObservableList;
    private int operatorID;
    private SimpleStringProperty serviceTime = new SimpleStringProperty("");
    private SimpleStringProperty status = new SimpleStringProperty("");


    public Operator(int operatorID){

        thread = new Thread(this, "operator");
        this.operatorID = operatorID;
//        this.serviceTime = new SimpleStringProperty(serviceTime);
//        this.status = new SimpleStringProperty("в обработке");
        thread.start();
    }

//    public ObservableList<Request> getRequestQueue(){
//        return requestQueue;
//    }

    @Override
    public void run() {
        timerTask = new OperatorTask(operatorID);
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask,0,10);
//        if (CollectionRequestQueue.getRequestQueue().size() > 0) {
//            request = CollectionRequestQueue.getRequestQueue().getLast();
//            requestQueue.add(request);
//            try {
//                Thread.sleep(Integer.parseInt(serviceTime.getValue()));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }
//
//    @Override
//    public void add(Request request) {
//        requestQueue.add(request);
//    }
}
