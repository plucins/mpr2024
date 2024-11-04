package pl.edu.pjwstk.mpr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.pjwstk.mpr.model.User;
import pl.edu.pjwstk.mpr.model.dto.ResponseDto;
import pl.edu.pjwstk.mpr.model.dto.UserCreateDto;
import pl.edu.pjwstk.mpr.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody UserCreateDto user){
        return ResponseEntity.ok(userService.createUser(new User(user.getEmail())));
    }

    @GetMapping("/cc")
    public ResponseEntity<List<User>> get(){
        userService.createUser(new User("email@email.com"));
        userService.createUser(new User("email@email.com"));
        userService.createUser(new User("email@email.com"));
        userService.createUser(new User("email@email.com"));
        userService.createUser(new User("email@email.com"));
        userService.createUser(new User("email@email.com"));
        userService.createUser(new User("email@email.com"));

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/")
    public ResponseEntity<ResponseDto<List<User>>> getAllUsers(){
        ResponseDto<List<User>> responseDto = new ResponseDto<>();
        responseDto.setBody(userService.getAllUsers());
        responseDto.setStatus(HttpStatus.OK);
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<User>> getUserById(@PathVariable("id") Long id){
        ResponseDto<User> responseDto = new ResponseDto<>();
        try {
            responseDto.setBody(userService.getUserById(id));
        }catch (IllegalArgumentException e){
            List<String> errors = responseDto.getErrors();
            errors.add(e.getMessage());
            responseDto.setErrors(errors);
            responseDto.setStatus(HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(responseDto);
        }
        responseDto.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(responseDto);
    }
}
