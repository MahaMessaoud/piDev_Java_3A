/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.rssFeed;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author maham
 */
@XmlRootElement(name = "channel")
public class RSSChannel {
    private String title;
    private String link;
    private String description;

    public RSSChannel() {
    }

    public RSSChannel(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    @XmlElement(name = "title")
    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    @XmlElement(name = "link")
    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement(name = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RSSChannel{" + "title=" + title + ", link=" + link + ", description=" + description + '}';
    }
}
