package pe.edu.upc.serviceimplements;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.edu.upc.serviceinterfaces.ISubirFotoService;

@Service
public class SubirFotoServiceImplement implements ISubirFotoService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	// CONSTANTE DEL ARCHIVO UPLOADS
	private final static String UPLOADS_FOLDER = "uploads";

	@Override
	public Resource load(String filename) throws MalformedURLException {
		// TODO Auto-generated method stub
		Path pathFoto=getPath(filename);
		log.info("getPath:"+pathFoto);
		Resource recurso=new UrlResource(pathFoto.toUri());
		if(!recurso.exists()||!recurso.isReadable()) {
			throw new RuntimeException("Error: no se puede cargar la imagen: "+pathFoto.toString());
		}
		
		return recurso;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		String uniqueFilename=UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
		Path rootPath=getPath(uniqueFilename);
		log.info("rootPath: "+rootPath);
		
		Files.copy(file.getInputStream(),rootPath);
		return uniqueFilename;
	}

	@Override
	public boolean delete(String filename) {
		// TODO Auto-generated method stub
		Path rootPath=getPath(filename);
		
		File archivo=rootPath.toFile();
		if(archivo.exists()&&archivo.canRead()) {
			if(archivo.delete()) {
				return true;
			}
		}
		return false;
	}

	public Path getPath(String filename) {
		return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
	}

}
