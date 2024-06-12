package com.ikmed.LearningManagement.service;

import com.ikmed.LearningManagement.DTO.ContentDTO;
import com.ikmed.LearningManagement.DTO.ModuleDTO;
import com.ikmed.LearningManagement.form.ContentForm;
import com.ikmed.LearningManagement.form.ContentUpdateForm;
import com.ikmed.LearningManagement.form.ModuleForm;
import com.ikmed.LearningManagement.mapper.ContentMapper;
import com.ikmed.LearningManagement.mapper.ModuleMapper;
import com.ikmed.LearningManagement.model.ContentModel;
import com.ikmed.LearningManagement.model.ModuleModel;
import com.ikmed.LearningManagement.repository.ContentRepository;
import com.ikmed.LearningManagement.repository.ModuleRepository;
import com.ikmed.LearningManagement.service.exceptions.ContentUpdateException;
import com.ikmed.LearningManagement.service.exceptions.ModuleInsertException;
import com.ikmed.LearningManagement.service.exceptions.ModuleNotFoundException;
import com.ikmed.LearningManagement.service.exceptions.ModuleUpdateException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class ContentService {
    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;

    public ContentService(ContentRepository contentRepository, ContentMapper contentMapper){
        this.contentRepository = contentRepository;
        this.contentMapper =  contentMapper;
    }

        public Set<ContentDTO> getAllContents(){
            Set<ContentModel> contentModelSet = contentRepository.findByIsActiveTrue();
            if(contentModelSet.isEmpty()){
                throw new ModuleNotFoundException("no active modules found");
            }
            return ContentMapper.INSTANCE.setModelToSetDto(contentModelSet);
        }

        public ContentDTO getContentByTittle(String title){
            ContentModel contentModel = findByTitleAndIsActiveTrue(title);

            return ContentMapper.INSTANCE.ModelToDto(contentModel);
        }

        @Transactional
        public ContentDTO insertContent(ContentForm contentForm){
            if(isContentExists(contentForm.getTitle())){
                throw new ModuleInsertException(String.format("The module ‘%s’ is already registered", contentForm.getTitle()));
            }
            try {
                ContentModel contentModel = ContentMapper.INSTANCE.formToModel(contentForm);
                Date date = new Date();

                contentModel.setVersion(1);
                contentModel.setActive(true);
                contentModel.setUpdatedAt(date);
                contentModel.setTitle(contentForm.getTitle());
                contentRepository.save(contentModel);
                return ContentMapper.INSTANCE.ModelToDto(contentModel);
            }catch (DataIntegrityViolationException err){
                throw new ModuleInsertException(String.format("Fail to ‘%s’register Module", contentForm.getTitle()));
            }
        }
        @Transactional
        public ContentDTO updateContent(String tittle , ContentUpdateForm contentUpdateForm){
            try{
                Date date = new Date();

                ContentModel contentModel = findByTitleAndIsActiveTrue(tittle);
                contentModel.setTitle(contentUpdateForm.getTitle());
                contentModel.setUpdatedAt(date);

                return ContentMapper.INSTANCE.ModelToDto(contentModel);
            }catch (DataIntegrityViolationException err){
                throw new ContentUpdateException(String.format("fail to update Module ‘%s’ ", contentUpdateForm.getTitle()));
            }
        }
        @Transactional
        public void deleteContent(String title){
            try{
                ContentModel contentModel = findByTitleAndIsActiveTrue(title);
                contentModel.setActive(false);
                contentRepository.save(contentModel);
            }catch (DataIntegrityViolationException err){
                throw new ModuleUpdateException(String.format("fail to delete Module ‘%s’ ", title));
            }
        }

        public Boolean isContentExists(String tittle){
            try{
                 contentRepository.findByTitleAndIsActiveTrue(tittle);
                return true;
            }catch(ModuleNotFoundException err){
                return false;
            }
        }
        public ContentModel findByTitleAndIsActiveTrue(String title){
            return contentRepository.findByTitleAndIsActiveTrue(title).orElseThrow(() -> new ModuleNotFoundException(String.format("The content ‘%s’ is already registered", title)));
        }
}

