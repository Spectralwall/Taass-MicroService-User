package com.example.microServiceUser.Controller;

import com.example.microServiceUser.Model.User;
import com.example.microServiceUser.Repo.UserRepository;
import com.example.microServiceUser.Repo.UserService;
import com.example.microServiceUser.Utilities.UserModifier;
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
        System.out.println("Micro service Users");
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/users/login")
    public ResponseEntity<String> login(@RequestBody User user){
        System.out.println("Micro service Login");
        //controllo se la mail esiste e in caso mi faccio ritornare i dati dell'utente
        Optional<User> customerOptional = userRepository.findByMailAndPassword(user.getEmail(), user.getPassword());
        if(!customerOptional.isPresent()){//in caso ritorno un errore
            return new ResponseEntity<>("User doesn't exist", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("User logged", HttpStatus.OK);
    }

    @PostMapping("/users/data")//se al login e stata confermata l'esistenza di utente si chiama questo metodo che passa tutti i dati
    public ResponseEntity<User> returnData(@RequestBody String mail){
        System.out.println("Micro service Data");
        Optional<User> customerOptional = userRepository.findByMail(mail);
        return new ResponseEntity<User>(customerOptional.get(), HttpStatus.OK);
    }

    //metodo per inserie un nuovo utente
    @PostMapping(value = "/users/create")
    public ResponseEntity<String> create(@RequestBody User user){
        //controllo se la mail è gia presente
        System.out.println("Micro service create");
        Optional<User> customerOptional = userRepository.findByMail(user.getEmail());
        if(customerOptional.isPresent()){//in caso ritorno un errore
            return new ResponseEntity<>("Mail already in use", HttpStatus.CONFLICT);
        }
        userRepository.save(user);//se non è presente salvo
        return new ResponseEntity<>("User account added", HttpStatus.OK);
    }

    //metodo che elimina un utente
    // BASTA INVIARE LA MAIL MA SI PUO MODIFICARE SE SERVONO ALTRI DATI
    @DeleteMapping("/users/delete")
    public ResponseEntity<String> deleteCustomer(@RequestBody String mail) {
        System.out.println("Micro service Delete");
        Optional<User> customerOptional = userRepository.findByMail(mail);
        if(customerOptional.isPresent()){//in caso ritorno un errore
            userRepository.deleteById(customerOptional.get().getId());
        }
        return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
    }

    //metodo per pulire il db, TOGLIE TUTTI GLI UTENTI
    @DeleteMapping("/users/deleteAll")
    public ResponseEntity<String> deleteAllCustomers() {
        System.out.println("Micro service Delete All");
        System.out.println("Delete All Customers...");
        userRepository.deleteAll();//cancelliamo tutti gli utenti
        return new ResponseEntity<>("All customers have been deleted!", HttpStatus.OK);
    }

    //metodi per aggiornare un utente
    @PostMapping(value = "/users/changeMail")
    public ResponseEntity<String> changeEmail(@RequestBody UserModifier userModifier){
        System.out.println("Micro service change mail");
        Optional<User> customerOptional = userRepository.findByMail(userModifier.getMail());
        User tmp = customerOptional.get();
        tmp.setEmail(userModifier.getNewMail());
        userRepository.save(tmp);
        return new ResponseEntity<>("email changed", HttpStatus.OK);
    }

    @PostMapping(value = "/users/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody UserModifier userModifier){
        System.out.println("Micro service change Password");
        Optional<User> customerOptional = userRepository.findByMail(userModifier.getMail());
        User tmp = customerOptional.get();
        tmp.setPassword(userModifier.getNewPassword());
        userRepository.save(tmp);
        return new ResponseEntity<>("password changed", HttpStatus.OK);
    }

}
