package interfaces.impl;

import interfaces.RequestQueue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import objects.RandomRange;
import objects.Request;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static controllers.Controller.operatorArrayList;


/**
 * Created by HP on 22.04.2017.
 */
public class CollectionRequestQueue implements RequestQueue, Runnable{
    private static ObservableList<Request> requestList = FXCollections.observableArrayList();

    @Override
    public void add(Request request) {
        requestList.add(request);
    }

    @Override
    public void delete(Request request) {

    }


    public void addListener() {
        ListChangeListener listChangeListener = new ListChangeListener<Request>() {
            @Override
            public void onChanged(Change<? extends Request> c) {
                for(int i = 0;i<operatorArrayList.size();i++){
                    System.out.println(operatorArrayList.get(i).getOperatorID());
                   // if(operatorArrayList.get(i).getRequestQueueObservableList().size()==0){
                        if(requestList.size()>0){
                            Request request = new Request(requestList.get(requestList.size()-1).getId(),"1000","в обработке");
                            operatorArrayList.get(i).getRequestQueueObservableList().add(request);
                           // System.out.println(requestList.get(requestList.size()-1).getId());
                        }
                  //  }
                }
            }
        };
        requestList.addListener(listChangeListener);
    }

    public void deleteAll(){
        requestList.clear();
    }

    public static synchronized ObservableList<Request> getRequestsList() {
        return requestList;
    }

    @Override
    public void run() {
        int id = 0;
        int period = 0;
        while (id<Request.getCount()){
            period = RandomRange.getRandomInRange(8,12);
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss:SSS", Locale.ENGLISH);
            Request req = new Request(Integer.toString(id),sf.format(date)+"/"+Integer.toString(period),"новая");
            requestList.add(req);

            id++;
            try {
                Thread.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

