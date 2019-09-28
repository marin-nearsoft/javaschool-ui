package com.nearsoft.javaschoolbackend.service;

import com.nearsoft.javaschoolbackend.model.request.TypeRequest;
import com.nearsoft.javaschoolbackend.model.response.PackageSize;
import com.nearsoft.javaschoolbackend.model.response.PackageType;
import com.nearsoft.javaschoolbackend.model.response.TransportType;
import com.nearsoft.javaschoolbackend.model.response.TransportVelocity;
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
    public void testGetPackageInfoTypes() {
        List<String> expected = Arrays.asList("Box", "Envelope");
        List<PackageType> packageTypes = new ArrayList<PackageType>();
        PackageType packageTypeOne = new PackageType(2, "Box", 10);
        PackageType packageTypeTwo = new PackageType(3, "Envelope", 5);
        packageTypes.add(packageTypeOne);
        packageTypes.add(packageTypeTwo);
        when(packageService.getPackageTypes()).thenReturn(packageTypes);
        assertEquals(expected, packageInfoService.getPackageDescriptionTypes());
    }

    @Test
    public void testGetPackageInfoSizes() {
        List<String> expected = Arrays.asList("Small", "Medium", "Large");
        List<PackageSize> packageSizes = new ArrayList<PackageSize>();
        PackageSize packageSizeOne = new PackageSize(1, "Small", 5);
        PackageSize packageSizeTwo = new PackageSize(2, "Medium", 10);
        PackageSize packageSizeThree = new PackageSize(3, "Large", 15);
        packageSizes.add(packageSizeOne);
        packageSizes.add(packageSizeTwo);
        packageSizes.add(packageSizeThree);
        when(packageService.getPackageSizes()).thenReturn(packageSizes);
        assertEquals(expected, packageInfoService.getPackageDescriptionSizes());
    }

    @Test
    public void testGetTransportInfoTypes() {
        List<String> expected = Arrays.asList("Land", "Air", "Sea");
        List<TransportType> transportTypes = new ArrayList<TransportType>();
        TransportType transportTypeOne = new TransportType(1, "Land", 2);
        TransportType transportTypeTwo = new TransportType(2, "Air", 4);
        TransportType transportTypeThree = new TransportType(3, "Sea", 6);
        transportTypes.add(transportTypeOne);
        transportTypes.add(transportTypeTwo);
        transportTypes.add(transportTypeThree);

        when(packageService.getTransportTypes()).thenReturn(transportTypes);
        assertEquals(expected, packageInfoService.getTransportDescriptionTypes());
    }

    @Test
    public void testGetTransportInfoVelocities() {
        List<String> expected = Arrays.asList("Slow", "Regular", "Fast");
        List<TransportVelocity> transportVelocities = new ArrayList<TransportVelocity>();
        TransportVelocity transportVelocityOne = new TransportVelocity(1, "Slow", 5);
        TransportVelocity transportVelocityTwo = new TransportVelocity(2, "Regular", 10);
        TransportVelocity transportVelocityThree = new TransportVelocity(3, "Fast", 15);
        transportVelocities.add(transportVelocityOne);
        transportVelocities.add(transportVelocityTwo);
        transportVelocities.add(transportVelocityThree);

        when(packageService.getTransportVelocities()).thenReturn(transportVelocities);
        assertEquals(expected, packageInfoService.getTransportDescriptionVelocities());
    }

}
