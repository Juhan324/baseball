package com.baseball.webgame.command;

import java.util.ArrayList;
import java.util.List;

public class NumberBaseball {
    public static List<Integer> stringToIntList(String intStr){
        String str = intStr.replaceAll(" ", "").replaceAll("", " ").trim();
        String tmp[] = str.split(" ");
		List<Integer> number = new ArrayList<>();
		for (String is : tmp)
			number.add(Integer.parseInt(is));
		return number;
    }

    public static String compare(List<Integer> correct, List<Integer> guess) {
		System.out.println(correct+" "+guess);
		String result = "";
		int strike = 0;
		int ball = 0;
		for (int i = 0; i < guess.size(); i++) {
			if (correct.contains(guess.get(i))){
				if (correct.get(i) == guess.get(i))
					strike++;
				else
					ball++;
			}
		}
		if (strike == 0 && ball == 0)
			result = "Out";
		else {
			if(strike != 0)
				result += strike + "S";
        	if(ball != 0)
            	result += ball + "B";
		}

		return result;
	}
}
