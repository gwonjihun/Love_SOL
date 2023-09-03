package com.ssafy.lovesol.domain.datelog.service;

import com.ssafy.lovesol.domain.datelog.entity.DateLog;
import com.ssafy.lovesol.domain.datelog.entity.Image;

import java.time.LocalDateTime;
import java.util.Optional;

public interface DateLogService {
    Long createDateLog(Long coupleId, LocalDateTime dateAt);

    DateLog getDateLog(Long dateLogId);

    void insertImage(Long dateLogId, Image image);
}
