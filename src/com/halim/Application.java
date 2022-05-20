package com.halim;

import org.apache.log4j.Logger;

import com.halim.searchEngine.SearchEngine;
import com.halim.utils.Language;

/**
 * @author zaaim halim 
 * main entry to the search engine
 *
 */
public class Application {
    public final static String BaseDir = "C:/Users/X1/Desktop/master - S3/text-mining/search-e"; 
	static Logger log = Logger.getLogger(Application.class.getName()); 
	public static void main(String[] args) {
		
		SearchEngine searchEngine = new SearchEngine(BaseDir+"/index", Language.ARABIC, false);
		//Gui gui = new Gui("Search Engine");
		//gui.init();
		//Scanner scanner = new Scanner(System.in);
		//String line = scanner.nextLine();
		String line = "يشمل التنظيم القضائي المحاكم التالية: "
				+ "1- المحاكم الابتدائية؛"
				+ "2- المحاكم الإدارية؛"
				+ "3- المحاكم التجارية "
				+ "4- محاكم الاستئناف؛"
				+ "5- محاكم الاستئناف الإدارية "
				+ "6- محاكم الاستئناف التجارية؛"
				+ "7- محكمة النقض. "
				+ " وتعين مقارها ودوائر نفوذها وعدد موظفيها بمقتضى مرسوم."
				+ "";
		searchEngine.search(Language.ARABIC, line);
		System.out.println(searchEngine.getSimilarityMap().toString());
	}

}
