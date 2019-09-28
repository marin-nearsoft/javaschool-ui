package com.nearsoft.javaschoolbackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nearsoft.javaschoolbackend.model.response.PackageSize;
import com.nearsoft.javaschoolbackend.model.response.PackageType;
import com.nearsoft.javaschoolbackend.service.impl.PackageInfoServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PackageInfoServiceTests {

    private  PackageInfoService packageInfoService;

    private PackageService packageService;

    @Before
    public void initUseCase(){
        packageService = mock(PackageService.class);
        packageInfoService = new PackageInfoServiceImpl(packageService);
    }

    @Test
    public void testGetPackageInfoTypes() throws JsonProcessingException {
        List<String> packageInfoTypes = Arrays.asList("Box", "Envelope");
        List<PackageType> packageTypes = new ArrayList<PackageType>();
        PackageType packageTypeOne = new PackageType(2, "Box", 10);
        PackageType packageTypeTwo = new PackageType(3, "Envelope", 5);
        packageTypes.add(packageTypeOne);
        packageTypes.add(packageTypeTwo);
        when(packageService.getPackageTypes()).thenReturn(packageTypes);
        assertEquals(packageInfoTypes, packageInfoService.getPackageDescriptionTypes());
    }

    @Test
    public void testGetPackageInfoSizes() throws JsonProcessingException {
        List<String> packageInfoSizes = Arrays.asList("Small", "Medium", "Large");
        List<PackageSize> packageSizes = new ArrayList<PackageSize>();
        PackageSize packageSizeOne = new PackageSize(1, "Small", 5);
        PackageSize packageSizeTwo = new PackageSize(2, "Medium", 10);
        PackageSize packageSizeThree = new PackageSize(3, "Large", 15);
        packageSizes.add(packageSizeOne);
        packageSizes.add(packageSizeTwo);
        packageSizes.add(packageSizeThree);
        when(packageService.getPackageSizes()).thenReturn(packageSizes);
        assertEquals(packageInfoSizes, packageInfoService.getPackageDescriptionSizes());
    }

}
