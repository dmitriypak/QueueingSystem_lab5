package controllers;

import interfaces.impl.CollectionRequestQueue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import objects.Operator;
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
    @FXML
    private TableView tableOperator1;
    @FXML
    private TableView tableOperator2;
    @FXML
    private TableView tableOperator3;
    @FXML
    private TableColumn colIdOperator1;
    @FXML
    private TableColumn colTimeOperator1;
    @FXML
    private TableColumn colStatusOperator1;

    @FXML
    private TableColumn colIdOperator2;
    @FXML
    private TableColumn colTimeOperator2;
    @FXML
    private TableColumn colStatusOperator2;

    @FXML
    private TableColumn colIdOperator3;
    @FXML
    private TableColumn colTimeOperator3;
    @FXML
    private TableColumn colStatusOperator3;

    @FXML
    private TextField txtCountRequest;

    private LinkedList<Request> requestQueue = new LinkedList<Request>();
    public static ObservableList<Operator> operatorArrayList = FXCollections.observableArrayList();
    private CollectionRequestQueue requestQueueImpl = new CollectionRequestQueue();
    @FXML
    private void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<Request,String>("id"));
        colTime.setCellValueFactory(new PropertyValueFactory<Request,String>("time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Request,String>("status"));

        colIdOperator1.setCellValueFactory(new PropertyValueFactory<Request,String>("id"));
        colTimeOperator1.setCellValueFactory(new PropertyValueFactory<Request,String>("time"));
        colStatusOperator1.setCellValueFactory(new PropertyValueFactory<Request,String>("status"));


        colIdOperator2.setCellValueFactory(new PropertyValueFactory<Request,String>("id"));
        colTimeOperator2.setCellValueFactory(new PropertyValueFactory<Request,String>("time"));
        colStatusOperator2.setCellValueFactory(new PropertyValueFactory<Request,String>("status"));

        colIdOperator3.setCellValueFactory(new PropertyValueFactory<Request,String>("id"));
        colTimeOperator3.setCellValueFactory(new PropertyValueFactory<Request,String>("time"));
        colStatusOperator3.setCellValueFactory(new PropertyValueFactory<Request,String>("status"));

        requestQueueImpl.addListener();
        createOperators(3);
    }

    private void createOperators(int count) {
        for(int i=0;i<count;i++){
            Operator operator = new Operator(i);
            operatorArrayList.add(operator);
        }

    }

    public void fillData(){
        Request.setCount(Integer.parseInt(txtCountRequest.getText()));
        requestQueueImpl.deleteAll();
        new Thread(requestQueueImpl,"RequestThread").start();
        tableRequest.setItems(requestQueueImpl.getRequestsList());

        tableOperator1.setItems(operatorArrayList.get(0).getRequestQueueObservableList());
        tableOperator2.setItems(operatorArrayList.get(1).getRequestQueueObservableList());
        tableOperator3.setItems(operatorArrayList.get(2).getRequestQueueObservableList());
    }


}

