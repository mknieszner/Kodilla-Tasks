package com.crud.tasks.service.mail;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Tasks Info Email Creator.
 */
@Service
public class TasksInfoEmailCreator implements EmailCreator {
  @Autowired
  private AdminConfig adminConfig;
  @Autowired
  DbService dbService;
  @Autowired
  @Qualifier("templateEngine")
  private TemplateEngine templateEngine;
  @Value("${info.company.name}")
  private String companyName;

  public String buildEmail(final String message) {
    final Context context = new Context();
    context.setVariable("task_count", dbService.count());
    context.setVariable("tasks_url", "https://mknieszner.github.io");
    context.setVariable("button", "Visit website");
    context.setVariable("admin_name", adminConfig.getAdminName());
    context.setVariable("company_name", companyName);
    context.setVariable("bye_message", "Bye!");
    context.setVariable("show_button", true);
    context.setVariable("is_friend", true);
    return templateEngine.process("mail/tasks-qty-information", context);
  }
}
