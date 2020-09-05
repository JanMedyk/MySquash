package pl.coderslab.squash.User.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.squash.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String name);

}
