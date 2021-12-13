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

    //metoto per aggiornare tutti i parametri di un utente
    @Transactional
    public void updateUser(Long id, String name, String surname, String mail, String password){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalStateException("" +
                "customers with id" + id + "does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(user.getName(),name)){
            user.setName(name);
        }
        if(surname != null && surname.length() > 0 && !Objects.equals(user.getSurname(),surname)){
            user.setSurname(surname);
        }
        if(mail != null && mail.length() > 0 && !Objects.equals(user.getEmail(),mail)){
            Optional<User> userOptional = userRepository.findByMail(mail);
            if(userOptional.isPresent()){
                throw  new IllegalStateException("email exist");
            }
            user.setEmail(mail);
        }
        if(password != null && password.length() > 0 && !Objects.equals(user.getPassword(),password)){
            user.setPassword(password);
        }
    }

    //aggiornare la mail
    @Transactional
    public void updateUserEmail(User user,String mail){
        if(mail != null && mail.length() > 0 && !Objects.equals(user.getEmail(),mail)){
            Optional<User> userOptional = userRepository.findByMail(mail);
            if(userOptional.isPresent()){
                throw  new IllegalStateException("email exist");
            }else {
                user.setEmail(mail);
            }
        }
    }

    //aggiornare il nome
    @Transactional
    public void updateUserName(Long id,String name){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalStateException("" +
                "customers with id" + id + "does not exist"));
        if(name != null && name.length() > 0 && !Objects.equals(user.getName(),name)){
            user.setName(name);
        }
    }

    //aggiornare il cognome
    @Transactional
    public void updateUserSurName(Long id, String surname){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalStateException("" +
                "customers with id" + id + "does not exist"));
        if(surname != null && surname.length() > 0 && !Objects.equals(user.getSurname(),surname)){
            user.setSurname(surname);
        }
    }

    //aggiornare la password
    @Transactional
    public void updateUserPassword(User user, String password){
        if(password != null && password.length() > 0 && !Objects.equals(user.getPassword(),password)){
            user.setPassword(password);
        }
    }


}
