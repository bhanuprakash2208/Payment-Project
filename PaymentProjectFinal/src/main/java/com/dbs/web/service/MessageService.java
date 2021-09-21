package com.dbs.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Message;
import com.dbs.web.repository.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository repo;
	
	public long getCount()
	{
		return this.repo.count();
	}
	public boolean insertMessage(Message msg)
	{
		if(this.repo.existsById(msg.getMessagecode()))
			return false;
		try {
			this.repo.save(msg);
			
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Message object is empty");
		}
		return true;
	}
	public boolean updateMessage(Message msg)
	{
		if(! this.repo.existsById(msg.getMessagecode()))
			return false;
		try {
			this.repo.save(msg);
			
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Message object is empty");
		}
		return true;
	}
	public boolean deleteMessage(String msgCode)
	{
		if(! this.repo.existsById(msgCode))
			return false;
		try {
			this.repo.deleteById(msgCode);
			
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Message object is empty");
		}
		return true;
	}
	public Message getMessageById(String msgCode)
	{
			return this.repo.findById(msgCode)
				.orElseThrow(()->  new EntityNotFoundException("Message does not exist with id: "+msgCode));
	}
	
	public List<Message> getMessages()
	{
		List<Message> messages = new ArrayList<Message>();
		this.repo.findAll().forEach(msg->messages.add(msg));
		return messages;
	}

}
