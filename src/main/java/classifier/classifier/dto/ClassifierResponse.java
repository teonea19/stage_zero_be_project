package classifier.classifier.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassifierResponse {
    private String status;
    private DataObj data;

    @Data
    @Builder
    public static class DataObj {
        private String name;
        private String gender;
        private Double probability;
        private Integer sample_size;
        private Boolean is_confident;
        private String processed_at;
    }
}