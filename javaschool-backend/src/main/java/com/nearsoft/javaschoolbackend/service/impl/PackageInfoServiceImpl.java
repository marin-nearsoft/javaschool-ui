package com.nearsoft.javaschoolbackend.service.impl;

import com.nearsoft.javaschoolbackend.service.PackageInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackageInfoServiceImpl implements PackageInfoService {

    @Override
    public List<String> getPackageTypes() {
        List<String> res = new ArrayList<String>();
        res.add("Box");
        res.add("Document");
        return res;
    }

    @Override
    public List<String> getPackageSizes() {
        List<String> res = new ArrayList<String>();
        res.add("Small");
        res.add("Medium");
        res.add("Large");
        return res;
    }
}
