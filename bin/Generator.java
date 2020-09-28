import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Generator{
    public Map<Pair<Character,Character>,Integer> letters;
    public Map<Character,Integer> firstLetters;
    public Map<Integer,Integer> wordLengths;
    int numWords;

    public Generator(){
        this.letters = new HashMap<>();
        this.firstLetters = new HashMap<>();
        this.wordLengths = new HashMap<>();
        numWords = 0;
    }

    /**
     *Reads a word and enters the information into the generator's data bank.
     * @param word The word being read.
     */
    public void readWord(String word){
        char[] chars = word.toCharArray();
        int wordLength = chars.length;
        numWords++;

        for(int i = 0; i < chars.length; i++){
            if(i == 0){
                boolean inList = false;
                for (Map.Entry<Character,Integer> entry : firstLetters.entrySet()){
                    if(entry.getKey().charValue() == chars[i]){
                        inList = true;
                        int curr = entry.getValue();
                        entry.setValue(curr  + 1);    
                    }
                }
                if(!inList){
                    firstLetters.put(chars[i], 1);
                }
            } else {
                boolean inList = false;
                char prevChar = chars[i-1];
                char currChar = chars[i];
                for (Map.Entry<Pair<Character,Character>,Integer> entry : letters.entrySet()){
                    if(entry.getKey().getA() == prevChar &&
                        entry.getKey().getB() == currChar){
                            int curr = entry.getValue();
                            entry.setValue(curr + 1);
                            inList = true;
                    }
                }
                if(!inList){
                    letters.put(new Pair<Character,Character>(prevChar,currChar), 1);
                }
            }
        }
        boolean inList = false;
        for (Map.Entry<Integer,Integer> entry : wordLengths.entrySet()){
            if(entry.getKey() == wordLength){
                int curr = entry.getValue();
                entry.setValue(curr + 1);
                inList = true;
            }
        }
        if(!inList){
            wordLengths.put(wordLength,1);
        }
    }

    /**
     *Generates a word using the generator's data.
     *@return A new word from previously stored data.
     */
    public String generateWord(){
        int len = 0;
        double chance = 0;
        Map<Integer,Double> newWordLengths = new HashMap<>();
        for(Map.Entry<Integer,Integer> entry : wordLengths.entrySet()){
            double value = entry.getValue();
            double perc = numWords; 
            chance += value / perc;
            newWordLengths.put(entry.getKey(), chance);
        }
        newWordLengths = sortByValue(newWordLengths);
        double rand = Math.random();
        for(Map.Entry<Integer,Double> entry : newWordLengths.entrySet()){
            if(rand <= entry.getValue()){
                len = entry.getKey();
                break;
            }
        }

        char firstLetter = '~';
        chance = 0;
        Map<Character,Double> newFirstLetters = new HashMap<>();
        for(Map.Entry<Character,Integer> entry : firstLetters.entrySet()){
            double value = entry.getValue();
            double perc = numWords; 
            chance += value / perc;
            newFirstLetters.put(entry.getKey(), chance);
        }
        newFirstLetters = sortByValue(newFirstLetters);
        rand = Math.random();
        for(Map.Entry<Character,Double> entry : newFirstLetters.entrySet()){
            if(rand <= entry.getValue()){
                firstLetter = entry.getKey();
                break;
            }
        }

        String word = String.valueOf(firstLetter);
        char currChar = firstLetter;
        for(int i = 1; i < len; i++){
            int num = 0;
            for(Map.Entry<Pair<Character,Character>, Integer> entry : letters.entrySet() ){
                if(entry.getKey().getA() == currChar){
                    num += entry.getValue();
                }
            }
            chance = 0;
            Map<Character,Double> newLetters = new HashMap<>();
            for(Map.Entry<Pair<Character,Character>, Integer> entry : letters.entrySet() ){
                if(entry.getKey().getA() == currChar){
                    double value = entry.getValue();
                    double perc = num; 
                    chance += value / perc;
                    newLetters.put(entry.getKey().getB(), chance);
                }
            }
            newLetters = sortByValue(newLetters);
            rand = Math.random();
            for(Map.Entry<Character,Double> entry : newLetters.entrySet()){
                if(rand <= entry.getValue()){
                    currChar = entry.getKey();
                    word += String.valueOf(currChar);
                    break;
                }
            }
    
        }
        return word;
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}