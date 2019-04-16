package edu.ua.culverhouse.mis.bookstore;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private String _id;
    private String cwid;
    private String cover;
    private String isbn;
    private String title;
    private String author;
    private int copies;
    private String genre;
    private int length;

    public Book() {
    }

    public Book(String cwid, String isbn, String title, String author, String cover, String genre, int length,
            int copies, String id) {
        this._id = id;
        this.cwid = cwid;
        this.cover = cover;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.copies = copies;
        this.genre = genre;
        this.length = length;

    }

    @JsonProperty("_id")
    public String getId() { return _id; }
    @JsonProperty("_id")
    public void setId(String value) { this._id = value; }

    @JsonProperty("cwid")
    public String getCwid() { return cwid; }
    @JsonProperty("cwid")
    public void setCwid(String value) { this.cwid = value; }

    @JsonProperty("cover")
    public String getCover() { return cover; }
    @JsonProperty("cover")
    public void setCover(String value) { this.cover = value; }

    @JsonProperty("isbn")
    public String getIsbn() { return isbn; }
    @JsonProperty("isbn")
    public void setIsbn(String value) { this.isbn = value; }

    @JsonProperty("title")
    public String getTitle() { return title; }
    @JsonProperty("title")
    public void setTitle(String value) { this.title = value; }

    @JsonProperty("author")
    public String getAuthor() { return author; }
    @JsonProperty("author")
    public void setAuthor(String value) { this.author = value; }

    @JsonProperty("copies")
    public int getCopies() { return copies; }
    @JsonProperty("copies")
    public void setCopies(int value) { this.copies = value; }

    @JsonProperty("genre")
    public String getGenre() { return genre; }
    @JsonProperty("genre")
    public void setGenre(String value) { this.genre = value; }

    @JsonProperty("length")
    public int getLength() { return length; }
    @JsonProperty("length")
    public void setLength(int value) { this.length = value; }

    @Override
    public String toString() {
        return _id + "\n" + cwid + "\n" + cover + "\n" + isbn + "\n" + title + "\n" + author + "\n" + copies + "\n"
                + genre + "\n" + length;
    }

}
