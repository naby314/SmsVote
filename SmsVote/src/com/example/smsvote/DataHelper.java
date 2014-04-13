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
	
	private void addVoteToContestant(String contestant) {
		if(votesPerContestants.get(contestant) == null) {
			votesPerContestants.put(contestant, 1);
		} else {
			votesPerContestants.put(contestant, votesPerContestants.get(contestant)+1);
		}
		
	}
	
	public void addVote(String phoneNumber, String voteChoice) {
		if (votesPerNumber.get(phoneNumber) == null) {
			votesPerNumber.put(phoneNumber, 1);
			addVoteToContestant(voteChoice);
			
		} else {
			if (votesPerNumber.get(phoneNumber) < 3) {
				votesPerNumber.put(phoneNumber, votesPerNumber.get(phoneNumber) + 1);
				addVoteToContestant(voteChoice);
			}
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
			unifiedList[i] = (String) entry.getKey() + " : " + (String) entry.getValue().toString();
			i++;
		}
		return unifiedList;
	}
}
