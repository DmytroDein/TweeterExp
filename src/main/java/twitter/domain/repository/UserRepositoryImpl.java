package twitter.domain.repository;


import org.springframework.stereotype.Repository;
import twitter.domain.User;

import java.util.*;

@Repository("UserRepository")
public class UserRepositoryImpl implements UserRepository {
    private Set<User> users = new HashSet<>();

    @Override
    public User getUser(long userId) {
        return users.stream()
                .filter(e -> e.getUserId() == userId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public User getUser(String name) {
        for (Iterator<User> it = users.iterator(); it.hasNext(); ) {
            User user = it.next();
            if (user.getUserName().equals(name)){
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(users);
    }
}
