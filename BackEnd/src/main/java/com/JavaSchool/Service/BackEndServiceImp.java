package com.JavaSchool.Service;

import com.JavaSchool.EntityMapper.PackageType;
import org.springframework.stereotype.Service;
import com.JavaSchool.Queue.QueueClient;
import java.util.List;

@Service
public class BackEndServiceImp implements BackEndService {

    private QueueClient queueClient;

    public BackEndServiceImp(final QueueClient queueClient) {
        this.queueClient = queueClient;
    }

    @Override
    public List<PackageType> getType() {
        return queueClient.getType();
    }

}
