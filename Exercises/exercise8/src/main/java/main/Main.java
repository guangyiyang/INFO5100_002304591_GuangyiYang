package main;

import model.Book;
import model.BookShelf;

import parser.XMLParser;
import parser.JSONParser;

import org.json.JSONObject;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Create sample data
        BookShelf bookShelf = new BookShelf();
        bookShelf.addBook(new Book("To Kill a Mockingbird", 1960, 281, Arrays.asList("Harper Lee")));
        bookShelf.addBook(new Book("Pride and Prejudice", 1813, 279, Arrays.asList("Jane Austen")));
        bookShelf.addBook(new Book("The Talisman", 1984, 644, Arrays.asList("Stephen King", "Peter Straub")));

        try {
            // XML Parsing
            Document xmlDocument = XMLParser.createXML(bookShelf);
            System.out.println("XML Output:");
            XMLParser.printXML(xmlDocument);

            // JSON Parsing
            JSONObject jsonObject = JSONParser.createJSON(bookShelf);
            System.out.println("\nJSON Output:");
            JSONParser.printJSON(jsonObject);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
