package com.example.testapp.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class Article {

    @Element(name = "title")
    private String title;

    @Element(name = "link")
    private String link;

    @Element(name = "description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        if (description.lastIndexOf("<") > 0)
            return description.substring(0, description.indexOf("<"));
        else return description;
    }

    @Override
    public boolean equals(Object obj) {
        Article tmp = (Article) obj;
        return tmp.title.equals(title);
    }
}