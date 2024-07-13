package kr.co.polycube.backendtest.user.service;

import kr.co.polycube.backendtest.user.dto.User;
import kr.co.polycube.backendtest.user.dto.PostUserRequest;

public interface UserService {
    User createUser(PostUserRequest postUserRequest);

    User getUserById(Long id);

    User updateUser(Long id, PostUserRequest userRequest);
}
