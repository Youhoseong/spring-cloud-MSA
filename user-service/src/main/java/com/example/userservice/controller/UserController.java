package com.example.userservice.controller;


import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {
    private final Environment env;
    private final Greeting greeting;
    private final UserService userService;

    @GetMapping("/health-check")
    public String status() {
        return "It's Working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome() {
        //return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@Valid @RequestBody RequestUser requestUser) {
        ModelMapper modelMapper =  new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(requestUser, UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = modelMapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

}
