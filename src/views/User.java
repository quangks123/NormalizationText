
package views;

import controllers.NormalizeText;
import java.util.Scanner;

public class User {
    private Scanner sc = new Scanner(System.in);
    NormalizeText n;
    public void run() {
        System.out.println("Enter name File: ");
        String pathName = sc.nextLine();
        n = new NormalizeText(pathName);
        n.viewFile();
    }
}
