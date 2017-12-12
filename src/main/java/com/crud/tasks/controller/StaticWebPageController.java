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
  @RequestMapping("/")
  public String index(final Map<String, Object> model) {
    model.put("variable", "My Thymeleaf variable");
    model.put("one", 2);
    model.put("two", 2);
    model.put("three", 2);
    return "index";
  }
}