package com.softserve.train;

import java.util.ArrayList;
import java.util.List;

import com.softserve.train.tools.AExternalReader;

interface ILogin {
    IFirstname setLogin(String login);
}

interface IFirstname {
    ILastname setFirstname(String firstname);
}

interface ILastname {
    IPassword setLastname(String lastname);
}

interface IPassword {
    IEmail setPassword(String password);
}

interface IEmail {
    IBuidUser setEmail(String email);
}

interface IBuidUser {
    IBuidUser setAdress(String adress);

    IBuidUser setPhone(String phone);

    IBuidUser setAge(int age);

    IUser build();
}

public class User implements ILogin, IFirstname, ILastname,
        IPassword, IEmail, IBuidUser, IUser {

    public String login; // required
    public String firstname; // required
    public String lastname; // required
    public String password; // required
    public String email; // required
    public String adress;
    public String phone;
    public int age;

    // public User(String login, String firstname, String lastname,
    // String password, String email) {
    // this.login = login;
    // this.firstname = firstname;
    // this.lastname = lastname;
    // this.password = password;
    // this.email = email;
    // this.adress = "";
    // this.phone = "";
    // this.age = -1;
    // }

    // public User(String login, String firstname, String lastname,
    // String password, String email,
    // String adress, String phone, int age) {
    // this.login = login;
    // this.firstname = firstname;
    // this.lastname = lastname;
    // this.password = password;
    // this.email = email;
    // this.adress = adress;
    // this.phone = phone;
    // this.age = age;
    // }

    // public User() {
    // }

    private User() {
        adress = "";
        phone = "";
        age = -1;
    }

    public static ILogin get() {
        return new User();
    }

    // setters

    public IFirstname setLogin(String login) {
        this.login = login;
        return this;
    }

    public ILastname setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public IPassword setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public IEmail setPassword(String password) {
        this.password = password;
        return this;
    }

    public IBuidUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public IBuidUser setAdress(String adress) {
        this.adress = adress;
        return this;
    }

    public IBuidUser setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public IBuidUser setAge(int age) {
        this.age = age;
        return this;
    }

    public IUser build() {
        return this;
    }
    
    // getters

    public String getLogin() {
        return login;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public static List<IUser> getExternal(AExternalReader externalReader) {
        List<IUser> result = new ArrayList<>();
        //
        for (List<String> currentRow : externalReader.getAllCells()) {
            // TODO Check Valid Data
            if (currentRow.get(4).contains("@")) {
                result.add(User.get()
                        .setLogin(currentRow.get(0))
                        .setFirstname(currentRow.get(1))
                        .setLastname(currentRow.get(2))
                        .setPassword(currentRow.get(3))
                        .setEmail(currentRow.get(4))
                        .setAdress(currentRow.get(5))
                        .setPhone(currentRow.get(6))
                        .setAge(Integer.parseInt(currentRow.get(7)))
                        .build());
            }
        }
        return result;
    }

}
