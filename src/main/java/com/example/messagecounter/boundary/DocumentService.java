package com.example.messagecounter.boundary;

import java.util.Map;
import java.util.stream.Collectors;

import com.example.messagecounter.entity.Document;
import com.example.messagecounter.entity.DocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentService {

    @Autowired
    DocumentRepository mr;

    @PostMapping("/document")
    // example: { "id": "123", "message": "hello world" }
    public Map<String,Integer> document(@RequestBody Document payload){
        var documentOpt = mr.findById(payload.getId());
        if(!documentOpt.isPresent()){
            mr.save(payload);
        }

        return Map.of("count", getAllDocumentMessageCount());
    }

    private Integer getAllDocumentMessageCount() {
        return mr.findAll().stream()//
            .map(m -> m.getMessage().split(" ").length)
            .collect(Collectors.summingInt(Integer::intValue));
    }
    
}
