package parser;

import model.Book;
import model.BookShelf;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONParser {
    public static JSONObject createJSON(BookShelf bookShelf) {
        JSONObject jsonObject = new JSONObject();
        JSONArray booksArray = new JSONArray();

        for (Book book : bookShelf.getBooks()) {
            JSONObject bookObject = new JSONObject();
            bookObject.put("title", book.getTitle());
            bookObject.put("publishedYear", book.getPublishedYear());
            bookObject.put("numberOfPages", book.getNumberOfPages());
            bookObject.put("authors", book.getAuthors());
            booksArray.put(bookObject);
        }

        jsonObject.put("BookShelf", booksArray);
        return jsonObject;
    }

    public static void printJSON(JSONObject jsonObject) {
        System.out.println(jsonObject.toString(4)); // Pretty print with indentation
    }
}
