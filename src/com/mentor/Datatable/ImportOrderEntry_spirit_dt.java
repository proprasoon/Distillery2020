package com.mentor.Datatable;

import java.util.Date;

public class ImportOrderEntry_spirit_dt {
	


	private boolean select;
	public boolean isSelect() {
		return select;
	}
	public void setSelect(boolean select) {
		this.select = select;
	}
	private String unit_add ;
	public String getUnit_add() {
		return unit_add;
	}
	public void setUnit_add(String unit_add) {
		this.unit_add = unit_add;
	}
	private boolean flg;

	public boolean isFlg() {
		return flg;
	}
	public void setFlg(boolean flg) {
		this.flg = flg;
	}
	//--------------------Submit Data
	private int  sr_n1;
	private int app_no ;
	public int getApp_no() {
		return app_no;
	}
	public void setApp_no(int app_no) {
		this.app_no = app_no;
	}


	private String  countryNm;
	private String  unitNM;
	private String  purchaseNo;
	private Date  crDate;
	private String  orderCopy;
	private Date  validDate;
	private String spirit_name_dt;
	private double qantity_bl_dt;
	private double strength_dt;
	private double quantity_al_dt;
	

	
	public String getSpirit_name_dt() {
		return spirit_name_dt;
	}
	public void setSpirit_name_dt(String spirit_name_dt) {
		this.spirit_name_dt = spirit_name_dt;
	}
	public double getQantity_bl_dt() {
		return qantity_bl_dt;
	}
	public void setQantity_bl_dt(double qantity_bl_dt) {
		this.qantity_bl_dt = qantity_bl_dt;
	}
	public double getStrength_dt() {
		return strength_dt;
	}
	public void setStrength_dt(double strength_dt) {
		this.strength_dt = strength_dt;
	}
	public double getQuantity_al_dt() {
		return quantity_al_dt;
	}
	public void setQuantity_al_dt(double quantity_al_dt) {
		this.quantity_al_dt = quantity_al_dt;
	}
	public int getSr_n1() {
		return sr_n1;
	}
	public void setSr_n1(int sr_n1) {
		this.sr_n1 = sr_n1;
	}
	public String getCountryNm() {
		return countryNm;
	}
	public void setCountryNm(String countryNm) {
		this.countryNm = countryNm;
	}
	public String getUnitNM() {
		return unitNM;
	}
	public void setUnitNM(String unitNM) {
		this.unitNM = unitNM;
	}
	public String getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public Date getCrDate() {
		return crDate;
	}
	public void setCrDate(Date crDate) {
		this.crDate = crDate;
	}
	public String getOrderCopy() {
		return orderCopy;
	}
	public void setOrderCopy(String orderCopy) {
		this.orderCopy = orderCopy;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}





}
