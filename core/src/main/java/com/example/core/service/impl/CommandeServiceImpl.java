package com.example.core.service.impl;

import com.example.core.entities.AppUser;
import com.example.core.entities.Commandes;
import com.example.core.service.ICommandeService;
import com.example.core.dao.ICommandeDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeServiceImpl implements ICommandeService {

    private final ICommandeDao iCommandeDao;

    public CommandeServiceImpl(ICommandeDao iCommandeDao) {
        this.iCommandeDao = iCommandeDao;
    }

    @Override
    public Commandes createCommande(Commandes commande) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Commandes findCommandeById(Commandes commande) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Commandes> findCommandeByUser(AppUser user) {
        return iCommandeDao.findAllByUser(user);
    }
}
