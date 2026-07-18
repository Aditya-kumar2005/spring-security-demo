package spring.ai.example.spring_ai_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.ai.example.spring_ai_demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	User findByUserName(String userName);

}
