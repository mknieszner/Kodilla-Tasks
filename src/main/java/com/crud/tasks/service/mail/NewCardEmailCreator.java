package com.crud.tasks.service.mail;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * New Card Email Creator.
 */
@Service
public class NewCardEmailCreator implements EmailCreator {
  @Autowired
  private AdminConfig adminConfig;
  @Autowired
  DbService dbService;
  @Autowired
  @Qualifier("templateEngine")
  private TemplateEngine templateEngine;
  @Value("${info.company.name}")
  private String companyName;

  @Override
  public String buildEmail(final String message) {
    final List<String> functionality = new ArrayList<>();
    functionality.add("You can range your tasks");
    functionality.add("Provides connection with Trello Account");
    functionality.add("Application allows sending tasks to Trello");

    final Context context = new Context();
    context.setVariable("message", message);
    context.setVariable("tasks_url", "https://mknieszner.github.io");
    context.setVariable("button", "Visit website");
    context.setVariable("admin_name", adminConfig.getAdminName());
    context.setVariable("company_name", companyName);
    context.setVariable("bye_message", "Bye!");
    context.setVariable("show_button", true);
    context.setVariable("is_friend", true);
    context.setVariable("application_functionality", functionality);
    return templateEngine.process("mail/created-trello-card-mail", context);
  }
}
