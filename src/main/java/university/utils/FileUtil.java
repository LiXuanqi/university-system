package university.utils;

import university.assignment.Answer;
import university.assignment.Essay;
import university.assignment.MultipleChoice;
import university.assignment.Question;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static List<Question> readQuestionsFromFile(String filepath) {
        List<Question> questions = new ArrayList<>();
        try {
            File file = new File(filepath);

            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null) {
//                System.out.println(st);
                if (st.startsWith("Type:Essay")) {
                    int point = getPointValueFromString(st);
                    String text = br.readLine();
                    Question question = new Question(text, point);
                    questions.add(question);
                } else if (st.startsWith("Type:Choice")) {
                    int point = getPointValueFromString(st);
                    StringBuilder sb = new StringBuilder();
                    sb.append(br.readLine());
                    sb.append('\n');
                    sb.append(br.readLine());
                    sb.append('\n');
                    sb.append(br.readLine());
                    sb.append('\n');
                    sb.append(br.readLine());
                    sb.append('\n');
                    sb.append(br.readLine());
                    String text = sb.toString();
                    Question question = new Question(text, point);
                    questions.add(question);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return questions;
    }

    public static List<Answer> readAnswersFromFile(String filepath) {
        List<Answer> answers = new ArrayList<>();
        try {
            File file = new File(filepath);

            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null) {
                String content = getAnswerContentFromString(st);
                if (st.startsWith("TYPE:ESSAY")) {
                    Answer answer = new Essay(content);
                    answers.add(answer);
                } else if (st.startsWith("TYPE:CHOICE")) {
                    Answer answer = new MultipleChoice();
                    String[] choiceStrs = content.split(" ");
                    for (String choiceStr : choiceStrs) {
                        ((MultipleChoice) answer).addChoice(choiceStr);
                    }
                    answers.add(answer);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return answers;
    }

    private static String getAnswerContentFromString(String str) {
        int startIndex = str.indexOf("CONTENT:") + 8;
        return str.substring(startIndex);
    }

    private static int getPointValueFromString(String str) {
        int pointIndex = str.lastIndexOf("Point:") + 6;
        return Integer.parseInt(str.substring(pointIndex));
    }

}
