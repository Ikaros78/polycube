package kr.co.polycube.backendtest.user.controller;

import kr.co.polycube.backendtest.exception.BadRequestException;
import kr.co.polycube.backendtest.exception.ResourceNotFoundException;
import kr.co.polycube.backendtest.user.dto.User;
import kr.co.polycube.backendtest.user.dto.PostUserRequest;
import kr.co.polycube.backendtest.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    //유저 등록
    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody PostUserRequest userRequest){

        if(userRequest.getName() == null || userRequest.getName().isEmpty()){
            throw new BadRequestException("공백이 아닌 이름을 입력해 주세요.");
        }

        User user = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("\"id\": " + user.getId());
    }

    //유저 조회
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id){

        User user = userService.getUserById(id);

        if (user == null){
            throw new ResourceNotFoundException("존재하지 않는 id입니다.");
        }

        return ResponseEntity.ok(user);
    }

    //유저 수정
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody PostUserRequest userRequest){

        User updateUser = userService.updateUser(id, userRequest);

        return ResponseEntity.ok(updateUser);
    }
}
