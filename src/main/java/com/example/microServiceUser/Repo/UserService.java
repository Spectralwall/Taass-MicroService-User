package com.example.microServiceUser.Repo;

import com.example.microServiceUser.Model.User;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



}
