package com.atlaspharmacy.atlaspharmacy.reports.DTO;

import java.util.Date;

public class PeriodDTO {
    private Date startPeriod;
    private Date endPeriod;

    public PeriodDTO(){ }

    public PeriodDTO(Date startPeriod, Date endPeriod) {
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }

    public Date getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Date startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Date getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Date endPeriod) {
        this.endPeriod = endPeriod;
    }
}
