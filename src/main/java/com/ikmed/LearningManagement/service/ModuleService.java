package com.ikmed.LearningManagement.service;

import com.ikmed.LearningManagement.DTO.ModuleDTO;
import com.ikmed.LearningManagement.form.ModuleForm;
import com.ikmed.LearningManagement.mapper.ModuleMapper;
import com.ikmed.LearningManagement.model.ModuleModel;
import com.ikmed.LearningManagement.repository.ModuleRepository;
import com.ikmed.LearningManagement.service.exceptions.ModuleInsertException;
import com.ikmed.LearningManagement.service.exceptions.ModuleNotFoundException;
import com.ikmed.LearningManagement.service.exceptions.ModuleUpdateException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final ModuleMapper moduleMapper;

    public ModuleService(ModuleRepository moduleRepository, ModuleMapper moduleMapper){
        this.moduleRepository = moduleRepository;
        this.moduleMapper =  moduleMapper;
    }

        public Set<ModuleDTO> getAllModules(){
            Set<ModuleModel> moduleModelSet = moduleRepository.findByIsActiveTrue();
            if(moduleModelSet.isEmpty()){
                throw new ModuleNotFoundException("no active modules found");
            }
            return ModuleMapper.INSTANCE.setModelToSetDto(moduleModelSet);
        }

        public ModuleDTO getModuleByTittle(String title){
            ModuleModel moduleModel = findByTitleAndIsActiveTrue(title);

            return ModuleMapper.INSTANCE.ModelToDto(moduleModel);
        }

        @Transactional
        public ModuleDTO insertModule(ModuleForm moduleForm){
            if(isModuleExists(moduleForm.getTitle())){
                throw new ModuleInsertException(String.format("The module ‘%s’ is already registered", moduleForm.getTitle()));
            }
            try {
                ModuleModel moduleModel = ModuleMapper.INSTANCE.formToModel(moduleForm);
                Date date = new Date();

                moduleModel.setVersion(1);
                moduleModel.setActive(true);
                moduleModel.setUpdatedAt(date);
                moduleModel.setTitle(moduleForm.getTitle());
                moduleRepository.save(moduleModel);
                return ModuleMapper.INSTANCE.ModelToDto(moduleModel);
            }catch (DataIntegrityViolationException err){
                throw new ModuleInsertException(String.format("Fail to ‘%s’register Module", moduleForm.getTitle()));
            }
        }
        @Transactional
        public ModuleDTO updateModule(String tittle , ModuleForm moduleForm){
            try{
                Date date = new Date();

                ModuleModel moduleModel = findByTitleAndIsActiveTrue(tittle);
                moduleModel.setTitle(moduleForm.getTitle());
                moduleModel.setUpdatedAt(date);

                return ModuleMapper.INSTANCE.ModelToDto(moduleModel);
            }catch (DataIntegrityViolationException err){
                throw new ModuleUpdateException(String.format("fail to update Module ‘%s’ ", moduleForm.getTitle()));
            }
        }
        @Transactional
        public void deleteModule(String title){
            try{
                ModuleModel moduleModel = findByTitleAndIsActiveTrue(title);
                moduleModel.setActive(false);
                moduleRepository.save(moduleModel);
            }catch (DataIntegrityViolationException err){
                throw new ModuleUpdateException(String.format("fail to delete Module ‘%s’ ", title));
            }
        }

        public Boolean isModuleExists(String tittle){
            try{
                moduleRepository.findByTitleAndIsActiveTrue(tittle);
                return true;
            }catch(ModuleNotFoundException err){
                return false;
            }
        }
        public ModuleModel findByTitleAndIsActiveTrue(String title){
            return moduleRepository.findByTitleAndIsActiveTrue(title).orElseThrow(() -> new ModuleNotFoundException(String.format("The module ‘%s’ is already registered", title)));
        }
}

