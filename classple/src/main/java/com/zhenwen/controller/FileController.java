package com.zhenwen.controller;

import com.zhenwen.common.web.domain.AjaxResult;
import com.zhenwen.service.FileService;
import com.zhenwen.utils.file.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhenwen
 * @date 2020/11/19
 */
@RestController
@RequestMapping("/resources")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/course")
    public AjaxResult getResources(int id) {
        AjaxResult ajax = AjaxResult.success(fileService.findResourcesByCourseId(id));

        return ajax;
    }

    @GetMapping("/download")
    public void download(int id, HttpServletResponse response, HttpServletRequest request) {
        String filename = fileService.findById(id).getFileName();

        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, filename));

            fileService.download(id, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile[] files) {
        return AjaxResult.success(fileService.upload(files));
    }
}
