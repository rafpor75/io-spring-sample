package io.spring.sample;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	final static Logger logger = LoggerFactory.getLogger(GreetingController.class);
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	

    	logger.info("Your print statement");
    	String fileInput="C:\\Users\\Lello\\Documents\\workspace-sts-3.9.4.RELEASE\\io-spring-sample\\fileinput.txt";
    	String fileOutPut="C:\\Users\\Lello\\Documents\\workspace-sts-3.9.4.RELEASE\\io-spring-sample\\fileoutput.txt";
    	Path pathoutput = Paths.get(fileOutPut);
    	 
    	//Use try-with-resource to get auto-closeable writer instance
    	try (BufferedWriter writer = Files.newBufferedWriter(pathoutput))
    	{
    	    
    		try (Stream<String> stream = Files.lines(Paths.get(fileInput))){
            stream.forEach(t -> {
				try {
					writer.write(t);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
    		}catch (IOException e) {
    			
    		}
    	} catch (IOException e) {
    	logger.error(e.getMessage());
		
    	}
    	
    	
    	return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}