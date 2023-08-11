package pl.polsl.baconwebapp.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * implements the logic of the program
 * @author Mateusz Kies
 */
public class BaconModel {
    /**  a hashmap that holds all the letter equivalents in the baconian cipher 
    */    
          private final Map<Character, String> codes;
{
        codes = new HashMap<>();
        codes.put('a', "AAAAA");
        codes.put('b', "AAAAB");
        codes.put('c', "AAABA");
        codes.put('d', "AAABB");
        codes.put('e', "AABAA");
        codes.put('f', "AABAB");
        codes.put('g', "AABBA");
        codes.put('h', "AABBB");
        codes.put('i', "ABAAA");
        codes.put('j', "ABAAB");
        codes.put('k', "ABABA");
        codes.put('l', "ABABB");
        codes.put('m', "ABBAA");
        codes.put('n', "ABBAB");
        codes.put('o', "ABBBA");
        codes.put('p', "ABBBB");
        codes.put('r', "BAAAB");
        codes.put('q', "BAAAA");
        codes.put('s', "BAABA");
        codes.put('t', "BAABB");
        codes.put('u', "BABAA");
        codes.put('v', "BABAB");
        codes.put('w', "BABBA");
        codes.put('x', "BABBB");
        codes.put('y', "BBAAA");
        codes.put('z', "BBAAB");
        codes.put(' ', "BBBAA"); // use ' ' to denote any non-letter
}
    /**  encrypts the text with lorem ipsum   
    */    
        static final String MESSAGE = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam placerat ullamcorper justo, "
                + "quis dapibus nisl semper sed. Quisque sit amet purus vitae diam tristique cursus quis sit amet odio. "
                + "Proin vel vestibulum magna, quis gravida ipsum. Donec a sodales mi, ut cursus dui. Nulla interdum malesuada "
                + "lacus sed feugiat. Vivamus ut risus accumsan, vehicula est eu, gravida magna. Nullam maximus feugiat risus, "
                + "sit amet scelerisque velit mattis sit amet. Etiam molestie, diam sed venenatis varius, purus massa interdum "
                + "nulla, ac elementum nisl turpis et felis. Fusce congue eleifend est ut molestie. Nunc ac laoreet lectus, quis "
                + "pharetra enim. Etiam dui.";

/**  the function that encodes the MESSAGE
    *  @param plainText the string that is to be encoded
    *  @return the encoded string
    */    
    public String encode(String plainText) 
    {
        String x = plainText.toLowerCase();
        StringBuilder y = new StringBuilder();
        for (char c : x.toCharArray()) 
        {
            if ('a' <= c && c <= 'z') y.append(codes.get(c));
            else y.append(codes.get(' '));
        }
        String z = y.toString();
        String b = MESSAGE.toLowerCase();  // 'A's to be in lower case, 'B's in upper case
        y.setLength(0);
        int count = 0;
        for (char c : b.toCharArray()) 
        {
            if ('a' <= c && c <= 'z') 
            {
                if (z.charAt(count) == 'A') y.append(c);
                else y.append(((char) (c - 32))); // upper case equivalent
                count++;
                if (count == z.length()) break;
            } 
            else y.append(c);
        }
        return y.toString();
    }

    /**  the function that decodes the message
    *  @param encoded the string to be decoded
    *  @return the decoded string        
    */    
    public String decode(String encoded) 
    {
        StringBuilder x = new StringBuilder();
        for (char c : encoded.toCharArray()) 
        {
            if ('a' <= c && c <= 'z') x.append('A');
            if ('A' <= c && c <= 'Z') x.append('B');
        }
        String z = x.toString();
        x.setLength(0);
        for (int i = 0; i < z.length(); i += 5) 
        {
            String y = z.substring(i, i + 5);
            Character key = codes.entrySet().stream().filter(a -> Objects.equals(a.getValue(), y)).findFirst().map(Map.Entry::getKey).orElse(null);
            x.append(key);
        }
        return x.toString();
    }
    
    /**  validates whether the data provided by the user is correct
    *  @param msg the data provided by a user in a string
    *  @return true if the data is valid, false if invalid
    */    
    public Boolean validateData(String msg)
    {
        int i=0;
        for (char c : msg.toCharArray()) 
        {
            if ('a' <= c && c <= 'z' || 'A' <= c && c <= 'Z') 
            i++;
        }
            if (i%5!=0)
                return false;
            else
                return true;
    }
}
