package com.example.bug_tracker.controller;

import com.example.bug_tracker.model.bug;
import com.example.bug_tracker.repository.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BugController {

    @Autowired
    private BugRepository bugRepo;

    @GetMapping("/bugform")
    public String bugForm() {
        return "bug_form";
    }

    @PostMapping("/submitbug")
    public String submitBug(@RequestParam String description, Model model) {
        bug bug = new bug();
        bug.setDescription(description);

        String desc = description.toLowerCase();
        String correction = "";

        if (desc.contains("int x = 5") && !desc.contains(";")) {
            correction = "Corrected: int x = 5;";
            if (desc.contains("system.out.println")) {
                correction += " System.out.println(x);";
            }
        } else if (desc.contains("system.out.println") && !desc.contains(";")) {
            correction = "Corrected: System.out.println(\"message\");";
        } else if (desc.contains("undeclared variable")) {
            correction = "Declare the variable before using it. Example: int y = 0;";
        } else if (desc.contains("missing return type")) {
            correction = "Add a return type. Example: void methodName()";
        } else if (desc.contains("missing bracket") || desc.contains("missing closing brace")) {
            correction = "Add closing bracket or brace. Example: }";
        } else if (desc.contains("array index out of bounds")) {
            correction = "Check array length before access. Use: if (i < array.length)";
        } else if (desc.contains("null pointer")) {
            correction = "Add null check before using object. Example: if(obj != null) {...}";
        } else if (desc.contains("infinite loop")) {
            correction = "Add a break condition or increment inside the loop.";
        } else if (desc.contains("cannot find symbol")) {
            correction = "Check variable/method spelling or declare it first.";
        } else if (desc.contains("class not found")) {
            correction = "Check if the class is imported or present in the classpath.";
        } else {
            correction = "Corrected: " + description.replace("error", "fixed");
        }

        bug.setCorrectedLine(correction);
        bugRepo.save(bug);
        model.addAttribute("fixedLine", correction);

        return "show_bug_fix";
    }
}