package cl.prezdev.port;

import cl.prezdev.model.response.AddAvlResponse;

public interface AvlClientPort {
    AddAvlResponse addAvls(String type, int count);
}
