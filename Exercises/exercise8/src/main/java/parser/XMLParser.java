package parser;

import model.Book;
import model.BookShelf;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class XMLParser {
    public static Document createXML(BookShelf bookShelf) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        // Root element
        Element root = doc.createElement("BookShelf");
        doc.appendChild(root);

        // Add books
        for (Book book : bookShelf.getBooks()) {
            Element bookElement = doc.createElement("Book");

            Element title = doc.createElement("title");
            title.appendChild(doc.createTextNode(book.getTitle()));
            bookElement.appendChild(title);

            Element year = doc.createElement("publishedYear");
            year.appendChild(doc.createTextNode(String.valueOf(book.getPublishedYear())));
            bookElement.appendChild(year);

            Element pages = doc.createElement("numberOfPages");
            pages.appendChild(doc.createTextNode(String.valueOf(book.getNumberOfPages())));
            bookElement.appendChild(pages);

            Element authors = doc.createElement("authors");
            for (String author : book.getAuthors()) {
                Element authorElement = doc.createElement("author");
                authorElement.appendChild(doc.createTextNode(author));
                authors.appendChild(authorElement);
            }
            bookElement.appendChild(authors);

            root.appendChild(bookElement);
        }

        return doc;
    }

    public static void printXML(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult console = new StreamResult(System.out);
        transformer.transform(source, console);
    }
}
