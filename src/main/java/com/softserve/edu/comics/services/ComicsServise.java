package com.softserve.edu.comics.services;

import com.softserve.edu.comics.constants.Message;
import com.softserve.edu.comics.dao.ComicsDao;
import com.softserve.edu.comics.dto.ComicsDto;
import com.softserve.edu.comics.entity.Comics;

public class ComicsServise {

    private ComicsDao comicsDao;

    public ComicsServise() {
        this.comicsDao = new ComicsDao();
    }

    public boolean createComics(ComicsDto comicsDto){
        return comicsDao.insert( new Comics(0L,
                comicsDto.getTitle(),
                comicsDto.getAuthor(),
                comicsDto.getPublishingOffice(),
                comicsDto.getDescription(),
                comicsDto.getIdUser()));
    }

    public ComicsDto getComicsDto(Long id, Long idUser){
        Comics comics = comicsDao.getById(id);
        if (idUser.equals(comics.getIdUser())) {
            return new ComicsDto(comics.getId(), comics.getTitle(), comics.getAuthor(),
                   comics.getPublishingOffice(), comics.getDescription(), comics.getIdUser());
       }
       return null;
    }

    public boolean setComicsDto(ComicsDto comicsDto, Long idUser) {
        boolean result = false;
        Comics comics = new Comics(comicsDto.getIdComics(), comicsDto.getTitle(),
                comicsDto.getAuthor(), comicsDto.getPublishingOffice(),
                comicsDto.getDescription(), idUser);
        if (comicsDto.getIdComics() > 0) {
            if (isExistComics(comics.getId())) {
                comicsDao.updateByEntity(comics);
                result = true;
            }
        } else {
            comicsDao.insert(comics);
            result = true;
        }
        return result;
    }

    public boolean isExistComics(Long id){
        boolean result = true;
        try {
            comicsDao.getById(id);
        }catch (RuntimeException e){
            System.out.println(Message.COMICS_DONOT_FOUND + e.getMessage());
            result = false;
        }
        return result;
    }

    public void deleComicsDtoById(Long id){
        if (isExistComics(id)){
            comicsDao.deleteById(id);
        }else {
            throw new RuntimeException(Message.DOESNT_EXISTS);
        }

    }

}
