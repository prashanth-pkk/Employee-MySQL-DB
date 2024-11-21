package com.mysql.emp.dto;

import java.util.List;

public class BatchSalaryUpdateRequest {
    private List<Long> ids;
    private Double increment;

    public BatchSalaryUpdateRequest(List<Long> ids, Double increment) {
        this.ids = ids;
        this.increment = increment;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Double getIncrement() {
        return increment;
    }

    public void setIncrement(Double increment) {
        this.increment = increment;
    }
}
