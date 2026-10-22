package com.example.demo.incident.dto;

public class IncidentStatsDto {
    private long total;
    private long choXuLy;
    private long dangXuLy;
    private long daXuLy;
    private long capBach;

    public IncidentStatsDto(long total, long choXuLy, long dangXuLy, long daXuLy, long capBach) {
        this.total = total;
        this.choXuLy = choXuLy;
        this.dangXuLy = dangXuLy;
        this.daXuLy = daXuLy;
        this.capBach = capBach;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getChoXuLy() {
        return choXuLy;
    }

    public void setChoXuLy(long choXuLy) {
        this.choXuLy = choXuLy;
    }

    public long getDangXuLy() {
        return dangXuLy;
    }

    public void setDangXuLy(long dangXuLy) {
        this.dangXuLy = dangXuLy;
    }

    public long getDaXuLy() {
        return daXuLy;
    }

    public void setDaXuLy(long daXuLy) {
        this.daXuLy = daXuLy;
    }

    public long getCapBach() {
        return capBach;
    }

    public void setCapBach(long capBach) {
        this.capBach = capBach;
    }
}
