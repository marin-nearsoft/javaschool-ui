package com.javaschool.temp;

import com.javaschool.modelmapper.*;

import java.util.List;

public interface PriceAndInfoService {

    List<Information> getInformation();

    Information postInformation(double price, String path);
}
