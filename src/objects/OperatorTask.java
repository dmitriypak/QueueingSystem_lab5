package objects;

import java.util.TimerTask;

import static controllers.Controller.operatorArrayList;
import static interfaces.impl.CollectionRequestQueue.requestList;

/**
 * Created by HP on 22.04.2017.
 */
public class OperatorTask extends TimerTask{

    private int operatorID;

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    private Operator operator;

    public int getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(int operatorID) {
        this.operatorID = operatorID;
    }


    public OperatorTask(int operatorID){
        this.operator = operatorArrayList.get(operatorID);
        this.operatorID = operatorID;
    }

    @Override
    public void run() {

        if(operator.getRequestQueueObservableList().size()>=1){
            int index = operator.getRequestQueueObservableList().size() - 1;
            int id = Integer.parseInt(operator.getRequestQueueObservableList().get(index).getId());
            int serviceTime=0;
            if(operator.getRequestQueueObservableList().get(index).getStatus()=="в обработке"&&operator.getFree()==1){
                operator.setFree(0);
                switch (operatorID) {
                    case 0:
                        serviceTime = RandomRange.getRandomInRange(15, 25);
                        break;
                    case 1:
                        serviceTime = RandomRange.getRandomInRange(30, 50);
                        break;
                    case 2:
                        serviceTime = RandomRange.getRandomInRange(20, 60);
                        break;
                    default:
                        serviceTime = 100;
                        break;
                }
                try {
                    Thread.sleep(serviceTime*10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                operator.getRequestQueueObservableList().get(index).setStatus("обработана");
                requestList.get(id).setStatus("обработана");
                operator.getRequestQueueObservableList().get(index).setTime(Integer.toString(serviceTime));
                operator.setFree(1);
            }

        }

    }

}
