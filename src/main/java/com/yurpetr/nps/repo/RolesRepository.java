package com.yurpetr.nps.repo;

import com.yurpetr.nps.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role,Integer> {
   Optional<Role> findByName(String name);
}
