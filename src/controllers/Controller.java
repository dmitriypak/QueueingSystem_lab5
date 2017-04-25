package controllers;

import interfaces.impl.CollectionRequestQueue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import objects.*;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

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
    @FXML
    private TableView tableDataStorage1;
    @FXML
    private TableView tableDataStorage2;
    @FXML
    private TableColumn colIDrequestDevice1;
    @FXML
    private TableColumn colIDrequestDevice2;
    @FXML
    private TableColumn colIDoperatorDevice1;
    @FXML
    private TableColumn colIDoperatorDevice2;
    @FXML
    private ProgressBar progressBar1;
    @FXML
    private ProgressBar progressBar2;
    @FXML
    private TextField txtDeviceCapacity1;
    @FXML
    private TextField txtDeviceCapacity2;
    @FXML
    private TableColumn colTimeDevice1;
    @FXML
    private TableColumn colTimeDevice2;
    @FXML
    private TableColumn colStatDevice1;
    @FXML
    private TableColumn colStatDevice2;
    @FXML
    private Slider slider1;
    @FXML
    private Slider slider2;


    private LinkedList<Request> requestQueue = new LinkedList<Request>();
    public static ObservableList<Operator> operatorArrayList = FXCollections.observableArrayList();
    public static ObservableList<Device> deviceArrayList = FXCollections.observableArrayList();
    private CollectionRequestQueue requestQueueImpl = new CollectionRequestQueue();
    public static int serviceTime1 = 0;
    public static int serviceTime2 = 0;
    public static int deviceCapacity1 = 0;
    public static int deviceCapacity2 = 0;
    public static double capacityload1=0;
    public static double capacityload2=0;
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

        colIDrequestDevice1.setCellValueFactory(new PropertyValueFactory<Request,String>("id"));
        colIDoperatorDevice1.setCellValueFactory(new PropertyValueFactory<Request,String>("operatorID"));
        colTimeDevice1.setCellValueFactory(new PropertyValueFactory<Request,String>("time"));
        colStatDevice1.setCellValueFactory(new PropertyValueFactory<Request,String>("status"));

        colIDrequestDevice2.setCellValueFactory(new PropertyValueFactory<Request,String>("id"));
        colIDoperatorDevice2.setCellValueFactory(new PropertyValueFactory<Request,String>("operatorID"));
        colTimeDevice2.setCellValueFactory(new PropertyValueFactory<Request,String>("time"));
        colStatDevice2.setCellValueFactory(new PropertyValueFactory<Request,String>("status"));

        requestQueueImpl.addListener();
        createOperators(3);
        createDevices(2);
        createProgressBar(2);
    }

    private void createProgressBar(int i) {
        for(int j = 0;j<i;j++) {
            TimerTask timerTask = new ProgressBarThread(deviceArrayList.get(j));
            Timer timer = new Timer(true);
            timer.scheduleAtFixedRate(timerTask, 0, 10);
        }
    }

    private synchronized void setprogressbar1(double i) {
        progressBar1.setProgress( i);
    }


    private synchronized void setprogressbar2(double i) {
        progressBar2.setProgress(i);
    }

    private void createDevices(int count) {
        for(int i=0;i<count;i++){
            if(i==0){
                Device device = new Device(i,Integer.parseInt(txtDeviceCapacity1.getText()));
                deviceArrayList.add(device);
            }
            else {
                Device device = new Device(i,Integer.parseInt(txtDeviceCapacity2.getText()));
                deviceArrayList.add(device);
            }

            DeviceTask deviceTask = new DeviceTask(i);
            Timer deviceTimer = new Timer(true);
            deviceTimer.scheduleAtFixedRate(deviceTask, 0, 10);
        }
    }

    private void createOperators(int count) {
        for(int i=0;i<count;i++){
            Operator operator = new Operator(i);
            operatorArrayList.add(operator);
            OperatorTask operatorTask = new OperatorTask(i);
            Timer operatorTimer = new Timer(true);
            operatorTimer.scheduleAtFixedRate(operatorTask,0,10);
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

        tableDataStorage1.setItems(deviceArrayList.get(0).getRequestDeviceQueueObservableList());
        tableDataStorage2.setItems(deviceArrayList.get(1).getRequestDeviceQueueObservableList());

    }

    public class ProgressBarThread extends TimerTask {
        Device device;
        public ProgressBarThread(Device device){
            this.device = device;
        }

        private synchronized double getCapacityLoad(){
            double notcomplete = 0;
            for(int i=0;i<device.getRequestDeviceQueueObservableList().size();i++){
                if(device.getRequestDeviceQueueObservableList().get(i).getStatus()=="в обработке"){
                    notcomplete++;
                }
            }
            if(device.getID()==0){
                capacityload1=notcomplete/device.getCapacity();
                return capacityload1;
            }
            else{
                capacityload2=notcomplete/device.getCapacity();
                return capacityload2;
            }

        }

        @Override
        public void run() {
            if(device.getRequestDeviceQueueObservableList().size()>0){
                deviceCapacity1 = Integer.parseInt(txtDeviceCapacity1.getText());
                deviceCapacity2 = Integer.parseInt(txtDeviceCapacity2.getText());
                if(device.getID()==0){
                    serviceTime1 = (int)slider1.getValue();
                    device.setServiceTime(serviceTime1);
                    setprogressbar1(getCapacityLoad());

                }
                else{
                    serviceTime2 = (int)slider2.getValue();
                    device.setServiceTime(serviceTime2);
                    setprogressbar2(getCapacityLoad());
                }
            }
        }
    }


}

