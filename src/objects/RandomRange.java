package objects;

import java.util.Random;

/**
 * Created by HP on 22.04.2017.
 */
public class RandomRange {

    public synchronized static int getRandomInRange(int min, int max){
        Random r = new Random();
        return (r.nextInt((max-min)+1)+min);
    }
}
