package com.cashwu.javatacocloud;

import com.cashwu.javatacocloud.model.MyUser;
import com.cashwu.javatacocloud.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author cash.wu
 * @since 2024/05/20
 */
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {

        return username -> {
            MyUser user = userRepository.findByUsername(username);

            if (user != null) {
                return user;
            }

            throw new UsernameNotFoundException("User not found : " + username);
        };
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/design", "/orders").hasRole("USER");
                    auth.anyRequest().permitAll();
                })
                .formLogin(formLogin -> {
                    formLogin.loginPage("/login");
                })
                .build();
    }

    //    @Bean
    //    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
    //        List<UserDetails> userList = new ArrayList<>();
    //
    //        userList.add(new User("cash",
    //                              encoder.encode("pw-1234"),
    //                              List.of(new SimpleGrantedAuthority("ROLE_USER"))));
    //
    //        return new InMemoryUserDetailsManager(userList);
    //    }
}
