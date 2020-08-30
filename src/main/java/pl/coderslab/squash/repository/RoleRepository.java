package pl.coderslab.squash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import pl.coderslab.squash.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String name);

}
