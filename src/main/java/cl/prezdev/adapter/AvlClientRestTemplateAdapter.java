package cl.prezdev.adapter;

import cl.prezdev.model.response.AddAvlResponse;
import cl.prezdev.port.AvlClientPort;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class AvlClientRestTemplateAdapter implements AvlClientPort {

    private RestTemplate restTemplate;
    
    @Value("${avl.client.base-url}")
    private String baseUrl;

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
    }

    @Override
    public AddAvlResponse addAvls(String type, int count) {
        String url = UriComponentsBuilder
                .fromUriString(baseUrl + "/api/avl/add")
                .queryParam("type", type)
                .queryParam("count", count)
                .toUriString();

        return restTemplate.postForObject(url, null, AddAvlResponse.class);
    }
}
