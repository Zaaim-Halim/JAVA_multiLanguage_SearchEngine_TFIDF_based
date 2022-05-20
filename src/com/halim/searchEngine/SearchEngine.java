package com.halim.searchEngine;

import java.util.Map;

import org.apache.log4j.Logger;

import com.halim.nlp.Corpus;
import com.halim.nlp.IndexDoc;
import com.halim.nlp.OccurrenceCorpus;
import com.halim.nlp.RequestDoc;
import com.halim.nlp.TfIdfCorpus;
import com.halim.utils.Language;

public class SearchEngine {
	static Logger log = Logger.getLogger(SearchEngine.class.getName());
	private static IndexDoc index = null;
	private String indexDir;
	private Map<String, Float> similarityMap;

	// disable Default Constructor
	@SuppressWarnings("unused")
	private SearchEngine() {

	}

	public SearchEngine(String indexDir, Language lang, boolean isOffline) {
		this.indexDir = indexDir;
		if(isOffline == false)
		  getIndex(lang);
		else {
			buildSearchEngineOfflineComponents("C:/Users/X1/Desktop/master - S3/text-mining/search-e",lang);
			
		}
	}

	private IndexDoc getIndex(Language lang) {
		log.info("Loading index : " + indexDir + "/" +"index.ser");
		if (index == null) {
			index = new IndexDoc("index.ser");
			index = (IndexDoc) index.load(indexDir + "/" +"index.ser");
			System.out.println(index.getTfIdfCorpus().getTfIdfMap().size());
			log.info("done loading index .....");
		}
		return index;
	}
    public void buildSearchEngineOfflineComponents(String BaseDir,Language lang) {
    	log.info("Starting building  ...");
		Corpus corpus = new Corpus("corpus.ser");
		corpus.buildCorpus(BaseDir+"/"+"corpus");
		OccurrenceCorpus occurence = new OccurrenceCorpus("occurence.ser");
		occurence.buildOccurenceCorpus(corpus, Language.ARABIC);
		occurence.save(BaseDir+"/"+"O");
		TfIdfCorpus tfIdf = new TfIdfCorpus("tfidf.ser");
		tfIdf.buildTfIdfCorpus(occurence);
		tfIdf.save(BaseDir+"/"+"TFIDF");
		IndexDoc index = new IndexDoc("index.ser",tfIdf);
		index.save(BaseDir+"/"+"index");
		log.info("finished building  ...");
		
		getIndex(lang);
    }
	public void search(Language lang, String... words) {
		RequestDoc request = new RequestDoc();
		request.buildRequestDoc(lang, words);
		log.info("Calculating CosinSimilarity ... ");
		this.similarityMap = Similarity.calculateSimilarity(index, request);

	}

	public String getIndexDir() {
		return indexDir;
	}

	public void setIndexDir(String indexDir) {
		this.indexDir = indexDir;
	}

	public Map<String, Float> getSimilarityMap() {
		return similarityMap;
	}

	public void setSimilarityMap(Map<String, Float> similarityMap) {
		this.similarityMap = similarityMap;
	}

	public static IndexDoc getIndex() {
		return index;
	}

	public static void setIndex(IndexDoc index) {
		SearchEngine.index = index;
	}
}
