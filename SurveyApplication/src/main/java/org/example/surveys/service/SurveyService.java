package org.example.surveys.service;

import org.example.surveys.data.MemberRepository;
import org.example.surveys.data.ParticipationRepository;
import org.example.surveys.data.SurveyRepository;
import org.example.surveys.domain.Member;
import org.example.surveys.domain.Point;
import org.example.surveys.domain.Status;
import org.example.surveys.domain.Survey;

import java.util.ArrayList;
import java.util.List;

public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final MemberRepository memberRepository;
    private final ParticipationRepository participationRepository;

    public SurveyService(SurveyRepository surveyRepository, MemberRepository memberRepository, ParticipationRepository participationRepository) {
        this.surveyRepository = surveyRepository;
        this.memberRepository = memberRepository;
        this.participationRepository = participationRepository;
    }

    public List<Member> getMembersBySurveyId(final Long surveyId) {
        List<Member> results = new ArrayList<>();
        participationRepository.getParticipants().stream().
                filter(participation -> participation.getSurveyId().equals(surveyId) && participation.getStatus().equals(Status.COMPLETED))
                .forEach(participation -> results.add(memberRepository.getMembers().get(participation.getMemberId())));
        return results;
    }

    public List<Survey> getCompletedSurveysByMemberId(final Long memberId) {
        List<Survey> results = new ArrayList<>();
        participationRepository.getParticipants().stream()
                .filter(participation -> participation.getMemberId().equals(memberId) && participation.getStatus().equals(Status.COMPLETED))
                .forEach(participation -> results.add(surveyRepository.getSurveys().get(participation.getSurveyId())));
        return results;
    }

    public List<Member> getNotInvitedActiveMembers(final Long surveyId) {
        List<Member> results = new ArrayList<>();
        participationRepository.getParticipants().stream()
                .filter(participation -> participation.getSurveyId().equals(surveyId) && participation.getStatus().equals(Status.NOT_ASKED))
                .forEach(participation -> {
                    Member member = memberRepository.getMembers().get(participation.getMemberId());
                    if (member.isActive()) {
                        results.add(member);
                    }
                });
        return results;
    }

    public List<Point> getSurveyPointsByMemberId(final Long memberId) {
        List<Point> results = new ArrayList<>();
        participationRepository.getParticipants().stream()
                .filter(participation -> participation.getMemberId().equals(memberId) && isEligibleForPoint(participation.getStatus()))
                .forEach(participation -> {
                    Survey survey = surveyRepository.getSurveys().get(participation.getSurveyId());
                    Point point = new Point();
                    point.setSurveyId(survey.getId());
                    point.setNumOfPoints(participation.getStatus().equals(Status.COMPLETED) ? survey.getCompletionPoints() : survey.getFilteredPoints());
                    results.add(point);
                });
        return results;
    }

    private boolean isEligibleForPoint(final Status status) {
        return Status.COMPLETED.equals(status) || Status.FILTERED.equals(status);
    }
}
