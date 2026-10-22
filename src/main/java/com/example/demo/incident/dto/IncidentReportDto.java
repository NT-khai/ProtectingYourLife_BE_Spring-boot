package com.example.demo.incident.dto;

import java.math.BigDecimal;

public class IncidentReportDto {

    private Long userId;
    private String loaiSuCo = "Hỏa hoạn / Khói lạ";
    private String mucDo = "Cap bach";
    private String tieuDe;
    private BigDecimal latitude = new BigDecimal("10.7769");
    private BigDecimal longitude = new BigDecimal("106.7009");
    private String diaChi = "Khu vực TP. Hồ Chí Minh";
    private String moTaChiTiet;
    private String imageUrl;
    private String trangThai;

    public IncidentReportDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoaiSuCo() {
        return (loaiSuCo == null || loaiSuCo.isBlank()) ? "Hỏa hoạn / Khói lạ" : loaiSuCo;
    }

    public void setLoaiSuCo(String loaiSuCo) {
        this.loaiSuCo = loaiSuCo;
    }

    public String getMucDo() {
        return (mucDo == null || mucDo.isBlank()) ? "Cap bach" : mucDo;
    }

    public void setMucDo(String mucDo) {
        this.mucDo = mucDo;
    }

    public String getTieuDe() {
        return (tieuDe == null || tieuDe.isBlank()) ? "Báo cáo sự cố an toàn" : tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public BigDecimal getLatitude() {
        return latitude != null ? latitude : new BigDecimal("10.7769");
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude != null ? longitude : new BigDecimal("106.7009");
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getDiaChi() {
        return (diaChi == null || diaChi.isBlank()) ? "Khu vực TP. Hồ Chí Minh" : diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMoTaChiTiet() {
        return moTaChiTiet;
    }

    public void setMoTaChiTiet(String moTaChiTiet) {
        this.moTaChiTiet = moTaChiTiet;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
