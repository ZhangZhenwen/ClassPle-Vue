package com.zhenwen.service.impl;

import com.zhenwen.common.web.domain.TreeEntity;
import com.zhenwen.config.ApplicationConfig;
import com.zhenwen.domain.File;
import com.zhenwen.mapper.CrseFileMapper;
import com.zhenwen.mapper.FileMapper;
import com.zhenwen.service.FileService;
import com.zhenwen.utils.Pager;
import com.zhenwen.utils.file.FileUploadUtils;
import com.zhenwen.utils.file.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileMapper fileMapper;

    @Autowired
    CrseFileMapper crseFileMapper;

    @Override
    public List<File> findAll() {
        return null;
    }

    @Override
    public Pager<File> findByPager(int page, int size) {
        return null;
    }

    @Override
    public File findById(int id) {
        return fileMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean insert(File file) {
        return fileMapper.insertSelective(file) > 0;
    }

    @Override
    public Boolean updateById(File file) {
        return fileMapper.updateByPrimaryKey(file) > 0;
    }

    @Override
    public Boolean deleteById(int id) {
        return fileMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public TreeEntity getTree(int id) {
        TreeEntity tree = new TreeEntity();
        File file = fileMapper.selectByPrimaryKey(id);

        tree.setParentId(0);
        tree.setNode(file);

        tree.setChildren(getChildren(file.getFileId()));

        return tree;
    }

    private List<TreeEntity> getChildren(int parentId) {
        List<File> files = fileMapper.findFileTree(parentId);

        if (files == null || files.size() == 0) {
            return null;
        }

        List<TreeEntity> list = new ArrayList<>();

        for (File file : files) {
            TreeEntity tree = new TreeEntity();
            tree.setParentId(parentId);
            tree.setNode(file);
            tree.setChildren(getChildren(file.getFileId()));

            list.add(tree);
        }

        return list;
    }

    @Override
    public List<TreeEntity> findResourcesByCourseId(Integer id) {
        List<Integer> fileIds = crseFileMapper.findFileIdsByCrseId(id);
        List<TreeEntity> list = new ArrayList<>();

        for (Integer fileId : fileIds) {
            list.add(getTree(fileId));
        }

        return list;
    }

    @Override
    public List<Integer> upload(MultipartFile[] files) {
        String filePath = ApplicationConfig.getUploadPath();
        List<Integer> ids = new ArrayList<>();

        try {
            for (MultipartFile file : files) {
                File myFile = new File();
                String fileName = FileUploadUtils.upload(filePath, file);

                myFile.setFileName(file.getOriginalFilename());
                myFile.setFileUrl(fileName);
                myFile.setFileSize((double) file.getSize());
                myFile.setFileType("file");

                fileMapper.insertSelective(myFile);

                ids.add(myFile.getFileId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ids;
    }

    @Override
    public Integer upload(MultipartFile file) {
        String filePath = ApplicationConfig.getUploadPath();

        File myFile = new File();
        String fileName = null;

        try {
            fileName = FileUploadUtils.upload(filePath, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        myFile.setFileName(file.getOriginalFilename());
        myFile.setFileUrl(fileName);
        myFile.setFileSize((double) file.getSize());
        myFile.setFileType(file.getContentType());
        myFile.setCreateTime(new Date());

        fileMapper.insertSelective(myFile);

        return myFile.getFileId();
    }

    @Override
    public Boolean download(Integer fileId, OutputStream os) {

        File myFile = fileMapper.selectByPrimaryKey(fileId);

        if (!myFile.getDownloadable()) {
            return false;
        }

        String localPath = ApplicationConfig.getProfile();
        String realFileName = myFile.getFileUrl().replace("/profile", "");
        String downloadPath = localPath + realFileName;

        try {
            FileUtils.writeBytes(downloadPath, os);
        } catch (IOException e) {
            e.printStackTrace();
        }

        myFile.setDownloadCount(myFile.getDownloadCount() + 1);
        fileMapper.updateByPrimaryKey(myFile);

        return true;
    }
}
