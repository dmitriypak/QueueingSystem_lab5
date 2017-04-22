package controllers;

import interfaces.impl.CollectionRequestQueue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import objects.Request;

import java.util.LinkedList;

public class Controller {

    @FXML
    private TableView tableRequest;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colStatus;
    @FXML
    private TableColumn colTime;
    private LinkedList<Request> requestQueue = new LinkedList<Request>();
    private CollectionRequestQueue requestQueueImpl = new CollectionRequestQueue();

    @FXML
    private void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<Request,String>("id"));
        colTime.setCellValueFactory(new PropertyValueFactory<Request,String>("time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Request,String>("status"));

    }

    public void fillData(){
        new Thread(requestQueueImpl,"RequestThread").start();
        tableRequest.setItems(requestQueueImpl.getRequestsList());
    }


}

