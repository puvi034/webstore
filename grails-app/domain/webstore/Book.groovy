package webstore

class Book{
String title;
String publisher;
String ISBN;
Double price;



    static constraints = {
	title()
	ISBN(blank:false)
    price(blank:false)
	publisher();
	
	
	}
	String toString(){
	title;
	}
	
	
}
