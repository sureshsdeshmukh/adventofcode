package com.ssd.advent.model;

public class Passport {
    private Integer byr;
    private Integer iyr;
    private Integer eyr;
    private String hgt;
    private String hcl;
    private String ecl;
    private String pid;
    private Integer cid;

    public Integer getByr() {
        return byr;
    }

    public void setByr(Integer byr) {
        this.byr = byr;
    }

    public Integer getIyr() {
        return iyr;
    }

    public void setIyr(Integer iyr) {
        this.iyr = iyr;
    }

    public Integer getEyr() {
        return eyr;
    }

    public void setEyr(Integer eyr) {
        this.eyr = eyr;
    }

    public String getHgt() {
        return hgt;
    }

    public void setHgt(String hgt) {
        this.hgt = hgt;
    }

    public String getHcl() {
        return hcl;
    }

    public void setHcl(String hcl) {
        this.hcl = hcl;
    }

    public String getEcl() {
        return ecl;
    }

    public void setEcl(String ecl) {
        this.ecl = ecl;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "byr=" + byr +
                ", iyr=" + iyr +
                ", eyr=" + eyr +
                ", hgt='" + hgt + '\'' +
                ", hcl='" + hcl + '\'' +
                ", ecl='" + ecl + '\'' +
                ", pid='" + pid + '\'' +
                ", cid=" + cid +
                '}';
    }
}
