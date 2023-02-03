package com.autogarage.dao.repair;

import com.autogarage.model.domain.repair.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairDao extends JpaRepository<Repair, Integer> {
}
