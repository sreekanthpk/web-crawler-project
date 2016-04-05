package crawling.tmall;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class ControllerTmall {
    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "C:/data";
        int numberOfCrawlers = 3;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setMaxDepthOfCrawling(7);
        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        controller.addSeed("https://list.tmall.com/search_product.htm?spm=875.7789098.2015121.52.O7FSkg&cat=56230008&sort=s&style=g&acm=lb-zebra-17931-289052.1003.2.449219&aldid=0tzOBAYt&industryCatId=50928018&from=sn_1_cat&scm=1003.2.lb-zebra-17931-289052.ITEM_1457448586120_449219&tmhkmain=0&pos=1");

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(TmallCrawler.class, numberOfCrawlers);
    }
}