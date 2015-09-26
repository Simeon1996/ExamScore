import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExamScore {
    public static void main(String[] args){

        Scanner reader = new Scanner(System.in);

        Map<Integer,TreeMap<String,Float>> students = new TreeMap<>();

        String nameMatcher = "([A-Za-z]+)\\s([A-Za-z]+)";
        String examScoreMatcher = "\\d+";
        String gradeMatcher = "\\d\\.\\d+";

        String input = reader.nextLine();

        while(!input.equals("")){
            Pattern namePattern = Pattern.compile(nameMatcher);
            Pattern examScorePattern = Pattern.compile(examScoreMatcher);
            Pattern gradePattern = Pattern.compile(gradeMatcher);

            Matcher firstMatch = namePattern.matcher(input);
            Matcher secondMatch = examScorePattern.matcher(input);
            Matcher thirdMatch = gradePattern.matcher(input);

            if(firstMatch.find() && secondMatch.find() && thirdMatch.find()){
                String name = firstMatch.group();
                Integer examScore = Integer.parseInt(secondMatch.group());
                Float grade = Float.parseFloat(thirdMatch.group());

                if(!students.containsKey(examScore)){
                    students.put(examScore,new TreeMap<>());
                }
                students.get(examScore).put(name,grade);
            }

            input = reader.nextLine();
        }

        for(Integer score : students.keySet()){
            System.out.print(score + "->");
            System.out.print(students.get(score).keySet());

            Double sum = 0d;

            for(Float grade : students.get(score).values()){
                sum += grade;
            }

            Double average = sum / students.get(score).values().size();
            System.out.printf("; avg=%.2f\n",average);
        }

    }
}
