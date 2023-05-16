package org.example.surveys;

import org.example.surveys.data.MemberRepository;
import org.example.surveys.data.ParticipationRepository;
import org.example.surveys.data.SurveyRepository;
import org.example.surveys.service.SurveyService;

public class SurveyApp {
    private final SurveyService surveyService;
    private final SurveyRepository surveyRepository;
    private final MemberRepository memberRepository;
    private final ParticipationRepository participationRepository;

    public SurveyApp() {
        surveyRepository = new SurveyRepository();
        memberRepository = new MemberRepository();
        participationRepository = new ParticipationRepository();
        surveyService = new SurveyService(surveyRepository, memberRepository, participationRepository);
    }

    public static void main(String[] args) {
        SurveyApp app = new SurveyApp();
    }
}