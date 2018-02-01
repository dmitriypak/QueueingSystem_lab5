package objects;

import java.util.TimerTask;

import static controllers.Controller.capacityload1;
import static controllers.Controller.capacityload2;
import static controllers.Controller.deviceArrayList;

/**
 * Created by HP on 22.04.2017.
 */
public class DeviceTask extends TimerTask{

    private Device device;
    private int deviceID;

    public DeviceTask(int deviceID){
        this.device = deviceArrayList.get(deviceID);
        this.deviceID = deviceID;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public void run() {
        int serviceTime = 0;

        if(device.getRequestDeviceQueueObservableList().size()>0){

            for(int i=0;i<device.getRequestDeviceQueueObservableList().size();i++){
                if(device.getRequestDeviceQueueObservableList().get(i).getStatus()=="в обработке"){
                    if(device.getID()==0){
                        if(capacityload1<=1){
                            serviceTime=device.getServiceTime();
                            try {
                                Thread.sleep(serviceTime);
                                device.getRequestDeviceQueueObservableList().get(i).setStatus("обработана");
                                device.getRequestDeviceQueueObservableList().get(i).setTime(Integer.toString(serviceTime));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            device.getRequestDeviceQueueObservableList().get(i).setStatus("не обработана");
                        }
                    }
                    else{
                        if(capacityload2<=1){
                            serviceTime=device.getServiceTime();

                            try {
                                Thread.sleep(serviceTime);
                                device.getRequestDeviceQueueObservableList().get(i).setStatus("обработана");
                                device.getRequestDeviceQueueObservableList().get(i).setTime(Integer.toString(serviceTime));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            device.getRequestDeviceQueueObservableList().get(i).setStatus("не обработана");
                        }
                    }

                }
            }

        }

    }

}
