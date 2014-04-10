package com.example.smsvote;

import java.util.HashMap;
import java.util.Map.Entry;

public class DataHelper {
	public HashMap<String, Integer> votesPerNumber = null;
	public HashMap<String, Integer> votesPerContestants = null;
	
	public DataHelper() {
		votesPerNumber = new HashMap<String, Integer>();
		votesPerContestants = new HashMap<String, Integer>();
	}
	
	public void addVote(String phoneNumber, String voteChoice) {
		if (votesPerNumber.get(phoneNumber) <= 3) {
			votesPerContestants.put(voteChoice,
					votesPerContestants.get(voteChoice) + 1);
			votesPerNumber.put(phoneNumber,
					votesPerNumber.get(votesPerNumber) + 1);
		}
	}
	
	public void clearDB() {
		votesPerNumber.clear();
		votesPerContestants.clear();
	}

	public int getSize() {
		return votesPerContestants.size();
	}
	
	public String[] getUnifiedList() {
		String[] unifiedList = new String[votesPerContestants.size()];
		int i = 0;
		for(Entry<String, Integer> entry : votesPerContestants.entrySet()){
			unifiedList[i] = (String) entry.getKey() + " " + (String) entry.getValue().toString();
			i++;
		}
		return unifiedList;
	}
}
