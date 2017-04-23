package objects;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Created by HP on 22.04.2017.
 */
public class Operator implements Runnable  {
    Thread thread;
  //  private OperatorTask timerTask;
  //  private Request request;
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

        if (requestQueueObservableList.size() > 0) {
            try {
                Thread.sleep(Integer.parseInt("10000"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
