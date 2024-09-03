package spring.boot.project3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.project3.Model.User;
@Repository
public interface AuthRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);

    User findUserByUsername(String username);
}
