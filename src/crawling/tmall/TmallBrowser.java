package crawling.tmall;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.DomChangeEvent;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TmallBrowser {
	
	public static void getProductInfo(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException{
		 try (final WebClient webClient = new WebClient()) {
		 
	       // final HtmlDivision div = page.getHtmlElementById("gl-item");
	        
		  OutputStreamWriter	fo = null;
		  
			 File f = new File("C:/data/tmallproductinfo.log");
			 File file = new File("C:/data/phantomjs-2.1.1-windows/bin/phantomjs.exe");
			 System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
			 PhantomJSDriver driver = new PhantomJSDriver();
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
			driver.get(url); 
			
			 try {
				 fo = new OutputStreamWriter(new FileOutputStream(f,true),"utf8");
				
		
				 //fo.write(url.toString() + "\n");
				 WebElement brandName = driver.findElement(By.id("J_attrBrandName"));
				 WebElement price = driver.findElement(By.className("tm-price"));
				 WebElement count = driver.findElement(By.className("tm-count"));
		
				 fo.write( "'"+brandName.getText() + "',");
				 fo.write( "'"+driver.findElement(By.className("tb-detail-hd")).getText().replace("\n", "") + "',");
				 fo.write("'"+price.getText() + "',");
				 fo.write("'"+count.getText() + "'\n");
				 
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				driver.close();
				driver.quit();
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
