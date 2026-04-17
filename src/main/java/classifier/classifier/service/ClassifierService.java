package classifier.classifier.service;


import classifier.classifier.client.GenderizeClient;
import classifier.classifier.dto.ClassifierResponse;
import classifier.classifier.dto.GenderizeResponse;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ClassifierService {

    @Autowired
    private GenderizeClient genderizeClient;

    public ClassifierResponse classify(String name) {

        GenderizeResponse res;

        try {
            res = genderizeClient.getGender(name);
        } catch (Exception e) {
            throw new RuntimeException("External service error");
        }

        if (res == null || res.getGender() == null || res.getCount() == null || res.getCount() == 0) {
            throw new RuntimeException("No prediction available for the provided name");
        }

        Double probability = res.getProbability() != null ? res.getProbability() : 0.0;
        Integer count = res.getCount() != null ? res.getCount() : 0;

        boolean isConfident = probability >= 0.7 && count >= 100;

        return ClassifierResponse.builder()
                .status("success")
                .data(ClassifierResponse.DataObj.builder()
                        .name(res.getName())
                        .gender(res.getGender())
                        .probability(probability)
                        .sample_size(count)
                        .is_confident(isConfident)
                        .processed_at(Instant.now().toString())
                        .build())
                .build();
    }
}
