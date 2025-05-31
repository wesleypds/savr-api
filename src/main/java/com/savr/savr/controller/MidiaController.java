package com.savr.savr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.savr.savr.service.YtDlpService;

@RestController
@RequestMapping("api/midia")
public class MidiaController {

    @Autowired
    private YtDlpService ytDlpService;

    @PostMapping("/baixar")
    public List<String> baixarMidia(@RequestParam String url, @RequestParam(defaultValue = "false") boolean audioOnly) {
        return ytDlpService.getDownloadLinks(url, audioOnly);
    }

}
