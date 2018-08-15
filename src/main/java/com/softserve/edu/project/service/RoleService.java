package com.softserve.edu.project.service;



import com.softserve.edu.project.dao.RoleDao;
import com.softserve.edu.project.dto.RoleDto;
import com.softserve.edu.project.entity.Role;


public class RoleService {
   private RoleDao roleDao;

    public RoleService(RoleDao dao) {
        roleDao = dao;
    }

    public RoleService() {
        roleDao=new RoleDao();
    }

    //create role ADMINISTRATOR and USER roles
    public void createRolesIfTheyDontExist(){
         Role admin=new Role();
        Role user=new Role();
        admin.setId(0L);
        admin.setName("ADMINISTRATOR");
        user.setId(0L);
        user.setName("USER");
        if(roleDao.getByFieldName("name",admin.getName()).isEmpty()){
            roleDao.insert(admin);
            System.out.println("created role ADMIN");
        }
        if(roleDao.getByFieldName("name",user.getName()).isEmpty()){
            roleDao.insert(user);
            System.out.println("created role USER");
        }
    }
    //create new role if it do not exist
    public void createRole(String role_name){
        Role role=new Role();
        role.setId(0L);
        role.setName(role_name);
        if(roleDao.getByFieldName("name",role_name).isEmpty()){
            roleDao.insert(role);
            System.out.println(role_name+" role was created");
        }
    }
    //delete role by id
    public void deleteRoleById(Long id_role){
        Role role=roleDao.getById(id_role);
        if(role.getId()!=null){
            roleDao.deleteById(id_role);
            System.out.println(role.getName()+" role was deleted");
        }
    }
    //delete role by role_name
    public void deleteRoleByName(String role_name){
        if(!roleDao.getByFieldName("name",role_name).isEmpty()){
            roleDao.deleteByFieldName("name",role_name);
            System.out.println(role_name+" role was deleted");
        }
    }
    //change role name by id_role
    public void changeRole(String role_name,String new_roleName){
        Role role=roleDao.getByFieldName("name",role_name).get(0);
        if(role.getId()!=null){
            role.setName(new_roleName);
            roleDao.updateByEntity(role);
            System.out.println(new_roleName+" role was changed");
        }
    }
    public Role findRoleByName(String nameRole){
        Role role=roleDao.getByFieldName("name",nameRole).get(0);
        if(role.getId()==null){
            return new Role();
        }
        return role;
    }
    public Role findRoleById(Long id_role){
        Role role=roleDao.getById(id_role);
        if(role.getId()==null){
            return new Role();
        }
        return role;
    }
    public RoleDto getAllRoles(){
        RoleDto roleDto=new RoleDto();
        for (Role r:roleDao.getAll()
             ) {
            roleDto.addRole(r.getName());
        }
        return roleDto;
    }
}
