package Controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import PayLoads.FileResponse;
import Services.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	
	@PostMapping("/upload")
	public ResponseEntity<FileResponse> uploadFile(@RequestParam("image") MultipartFile image) {
		String filename;
		try {
			filename = this.fileService.uploadImage(path, image);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<FileResponse>(
					new FileResponse(null, "Image is not uploaded due to some Resaon !! "),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<FileResponse>(new FileResponse(filename, "Image upload successfully !! "),
				HttpStatus.OK);

	}
}
