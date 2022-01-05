package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";
    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        boolean oneTask = checkIfOneTask(size);
        send(oneTask, size);
    }

    private boolean checkIfOneTask(final long size) {
        return size == 1;
    }

    private void send(final boolean oneTask, final long size){
        Mail mail = buildMailWithMessage(oneTask, size);
        simpleEmailService.send(mail);
    }

    private Mail buildMailWithMessage(final boolean oneTask, final long size) {
        String message = "";
        if (oneTask)
            message = String.format("Currently in database you got: %d task", size);
        else
            message = String.format("Currently in database you got: %d tasks", size);

        return build(message);
    }

    private Mail build(final String message) {
        return Mail.builder()
                .mailTo(adminConfig.getAdminMail())
                .subject(SUBJECT)
                .message(message)
                .build();
    }
}
