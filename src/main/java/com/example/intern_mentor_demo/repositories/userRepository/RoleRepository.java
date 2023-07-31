package com.example.intern_mentor_demo.repositories.userRepository;

import com.example.intern_mentor_demo.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
