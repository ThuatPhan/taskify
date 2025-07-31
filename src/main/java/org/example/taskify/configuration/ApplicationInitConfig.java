package org.example.taskify.configuration;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.example.taskify.constant.PredefinedRole;
import org.example.taskify.entity.Role;
import org.example.taskify.entity.User;
import org.example.taskify.helpers.AvatarHelper;
import org.example.taskify.repository.RoleRepository;
import org.example.taskify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    String FIRSTNAME = "John";
    String LASTNAME = "Doe";

    @NonFinal
    @Value("${admin.username}")
    String USERNAME;

    @NonFinal
    @Value("${admin.password}")
    String PASSWORD;

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            boolean isAdminExists = userRepository.existsByUsername(USERNAME);
            if (isAdminExists) {
                return;
            }

            Role adminRole = roleRepository.save(
                    Role.builder().name(PredefinedRole.ADMIN).build());
            roleRepository.save(Role.builder().name(PredefinedRole.USER).build());

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            userRepository.save(User.builder()
                    .username(USERNAME)
                    .firstName(FIRSTNAME)
                    .lastName(LASTNAME)
                    .password(passwordEncoder.encode(PASSWORD))
                    .avatar(AvatarHelper.generateDefaultAvatar(FIRSTNAME, LASTNAME))
                    .roles(roles)
                    .build());

            log.warn("Admin account created with password: {}, please change it.", PASSWORD);
        };
    }
}
