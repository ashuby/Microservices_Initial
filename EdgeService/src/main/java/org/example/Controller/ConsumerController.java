package org.example.Controller;

import org.bson.types.ObjectId;
import org.example.Model.Movie;
import org.example.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    UserRestConsumer userRestConsumer;
    @Autowired
    MovieRestConsumer movieRestConsumer;
    @Autowired
    TokenRestConsumer tokenRestConsumer;
    @GetMapping("/user/getall")
    public List<User> findall(){
//        System.out.println(userRestConsumer.getClass().getSimpleName());
//        System.out.println("accessing from user service");
        return userRestConsumer.getAllUsers();
    }

    @GetMapping("/movie/getall")
    public List<Movie> getAllMovies() {
        return movieRestConsumer.getAllMovies();
    }

    @PostMapping("/user/login")
    public String loginUser(@RequestBody User user){
        User foundUser = userRestConsumer.findUserByUserName(user);
        ObjectId id = foundUser.getId();
        String gotToken = tokenRestConsumer.getToken(id.toString());
//        return userRestConsumer.userLogin(foundUser,gotToken);
        return "{" +
                "\"message\" :"+"Successfully logged in\",\n"+
                "\"data\" :"+user+",\n"+
                "\"UserName: " + user.getUserName() + "\n"+
                "\"token: " + gotToken+ "\" "+
                "}";
    }
}
