package classifier.classifier.controller;

import classifier.classifier.service.ClassifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ClassifierController {

    @Autowired
    private ClassifierService classifierService;

    @GetMapping("/classify")
    public ResponseEntity<?> classify(@RequestParam(required = false) Object name) {

        if (name == null || name.toString().trim().isEmpty()) {
            return ResponseEntity.status(400)
                    .body(Map.of("status", "error", "message", "Name is required"));
        }

        if (!(name instanceof String)) {
            return ResponseEntity.status(422)
                    .body(Map.of("status", "error", "message", "Name must be a string"));
        }

        return ResponseEntity.ok(classifierService.classify(name.toString()));
    }
}
