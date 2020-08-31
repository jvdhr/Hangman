package ir.progressivesoft.hangman;

import android.content.Context;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.io.InputStreamReader;

public class CategoryDB {
    static Context kontekstDB;
    static String pojam;
    static String f8s;

    public static String getCategoryTerms(Context kontekst, String kategorija) {
        kontekstDB = kontekst;
        switch (kategorija) {
            case "فیلم ها":
                    pojam = readText("Movies");
                break;
            case "هری پاتر":
                    pojam = readText("Harry Potter");
                break;
            case "کاراکتر":
                    pojam = readText("Characters");
                break;
            case "کشور ها":
                    pojam = readText("Countries");
                    break;
            case "Landmarks":
                    pojam = readText("Landmarks");
                    break;
            case "مشاهیر":
                    pojam = readText("Influental People");
                    break;
            case "Celebrities":
                    pojam = readText("Celebrities");
                    break;
            case "ماشین":
                    pojam = readText("Cars");
                    break;
            case "شغل ها":
                    pojam = readText("Jobs");
                    break;
            case "Games":
                    pojam = readText("Games");
                    break;
            case "کارتون ها":
                    pojam = readText("Cartoons");
                    break;
            case "خواننده ها":
                    pojam = readText("Music");
                    break;
            case "Sport":
                    pojam = readText("Sport");
                    break;
            case "بازیکنان فوتبال":
                    pojam = readText("Players");
                    break;
            case "Marvel and DC":
                    pojam = readText("Marvel and DC");
                    break;
            case "حیوانات":
                    pojam = readText("Animals");
                    break;
            case "بازیگران":
                    pojam = readText("Actors");
                    break;
            case "برند ها":
                    pojam = readText("Brands");
                    break;
            case "Cities":
                    pojam = readText("Cities");
                    break;
            case "Comics":
                    pojam = readText("Comics");
                    break;
        }
        return pojam;
    }

    public static String readText(String kategorija) {
        try {
            InputStreamReader InputRead = new InputStreamReader(kontekstDB.openFileInput(kategorija + ".txt"));
            char[] inputBuffer = new char[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
            f8s = BuildConfig.FLAVOR;
            while (true) {
                int charRead = InputRead.read(inputBuffer);
                if (charRead <= 0) {
                    break;
                }
                f8s += String.copyValueOf(inputBuffer, 0, charRead);
            }
            InputRead.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f8s;
    }
}
