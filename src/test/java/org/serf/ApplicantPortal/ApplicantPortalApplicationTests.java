package org.serf.ApplicantPortal;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.serf.ApplicantPortal.domain.Applicant;
import org.serf.ApplicantPortal.domain.Faculty;
import org.serf.ApplicantPortal.domain.Subject;
import org.serf.ApplicantPortal.domain.User;
import org.serf.ApplicantPortal.repository.ApplicantRepo;
import org.serf.ApplicantPortal.repository.FacultyRepo;
import org.serf.ApplicantPortal.repository.SubjectRepo;
import org.serf.ApplicantPortal.repository.UserRepo;
import org.serf.ApplicantPortal.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages = "org.serf.ApplicantPortal.*")
class ApplicantPortalApplicationTests {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ApplicantService applicantService;
    @Autowired
    private ApplicantRepo applicantRepo;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectRepo subjectRepo;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private FacultyRepo facultyRepo;

    @Test
    void testSaveUser() {
        List<User> users = userRepo.findAll();
        assertThat(users, hasSize(0));

        User user = new User();
        user.setUsername("TestUser");
        user.setEmail("TestUser@gmail.com");
        user.setFirstName("TestUser1");
        user.setLastName("TestUser1");
        user.setBirthYear(2005);
        user.setPassword("123456789");

        registrationService.saveUser(user);

        users = userRepo.findAll();
        assertThat(users, hasSize(1));

        User userFromDB = users.get(0);

        assertTrue(userFromDB.getUsername().equals(user.getUsername()));
        assertTrue(userFromDB.getEmail().equals(user.getEmail()));
        assertTrue(userFromDB.getFirstName().equals(user.getFirstName()));
        assertTrue(userFromDB.getLastName().equals(user.getLastName()));
        assertTrue(userFromDB.getPassword().equals(user.getPassword()));
        assertTrue(userFromDB.getRole().equals(user.getRole()));
    }

    @Test
    void testGetUserByUsername() {
        List<User> users = userRepo.findAll();
        assertThat(users, hasSize(0));

        User user = new User();
        user.setUsername("TestUser1");
        user.setEmail("TestUser1@gmail.com");
        user.setFirstName("TestUser2");
        user.setLastName("TestUser2");
        user.setBirthYear(2010);
        user.setPassword("12345678910");

        registrationService.saveUser(user);

        User foundedUser = userService.getUserByUserName(user.getUsername()).get();

        assertTrue(user.getUsername().equals(foundedUser.getUsername()));
        assertEquals(user, foundedUser);
    }

    @Test
    void testSaveApplicant() {
        List<Applicant> applicants = applicantRepo.findAll();
        assertThat(applicants, hasSize(0));

        Applicant applicant = new Applicant();
        applicant.setFirstName("Applicant1");
        applicant.setLastName("Applicant2");
        applicant.setBirthYear(2005);

        applicantService.saveApplicant(applicant);

        applicants = applicantRepo.findAll();
        assertThat(applicants, hasSize(1));

        Applicant applicantFromDB = applicants.get(0);

        assertTrue(applicant.getFirstName().equals(applicantFromDB.getFirstName()));
        assertTrue(applicant.getLastName().equals(applicantFromDB.getLastName()));
        assertTrue(applicant.getBirthYear() == applicantFromDB.getBirthYear());
    }

    @Test
    void testGetApplicantById() {
        List<Applicant> applicants = applicantRepo.findAll();
        assertThat(applicants, hasSize(0));

        Applicant applicant = new Applicant();
        applicant.setFirstName("Applicant2");
        applicant.setLastName("Applicant3");
        applicant.setBirthYear(2006);

        applicantService.saveApplicant(applicant);

        Applicant foundedApplicant = applicantService.findApplicantById(1).get();

        assertTrue(applicant.getId().equals(foundedApplicant.getId()));
        assertEquals(applicant, foundedApplicant);

    }

    @Test
    void testGetAllApplicants() {
        List<Applicant> applicants = applicantRepo.findAll();
        assertThat(applicants, hasSize(0));

        Applicant applicant1 = new Applicant();
        applicant1.setFirstName("Applicant1");
        applicant1.setLastName("Applicant1");
        applicant1.setBirthYear(2001);

        Applicant applicant2 = new Applicant();
        applicant2.setFirstName("Applicant2");
        applicant2.setLastName("Applicant2");
        applicant2.setBirthYear(2002);

        applicantRepo.saveAll(Arrays.asList(applicant1, applicant2));

        Applicant applicantFromDB1 = applicantService.getAllApplicants().get(0);
        Applicant applicantFromDB2 = applicantService.getAllApplicants().get(1);

        assertEquals(applicant1, applicantFromDB1);
        assertEquals(applicant2, applicantFromDB2);
    }

    @Test
    void testSaveSubject() {
        List<Subject> subjects = subjectRepo.findAll();
        assertThat(subjects, hasSize(0));

        Subject subject = new Subject();
        subject.setName("MATH");
        subject.setRate((byte) 100);

        subjectService.saveSubject(subject);

        subjects = subjectRepo.findAll();
        assertThat(subjects, hasSize(1));

        Subject subjectFromDB = subjects.get(0);

        assertTrue(subject.getName().equals(subjectFromDB.getName()));
        assertTrue(subject.getRate().equals(subjectFromDB.getRate()));
    }

    @Test
    void testGetAllFaculties() {
        List<Faculty> faculties = facultyRepo.findAll();
        assertThat(faculties, hasSize(0));

        Faculty faculty1 = new Faculty();
        faculty1.setFacultyName("HISTORY");
        faculty1.setRecruitmentPlan(25);

        Faculty faculty2 = new Faculty();
        faculty2.setFacultyName("MATH");
        faculty2.setRecruitmentPlan(60);

        facultyRepo.saveAll(Arrays.asList(faculty1, faculty2));

        Faculty facultyFromDB1 = facultyService.getAllFaculties().get(0);
        Faculty facultyFromDB2 = facultyService.getAllFaculties().get(1);

        assertEquals(faculty1, facultyFromDB1);
        assertEquals(faculty2, facultyFromDB2);
    }
}
