package edu.mayo.lpea.cad.cadence3.azure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AzureRedirectController {
  @GetMapping("/" )
  public String successfulLoginRedirect() {
    return "redirect:/users";
  }
}
