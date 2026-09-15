
package lk.ijse.mental_hospital.security;

import lk.ijse.mental_hospital.entity.User;
import lk.ijse.mental_hospital.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByUserName(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = optionalUser.get();

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getUserPassword())
                .roles(user.getRole().getRoleName())
                .build();
    }
}

