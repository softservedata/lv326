package com.softserve.edu.service;

import com.softserve.edu.dao.UsingDAO;
import com.softserve.edu.dto.UsingDTO;
import com.softserve.edu.entity.Using;

public class OrderService {
    private HibernateManager hibernateManager;
    private UsingDAO usingDao;

    public OrderService(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
        this.usingDao = new UsingDAO(hibernateManager.getSession());
    }

    public Using addUsing(UsingDTO usingDto) {
    	Using using = new Using();
    	using.setBook(usingDto.getBook());
    	using.setReader(usingDto.getReader());
    	using.setDateReturn(usingDto.getDateReturn());
        hibernateManager.transactionBegin();
        using = usingDao.add(using);
        hibernateManager.transactionEnd();
        return using;
    }

}
