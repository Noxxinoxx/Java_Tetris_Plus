package se.liu.noaan869.LabbTetris;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class HighscoreList {
	private static final String url = "C:/Users/Nox/Desktop/tetris.txt";
	private static final String urlTemp = "C:/Users/Nox/Desktop/TempTetris.txt";
	private final List<Highscore> highSList;
    	public ErrorHandeling errorHandel = new ErrorHandeling(this);
	public HighscoreList() {
		this.highSList = readDataFromHScoreFile();
	}
	public  List<Highscore> gethighSList() {
		return highSList;
	}

	public void add(Highscore score) {
		highSList.add(score);
		sortList();
	}
	public void sortList() {
		highSList.sort(new ScoreComp());
		saveToFile();
	}
	public List<Highscore> readDataFromHScoreFile() {
		Gson gson = new Gson();
		List<Highscore> list;
		try {
			Type listType = new TypeToken<ArrayList<Highscore>>(){}.getType();
			FileReader r = new FileReader(url);
			list = gson.fromJson(r, listType);
			try {
				r.close();
			} catch (IOException e) {
				errorHandel.errorIOException(e);
			}
			
			if(list == null) {
			    list = new ArrayList<>();
			    System.out.println("fuked u list");
			}
	    } catch (FileNotFoundException e) {
	      
	       try {
	           File file = new File(url);
	           boolean createdFile = file.createNewFile();
		   errorHandel.errorCreateFile(createdFile);
	       } catch(IOException ee) {
		   errorHandel.errorIOException(ee);
	       }
	      list = new ArrayList<>();
	      
	    }
		System.out.println(list);
		return list;

	}
	
	public void saveToFile() {
	   Gson gson = new Gson();
		
	   try(FileWriter writer = new FileWriter(urlTemp)) {
	    	
	    	gson.toJson(highSList, writer);
	    	writer.close();
	    	renameAndRemove();
		   
       }
       catch (IOException e) {
	   errorHandel.errorIOException(e);
       }
	}
	
	public void renameAndRemove() {
		File file = new File(url);

	    errorHandel.errorDeleteFile(file.delete());
		 
	
        File filee = new File(urlTemp);
  
        File rename = new File(url);
  
       
        boolean flag = filee.renameTo(rename);

	    errorHandel.errorRenameFile(flag);

		
	}
	
	
	public String showList() {
		StringBuilder stringBuilder = new StringBuilder();

		highSList.forEach((element) -> {
			stringBuilder.append(element.getName());
		    stringBuilder.append(" has score: ");
		    stringBuilder.append(element.getScore());
		    stringBuilder.append("\n");
		
		});
		    
		

		return stringBuilder.toString();
	}
	
}