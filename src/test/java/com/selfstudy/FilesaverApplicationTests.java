package com.selfstudy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.selfstudy.entites.Image;
import com.selfstudy.repo.ImageRepository;

@SpringBootTest
class FilesaverApplicationTests {
	
	@Autowired
	ImageRepository repository;
	

	@Test
	void testImageSave() throws Exception {
		Image image = new Image();
		image.setId(1);
		image.setName("Kartik.jpg");
		
		File file = new File("G:/Spring Workspace/filesaver/src/main/resources/kartik.JPG");
		
		byte fileContent[] = new byte[(int)file.length()];
		FileInputStream inputStream = new FileInputStream(file);
		inputStream.read(fileContent);
		
		image.setData(fileContent);
		repository.save(image);
		inputStream.close();
	}
	
	@Test
	public void testReadImage(){

		Image image = repository.findById(1).orElse(new Image());
		File file = new File("G:/Spring Workspace/filesaver/src/main/resources/download/"+image.getName());
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(image.getData());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				fileOutputStream.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
		
		
		
	}
	

}
