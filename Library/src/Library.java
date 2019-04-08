import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class Library {
	
	public boolean isBookTitleInCollection(String title,ArrayList<Book>bookCollection){
		boolean bookFound=false;
		int counter=0;
		while(!bookFound && counter<bookCollection.size()){
			if(bookCollection.get(counter).getTitle().equals(title)){
				//return true if the string of titles match
				bookFound=true;
			}else{
				counter++;
			}
		}
		return bookFound;
	}
	
	public void loanBookOut(String title,ArrayList<Book>bookCollection){
		boolean bookFound=false;
		int counter=0;
		while(!bookFound && counter<bookCollection.size()){
			//storing this variable inside the Book
			Book theBook=bookCollection.get(counter);
			if(theBook.getTitle().equals(title)){
				//return true if the strings match
				bookFound=true;
				theBook.setAvailable(false);
			}else{
				counter++;
			}
		}
	}
	
	public boolean isBookAvailable(String title,ArrayList<Book>bookCollection){
		boolean bookFound=false;
		boolean bookAvailable=false;
		int counter=0;
		while(!bookFound && counter<bookCollection.size()){
			Book theBook=bookCollection.get(counter);
			if(theBook.getTitle().equals(title)){
				//return true if the strings match
				if(theBook.isAvailable()){
					bookAvailable=true;
					bookFound=true;
				
			}else{
				counter++;
			}
			
		}	
		}
	    return bookAvailable;
	}
	
	public void returnBook(String title,ArrayList<Book>bookCollection){
		boolean bookFound=false;
		int counter=0;
		while(!bookFound && counter<bookCollection.size()){
			//storing the variable inside the book
			Book theBook=bookCollection.get(counter);
			theBook.setAvailable(true);
			if(theBook.getTitle().equals(title)){
				theBook.setAvailable(true);
			}
		}
	}

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Book>bookCollection=new ArrayList<Book>();
		try{
			FileReader fileReader=new FileReader("books.txt");
			BufferedReader bufferedReader=new BufferedReader(fileReader);
			boolean endOfFile=false;
			while(!endOfFile){
				String bookData=bufferedReader.readLine();
				String[] bookProperties=bookData.split(",");
				//use constants instead of 0
				String title=bookProperties[0];
				String author=bookProperties[1];
				int year=Integer.parseInt(bookProperties[2]);
				String publisher=bookProperties[3];
				String location=bookProperties[4];
				Book libraryBook=new Book(title,author,publisher,year,location);
				//save it in a collection
				bookCollection.add(libraryBook);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}

	}

}
