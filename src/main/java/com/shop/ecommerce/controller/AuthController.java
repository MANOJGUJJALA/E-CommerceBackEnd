package com.shop.ecommerce.controller;


import com.shop.ecommerce.config.JwtTokenProvider;
import com.shop.ecommerce.exception.UserException;
import com.shop.ecommerce.modal.User;
import com.shop.ecommerce.repository.UserRepository;
import com.shop.ecommerce.response.AuthResponse;
import com.shop.ecommerce.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private JwtTokenProvider jwtTokenProvider;

    private CartService cartService;

    public AuthController(UserRepository userRepository,PasswordEncoder passwordEncoder,CartService cartService,JwtTokenProvider jwtTokenProvider){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.cartService=cartService;
        this.jwtTokenProvider=jwtTokenProvider;

    }



    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@Valid @RequestBody User user) throws UserException {

        String email = user.getEmail();
        String password = user.getPassword();
        String firstName=user.getFirstName();
        String lastName=user.getLastName();

        User isEmailExist=userRepository.findByEmail(email);

        // Check if user with the given email already exists
        if (isEmailExist!=null) {
            // System.out.println("--------- exist "+isEmailExist).getEmail());

            throw new UserException("Email Is Already Used With Another Account");
        }

        User createdUser= new User();
        createdUser.setEmail(email);
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);

        createdUser.setPassword(passwordEncoder.encode(password));

        User savedUser= userRepository.save(createdUser);

        cartService.createCart(savedUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        AuthResponse authResponse= new AuthResponse(token,true);

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

    }

}
