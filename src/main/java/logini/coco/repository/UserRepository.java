package logini.coco.repository;

import logini.coco.entity.coMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//ibatis, MyBatis에서 DAO라 불리는 DB Layer 접근자
public interface UserRepository extends JpaRepository<coMember, Long> {
    Optional<coMember> findByEmail(String email);

    List<coMember> findAll();

    boolean existsByName(String name);
}
