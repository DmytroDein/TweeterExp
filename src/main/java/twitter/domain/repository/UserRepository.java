package twitter.domain.repository;


import twitter.domain.User;

import java.util.List;

public interface UserRepository {
    void save(User user);
    User getUser(String name);
    User getUser(long userId);
    List<User> findAllUsers();
}
