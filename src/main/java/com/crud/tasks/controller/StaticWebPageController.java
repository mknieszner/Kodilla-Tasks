package com.crud.tasks.controller;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Static WebPage Controller.
 */
@CrossOrigin(origins = "*")
@Controller
public class StaticWebPageController {
  @Autowired
  private DbService dbService;

  @RequestMapping("/")
  public String index(final Map<String, Object> model) {
    model.put("variable", "My Thymeleaf variable");
    model.put("one", 2);
    model.put("two", 2);
    model.put("three", 2);
    return "index";
  }

  @Autowired
  private AdminConfig adminConfig;
  @Value("${info.company.name}")
  private String companyName;

//  @RequestMapping("/testMail")
// public String testMailTemplate(final Map<String, Object> model) {
//    List<String> functionality = new ArrayList<>();
//    functionality.add("You can range your tasks");
//    functionality.add("Provides connection with Trello Account");
//    functionality.add("Application allows sending tasks to Trello");
//
//    model.put("message", "Test message");
//    model.put("tasks_url", "http://localhost:8080/crud");
//    model.put("button", "Visit website");
//    model.put("admin_name", adminConfig.getAdminName());
//    model.put("company_name", companyName);
//    model.put("bye_message", "Bye!");
//    model.put("show_button", true);
//    model.put("is_friend", true);
//    model.put("application_functionality",functionality);
//    return "mail/created-trello-card-mail";
//  }
//
//  @RequestMapping("/testMail2")
//  public String tasksQtyInformationEmail(final Map<String, Object> model) {
//    model.put("task_count", dbService.count().toString());
//    model.put("tasks_url","http://localhost:8080/crud");
//    model.put("button","Visit website");
//    model.put("admin_name", adminConfig.getAdminName());
//    model.put("company_name",companyName);
//    model.put("bye_message","Bye!");
//    model.put("show_button",true);
//    model.put("is_friend", true);
//    return "mail/tasks-qty-information";
//  }
}