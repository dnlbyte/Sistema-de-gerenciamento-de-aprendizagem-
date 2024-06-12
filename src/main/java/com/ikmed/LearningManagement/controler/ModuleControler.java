package com.ikmed.LearningManagement.controler;


import com.ikmed.LearningManagement.DTO.ModuleDTO;
import com.ikmed.LearningManagement.form.ModuleForm;
import com.ikmed.LearningManagement.model.ModuleModel;
import com.ikmed.LearningManagement.service.ModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/model")
public class ModuleControler {
    ModuleService moduleService;
    public ModuleControler(ModuleService moduleService){ this.moduleService = moduleService;}

    @GetMapping
    public ResponseEntity<Set<ModuleDTO>> getAllModules(){
        Set<ModuleDTO> moduleDtoSet = moduleService.getAllModules();
        return ResponseEntity.ok().body(moduleDtoSet);
    }

    @GetMapping("/{title}")
    public ResponseEntity<ModuleDTO> getModuleByTittle(@PathVariable("title") String title){
        ModuleDTO moduleDTO = moduleService.getModuleByTittle(title);
        return ResponseEntity.ok().body(moduleDTO);
    }

    @PostMapping("/{title}")
    public ResponseEntity<ModuleDTO> insertModule(ModuleForm moduleForm){
        ModuleDTO moduleDTO = moduleService.insertModule(moduleForm);

        return  ResponseEntity.ok().body(moduleDTO);
    }

    @PutMapping("/{title}")
    public ResponseEntity<ModuleDTO> updateModule(ModuleForm moduleForm){
        ModuleDTO moduleDTO = moduleService.updateModule(moduleForm.getTitle(), moduleForm);

        return ResponseEntity.ok().body(moduleDTO);
    }

    @DeleteMapping(("/{title}"))
    public ResponseEntity<ModuleDTO> deleteModule(@PathVariable("name") String title){
        moduleService.deleteModule(title);
        return ResponseEntity.noContent().build();
    }


}
