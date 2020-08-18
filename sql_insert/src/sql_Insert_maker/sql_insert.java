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
public class sql_insert {

	public static void main(String[] args) throws IOException {
			
		  String name = "D:\\sql.txt";
		  @SuppressWarnings("resource")
		Scanner scan = new Scanner (System.in);
		  String st; 
		  System.out.println("Enter Table Name");
		  String table_name=scan.nextLine();
		  Integer IsTrial = 0;
		  System.out.println("Review the generated SQL Query");
		 while(true) {
			 BufferedReader br = getInfo(name);
			 while ((st = br.readLine()) != null) 
			  {
				 String Query = makeQuery(st,table_name);
				if(IsTrial!=1)
					System.out.println(Query); 
				if(IsTrial==1 && !Query.isEmpty())
				writeIntoFile(Query);
			  } 
			 if(IsTrial==1) {
				 break;
			 }
			 System.out.println();
			 System.out.println();
			 System.out.println("If Everything Okay, Enter 1 to Write into SQL file");
			 IsTrial = scan.nextInt();
		 }
		 System.out.println("COMPLETED.....");
  } 
	
	static void writeIntoFile(String input) {
		 try{    
			  FileWriter fw = new FileWriter(new File("D:\\SQL_Writer.sql"), true);
			  BufferedWriter br1 = new BufferedWriter(fw);
			  PrintWriter pr = new PrintWriter(br1);
			  pr.println(input);
			  pr.close();
			  br1.close();
	          }catch(Exception e){System.out.println(e);}    
        
	}
	
	static BufferedReader getInfo(String file) throws FileNotFoundException {
		File file1 = new File(file); 
		BufferedReader br1 = new BufferedReader(new FileReader(file1)); 
		return br1;
	}
	
	private static String makeQuery(String st, String table_name) {
		st = st.replaceAll("\\s{2,}", " ");
		String array[]=st.split("	");
		String query=  "INSERT INTO "+table_name+" VALUES(";
		boolean start = false;
		for(String s:array){
			if(start)
				query+=",";
			try {
				if(s.contains("'"))
				s=s.replaceAll("'","''");
				query+=s;
			}catch (Exception E) {query+="'"+s+"'";}
			start = true;
		}
		query+=");";
		return query;
	}
}
