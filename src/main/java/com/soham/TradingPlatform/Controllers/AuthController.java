package com.soham.TradingPlatform.Controllers;

import com.soham.TradingPlatform.Config.JwtProvider;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Repository.UserRepository;
import com.soham.TradingPlatform.Response.AuthResponse;
import com.soham.TradingPlatform.Service.CustomUserDeatilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDeatilsService customUserDeatilsService;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception {





        User isEmailExsist= userRepository.findByEmail(user.getEmail());
        if(isEmailExsist!=null){
            throw new Exception("Email is already used with other account");
        }
        User newUser= new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setFullName(user.getFullName());

        User savedUser= userRepository.save(newUser);
        Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());

        SecurityContextHolder.getContext().setAuthentication(auth);


        String jwt= JwtProvider.generateToken(auth);


        AuthResponse res= new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("Registered succesfully");

        return new ResponseEntity<>(res, HttpStatus.CREATED);




    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) throws Exception {

        String userName=user.getEmail();
        String passsword=user.getPassword();

        Authentication auth = authenticate(userName,passsword);

        SecurityContextHolder.getContext().setAuthentication(auth);


        String jwt= JwtProvider.generateToken(auth);

        if(user.getTwoFactorAuth().isEnabled()){
            AuthResponse res= new AuthResponse();
            res.setMessage("Two factor auth is enabled");
            res.setTwoFactorAuthEnabled(true);



        }

        AuthResponse res= new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("Login succesfully");

        return new ResponseEntity<>(res, HttpStatus.CREATED);




    }

    private Authentication authenticate(String userName, String passsword) {
        UserDetails userDetails=customUserDeatilsService.loadUserByUsername(userName);
        if(userDetails==null){
            throw  new BadCredentialsException("Invalid username");
        }
        if(!passsword.equals(userDetails.getPassword())){
            throw new BadCredentialsException("invalid password");

        }
        return new UsernamePasswordAuthenticationToken(userDetails,passsword,userDetails.getAuthorities());






    }


}
