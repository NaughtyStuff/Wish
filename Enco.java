package secre;


// https://textuploader.com/tdu5h/raw


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Enco{
    static void out(Object tt){ 
        
        // print function

        System.out.println(tt);
    }
    
    static void show(Path datapath){

        // shows the list of thoughts

        out("here is your list of thoughts, make sure your'e private");
        
        try
        {
            FileReader fread = new FileReader(datapath.toString());
            BufferedReader bread = new BufferedReader(fread);

            String buff;
            out("");
            while ((buff = bread.readLine()) != null){
                out(decod(buff));
                out("");
            }
            bread.close();
            fread.close();

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    static void writebro(Path datapath, String ss){

        // writes the given string into the file

        try{
            FileWriter fwrite = new FileWriter(datapath.toString(), true);
            fwrite.write(encod(ss)+"\n");
            fwrite.close();

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    static String encod (String ss){
        ArrayList<String> enclst = new ArrayList<String>();

        for (char c: ss.toCharArray()){
            int temp =  c;
            enclst.add(0, Integer.toOctalString(temp*temp)+" ");
        }
        return String.join(" ", enclst);
    }
 
    static String decod (String ss){
        StringBuilder dec = new StringBuilder();
        String[] splited = ss.split("\\s+");
        for (int i = splited.length - 1; i >= 0; i--){
            int tmp = Integer.parseInt(splited[i], 8);
            double tt = Math.sqrt(tmp);
            tmp = (int) tt;
            dec.append((char) tmp);
        }
        return dec.toString();
    }
    
    // --------------------------------------------------------

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello, Master!\nAuthorisation Required");
        System.out.println("Enter the Passcode here : ");

        //temporary password must change later
        String corrPass = "5220  5544  10000  22604  21501  22301  21501";
        String passcode = sc.nextLine();

        // passcheck

        if (!passcode.equals(decod(corrPass))){
            sc.close();
            return;}

        out("Authorisation Successful!\n");

        out("Select your file here");
        String flnm = sc.nextLine();

        String fullpath = "C:/Users/ganes/G/JavaFold/secre/" + flnm;
        
        try{
            File f = new File(fullpath);
            if (f.createNewFile()){
                out("\nThis file doesn't exist, do you want to create a new file?");
                String opinion = sc.nextLine();
                if (opinion.equals("no")){
                    f.delete();
                }
                if (opinion.equals("yes")){
                out("file successfully created! "+ f.getName());}
            }
            

        } catch (IOException e){
            out("an error occured");
            e.printStackTrace();
        }

        Path datapath = Path.of(fullpath);


        //Path datapath = Path.of("C:/Users/ganes/G/JavaFold/secre/myThoughts.txt");

        while (true){
            out("\n>>>>");
            String inp = sc.nextLine();

            if (inp.equals("thank you")){
                out("DON'T FORGET TO UPLOAD THE TXT FILE TO https://textuploader.com/tdu5h/raw");
                break;
            }

            if (inp.equals("new thought")){
                out("hmmm... whats that? ;)");
                //thoughts.add(sc.nextLine());
                String k = sc.nextLine();
                writebro(datapath, k + "\n");
            }

            else if (inp.equals("what are my thoughts")){
                show(datapath);
            }

            else if (inp.equals("change passcode")){
                out("yeah sure");
                out("enter new pass");
                    corrPass = encod(sc.nextLine());
                    out("Successfully changed the corrPass");
                    out(corrPass);
            }

            else{out("Really? How high are you?");}
        }
        sc.close();
        
    }
}

