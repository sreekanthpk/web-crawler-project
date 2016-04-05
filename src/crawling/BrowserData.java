package crawling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class BrowserData {
	
	public static void getProductInfo(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		 try (final WebClient webClient = new WebClient()) {
		  final HtmlPage page = webClient.getPage(url);
	       // final HtmlDivision div = page.getHtmlElementById("gl-item");
	        
		  OutputStreamWriter	fo = null;
		  
			 File f = new File("C:/data/jdproductinfo.log");
			 
			 try {
				 fo = new OutputStreamWriter(new FileOutputStream(f,true),"utf8");
				 DomElement element = page.getElementById("plist");
				 Document doc = Jsoup.parse(element.asXml());
				 Elements e = doc.getElementsByClass("gl-item");
				 fo.append(url.toString()+ "\n") ;
				 	for (Element elem : e) {
				 		Elements productName = elem.getElementsByClass("p-name");
				 		fo.append(productName.get(0).text()+ "        ");
				 		Elements productPrice = elem.getElementsByClass("p-price");
				 		fo.append(productPrice.get(0).text() + "\n" );		
				 	}
				 } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					fo.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	        
		 }
	}

}
