package logini.coco.repository;

import logini.coco.entity.coMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<coMember, Long> {
    Optional<coMember> findByEmail(String email);
}
