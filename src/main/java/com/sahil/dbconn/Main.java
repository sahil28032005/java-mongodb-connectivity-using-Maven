package com.sahil.dbconn;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
public class Main {
	static MongoClient mongoClient;
	static MongoDatabase database;
	static  MongoCollection<Document> test;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner sc=new Scanner(System.in);
		 mongoClient = new MongoClient("localhost", 27017);
		 database = mongoClient.getDatabase("external");
		 test=database.getCollection("test");
		 
		 System.out.println("connection success....");
//		 insert(sc);
//		 update(sc);
//		 delete(sc);
		 displayData(sc);
	}
	
	private static void insert(Scanner sc) {
		 //take details for record
		
		System.out.println("enter id");
		String id=sc.nextLine();
		
		System.out.println("enter name");
		String name=sc.nextLine();
		
		Document doc=new Document("_id",id).append("name", name);
		test.insertOne(doc);
		System.out.println("inserted document");
		
	} 
	
	private static void update(Scanner sc) {
		//take updation values
		
		System.out.println("enter id");
		String id=sc.nextLine();
		
		System.out.println("enter name");
		String name=sc.nextLine();
		
		test.updateOne(new Document("_id",id),
				new Document("$set",new Document("name",name)));
		System.out.println("Student updated successfully!");
	}
	
	private static void delete(Scanner sc) {
		//take deletion id or params
		System.out.println("enter id");
		String id=sc.nextLine();
		
		
		test.deleteOne(new Document("_id",id));
		
		System.out.println("value deleted successfully");
		
	}
	
	private static void displayData(Scanner sc) {
		System.out.println("Data:");
		
		for(Document entity: test.find()) {
			System.out.println(entity.toJson());
		}
	}

}
