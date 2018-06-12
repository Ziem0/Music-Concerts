package com.entertainment.enigma.modules;

import com.entertainment.enigma.exceptions.WrongKeyException;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import java.util.List;
import java.util.Map;

public class MorseEnigma implements EnigmaService {

    private final List<String> MORSE = Lists.newArrayList("-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----."
            ,".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-."
            ,"...", "-", "..-", ".--", "-..-", "-.--", "--..", "...-");

    private Multimap<String, String> enigmaLogic;

    public MorseEnigma() {
        this.enigmaLogic = ArrayListMultimap.create();

        int counter = 0;
        for (int i = 48; i < 123; i++) {
            String v = String.valueOf((char) i);
            if (!(i > 57 && i < 65) && i < 91) {
                enigmaLogic.put(MORSE.get(counter), v);
                counter++;
            } else if (i > 96) {
                enigmaLogic.put(MORSE.get(counter-32)+"__", v);
                counter++;
            }
        }
    }

    @Override
    public String encipher(String input) {
        String ciphered = "";
        for (String s : input.split("")) {
            String swap = enigmaLogic.entries().stream()
                    .filter(entry -> entry.getValue().equals(s))
                    .map(Map.Entry::getKey)
                    .max(String::compareTo).get();
            ciphered += (swap+" ");
        }
        return ciphered;
    }

    @Override
    public String decipher(String ciphered) {
        String deciphered = "";
        for (String s : ciphered.split(" ")) {
            String swap = enigmaLogic.entries().stream()
                    .filter(entry -> entry.getKey().equals(s))
                    .map(Map.Entry::getValue)
                    .max(String::compareTo).get();
            deciphered += swap;
        }
        return deciphered;
    }

    @Override
    public String getName() {
        return "Morse";
    }

    @Override
    public Boolean isKeyRequired() {
        return false;
    }

    @Override
    public void setKey(String key) throws WrongKeyException {
    }


//    public static void main(String[] args) {
//        MorseEnigma m = new MorseEnigma();
//        String result = m.encipher("ZiemoAndrzejewski");
//        System.out.println(result);
//        System.out.println(m.decipher(result));
//        m.enigmaLogic.entries().forEach(a -> System.out.printf("%-10s %s\n", a.getKey(), a.getValue()));
//    }
}
