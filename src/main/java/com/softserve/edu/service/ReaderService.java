package com.softserve.edu.service;

import com.softserve.edu.dao.ReaderDAO;
import com.softserve.edu.dto.ReaderDTO;
import com.softserve.edu.entity.Reader;

public class ReaderService {
    private HibernateManager hibernateManager;
    private ReaderDAO readerDao;

    public ReaderService(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
        this.readerDao = new ReaderDAO(hibernateManager.getSession());
    }

    public Reader addReader(ReaderDTO readerDto) {
    	Reader reader = new Reader();
    	reader.setName(readerDto.getName());
    	reader.setUsing(readerDto.getUsings());
        hibernateManager.transactionBegin();
        reader = readerDao.add(reader);
        hibernateManager.transactionEnd();
        return reader;
    }

}
