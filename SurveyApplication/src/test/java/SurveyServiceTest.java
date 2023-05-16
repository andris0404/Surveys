import org.example.surveys.data.MemberRepository;
import org.example.surveys.data.ParticipationRepository;
import org.example.surveys.data.SurveyRepository;
import org.example.surveys.service.SurveyService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SurveyServiceTest {
    @InjectMocks
    private SurveyService underTest;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private SurveyRepository surveyRepository;
    @Mock
    private ParticipationRepository participationRepository;
}
