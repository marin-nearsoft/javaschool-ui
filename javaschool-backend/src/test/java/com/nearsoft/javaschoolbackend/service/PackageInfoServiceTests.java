package com.nearsoft.javaschoolbackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
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

}
