package com.alphaserve.test.repos;

import com.alphaserve.test.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository <User, Long> {
    User findByUsername(String username);
}
