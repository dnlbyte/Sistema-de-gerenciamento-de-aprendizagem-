package com.ikmed.LearningManagement.repository;
import com.ikmed.LearningManagement.model.ContentModel;
import com.ikmed.LearningManagement.model.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ikmed.LearningManagement.model.ModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ModuleRepository extends JpaRepository<ModuleModel, UUID>{

    Optional<ModuleModel> findByTitleAndIsActiveTrue(String title);

    Set<ModuleModel> findByIsActiveTrue();
}
