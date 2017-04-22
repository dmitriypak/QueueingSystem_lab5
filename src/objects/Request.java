package objects;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by HP on 22.04.2017.
 */
public class Request {
    private SimpleStringProperty id = new SimpleStringProperty("");
    private SimpleStringProperty time = new SimpleStringProperty("");
    private SimpleStringProperty status = new SimpleStringProperty("");

    public Request(String id, String time, String status){
        this.id = new SimpleStringProperty(id);
        this.time = new SimpleStringProperty(time);
        this.status = new SimpleStringProperty(status);
    }

    public void setStatus(String status){
        this.id.set(status);
    }
    public void setTime(String time){
        this.time.set(time);
    }
    public String getId(){
        return id.get();
    }

    public SimpleStringProperty statusProperty(){
        return status;
    }

    public SimpleStringProperty timeProperty(){
        return time;
    }
}
