package com.autogarage.dao.part;

import com.autogarage.model.domain.part.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartDao extends JpaRepository<Part, Integer> {
}
