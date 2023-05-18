package org.example.surveys;

import org.example.surveys.data.MemberRepository;
import org.example.surveys.data.ParticipationRepository;
import org.example.surveys.data.SurveyRepository;
import org.example.surveys.service.SurveyService;

import java.util.List;
import java.util.Scanner;

public class SurveyApp {
    private static final List<String> OPTIONS;

    static {
        OPTIONS = List.of(
                "1. Fetch all members who completed the given survey.",
                "2. Fetch all surveys that were completed by the given member.",
                "3. Fetch the list of points per survey the given member has collected.",
                "4. Fetch all members who can be invited for the given survey.",
                "5. Fetch survey statistics.");
    }

    private final SurveyService surveyService;
    private final Scanner scanner;

    public SurveyApp() {
        MemberRepository memberRepository = new MemberRepository();
        SurveyRepository surveyRepository = new SurveyRepository();
        ParticipationRepository participationRepository = new ParticipationRepository();
        surveyService = new SurveyService(surveyRepository, memberRepository, participationRepository);
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        new SurveyApp().run();
    }

    public void run() {
        String option;
        do {
            System.out.println("Please choose an option or press q to quit.");
            OPTIONS.forEach(System.out::println);
            System.out.println();
            option = scanner.nextLine();
            switch (option) {
                case "1" -> {
                    System.out.println("Enter a survey ID:");
                    surveyService.getMembersBySurveyId(scanner.nextLong()).forEach(System.out::println);
                    scanner.nextLine();
                    System.out.println();
                }
                case "2" -> {
                    System.out.println("Enter a member ID:");
                    surveyService.getCompletedSurveysByMemberId(scanner.nextLong()).forEach(System.out::println);
                    scanner.nextLine();
                    System.out.println();
                }
                case "3" -> {
                    System.out.println("Enter a member ID:");
                    surveyService.getSurveyPointsByMemberId(scanner.nextLong()).forEach(System.out::println);
                    scanner.nextLine();
                    System.out.println();
                }
                case "4" -> {
                    System.out.println("Enter a survey ID:");
                    surveyService.getNotInvitedActiveMembers(scanner.nextLong()).forEach(System.out::println);
                    scanner.nextLine();
                    System.out.println();
                }
                case "5" -> {
                    surveyService.getSurveyStatistics().forEach(System.out::println);
                    System.out.println();
                }
            }
        } while (!"q".equals(option));
    }
}