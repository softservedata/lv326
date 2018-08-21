package com.softserve.train.data;

import com.softserve.train.IUser;
import com.softserve.train.UserRepository;

public class Appl {
    public static void main(String[] args) {
        // 1. Classic Constructor
        //User user = new User("iva", "Ivanka", "Petrova",
        //         "qwerty", "iva@gmail.com", "Lviv", "1234567", 20);
        //
        // 2. Default Construcrot
        //User user = new User();
        //user.setLogin("iva");
        //user.setFirstname("Ivanka");
        //user.setLastname("Petrova");
        //user.setPassword("qwerty");
        //user.setEmail("iva@gmail.com");
        //user.setAdress("Lviv");
        //user.setPhone("1234567");
        //user.setAge(20);
        //
        // 3. Add Fluent Interface
//        User user = new User()
//            .setLogin("iva")
//            .setFirstname("Ivanka")
//            .setLastname("Petrova")
//            .setPassword("qwerty")
//            .setEmail("iva@gmail.com")
//            .setAdress("Lviv")
//            .setPhone("1234567")
//            .setAge(20);
        //
        // 4. Static Factory
//        User user = User.get()
//                .setLogin("iva")
//                .setFirstname("Ivanka")
//                .setLastname("Petrova")
//                .setPassword("qwerty")
//                .setEmail("iva@gmail.com")
//                .setAdress("Lviv")
//                .setPhone("1234567")
//                .setAge(20);
        //
        // 5. Add Builder
//        User user = User.get()
//                .setLogin("iva")
//                .setFirstname("Ivanka")
//                .setLastname("Petrova")
//                .setPassword("qwerty")
//                .setEmail("iva@gmail.com")
//                .setAdress("Lviv")
//                .setPhone("1234567")
//                .setAge(20)
//                .build();
//        System.out.println("email= " + user.setEmail("PRIVET"));
        //
        // 6. Add Dependency Inversion
//        IUser user = User.get()
//                  .setLogin("iva")
//                  .setFirstname("Ivanka")
//                  .setLastname("Petrova")
//                  .setPassword("qwerty")
//                  .setEmail("iva@gmail.com")
//                  .setAdress("Lviv")
//                  .setPhone("1234567")
//                  .setAge(20)
//                  .build();
        //System.out.println("email= " + user.setEmail("PRIVET")); // Compile Error
        //System.out.println("email= " + ((User) user).setEmail("PRIVET"));
        //
        // 7-8. Add Repository, Singleton
        //IUser user = UserRepository.get().valid();
        //System.out.println("email= " + user.getEmail().trim());
        //System.out.println("email= " + user.getEmail());
        //System.out.println("adress= " + user.getAdress());
        //
        // 9. Read from External Data
//        for (IUser current : UserRepository.get().fromCSV()) {
//            System.out.println("email= " + current.getEmail());
//            System.out.println("adress= " + current.getAdress());
//        }
        for (IUser current : UserRepository.get().fromExcel()) {
            System.out.println("email= " + current.getEmail());
            System.out.println("adress= " + current.getAdress());
        }
    }
}
