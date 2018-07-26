package com.softserve.edu.comics.tools;

import com.softserve.edu.comics.constants.FieldName;
import com.softserve.edu.comics.constants.Message;
import com.softserve.edu.comics.dto.LoginDto;
import com.softserve.edu.comics.dto.UserDto;
import com.softserve.edu.comics.services.IocContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserUtils {

    // CREATE USER
    public static UserDto createUser(HttpServletRequest req, HttpServletResponse resp) {
        if (isPasswordsMatch(req)){
            req.getSession().setAttribute(FieldName.LOGIN, req.getParameter(FieldName.LOGIN));
            UserDto userDto = new UserDto(req.getParameter(FieldName.LOGIN),
                    req.getParameter(FieldName.PASSWORD),
                    req.getParameter(FieldName.NAME),
                    FieldName.USER_ROLE);
            IocContainer.getContainer().getUserService().createUser(userDto);
            return userDto;
        }
        return null;
    }

    // CHANGE USER PASSWORD
    public static UserDto changePassw(HttpServletRequest req) {
        if (isPasswordsMatch(req) && isOldPasswordCorrect(req)){
            Long userId = UserUtils.getUserId(req);
            UserDto userDto = (UserDto)req.getSession().getAttribute(FieldName.USER_DTO);
            UserDto newUserDto = new UserDto(userDto.getLogin(),
                    req.getParameter(FieldName.PASSWORD),
                    userDto.getName(),
                    FieldName.USER_ROLE);
            IocContainer.getContainer().getUserService().updateUser(newUserDto, userId);
            return userDto;
        }
        return null;
    }

    //IS OLD PASSWORD CORRECT
    public static boolean isOldPasswordCorrect(HttpServletRequest req){
        UserDto userDto = (UserDto) req.getSession().getAttribute(FieldName.USER_DTO);
        String password = userDto.getPassword();
        String oldpassword = getStringParameter(req, FieldName.OLD_PASSW);
        if (password != null && oldpassword != null && password.equals(oldpassword)){
            return true;
        }else {
            return false;
        }
    }

    // PASSWORD MATCH
    private static boolean isPasswordsMatch(HttpServletRequest req) {
        String password = getStringParameter(req, FieldName.PASSWORD);
        String confirmpassword = getStringParameter(req, FieldName.CONFIRM_PASSW);
        if (password != null && confirmpassword != null && password.equals(confirmpassword)) {
            return true;
        } else {
            req.setAttribute(FieldName.ERROR, Message.PASSWORDS_DONOT_MATCH);
            return false;
        }
    }

    // ACTIVE SESSION
    public static boolean isActiveSession(HttpServletRequest req) {
        return ((req.getSession().getAttribute(FieldName.USER_DTO) != null) ? true : false);
    }

    // CREATE SESSION
    public static void createSession(HttpServletRequest req) {
        UserDto userDto = (UserDto) req.getAttribute(FieldName.USER_DTO);
        if (isActiveSession(req)) {
            req.getSession().setAttribute(FieldName.USER_DTO, userDto);
        } else {
            req.getSession(true).setAttribute(FieldName.USER_DTO, userDto);
        }
    }

    // IS LOGGED
    public static boolean isLogged(HttpServletRequest req) {
        String login = getStringParameter(req, FieldName.LOGIN);
        String password = getStringParameter(req, FieldName.PASSWORD);
        LoginDto loginDto = new LoginDto(login, password);
        if (login != null && password != null) {
            if (IocContainer.getContainer().getUserService().isLogged(login, password)) {
                UserDto userDto = IocContainer.getContainer().getUserService().getUserDto(loginDto);
                req.setAttribute(FieldName.USER_DTO, userDto);
                return true;
            }
        }
        req.setAttribute(FieldName.ERROR, Message.INVALID_LOGIN_OR_PASSWORD);
        return false;
    }

    //GET USER ID
    public static Long getUserId(HttpServletRequest req){
        String login = ((UserDto)req.getSession().getAttribute(FieldName.USER_DTO)).getLogin();
        UserDto userDto = new UserDto(login,
                req.getParameter(FieldName.PASSWORD),
                req.getParameter(FieldName.NAME),
                FieldName.USER_ROLE);
        Long userId = IocContainer.getContainer().getUserService().getIdUserByLogin(userDto);
        return userId;
    }

    //GET STRING PARAMETER
    protected static String getStringParameter(HttpServletRequest req, String parameter) {
        if (req.getParameter(parameter) != null && !req.getParameter(parameter).isEmpty()) {
            return req.getParameter(parameter);
        } else {
            return null;
        }
    }

}
