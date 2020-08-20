package sql_Insert_maker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

import com.sun.javafx.collections.MappingChange.Map;
/*
		Author ILARUM SQL_Writer.sql generator
*/
public class SqlWriter {

	public static void main(String[] args) throws IOException {
			
		  String source_file = "D:\\sql.txt";
		Scanner scan = new Scanner (System.in);
		  String st; 
		  System.out.println("Enter Table Name");
		  String tableName=scan.nextLine();
		  int isTrial = 0;
		  System.out.println("Review the generated SQL Query");
		 while(true) {
			 BufferedReader br = getInfo(source_file);
			 while ((st = br.readLine()) != null) 
			  {
				 String Query = makeQuery(st,tableName);
				if(isTrial!=1)
					System.out.println(Query); 
				if(isTrial==1 && !Query.isEmpty())
				writeIntoFile(Query);
			  } 
			 if(isTrial==1) {
				 break;
			 }
			 System.out.println();
			 System.out.println();
			 System.out.println("If Everything Okay, Enter 1 to Write into SQL file");
			 isTrial = scan.nextInt();
		 }
		 scan.close();
		 System.out.println("COMPLETED.....");
  } 
	
	static void writeIntoFile(String Query) {
		 try{    
			  FileWriter fw = new FileWriter(new File("D:\\SQL_Writer.sql"), true);
			  BufferedWriter br1 = new BufferedWriter(fw);
			  PrintWriter pr = new PrintWriter(br1);
			  pr.println(Query);
			  pr.close();
			  br1.close();
	          }catch(Exception e){System.out.println(e);}    
        
	}
	
	static BufferedReader getInfo(String file) throws FileNotFoundException {
		File file1 = new File(file); 
		BufferedReader br1 = new BufferedReader(new FileReader(file1)); 
		return br1;
	}
	
	private static String makeQuery(String st, String tableName) {
		st = st.replaceAll("\\s{2,}", "   ");
		String array[]=st.split("   ");
		String query=  "INSERT INTO "+tableName+" VALUES(";
		boolean start = false;
		for(String s:array){
			if(start)
				query+=",";
			try {
				if(s.contains("'"))
				s=s.replaceAll("'","''");
				Integer.parseInt(s);
				query+=s;
			}catch (Exception E) {query+="'"+s+"'";}
			start = true;
		}
		query+=");";
		return query;
	}
}
