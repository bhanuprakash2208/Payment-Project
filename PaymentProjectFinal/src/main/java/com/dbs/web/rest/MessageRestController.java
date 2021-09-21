package com.dbs.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.web.beans.Message;
import com.dbs.web.service.MessageService;

@CrossOrigin
@RestController
@RequestMapping("/messages")
public class MessageRestController {
	
	@Autowired
	private MessageService messageService;
	
	@GetMapping
	public List<Message> findMessages(){
		
		return this.messageService.getMessages();
		
	}

}
