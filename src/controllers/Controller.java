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
import objects.OperatorTask;
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
    private TableColumn colStatusOperator1;
    @FXML
    private TextField txtCountRequest;

    private LinkedList<Request> requestQueue = new LinkedList<Request>();
    private static ObservableList<Operator> operatorArrayList = FXCollections.observableArrayList();
    private LinkedList<Request> requestQueueOperator1 = new LinkedList<Request>();
    private CollectionRequestQueue requestQueueImpl = new CollectionRequestQueue();
    @FXML
    private void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<Request,String>("id"));
        colTime.setCellValueFactory(new PropertyValueFactory<Request,String>("time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Request,String>("status"));
        colIdOperator1.setCellValueFactory(new PropertyValueFactory<OperatorTask,String>("serviceTime"));
        colIdOperator1.setCellValueFactory(new PropertyValueFactory<Operator,String>("status"));
        requestQueueImpl.addListener();
    }

    private void createOperators(int count) {
        String serviceTime="";

        for(int i=0;i<count;i++){
//            switch (i){
//                case 0:serviceTime = Integer.toString(RandomRange.getRandomInRange(15,25));
//                break;
//                case 1:serviceTime = Integer.toString(RandomRange.getRandomInRange(30,50));
//                break;
//                case 2:serviceTime = Integer.toString(RandomRange.getRandomInRange(20,60));
//                break;
//                default:serviceTime = Integer.toString(100);
//                break;
//            }
            int operatorThread = 0;
            while(operatorThread<count){
                Operator operator = new Operator(i);
//                TimerTask operator = new Operator();
                operatorArrayList.add(operator);
//                Timer operatorTimer = new Timer(true);
//                operatorTimer.scheduleAtFixedRate(operator,0,10);
                operatorThread++;
            }
        }

    }


    public void fillData(){
        Request.setCount(Integer.parseInt(txtCountRequest.getText()));
        requestQueueImpl.deleteAll();
        new Thread(requestQueueImpl,"RequestThread").start();
        tableRequest.setItems(requestQueueImpl.getRequestsList());
        createOperators(3);
        tableOperator1.setItems(OperatorTask.getrequestQueue());
    }


}

