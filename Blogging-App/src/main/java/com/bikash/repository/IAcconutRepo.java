package com.bikash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bikash.entity.Account;


public interface IAcconutRepo extends JpaRepository<Account, Integer> {
	public Account findByEmail(String email);
}
