
package controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import models.Text;

public class NormalizeText {
    Text t;
    public NormalizeText(String pathName) {
        readFile(pathName);
        writeFile();
    }
  
    private void readFile(String pathName){
        try {
            String content = "";
            String s;
            BufferedReader br = new BufferedReader(new FileReader(pathName));
            while ((s = br.readLine()) != null) {
                s = s.trim();
                if (s.equals("\n")) {
                    continue;
                }
                content = content + " " + s;
            }
            t = new Text(content.trim());
            correct();
            br.close();       
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
       
    private void correct() {
        nomalizeSpace();
        nomalizeUpperCase();
    }
    
    private void nomalizeSpace() {
        String current, pre;
        current = pre = t.getChar(0);
        String[] separator = {",", ".", ":", "\"", "?", "!"};
        
        for (int i = 1; i < t.get().length(); i++) {
            current = t.getChar(i);
            
            if (pre.equals(" ") && current.equals(" ")) {
                t = new Text(removeCharAt(t.get(), i));
                i--;
            }
            else {
                for (String s : separator) {
                    if (pre.equals(" ") && current.equals(s)) {
                        t = new Text(removeCharAt(t.get(), i - 1));
                        i--;
                        break;
                    } else if (pre.equals(s) && Character.isAlphabetic(current.charAt(0))) {
                        t = new Text(t.get().substring(0, i) + " " + t.getChar(i).toLowerCase() + t.get().substring(i + 1));
                        i++;
                        break;
                    }  
                }
                
                pre = current;
              
            }
        }
    }
    
    private void nomalizeUpperCase() {
        String[] separator = {".", ":", "?", "!"};
        if (!Character.isUpperCase(t.get().charAt(0))) {
            upperCase(0);
        }
        
        for (int i = 1; i < t.get().length() - 1; i++) {
            for (String s : separator) {
                if (t.getChar(i).equals(s)) {
                    upperCase(i + 2);
                    break;
                }
            }
        }
    }
    
    public void viewFile() {
        System.out.println(t);
    }
    
    private String removeCharAt(String s, int pos) {
      return s.substring(0, pos) + s.substring(pos + 1);
    }
    
    private void writeFile() {
        try {
            FileWriter fw = new FileWriter("result.txt");
            fw.write(t.toString());
            fw.close();
        } catch (Exception e) {
        }
    }
    
    private void upperCase(int index) {
        String s = t.get().substring(0, index) + t.getChar(index).toUpperCase() + t.get().substring(index + 1);
        t = new Text(s);
    }
}
