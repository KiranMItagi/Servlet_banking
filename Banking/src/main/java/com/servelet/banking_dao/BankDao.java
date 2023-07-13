package com.servelet.banking_dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;

import com.servelet.banking_dto.BankAccount;

public class BankDao {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = manager.getTransaction();

	public void save(BankAccount account) {

		entityTransaction.begin();
		manager.persist(account);
		entityTransaction.commit();
	}

	public List<BankAccount> fetchAll() {

		return manager.createQuery("select x from BankAccount x").getResultList();
	}

	public BankAccount find(long acno) {

		return manager.find(BankAccount.class, acno);
		
		
	}

	public void update(BankAccount account) {

		entityTransaction.begin();
		manager.merge(account);
		entityTransaction.commit();

	}

}
