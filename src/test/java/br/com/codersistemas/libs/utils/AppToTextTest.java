package br.com.codersistemas.libs.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.junit.Test;

public class AppToTextTest {

	private Path masterPath = Paths.get("/home/gustavo/dev/workspace-coder/coder-libs/src");
	private Path pathTarget = Paths.get("/home/gustavo/coder-libs-src.txt");
	private boolean canExecute = false;

	/**
	 * Serializa o diretório/arquivos no arquivo definido como backup
	 * @throws Exception
	 */
	@Test
	public void appToText() throws Exception {
		
		if(!canExecute) {
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		listPaths(masterPath, sb);
		
		Files.deleteIfExists(pathTarget);
		Files.createFile(pathTarget);
		Files.write(pathTarget, sb.toString().getBytes());
		
	}
	
	@Test
	public void textToApp() throws IOException {
		
		if(!canExecute) {
			return;
		}
		
		List<String> readAllLines = Files.readAllLines(pathTarget);
		Path pathFile = null;
		for (String line : readAllLines) {
			if(line.startsWith("DIRECTORY:")) {
				Path pathDirectory = Paths.get(line.substring("DIRECTORY:".length()));
				System.out.println("CREATE: "+line.substring("DIRECTORY:".length()));
				Files.createDirectories(pathDirectory);
			} else 
			if(line.startsWith("FILE:")) {
				pathFile = Paths.get(line.substring("FILE:".length()));
				System.out.println("CREATE: "+line.substring("FILE:".length()));
				Files.deleteIfExists(pathFile);
				Files.createFile(pathFile);
			} else {
				if(pathFile != null) {
					Files.write(pathFile, (line+"\n").getBytes(), StandardOpenOption.APPEND);	
				}
			}
		}
	}
	
	private void listPaths(Path masterPath, StringBuilder sb) throws IOException {
		Files.list(masterPath).forEach(path->{
			if(path.toFile().isDirectory()) {
				sb.append("DIRECTORY:"+path.toFile().getAbsolutePath()+"\n");
			}
			if(path.toFile().isDirectory()) {
				try {
					listPaths(path, sb);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					sb.append("FILE:"+path.toFile().getAbsolutePath()+"\n");
					sb.append(new String(Files.readAllBytes(path)));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}



}
