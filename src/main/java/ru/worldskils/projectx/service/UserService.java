package ru.worldskils.projectx.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.worldskils.projectx.DTO.LoginDTO;
import ru.worldskils.projectx.DTO.UserDTO;
import ru.worldskils.projectx.JWT.JWTUtil;
import ru.worldskils.projectx.exception.UserException;
import ru.worldskils.projectx.models.UserModel;
import ru.worldskils.projectx.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    protected final PasswordEncoder passwordEncoder;

    protected final UserRepository userRepository;

    protected final JWTUtil jwtUtil;

    public UserService(PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       JWTUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    public String login(LoginDTO l){

        UserModel uDb = userRepository.findByLogin(l.getLogin());

        if(uDb == null) throw new UserException.NoLoginException();

        if(!passwordEncoder.matches(l.getPassword(),uDb.getPassword())) throw new UserException.NoLoginException();

        String token = jwtUtil.generateToken(uDb.getLogin());

        return token;
    }

    public UserDTO registration(UserModel u){

        boolean uDb = userRepository.existsByLogin(u.getLogin());

        if(uDb) throw new UserException.ConflictLoginException();

        u.setPassword(passwordEncoder.encode(u.getPassword()));

        userRepository.save(u);

        return new UserDTO(
                u.getId(),
                u.getFirstName(),
                u.getLastName(),
                u.getLogin()
        );
    }
}
