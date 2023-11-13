package Services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
	
		//Get File name 
		
		 String name = file.getOriginalFilename();
		// Full Path
		 
		 String filePath = path+ File.separator+name; 
		
		// create folder if not created
		 
		 File f = new File(path);
		 if(!f.exists()) {
			
			 f.mkdir();
		 }
		
		// File Copy
		 
		 Files.copy(file.getInputStream(),Paths.get(filePath));
		
		
		
		return name;
	}

}
