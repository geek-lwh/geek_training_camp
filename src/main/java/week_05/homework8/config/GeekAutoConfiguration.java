package week_05.homework8.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import week_05.homework8.entity.Klass;
import week_05.homework8.entity.School;
import week_05.homework8.entity.Student;
import week_05.homework8.preperties.KlassProperties;
import week_05.homework8.preperties.SchoolProperties;
import week_05.homework8.preperties.StudentProperties;

@Configuration
@EnableConfigurationProperties({KlassProperties.class,SchoolProperties.class,StudentProperties.class})
//@EnableConfigurationProperties({KlassProperties.class})
public class GeekAutoConfiguration {

    @Autowired
    private KlassProperties klassProperties;
//
    @Autowired
    private SchoolProperties schoolProperties;

    @Autowired
    private StudentProperties studentProperties;

    @Bean
    public Klass klass() {
        Klass klass = new Klass();
        klass.setKlassName(klassProperties.getKlassName());
        return klass;
    }

    @Bean
    public School school() {
        School school = new School();
        school.setSchoolName(schoolProperties.getSchoolName());
        return school;
    }

    @Bean
    public Student student() {
        Student student = new Student();
        student.setStudentName(schoolProperties.getSchoolName());
        return student;
    }

}
