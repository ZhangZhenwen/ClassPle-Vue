package com.zhenwen.controller;

import com.zhenwen.common.web.domain.AjaxResult;
import com.zhenwen.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhenwen
 * @date 2020/12/1
 */
@RestController
@RequestMapping("/archive")
public class ArchiveController {

    @Autowired
    private ArchiveService archiveService;

    @GetMapping("/list")
    public AjaxResult getArchiveCourses() {
        return AjaxResult.success(archiveService.findFiledCoursesByUser());
    }

    @PostMapping("/addArchive")
    public AjaxResult fileCourseForOne(@RequestBody Integer crseId) {
        return archiveService.fileCourseForOne(crseId) ? AjaxResult.success() : AjaxResult.error();
    }

    @PostMapping("/addArchiveAll")
    public AjaxResult fileCourseAll(@RequestBody Integer crseId) {
        return archiveService.fileCourseAll(crseId) ? AjaxResult.success() : AjaxResult.error();
    }

    @PostMapping("/cancelArchive")
    public AjaxResult cancelFileCourseForOne(@RequestBody Integer crseId) {
        return archiveService.cancelFileCourseForOne(crseId) ? AjaxResult.success() : AjaxResult.error();
    }

    @PostMapping("/cancelArchiveAll")
    public AjaxResult cancelFileCourseAll(@RequestBody Integer crseId) {
        return archiveService.cancelFileCourseAll(crseId) ? AjaxResult.success() : AjaxResult.error();
    }
}
