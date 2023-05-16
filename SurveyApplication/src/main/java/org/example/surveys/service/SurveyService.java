package org.example.surveys.service;

import org.example.surveys.data.MemberRepository;
import org.example.surveys.data.ParticipationRepository;
import org.example.surveys.data.SurveyRepository;
import org.example.surveys.domain.Member;
import org.example.surveys.domain.Status;
import org.example.surveys.domain.Survey;

import java.util.HashSet;
import java.util.Set;

public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final MemberRepository memberRepository;
    private final ParticipationRepository participationRepository;

    public SurveyService(SurveyRepository surveyRepository, MemberRepository memberRepository, ParticipationRepository participationRepository) {
        this.surveyRepository = surveyRepository;
        this.memberRepository = memberRepository;
        this.participationRepository = participationRepository;
    }

    public Set<Member> getMembersBySurveyId(final Long surveyId) {
        Set<Member> result = new HashSet<>();
        participationRepository.getParticipants().stream().
                filter(participation -> participation.getSurveyId().equals(surveyId) && participation.getStatus().equals(Status.COMPLETED))
                .forEach(participation -> result.add(memberRepository.getMembers().get(participation.getMemberId())));
        return result;
    }

    public Set<Survey> getCompletedSurveysByMemberId(final Long memberId) {
        Set<Survey> result = new HashSet<>();
        participationRepository.getParticipants().stream()
                .filter(participation -> participation.getMemberId().equals(memberId) && participation.getStatus().equals(Status.COMPLETED))
                .forEach(participation -> result.add(surveyRepository.getSurveys().get(participation.getSurveyId())));
        return result;
    }

    public Set<Member> getNotInvitedActiveMembers(final Long surveyId) {
        Set<Member> result = new HashSet<>();
        participationRepository.getParticipants().stream()
                .filter(participation -> participation.getSurveyId().equals(surveyId) && participation.getStatus().equals(Status.NOT_ASKED))
                .forEach(participation -> {
                    Member member = memberRepository.getMembers().get(participation.getMemberId());
                    if (member.isActive()) {
                        result.add(member);
                    }
                });
        return result;
    }
}
