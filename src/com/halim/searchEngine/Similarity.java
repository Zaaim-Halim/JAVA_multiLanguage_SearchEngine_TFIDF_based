package com.halim.searchEngine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.halim.nlp.IndexDoc;
import com.halim.nlp.RequestDoc;

public class Similarity {

	public static Map<String, Float> calculateSimilarity(IndexDoc index, RequestDoc doc) {

		Map<String, Float> similarityMap = new HashMap<>();
		for (Map.Entry<String, Map<String, Float>> entry : index.getTfIdfCorpus().getTfIdfMap().entrySet()) {
			similarityMap.put(entry.getKey(), cosinSimilarityBetweenTowDocs(entry.getValue(), doc.getTfMap()));
		}
		//sort  the map and return it
		LinkedHashMap<String, Float> reverseSortedMap = new LinkedHashMap<>();
		similarityMap.entrySet()
			    .stream()
			    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
			    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
       return reverseSortedMap;
	}

	public static Map<String, Float> calculateSimilarity(String indexFullPath, RequestDoc doc) {
		IndexDoc index = new IndexDoc("index.ser");
		index = (IndexDoc) index.load(indexFullPath);
		return calculateSimilarity(index, doc);
	}

	private static Float cosinSimilarityBetweenTowDocs(Map<String, Float> doc1, Map<String, Float> doc2) {

		// need to know wether to extend the lenght or not ...
		
        List<Float> doc1Values = doc1.values().stream().collect(Collectors.toCollection(ArrayList::new)); 
        List<Float> doc2Values = doc2.values().stream().collect(Collectors.toCollection(ArrayList::new));
        Double lenght_doc1Values = 0D;
        Double lenght_doc2Values = 0D;
        Double temp = 0D;
        for(Float f : doc1Values) {
        	temp = (temp + Math.pow(f, 2));
        }
        lenght_doc1Values = Math.sqrt(temp);   //   ||V||
        temp = 0D;
        for(Float f : doc2Values) {
        	temp =(temp + Math.pow(f, 2));
        }
        lenght_doc2Values = Math.sqrt(temp);    //   ||W||
        
        // calculate V*W
        Float dotProduct = 0F;
        if(doc1Values.size() <= doc2Values.size()) // doc1Values is the smallest
        {
        	for(int i = 0; i<doc1Values.size();i++) {
        		dotProduct = dotProduct + (doc1Values.get(i) * doc2Values.get(i));
        	}
        }else {  // doc2Values is the smallest
        	for(int i = 0; i<doc2Values.size();i++) {
        		dotProduct = dotProduct + (doc1Values.get(i) * doc2Values.get(i));
        	}
        }
        
		return (float) (dotProduct /(lenght_doc2Values*lenght_doc1Values));
	}
	

}
