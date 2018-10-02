package com.lntts.streamchannel.web;

import com.lntts.streamchannel.service.StreamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StreamController {
	@Autowired
    private StreamService streamService;

    @GetMapping("/stream")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String streamData() {
    	System.out.println("data===");
        streamService.sendData();
    	return "success";
    }
}
