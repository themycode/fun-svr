package com.okay.family.common

import com.okay.family.constants.bean.RequestSaveBean
import com.okay.family.mapper.SaveMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class Task {

    static Logger logger = LoggerFactory.getLogger(Task.class)

    @Autowired
    SaveMapper saveMapper

    @Scheduled(cron = "0 * * * * ?")
    def saveRequestBean() {
        while (true) {
            if (RequestSave.getWorkNum() == 0) break
            RequestSaveBean work = RequestSave.getWork()
            if (work == null) break
            saveMapper.saveRequest(work)
        }
    }
}