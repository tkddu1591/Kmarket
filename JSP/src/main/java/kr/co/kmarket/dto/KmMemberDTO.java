package kr.co.kmarket.dto;

public class KmMemberDTO {
    private String uid;
    private String pass;
    private String name;
    private int gender;
    private String hp;
    private String email;
    private int type;
    private int point;
    private int level;
    private String zip;
    private String addr1;
    private String addr2;
    private String company;
    private String ceo;
    private String bizRegNum;
    private String comRegNum;
    private String tel;
    private String manager;
    private String managerHp;
    private String fax;
    private String regIp;
    private String wDate;
    private String rDate;
    private int etc1;
    private int etc2;
    private String etc3;
    private String etc4;
    private String etc5;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
    public void setGender(String gender) {
        this.gender = Integer.parseInt(gender);
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPoint() {
        return point;
    }
    public String getPointWithComma() {
        return String.format("%,d",point);
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getBizRegNum() {
        return bizRegNum;
    }

    public void setBizRegNum(String bizRegNum) {
        this.bizRegNum = bizRegNum;
    }

    public String getComRegNum() {
        return comRegNum;
    }

    public void setComRegNum(String comRegNum) {
        this.comRegNum = comRegNum;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManagerHp() {
        return managerHp;
    }

    public void setManagerHp(String managerHp) {
        this.managerHp = managerHp;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public String getwDate() {
        return wDate;
    }

    public void setwDate(String wDate) {
        this.wDate = wDate;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public int getEtc1() {
        return etc1;
    }

    public void setEtc1(int etc1) {
        this.etc1 = etc1;
    }

    public int getEtc2() {
        return etc2;
    }

    public void setEtc2(int etc2) {
        this.etc2 = etc2;
    }

    public String getEtc3() {
        return etc3;
    }

    public void setEtc3(String etc3) {
        this.etc3 = etc3;
    }

    public String getEtc4() {
        return etc4;
    }

    public void setEtc4(String etc4) {
        this.etc4 = etc4;
    }

    public String getEtc5() {
        return etc5;
    }

    public void setEtc5(String etc5) {
        this.etc5 = etc5;
    }

	@Override
	public String toString() {
		return "KmMemberDTO [uid=" + uid + ", pass=" + pass + ", name=" + name + ", gender=" + gender + ", hp=" + hp
				+ ", email=" + email + ", type=" + type + ", point=" + point + ", level=" + level + ", zip=" + zip
				+ ", addr1=" + addr1 + ", addr2=" + addr2 + ", company=" + company + ", ceo=" + ceo + ", bizRegNum="
				+ bizRegNum + ", comRegNum=" + comRegNum + ", tel=" + tel + ", manager=" + manager + ", managerHp="
				+ managerHp + ", fax=" + fax + ", regIp=" + regIp + ", wDate=" + wDate + ", rDate=" + rDate + ", etc1="
				+ etc1 + ", etc2=" + etc2 + ", etc3=" + etc3 + ", etc4=" + etc4 + ", etc5=" + etc5 + "]";
	}
    
    
    
}
