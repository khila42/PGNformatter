import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
/**
 *
 * @author Nathaniel
 */
public class Formatter {
    Scanner in;
    /**
     * @param args the command line arguments
     */
    public Formatter(String fileName)
    throws FileNotFoundException
    {
        in = new Scanner(new File(fileName));
        changeToString();
    }
    public void changeToString()
    {
        String unformatted = "";
        while(in.hasNext())
        {
            unformatted += in.next() + " ";
        }
        format(unformatted);
    }
    public static void main(String[] args)
    throws FileNotFoundException
    {
        Scanner kbd = new Scanner(System.in);
        System.out.println("What is the name of the file?");
        String fileName = kbd.next();
        Formatter f = new Formatter(fileName);
    }

    public static void format (String unformatted)
    {
        Scanner para = new Scanner(unformatted);
        int spacesAvalible = 75;
        int spacesUsedSoFar = 0;
        System.out.println();
        while (para.hasNext())
        {
            //The next word on the line
            String word = para.next();
            //How many spaces are required for the line (plus a space after)
            int spacesNeeded = word.length() + 1;
            //if the spaces are greather than whats available...
            if (spacesUsedSoFar + spacesNeeded > spacesAvalible)
            {
                //...jump a line
                System.out.println();
                //reset how many spaces are used
                spacesUsedSoFar = 0;
            }
            //if the word is a comment ignore the line break if the number
            //ends in 1
            if(word.contains("{"))
            {
                do
                {
                    System.out.print(word + " ");
                    word = para.next();
                    spacesNeeded = word.length() + 1;
                    if (spacesUsedSoFar + spacesNeeded > spacesAvalible)
                    {
                        System.out.println();
                        spacesUsedSoFar = 0;
                    }
                    spacesUsedSoFar += spacesNeeded;
                }
                while(!word.contains("}") && para.hasNext());
                System.out.print(word);
            }
            //if it ends in one cause a line break
            else if(word.contains("1."))
            {
                System.out.print("\n" + word + " ");
                spacesUsedSoFar = 0;
            }
            else if(word.contains("]"))
            {
                System.out.println(word);
                spacesUsedSoFar = 0;
            }
            else
            {
                System.out.print(word + " ");
            }
            spacesUsedSoFar += spacesNeeded;
        }
    }

}
