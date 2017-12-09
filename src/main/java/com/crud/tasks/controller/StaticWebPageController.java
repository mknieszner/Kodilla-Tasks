package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
