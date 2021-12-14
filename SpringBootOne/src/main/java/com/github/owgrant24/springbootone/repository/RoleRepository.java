package com.github.owgrant24.springbootone.repository;

import com.github.owgrant24.springbootone.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
