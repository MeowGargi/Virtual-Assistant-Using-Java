package com.MyAssistant;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class Execution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        LocalDateTime dt = LocalDateTime.now();
        String block = """  
                I can help you set reminders, to ease your tasks just type "reminder" to set your reminders
                Or you can ask me about the weather just type "weather" to know the on going weather of your city
                Or you can type "date" , "time" for today's date and time and nothing now or bye to end this program
                """;
        while( true ){
            System.out.println("Hey I am your Virtual Assistant! What do you want me to do? :)");
            System.out.println(block);
            try {
                String command = reader.readLine().substring(0, 1).toUpperCase();
                if( command.contains("N") || command.contains("B")){
                    System.out.println("Ok bye! See you around soon. It was great interacting with you! :)");
                    break;
                }
                else if( command.contains("W")){
                    System.out.println("Enter any city or country you want :)");
                    String cityCommand = reader.readLine();
                    System.out.println(Weather.getWeather(cityCommand));
                }
                else if( command.contains("T")){
                    System.out.println(("The current time is: " + dt.getHour() + ":" + dt.getMinute() + ":"  + dt.getSecond()));
                }
                else if (command.contains("D")) {
                    System.out.println( "Today's date is " + dt.toLocalDate());
                }
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("I think there is some problem restart the program!");
            }

        }
    }
}
