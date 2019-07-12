package cn.xz.bean;

/**
 * @author xz
 * @ClassName Partition
 * @Description
 * @date 2019/4/11 0011 14:51
 **/
public class Partition {
    private String province;
    private String levCode;
    private String subCode;
    private String yeaCode;
    private Integer grade;
    private Integer userNum;
    private Integer userTotal;
    private Integer gradePartition;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLevCode() {
        return levCode;
    }

    public void setLevCode(String levCode) {
        this.levCode = levCode;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getYeaCode() {
        return yeaCode;
    }

    public void setYeaCode(String yeaCode) {
        this.yeaCode = yeaCode;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

    public Integer getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(Integer userTotal) {
        this.userTotal = userTotal;
    }

    public Integer getGradePartition() {
        return gradePartition;
    }

    public void setGradePartition(Integer gradePartition) {
        this.gradePartition = gradePartition;
    }
}
