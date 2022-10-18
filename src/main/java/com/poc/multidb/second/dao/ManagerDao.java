package com.poc.multidb.second.dao;

import com.poc.multidb.second.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerDao extends JpaRepository<ManagerEntity, Long> {
}
