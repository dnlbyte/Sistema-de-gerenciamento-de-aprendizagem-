package com.ikmed.LearningManagement.controler;


import com.ikmed.LearningManagement.DTO.ContentDTO;
import com.ikmed.LearningManagement.DTO.ModuleDTO;
import com.ikmed.LearningManagement.form.ContentForm;
import com.ikmed.LearningManagement.form.ContentUpdateForm;
import com.ikmed.LearningManagement.form.ModuleForm;
import com.ikmed.LearningManagement.service.ContentService;
import com.ikmed.LearningManagement.service.ModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/content")
public class ContentControler {
    private final ContentService contentService;
    public ContentControler(ContentService contentService){ this.contentService = contentService;}

    @GetMapping
    public ResponseEntity<Set<ContentDTO>> getAllContents(){
        Set<ContentDTO> contentDTOS = contentService.getAllContents();
        return ResponseEntity.ok().body(contentDTOS);
    }

    @GetMapping("/{title}")
    public ResponseEntity<ContentDTO> getContentByTittle(@PathVariable("title") String title){
        ContentDTO contentDTO = contentService.getContentByTittle(title);
        return ResponseEntity.ok().body(contentDTO);
    }

    @PostMapping("/{title}")
    public ResponseEntity<ContentDTO> insertContent(ContentForm contentForm){
        ContentDTO contentDTO = contentService.insertContent(contentForm);

        return  ResponseEntity.ok().body(contentDTO);
    }

    @PutMapping("/{title}")
    public ResponseEntity<ContentDTO> updateContent(ContentUpdateForm contentUpdateForm){
        ContentDTO contentDTO = contentService.updateContent(contentUpdateForm.getTitle(), contentUpdateForm);

        return ResponseEntity.ok().body(contentDTO);
    }

    @DeleteMapping(("/{title}"))
    public ResponseEntity<ModuleDTO> deleteContent(@PathVariable("title") String title){
        contentService.deleteContent(title);
        return ResponseEntity.noContent().build();
    }


}
