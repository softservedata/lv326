package com.softserve.edu.project.consts;

public class DBConst {

    private DBConst() {
    }

    public final static String CREATE_DB = "CREATE SCHEMA `myteams` DEFAULT CHARACTER SET utf8";
    public final static String CREATE_TABLE_USERS = "CREATE TABLE `myteams`.`users` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `username` VARCHAR(45) NOT NULL,\n" +
            "  `password` VARCHAR(45) NOT NULL,\n" +
            "  `name` VARCHAR(45) NULL,\n" +
            "  `surname` VARCHAR(45) NULL,\n" +
            "  `idRole` INT NOT NULL,\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE INDEX `id_UNIQUE` (`id` ASC),\n" +
            "  UNIQUE INDEX `username_UNIQUE` (`username` ASC))\n" +
            "ENGINE = InnoDB\n" +
            "DEFAULT CHARACTER SET = utf8;";
    public final static String CREATE_TABLE_ROLES = "CREATE TABLE `myteams`.`roles` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NOT NULL,\n" +
            "  UNIQUE INDEX `id_UNIQUE` (`id` ASC),\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE INDEX `name_UNIQUE` (`name` ASC))\n" +
            "ENGINE = InnoDB\n" +
            "DEFAULT CHARACTER SET = utf8;\n";
    public final static String CREATE_TABLE_TEAMS="CREATE TABLE `myteams`.`teams` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NOT NULL,\n" +
            "  `country` VARCHAR(45) NOT NULL,\n" +
            "  `city` VARCHAR(45) NOT NULL,\n" +
            "  UNIQUE INDEX `id_UNIQUE` (`id` ASC),\n" +
            "  PRIMARY KEY (`id`))\n" +
            "ENGINE = InnoDB\n" +
            "DEFAULT CHARACTER SET = utf8;";
    public final static String CREATE_TABLE_USER_HAS_TEAMS="CREATE TABLE `myteams`.`user_has_teams` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `idUser` INT NOT NULL,\n" +
            "  `idTeam` INT NOT NULL,\n" +
            "  PRIMARY KEY (`id`))\n" +
            "ENGINE = InnoDB\n" +
            "DEFAULT CHARACTER SET = utf8;";

}

