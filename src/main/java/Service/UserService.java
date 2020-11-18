package Service;

import domain.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public String findAllJson();
}
