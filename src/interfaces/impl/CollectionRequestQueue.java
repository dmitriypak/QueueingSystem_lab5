package interfaces.impl;

import interfaces.RequestQueue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Request;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;


/**
 * Created by HP on 22.04.2017.
 */
public class CollectionRequestQueue implements RequestQueue, Runnable{
    private ObservableList<Request> requestList = FXCollections.observableArrayList();
    private LinkedList<Request>requestQueue = new LinkedList<Request>();

    @Override
    public void add(Request request) {
        requestList.add(request);
        requestQueue.add(request);
    }

    public synchronized ObservableList<Request> getRequestsList() {
        return requestList;
    }
    public synchronized LinkedList<Request> getRequestQueue(){
        return requestQueue;
    }

    private static int getRandomInRange(int min, int max){
        Random r = new Random();
        return (r.nextInt((max-min)+1)+min)*10;
    }
    @Override
    public void run() {
        int id = 0;
        int period = 0;
        while (id<300){
            period = getRandomInRange(8,12);
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("hh:mm:ss:SSS", Locale.ENGLISH);
            Request req = new Request(Integer.toString(id),sf.format(date)+"/"+Integer.toString(period),"новая");
            requestList.add(req);
            requestQueue.add(req);
            id++;
            try {
                Thread.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

