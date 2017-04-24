package objects;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by HP on 22.04.2017.
 */
public class Request {
    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Request.count = count;
    }

    private static int count;
    private SimpleStringProperty id = new SimpleStringProperty("");
    private SimpleStringProperty time = new SimpleStringProperty("");
    private SimpleStringProperty status = new SimpleStringProperty("");

    public String getOperatorID() {
        return operatorID.get();
    }

    public void setOperatorID(String operatorID) {
        this.operatorID.set(operatorID);
    }

    public String getStatus() {
        return status.get();
    }

    private SimpleStringProperty operatorID = new SimpleStringProperty("");

    public Request(String id, String time, String status){
        this.id = new SimpleStringProperty(id);
        this.time = new SimpleStringProperty(time);
        this.status = new SimpleStringProperty(status);
    }

    public Request(String id, String time, String status, String operatorID){
        this.id = new SimpleStringProperty(id);
        this.time = new SimpleStringProperty(time);
        this.status = new SimpleStringProperty(status);
        this.operatorID = new SimpleStringProperty(operatorID);
    }


    public void setStatus(String status){
        this.status.set(status);
    }
    public void setTime(String time){
        this.time.set(time);
    }
    public String getId(){
        return id.get();
    }

    public SimpleStringProperty idProperty(){
        return id;
    }
    public SimpleStringProperty statusProperty(){
        return status;
    }
    public SimpleStringProperty timeProperty(){
        return time;
    }
    public SimpleStringProperty operatorIDProperty() {
        return operatorID;
    }
}
