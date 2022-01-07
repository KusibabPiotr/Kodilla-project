package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EmailSchedulerTest {

    @InjectMocks
    private EmailScheduler emailScheduler;
    @Mock
    private SimpleEmailService simpleEmailService;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private AdminConfig adminConfig;


//
//    @Test
//    void testSendInformationEmail() {
//        //given
//        String to = "test@test.pl";
//        String message = "message";
//        String subject = "subject";
//        Mail mail = new Mail(to, null, subject, message);
//        when(taskRepository.count()).thenReturn(1L);
//        when(adminConfig.getAdminMail()).thenReturn("test@test.pl");
//        doAnswer().when(simpleEmailService).send(mail);
//        //when
//        emailScheduler.sendInformationEmail();
//        //then
//        verify(simpleEmailService,times(1)).send(mail);
//    }


}