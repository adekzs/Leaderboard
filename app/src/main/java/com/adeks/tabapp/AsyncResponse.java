package com.adeks.tabapp;

import com.adeks.tabapp.model.StudentHrs;
import com.adeks.tabapp.model.StudentIq;

import java.util.List;

public interface AsyncResponse {
    void processFinished(List<StudentHrs> objectList);

    void processFinished(List<StudentIq> objectList, boolean isTrue);
}
