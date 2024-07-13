package kr.co.polycube.backendtest.user.service;

import kr.co.polycube.backendtest.user.dto.User;
import kr.co.polycube.backendtest.user.dto.PostUserRequest;
import kr.co.polycube.backendtest.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(PostUserRequest postUserRequest) {
        User user = new User();
        user.setName(postUserRequest.getName());

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long id, PostUserRequest userRequest) {

        User originUser = userRepository.findById(id).orElse(null);

        if(originUser != null){
            originUser.setName(userRequest.getName());

            return userRepository.save(originUser);
        }
        else {

            return null;
        }
    }
}
