package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Product> list = new ArrayList<>();
		
		System.out.println("Enter a file path: ");
		String strPath = sc.nextLine();
		
		File strFile = new File(strPath);
		String sourcedFile = strFile.getParent();
		
		boolean success = new File(sourcedFile + "\\out").mkdir();
		
		String targetPath = sourcedFile + "\\out\\summary.csv";
		
		try(BufferedReader br = new BufferedReader(new FileReader(strPath)))	{
			
			String line = br.readLine();
			
			while(line != null) {
				String[] fields = line.split(",");
				String name = fields[0];
				Double price = Double.parseDouble(fields[1]);
				Integer quantity = Integer.parseInt(fields[2]);
				
				list.add(new Product(name, price, quantity));
				
				line = br.readLine();
			}
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetPath))){
				
				for(Product e : list) {
					bw.write(e.getName() + ", $ " + String.format("%.2f", e.total()));
					bw.newLine();
				}
				
				System.out.println("File created successfully!");
			}
			catch(IOException e) {
				System.out.println("Error: The file couldn't be created.");
			}
			
		}
		catch(IOException e) {
			System.out.println("Error: file didn't find.");
		}
		
		sc.close();
		System.out.println("Hello World!");
	}

}
