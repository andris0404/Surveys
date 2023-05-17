package org.example.surveys;

import org.example.surveys.data.MemberRepository;
import org.example.surveys.data.ParticipationRepository;
import org.example.surveys.data.SurveyRepository;
import org.example.surveys.domain.Member;
import org.example.surveys.domain.Point;
import org.example.surveys.domain.Statistics;
import org.example.surveys.domain.Survey;
import org.example.surveys.service.SurveyService;

import java.util.List;

public class SurveyApp {
    private final SurveyService surveyService;
    private final SurveyRepository surveyRepository;
    private final MemberRepository memberRepository;
    private final ParticipationRepository participationRepository;

    public SurveyApp() {
        memberRepository = new MemberRepository();
        surveyRepository = new SurveyRepository();
        participationRepository = new ParticipationRepository();
        surveyService = new SurveyService(surveyRepository, memberRepository, participationRepository);
    }

    public static void main(String[] args) {
        new SurveyApp().start();
    }

    public void start() {
        List<Member> membersBySurveyId = surveyService.getMembersBySurveyId(1L);
        System.out.println("All respondents who completed the given survey:");
        membersBySurveyId.forEach(System.out::println);
        System.out.println();
        List<Survey> completedSurveysByMemberId = surveyService.getCompletedSurveysByMemberId(1L);
        System.out.println("All surveys with the given member ID:");
        completedSurveysByMemberId.forEach(System.out::println);
        System.out.println();
        List<Member> notInvitedActiveMembers = surveyService.getNotInvitedActiveMembers(1L);
        System.out.println("All active members who can be invited for the given survey:");
        notInvitedActiveMembers.forEach(System.out::println);
        System.out.println();
        List<Point> surveyPointsByMemberId = surveyService.getSurveyPointsByMemberId(1L);
        System.out.println("All points by the given member ID:");
        surveyPointsByMemberId.forEach(System.out::println);
        System.out.println();
        List<Statistics> surveyStatistics = surveyService.getSurveyStatistics();
        System.out.println("Survey statistics:");
        surveyStatistics.forEach(System.out::println);
    }
}