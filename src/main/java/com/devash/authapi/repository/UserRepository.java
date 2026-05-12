package com.devash.authapi.repository;
import java.util.UUID;
import com.devash.authapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
    Boolean existsByUsername(String username);
}
