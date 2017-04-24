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
    public static ObservableList<Request> requestList = FXCollections.observableArrayList();

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
                int index = requestList.size()-1;
                for(int i = 0;i<operatorArrayList.size();i++){
                    if(operatorArrayList.get(i).getFree()==1){
                        Request request = new Request(requestList.get(index).getId(),"","в обработке",Integer.toString(i));
                        operatorArrayList.get(i).getRequestQueueObservableList().add(request);
                        break;
                    }
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
            period = RandomRange.getRandomInRange(8,12)*10;
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

