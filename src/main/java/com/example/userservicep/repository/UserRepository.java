package com.example.userservicep.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.userservicep.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // <--- This line is important
}

