
package models;

public class Text {
    private String content;
    
    public Text() {
        content = "";
    }
    
    public Text(String s) {
        content = s;
    }
    
    public void addContent(String s) {
        content = content + " " + s;
    }
    
    public String getChar(int index) {
        return String.valueOf(content.charAt(index));
    }
  
    @Override
    public String toString() {
        return content;
    }
    
    public String get() {
        return content;
    }
}
