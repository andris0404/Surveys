package org.example.surveys.data;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.surveys.domain.Member;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MemberRepository {
    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int EMAIL = 2;
    private static final int IS_ACTIVE = 3;
    private static final String PATH = "src/main/resources/OO - 2 - Members.csv";
    private final Map<Long, Member> members;

    public MemberRepository() {
        this.members = new HashMap<>();
        readMembersFromCSV();
    }

    public Map<Long, Member> getMembers() {
        return members;
    }

    private void readMembersFromCSV() {
        try (CSVReader csvReader = new CSVReader(new FileReader(PATH))) {
            csvReader.readNext(); // skipping first line
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                createMember(line);
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createMember(final String[] line) {
        Member member = new Member();
        member.setId(Long.parseLong(line[ID]));
        member.setName(line[NAME]);
        member.setEmail(line[EMAIL]);
        member.setActive(line[IS_ACTIVE].equals("1") ? true : false);
        members.put(member.getId(), member);
    }
}
