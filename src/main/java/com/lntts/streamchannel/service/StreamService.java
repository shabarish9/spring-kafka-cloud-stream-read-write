package com.lntts.streamchannel.service;

/**
 * author shabarish
 */
import com.lntts.streamchannel.stream.StreamsChannel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class StreamService {
	
    private final StreamsChannel streamChannel;
    private String fileLocation="/home/shabarish/Desktop/20071207100605.plt";

    public StreamService(StreamsChannel streamChannel) {
        this.streamChannel = streamChannel;
    }

    public void sendData() {
    	Stream<String> streamdata = Stream.empty();
        try {
        	MessageChannel messageChannel = streamChannel.outboundGreetings();
			streamdata = Files.lines(Paths.get(fileLocation));
			streamdata.forEach( line -> messageChannel.send(streamdata(line)));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        finally {
        	streamdata.close();
        }
    }

	private Message<String> streamdata(String line) {
		return MessageBuilder
		.withPayload(line)
		.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.ALL)
		.build();
	}
}
