package br.com.proprietyservice.repository;

import br.com.proprietyservice.model.Propriety;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProprietyRepository extends
        JpaRepository<Propriety, Long> {
     List<Propriety> findByUserIdIgnoreCaseContaining(String userId);

  }
