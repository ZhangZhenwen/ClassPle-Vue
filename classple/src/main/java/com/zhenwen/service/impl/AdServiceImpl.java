package com.zhenwen.service.impl;

import com.zhenwen.domain.Ad;
import com.zhenwen.service.AdService;
import com.zhenwen.utils.Pager;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhenwen
 * @date 2020/10/13
 */
@Service
public class AdServiceImpl implements AdService {

    @Override
    public List<Ad> findAll() {
        return null;
    }

    @Override
    public Pager<Ad> findByPager(int page, int size) {
        return null;
    }

    @Override
    public Ad findById(int id) {
        return null;
    }

    @Override
    public Boolean insert(Ad ad) {
        return null;
    }

    @Override
    public Boolean updateById(Ad ad) {
        return null;
    }

    @Override
    public Boolean deleteById(int id) {
        return null;
    }
}
