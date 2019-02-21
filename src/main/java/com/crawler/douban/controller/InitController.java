package com.crawler.douban.controller;

import com.crawler.douban.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/15
 */
@RestController
@RequestMapping("init")
public class InitController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{personal}")
    public ResponseEntity crawle(@PathVariable("personal") String personal) throws IOException, InterruptedException {
        taskService.crawle(personal);
        return ResponseEntity.ok().build();
    }

}
