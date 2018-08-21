package com.softserve.train;

import java.util.List;

import com.softserve.train.tools.CSVReader;
import com.softserve.train.tools.ExcelReader;

public final class UserRepository {
    private static volatile UserRepository instance = null;

    private UserRepository() {
    }

    public static UserRepository get() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public IUser valid() {
        return User.get()
                  .setLogin("iva")
                  .setFirstname("Ivanka")
                  .setLastname("Petrova")
                  .setPassword("qwerty")
                  .setEmail("iva@gmail.com")
                  .setAdress("Lviv")
                  .setPhone("1234567")
                  .setAge(20)
                  .build();
    }

    public List<IUser> fromCSV() {
        return fromCSV("users.csv");
    }

    public List<IUser> fromCSV(String filename) {
        return User.getExternal(new CSVReader(filename));
    }
    
    public List<IUser> fromExcel() {
        return fromExcel("users.xlsx");
    }

    public List<IUser> fromExcel(String filename) {
        return User.getExternal(new ExcelReader(filename));
    }

}
