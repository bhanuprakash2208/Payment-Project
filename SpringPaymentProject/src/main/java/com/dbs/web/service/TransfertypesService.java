package com.dbs.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Transfertypes;
import com.dbs.web.repository.TransfertypesRepository;

@Service
public class TransfertypesService {
	
	@Autowired
	private TransfertypesRepository repo;
	
	public long getCount()
	{
		return this.repo.count();
	}
	public boolean insertTransferType(Transfertypes ttp)
	{
		if(this.repo.existsById(ttp.getTransfertypecode()))
			return false;
		try {
			this.repo.save(ttp);
			
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Transfer object is empty");
		}
		return true;
	}
	public boolean updateMessage(Transfertypes ttp)
	{
		if(! this.repo.existsById(ttp.getTransfertypecode()))
			return false;
		try {
			this.repo.save(ttp);
			
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Transfer object is empty");
		}
		return true;
	}
	public boolean deleteTransferType(String ttpCode)
	{
		if(! this.repo.existsById(ttpCode))
			return false;
		try {
			this.repo.deleteById(ttpCode);
			
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Transfer object is empty");
		}
		return true;
	}
	public Transfertypes getTransfertypeById(String ttpCode)
	{
			return this.repo.findById(ttpCode)
				.orElseThrow(()->  new EntityNotFoundException("TransferType does not exist with id: "+ttpCode));
	}
	
	public List<Transfertypes> getTransfertypes()
	{
		List<Transfertypes> transfertypes = new ArrayList<Transfertypes>();
		this.repo.findAll().forEach(ttp->transfertypes.add(ttp));
		return transfertypes;
	}

	

}
