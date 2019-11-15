/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kfctryparse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Student
 */
public class MacDacMenuParser {
    
    public static void main(String[] args) {
        String URL = "https://mcdonalds.ru/menu";
        try {
            getDataFromURL(URL);
        } catch (IOException ex) {
            Logger.getLogger(MacDacMenuParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void getDataFromURL(String URL) throws IOException{
        Document html = Jsoup.connect(URL).get();
        String title = html.title();
        System.out.println(title);
        String select = ".base-grid-container li a";
        Elements categories = html.select(select);
        for (Element category: categories){
            title = category.text();
            String cat_url = category.absUrl("href");
            System.out.println(title+": "+cat_url);
            getItemsFromURL(cat_url);
            System.out.println();
        }
        
    }
    
    public static void getItemsFromURL(String URL) throws IOException{
        Document html = Jsoup.connect(URL).get();
        String select = ".base-grid-container .catalog-product";
        Elements items = html.select(select);
        System.out.println(items.size());
        for (Element item: items){
            //System.out.println(item);
            String title = item.select(".catalog-product__title").get(0).text();
            String price = item.select(".catalog-product__price").get(0).text().trim();
            System.out.println("\t"+title+": "+price);
        }
        
    }
}





/*
public class KFCTryParse {

     public static void main(String[] args) {
        String URL = "https://burgerking.ru/menu";
        try {
            getDataFromURL(URL);
        } catch (IOException ex) {
            Logger.getLogger(KFCTryParse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void getDataFromURL(String URL) throws IOException{
        Document html = Jsoup.connect(URL).get();
        String title = html.title();
        System.out.println(title);
        Elements menu = html.select(".content .food-category a");
        for (Element item: menu){
            String category = item.text();
            String item_url = item.absUrl("href");
            System.out.println(category+": "+item_url);
            getItemsFromURL(item_url);
            System.out.println();
        }
        
    }
    
    public static void getItemsFromURL(String URL) throws IOException{
        Document html = Jsoup.connect(URL).get();
        System.out.println(URL);
        //System.out.println(html);
        Elements menu = html.select(".content .food-item .inner");
        for (Element facultet: menu){
            System.out.println(facultet.text());
        }
        
    }
    
}
*/