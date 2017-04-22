package interfaces;

import objects.Request;

/**
 * Created by HP on 22.04.2017.
 */
public interface RequestQueue {
    void add(Request request);
    void delete(Request request);
}
