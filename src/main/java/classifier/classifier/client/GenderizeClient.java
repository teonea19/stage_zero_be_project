package classifier.classifier.client;


import classifier.classifier.dto.GenderizeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GenderizeClient {

    @Autowired
    private RestTemplate restTemplate;

    public GenderizeResponse getGender(String name){
        String url = "https://api.genderize.io?name=" + name;
        return restTemplate.getForObject(url, GenderizeResponse.class);

    }
}
