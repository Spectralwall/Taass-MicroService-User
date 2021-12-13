package com.example.microServiceUser.Controller;

import com.example.microServiceUser.Model.User;
import com.example.microServiceUser.Repo.UserRepository;
import com.example.microServiceUser.Repo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserRepository userRepository;
    UserService userService;

    //metodo che ritorna tutti gli utenti
    @GetMapping("/users")
    public List<User> listAll(){
        return (List<User>) userRepository.findAll();
    }

    //metodo per inserie un nuovo utente
    @PostMapping(value = "/users/create")
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody User user){
        //controllo se la mail è gia presente
        Optional<User> customerOptional = userRepository.findByMail(user.getEmail());
        if(customerOptional.isPresent()){//in caso ritorno un errore
            throw new IllegalStateException("mail taken");
        }
        userRepository.save(user);//se non è presente salvo
    }

    //metodo che elimina un utente
    // BASTA INVIARE LA MAIL MA SI PUO MODIFICARE SE SERVONO ALTRI DATI
    @DeleteMapping("/users/delete")
    public ResponseEntity<String> deleteCustomer(@RequestBody String mail) {
        Optional<User> customerOptional = userRepository.findByMail(mail);
        if(customerOptional.isPresent()){//in caso ritorno un errore
            userRepository.deleteById(customerOptional.get().getId());
        }
        return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
    }

    //metodo per pulire il db, TOGLIE TUTTI GLI UTENTI
    @DeleteMapping("/users/deleteAll")
    public ResponseEntity<String> deleteAllCustomers() {
        System.out.println("Delete All Customers...");
        userRepository.deleteAll();//cancelliamo tutti gli utenti
        return new ResponseEntity<>("All customers have been deleted!", HttpStatus.OK);
    }

    //metodi per aggiornare un utente
    @PostMapping(value = "/users/changeMail")
    public ResponseEntity<String> changeEmail(@RequestBody String oldMail,String newMail){
        Optional<User> customerOptional = userRepository.findByMail(oldMail);
        userService.updateUserEmail(customerOptional.get(),newMail);
        return new ResponseEntity<>("email changed", HttpStatus.OK);
    }

    @PostMapping(value = "/users/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody String mail,String pass){
        Optional<User> customerOptional = userRepository.findByMail(mail);
        userService.updateUserPassword(customerOptional.get(),pass);
        return new ResponseEntity<>("password changed", HttpStatus.OK);
    }

}
