package Server.Repository;

import Server.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findOneByUsername(String username);

}


