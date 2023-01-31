package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User(new Car("Ford", 5648), "Ivan", "Konovalov", "user1@mail.ru"));
      userService.add(new User(new Car("Chevrolet", 4568), "Petr", "Smirnov", "user2@mail.ru"));
      userService.add(new User(new Car("Honda", 5487), "Kate", "Solomonova", "user3@mail.ru"));
      userService.add(new User(new Car("Mazda", 7523), "Dmitry", "Romanov", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar().getModel());
         System.out.println("Car = "+user.getCar().getSeries());
         System.out.println();
      }

      User user = userService.getUserByCar("Ford", 5648);
      System.out.println("Id = "+user.getId());
      System.out.println("First Name = "+user.getFirstName());
      System.out.println("Last Name = "+user.getLastName());
      System.out.println("Email = "+user.getEmail());
      System.out.println("Model car = "+user.getCar().getModel());
      System.out.println("Series car = "+user.getCar().getSeries());
      System.out.println();

      context.close();
   }
}
