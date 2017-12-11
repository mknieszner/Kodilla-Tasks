package com.crud.tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

/**
 * Static WebPage Controller.
 */
@Controller
public class StaticWebPageController {

  @RequestMapping("/")
  public String index(final Map<String, Object> model) {
    model.put("variable", "My Thymeleaf variable");
    model.put("one", 2);
    model.put("two", 2);
    model.put("three", 2);
    return "index";
  }

  @RequestMapping("/testMail")
 public String testMailTemplate(final Map<String, Object> context) {
    context.put("message", "Test message");
    context.put("tasks_url", "http://localhost:8080/crud");
    context.put("button", "Visit website");
    context.put("admin_name", "Mateusz");
    context.put("company_name", "Firma");
    context.put("bye_message", "Bye!");
    context.put("show_button", true);
    return "mail/created-trello-card-mail";
  }
}