package cl.prezdev.port;

import cl.prezdev.model.response.AddAvlResponse;
import cl.prezdev.model.response.RemoveAllResponse;
import cl.prezdev.model.response.StatResponse;

public interface AvlClientPort {
    AddAvlResponse addAvls(String type, int count);

    StatResponse getStats();
    
    RemoveAllResponse removeAll();
}
