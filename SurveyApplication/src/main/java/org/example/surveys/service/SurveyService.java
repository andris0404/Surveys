package org.example.surveys.service;

import org.example.surveys.data.MemberRepository;
import org.example.surveys.data.ParticipationRepository;
import org.example.surveys.data.SurveyRepository;
import org.example.surveys.domain.Member;
import org.example.surveys.domain.Participation;
import org.example.surveys.domain.Point;
import org.example.surveys.domain.Statistics;
import org.example.surveys.domain.Status;
import org.example.surveys.domain.Survey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return participationRepository.getParticipants().stream().
                filter(participation -> participation.getSurveyId().equals(surveyId) && participation.getStatus().equals(Status.COMPLETED))
                .map(participation -> memberRepository.getMembers().get(participation.getMemberId()))
                .toList();
    }

    public List<Survey> getCompletedSurveysByMemberId(final Long memberId) {
        return participationRepository.getParticipants().stream()
                .filter(participation -> participation.getMemberId().equals(memberId) && participation.getStatus().equals(Status.COMPLETED))
                .map(participation -> surveyRepository.getSurveys().get(participation.getSurveyId()))
                .toList();
    }

    public List<Member> getNotInvitedActiveMembers(final Long surveyId) {
        return participationRepository.getParticipants().stream()
                .filter(participation -> participation.getSurveyId().equals(surveyId) && participation.getStatus().equals(Status.NOT_ASKED))
                .map(participation -> memberRepository.getMembers().get(participation.getMemberId()))
                .filter(Member::isActive)
                .toList();

    }

    public List<Point> getSurveyPointsByMemberId(final Long memberId) {
        return participationRepository.getParticipants().stream()
                .filter(participation -> participation.getMemberId().equals(memberId) && isEligibleForPoint(participation.getStatus()))
                .map(participation -> {
                    Survey survey = surveyRepository.getSurveys().get(participation.getSurveyId());
                    Point point = new Point();
                    point.setSurveyId(survey.getId());
                    point.setNumOfPoints(participation.getStatus().equals(Status.COMPLETED) ? survey.getCompletionPoints() : survey.getFilteredPoints());
                    return point;
                })
                .toList();
    }

    private boolean isEligibleForPoint(final Status status) {
        return Status.COMPLETED.equals(status) || Status.FILTERED.equals(status);
    }

    public List<Statistics> getSurveyStatistics() {
        List<Statistics> results = new ArrayList<>();
        Map<Long, List<Participation>> participantMap = participationRepository.getParticipants().stream()
                .collect(Collectors.groupingBy(Participation::getSurveyId));
        participantMap.forEach((surveyId, participationList) -> {
            Statistics statistics = createStatisticsForSurvey(surveyId, participationList);
            results.add(statistics);
        });
        return results;
    }

    private Statistics createStatisticsForSurvey(final Long surveyId, final List<Participation> participationList) {
        int completed = 0;
        int filtered = 0;
        int rejected = 0;
        float totalLength = 0;
        for (Participation participation : participationList) {
            totalLength += participation.getLength();
            if (Status.COMPLETED.equals(participation.getStatus())) {
                completed++;
            } else if (Status.FILTERED.equals(participation.getStatus())) {
                filtered++;
            } else if (Status.REJECTED.equals(participation.getStatus())) {
                rejected++;
            }
        }
        Statistics statistics = new Statistics();
        statistics.setSurveyId(surveyId);
        statistics.setSurveyName(surveyRepository.getSurveys().get(surveyId).getName());
        statistics.setNumOfCompletes(completed);
        statistics.setNumOfFiltered(filtered);
        statistics.setNumOfRejected(rejected);
        statistics.setAvgLength(Math.round(totalLength / participationList.size()));
        return statistics;
    }
}
