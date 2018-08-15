package com.softserve.edu.project.service;



import com.softserve.edu.project.dao.RoleDao;
import com.softserve.edu.project.dao.UserDao;
import com.softserve.edu.project.dto.LoginDto;
import com.softserve.edu.project.dto.UserDto;
import com.softserve.edu.project.entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserService {
    private UserDao userDao;
    private RoleDao roleDao;

    public UserService(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    public UserService() {
        userDao=new UserDao();
        roleDao=new RoleDao();
    }

    //will create user if admin dont exist with username : admin and password : admin and role : ADMINISTRATOR;
    public void createAdmin() {
        User user = new User(0L,"admin","admin",MyFilter.firstLetterUppCaseOtherLowercase("Ivan"),
                MyFilter.firstLetterUppCaseOtherLowercase("IVAN"),1L);
        if (userDao.getByFieldName("username", user.getUsername()).isEmpty()) {
            userDao.insert(user);
            System.out.println("created user admin");
        }
    }

    public Long getIdUserByLogin(LoginDto loginDto) {
        return userDao.getUserEntityByLogin(loginDto.getUsername()).getId();
    }

    public Long getIdUserByLogin(UserDto userDto) {
        return userDao.getUserEntityByLogin(userDto.getUsername()).getId();
    }
    public boolean isValid(LoginDto loginDto) {
        boolean result = true;
        User users = null;
        try {
            users = userDao.getUserEntityByLogin(loginDto.getUsername());
        } catch (Exception e) {
            // Logging Exception
            System.out.println("RuntimeException, message: " + e.getMessage());
            result = false;
        }
        result = result && (users.getPassword().equals(loginDto.getPassword()));
        return result;
    }
    public UserDto getUserDto(LoginDto loginDto){
        User user=userDao.getUserEntityByLogin(loginDto.getUsername());
        return  new UserDto(user.getUsername(),user.getPassword(),user.getName(),user.getSurname(),
                roleDao.getById(user.getIdRole()).getName());
    }
    public boolean createUserDto(UserDto userDto) {
        if(findUserByUsername(userDto.getUsername()).getId()==null){
            userDao.insert(new User(0L,userDto.getUsername(),userDto.getPassword(),MyFilter.firstLetterUppCaseOtherLowercase(userDto.getName())
                    , MyFilter.firstLetterUppCaseOtherLowercase(userDto.getUsername()),roleDao.getByFieldName("name","USER").get(0).getId()));
            return true;
        }
        return false;

    }

    public User findUserById(Long idUser){
        User user=userDao.getById(idUser);
        if(user.getId()==null){
            return new User();
        }
        return user;
    }
    public User findUserByUsername(String username){
        try{
            User user= userDao.getByFieldName("username",username).get(0);
            return user;}
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return new User();
        }
    }
    public UserDto findUserDtoByUsername(String username){
        try{
            User user= userDao.getByFieldName("username",username).get(0);

            return new UserDto(user.getUsername(),user.getPassword(),user.getName(),user.getSurname(),roleDao.getById(user.getIdRole()).getName());}
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return new UserDto();
        }
    }

    public boolean updateUser(UserDto userDto){
        User user=findUserByUsername(userDto.getUsername());
        if(user.getUsername()!=null){
            user.setPassword(userDto.getPassword());
            user.setName(MyFilter.firstLetterUppCaseOtherLowercase(userDto.getName()));
            user.setSurname(MyFilter.firstLetterUppCaseOtherLowercase(userDto.getSurname()));
            user.setIdRole(roleDao.getByFieldName("name",userDto.getRole()).get(0).getId());
            userDao.updateByEntity(user);
            return true;
        }
        return false;
    }
    public List<UserDto> getAllUsersDto(){
        List<UserDto> userList=new ArrayList<UserDto>();
            for (User u:userDao.getAll()
                 ) {
                  userList.add(new UserDto(u.getUsername(),u.getPassword(),u.getName(),u.getSurname(),roleDao.getById(u.getIdRole()).getName()));
            }
        return userList;
    }
    public Set<User> getAllUsers(){
        List<User>us=userDao.getAll();
        Set<User> users=new HashSet<User>();
        users.addAll(us);
        return users;
    }
    public List<UserDto> getUserDtoPagination(int currentPage, int recordsPerPage){
        int start = currentPage * recordsPerPage - recordsPerPage;
        List<User>users=userDao.getPagination(start,recordsPerPage);
        if(users.isEmpty()){
            return new ArrayList<UserDto>();
        }else{
            List<UserDto> userList=new ArrayList<UserDto>();
            for (User u:userDao.getAll()
                    ) {
                userList.add(new UserDto(u.getUsername(),u.getPassword(),u.getName(),u.getSurname(),roleDao.getById(u.getIdRole()).getName()));
            }
            return userList;
        }
    }
    public int getNumberOfRows() {
        int numOfRows = 0;
        numOfRows=getAllUsers().size();
        return numOfRows;
    }

    public void deleteUserByUsername(String username){
        userDao.deleteByFieldName("username",username);
    }
}
