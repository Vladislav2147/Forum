package com.twopythons.forum.model.service.impl;

import com.twopythons.forum.model.entity.Role;
import com.twopythons.forum.model.entity.User;
import com.twopythons.forum.model.repository.RoleRepository;
import com.twopythons.forum.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl extends ServiceCommonImpl<User, UserRepository> implements UserDetailsService {

    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {

        super(repository);
        this.roleRepository = roleRepository;
        this.encoder = encoder;

    }

    public void update(User user) {
        repository.save(user);
    }

    @Override
    public void create(User user) {

        user.setPassword(encoder.encode(user.getPassword()));
        super.create(user);

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = repository.findByLogin(s);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;

    }

    @Transactional
    public void addRole(Long id, String roleName) {

        Optional<Role> newRole = roleRepository.findByName(roleName);
        newRole.ifPresent(role -> getById(id).ifPresent(user -> user.getRoles().add(role)));

    }

    @Transactional
    public void removeRole(Long id, String roleName) {

        getById(id).ifPresent(user -> user.getRoles().removeIf(role -> role.getName().equals(roleName)));

    }

    @Transactional
    public void banById(Long id) {
        getById(id).ifPresent(user -> user.setNonLocked(false));
    }

    @Transactional
    public void unbanById(Long id) {
        getById(id).ifPresent(user -> user.setNonLocked(true));
    }

    public void updatePassword(Long id, String newPassword) {

        getById(id).ifPresent(user -> {
            user.setPassword(encoder.encode(newPassword));
            update(user);
        });

    }
}
