import org.example.surveys.data.MemberRepository;
import org.example.surveys.data.ParticipationRepository;
import org.example.surveys.data.SurveyRepository;
import org.example.surveys.domain.Member;
import org.example.surveys.domain.Participation;
import org.example.surveys.domain.Status;
import org.example.surveys.domain.Survey;
import org.example.surveys.service.SurveyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SurveyServiceTest {
    private static final Long NONEXISTENT_ID = 5000L;
    @InjectMocks
    private SurveyService underTest;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private SurveyRepository surveyRepository;
    @Mock
    private ParticipationRepository participationRepository;

    @Test
    void getMembersBySurveyId_validIdGiven_returnsMembersWhoCompleted() {
        // Given
        List<Participation> participants = createTestParticipationList();
        Map<Long, Member> members = createTestMemberList();
        int expectedMembers = 2;
        when(participationRepository.getParticipants()).thenReturn(participants);
        when(memberRepository.getMembers()).thenReturn(members);
        // When
        List<Member> actual = underTest.getMembersBySurveyId(1L);
        // Then
        assertEquals(expectedMembers, actual.size());
    }

    @Test
    void getMembersBySurveyId_invalidIdGiven_returnsEmptyList() {
        // Given
        List<Participation> participants = createTestParticipationList();
        when(participationRepository.getParticipants()).thenReturn(participants);
        // When
        List<Member> actual = underTest.getMembersBySurveyId(NONEXISTENT_ID);
        // Then
        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }

    @Test
    void getCompletedSurveysByMemberId_validIdGiven_returnsCompletedSurveys() {
        // Given
        List<Participation> participants = createTestParticipationList();
        Map<Long, Survey> surveys = createTestSurveyList();
        int expectedSurveys = 3;
        when(participationRepository.getParticipants()).thenReturn(participants);
        when(surveyRepository.getSurveys()).thenReturn(surveys);
        // When
        List<Survey> actual = underTest.getCompletedSurveysByMemberId(3L);
        // Then
        assertEquals(expectedSurveys, actual.size());
    }

    @Test
    void getCompletedSurveysByMemberId_invalidIdGiven_returnsEmptyList() {
        // Given
        List<Participation> participants = createTestParticipationList();
        when(participationRepository.getParticipants()).thenReturn(participants);
        // When
        List<Survey> actual = underTest.getCompletedSurveysByMemberId(NONEXISTENT_ID);
        // Then
        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }

    @Test
    void getNotInvitedActiveMembersBySurveyId_validIdGiven_returnsActiveMembers() {
        // Given
        List<Participation> participants = createTestParticipationList();
        Map<Long, Member> members = createTestMemberList();
        when(participationRepository.getParticipants()).thenReturn(participants);
        when(memberRepository.getMembers()).thenReturn(members);
        int expectedMembers = 1;
        // When
        List<Member> actual = underTest.getNotInvitedActiveMembers(4L);
        // Then
        assertEquals(expectedMembers, actual.size());
    }

    @Test
    void getNotInvitedActiveMembersBySurveyId_validIdGiven_noOneCanBeInvited() {
        // Given
        List<Participation> participants = createTestParticipationList();
        when(participationRepository.getParticipants()).thenReturn(participants);
        // When
        List<Member> actual = underTest.getNotInvitedActiveMembers(3L);
        // Then
        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }

    @Test
    void getNotInvitedActiveMembersBySurveyId_invalidIdGiven_returnsEmptyList() {
        // Given
        List<Participation> participants = createTestParticipationList();
        when(participationRepository.getParticipants()).thenReturn(participants);
        // When
        List<Member> actual = underTest.getNotInvitedActiveMembers(NONEXISTENT_ID);
        // Then
        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }

    private Map<Long, Member> createTestMemberList() {
        Member member1 = createTestMember(1L, true);
        Member member2 = createTestMember(2L, true);
        Member member3 = createTestMember(3L, true);
        Member member4 = createTestMember(4L, false);
        Member member5 = createTestMember(5L, true);
        Map<Long, Member> members = new HashMap<>();
        members.put(member1.getId(), member1);
        members.put(member2.getId(), member2);
        members.put(member3.getId(), member3);
        members.put(member4.getId(), member4);
        members.put(member5.getId(), member5);
        return members;
    }

    private Map<Long, Survey> createTestSurveyList() {
        Survey survey1 = createTestSurvey(1L, "test1", 30, 5, 2);
        Survey survey2 = createTestSurvey(2L, "test2", 70, 15, 4);
        Survey survey3 = createTestSurvey(3L, "test3", 100, 10, 2);
        Survey survey4 = createTestSurvey(4L, "test4", 20, 10, 1);
        Survey survey5 = createTestSurvey(5L, "test5", 20, 35, 4);
        Map<Long, Survey> surveys = new HashMap<>();
        surveys.put(survey1.getId(), survey1);
        surveys.put(survey2.getId(), survey2);
        surveys.put(survey3.getId(), survey3);
        surveys.put(survey4.getId(), survey4);
        surveys.put(survey5.getId(), survey5);
        return surveys;
    }

    private List<Participation> createTestParticipationList() {
        List<Participation> testParticipationList = new ArrayList<>();
        testParticipationList.add(createTestParticipation(1L, 1L, Status.COMPLETED, 20));
        testParticipationList.add(createTestParticipation(1L, 2L, Status.FILTERED, 0));
        testParticipationList.add(createTestParticipation(1L, 3L, Status.COMPLETED, 18));
        testParticipationList.add(createTestParticipation(1L, 4L, Status.REJECTED, 0));
        testParticipationList.add(createTestParticipation(1L, 5L, Status.NOT_ASKED, 0));
        testParticipationList.add(createTestParticipation(2L, 1L, Status.FILTERED, 0));
        testParticipationList.add(createTestParticipation(2L, 2L, Status.COMPLETED, 7));
        testParticipationList.add(createTestParticipation(2L, 3L, Status.COMPLETED, 11));
        testParticipationList.add(createTestParticipation(2L, 4L, Status.NOT_ASKED, 0));
        testParticipationList.add(createTestParticipation(2L, 5L, Status.REJECTED, 0));
        testParticipationList.add(createTestParticipation(3L, 1L, Status.FILTERED, 0));
        testParticipationList.add(createTestParticipation(3L, 2L, Status.COMPLETED, 10));
        testParticipationList.add(createTestParticipation(3L, 3L, Status.REJECTED, 0));
        testParticipationList.add(createTestParticipation(3L, 4L, Status.COMPLETED, 25));
        testParticipationList.add(createTestParticipation(3L, 5L, Status.COMPLETED, 14));
        testParticipationList.add(createTestParticipation(4L, 1L, Status.COMPLETED, 5));
        testParticipationList.add(createTestParticipation(4L, 2L, Status.REJECTED, 0));
        testParticipationList.add(createTestParticipation(4L, 3L, Status.COMPLETED, 20));
        testParticipationList.add(createTestParticipation(4L, 4L, Status.NOT_ASKED, 0));
        testParticipationList.add(createTestParticipation(4L, 5L, Status.NOT_ASKED, 0));
        testParticipationList.add(createTestParticipation(5L, 1L, Status.FILTERED, 0));
        testParticipationList.add(createTestParticipation(5L, 2L, Status.FILTERED, 0));
        testParticipationList.add(createTestParticipation(5L, 3L, Status.REJECTED, 0));
        testParticipationList.add(createTestParticipation(5L, 4L, Status.COMPLETED, 30));
        testParticipationList.add(createTestParticipation(5L, 5L, Status.COMPLETED, 17));
        return testParticipationList;
    }

    private Member createTestMember(Long id, boolean isActive) {
        Member member = new Member();
        member.setId(id);
        member.setActive(isActive);
        return member;
    }

    private Survey createTestSurvey(Long id, String name, int expectedCompletes, int completionPoints, int FilteredPoints) {
        Survey survey = new Survey();
        survey.setId(id);
        survey.setName(name);
        survey.setExpectedCompletes(expectedCompletes);
        survey.setCompletionPoints(completionPoints);
        survey.setFilteredPoints(FilteredPoints);
        return survey;
    }

    private Participation createTestParticipation(Long memberId, Long surveyId, Status status, int length) {
        Participation participation = new Participation();
        participation.setMemberId(memberId);
        participation.setSurveyId(surveyId);
        participation.setStatus(status);
        participation.setLength(length);
        return participation;
    }
}
