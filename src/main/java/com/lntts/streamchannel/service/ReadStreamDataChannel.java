package com.lntts.streamchannel.service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * author shabarish
 */
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import com.lntts.streamchannel.stream.StreamsChannel;
@Component
public class ReadStreamDataChannel {
	
	 @StreamListener(StreamsChannel.INPUT)
	    public void handleStreamData(String data) {
		 String basePath = "/home/shabarish/Desktop/";
		 String path = "";
		 String filePath = "";
	        try {
	        	String line[] = data.split(",");
	        	String diretoryAndFilename = line[5];
	        	path = basePath+diretoryAndFilename;
	        	File dir = new File(path);
	        	if(dir.exists() && dir.isDirectory()) {
	        		System.out.println("directory exists()");
	        	} else {
	        		dir.mkdir();
	        	}
	        	filePath = path+"/"+diretoryAndFilename;
	        	File file = new File(filePath);
	        	if(file.exists()&&file.isFile()) {
	        		System.out.println("file exists");
	        	} else {
	        		file.createNewFile();
	        	}
				Files.write(Paths.get(filePath),(data+System.lineSeparator()).getBytes(),StandardOpenOption.APPEND);
				//StandardCharsets.UTF_8
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}
