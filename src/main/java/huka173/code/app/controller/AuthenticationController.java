package huka173.code.app.controller;

import huka173.code.app.dto.AuthRequest;
import huka173.code.app.dto.user.UserCreateDTO;
import huka173.code.app.sevice.UserService;
import huka173.code.app.util.JWTUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="/api")
public class AuthenticationController {
    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/login")
    public String login(@RequestBody AuthRequest authRequest) {
        var authentication = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword());

        authenticationManager.authenticate(authentication);

        var token = jwtUtils.generateToken(authRequest.getUsername());
        return token;
    }

//    @PostMapping(path = "/register")
//    @ResponseStatus(HttpStatus.CREATED)
//    public String register(@Valid @RequestBody UserCreateDTO dto) {
//        var user = userService.create(dto);
//        var authentication = new UsernamePasswordAuthenticationToken(
//                dto.getEmail(),
//                dto.getPassword());
//
//        authenticationManager.authenticate(authentication);
//
//        var token = jwtUtils.generateToken(user.getEmail());
//
//        return token;
//    }
}
