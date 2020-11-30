package com.mentor.Datatable;

import java.util.Date;

public class EOI_App_Exporder_SpiritDT {
	
	
	
	//===========brand details dt =====================================================================		
	
		private int srno_brand ;
		private int spirit_id ;
		private String spirit_type ;
		private double qty_bl ;
		private double qty_al ;
		private double strength ;
		private double requested_al ;
		private double requested_bl ;
		private double requestedal ;
		private double requestedbl ;
		private double strength1 ;
		private String import_order_no1 ;
		
	//===================================== brand details dt = getter and setter ==============================	
		public int getSrno_brand() {
			return srno_brand;
		}
		
		 
		public String getImport_order_no1() {
			return import_order_no1;
		}


		public void setImport_order_no1(String import_order_no1) {
			this.import_order_no1 = import_order_no1;
		}


		public void setSrno_brand(int srno_brand) {
			this.srno_brand = srno_brand;
		}
		  
		
		public int getSpirit_id() {
			return spirit_id;
		}


		public void setSpirit_id(int spirit_id) {
			this.spirit_id = spirit_id;
		}


		public String getSpirit_type() {
			return spirit_type;
		}


		public void setSpirit_type(String spirit_type) {
			this.spirit_type = spirit_type;
		}


		public double getQty_bl() {
			return qty_bl;
		}


		public void setQty_bl(double qty_bl) {
			this.qty_bl = qty_bl;
		}


		public double getQty_al() {
			return qty_al;
		}


		public void setQty_al(double qty_al) {
			this.qty_al = qty_al;
		}


		public double getStrength() {
			return strength;
		}


		public void setStrength(double strength) {
			this.strength = strength;
		}


		public double getRequested_al() {
			
		if(this.requested_bl > 0 && this.strength > 0 ){
			this.requested_al  =(this.requested_bl * this.strength)/100 ;

			}else{
				this.requested_al = 0 ;
			}
			
			return requested_al;
		}


		public void setRequested_al(double requested_al) {
			this.requested_al = requested_al;
		}


		public double getRequested_bl() {
			return requested_bl;
		}


		public void setRequested_bl(double requested_bl) {
			this.requested_bl = requested_bl;
		}
 
		public double getRequestedal() {
			return requestedal;
		}


		public void setRequestedal(double requestedal) {
			this.requestedal = requestedal;
		}


		public double getRequestedbl() {
			return requestedbl;
		}


		public void setRequestedbl(double requestedbl) {
			this.requestedbl = requestedbl;
		}








		public double getStrength1() {
			return strength1;
		}


		public void setStrength1(double strength1) {
			this.strength1 = strength1;
		}








		//==================================  export order dt ================================================
		private int srno_export ;
		private int application_no ;
		private String application_date ;
		private long no_of_bottles ;
		private long ceo_no ;
		private long brc_no ;
		private Date reciept_at_icd ;

		public int getSrno_export() {
			return srno_export;
		}
		public void setSrno_export(int srno_export) {
			this.srno_export = srno_export;
		}
		public int getApplication_no() {
			return application_no;
		}
		public void setApplication_no(int application_no) {
			this.application_no = application_no;
		}
		public String getApplication_date() {
			return application_date;
		}
		public void setApplication_date(String application_date) {
			this.application_date = application_date;
		}
		public long getNo_of_bottles() {
			return no_of_bottles;
		}
		public void setNo_of_bottles(long no_of_bottles) {
			this.no_of_bottles = no_of_bottles;
		}
		public long getCeo_no() {
			return ceo_no;
		}
		public void setCeo_no(long ceo_no) {
			this.ceo_no = ceo_no;
		}
		public long getBrc_no() {
			return brc_no;
		}
		public void setBrc_no(long brc_no) {
			this.brc_no = brc_no;
		}
		public Date getReciept_at_icd() {
			return reciept_at_icd;
		}
		public void setReciept_at_icd(Date reciept_at_icd) {
			this.reciept_at_icd = reciept_at_icd;
		}
		
		private long deleted_bottles ;
		//===================================== export order dt = getter and setter ==============================	

		public long getDeleted_bottles() {
			return deleted_bottles;
		}
		public void setDeleted_bottles(long deleted_bottles) {
			this.deleted_bottles = deleted_bottles;
		}
		
		private String del_etin ;

		public String getDel_etin() {
			return del_etin;
		}
		public void setDel_etin(String del_etin) {
			this.del_etin = del_etin;
		}
		
		private boolean flg;

		public boolean isFlg() {
			return flg;
		}
		public void setFlg(boolean flg) {
			this.flg = flg;
		}
		
	//===========================
		
		private int srno ;
		
		private int application ;
		
		private String application_dt ;
		
		private long no_of_bottl ;
		
		private String import_no ;

		public int getSrno() {
			return srno;
		}
		public void setSrno(int srno) {
			this.srno = srno;
		}
		public int getApplication() {
			return application;
		}
		public void setApplication(int application) {
			this.application = application;
		}
		public String getApplication_dt() {
			return application_dt;
		}
		public void setApplication_dt(String application_dt) {
			this.application_dt = application_dt;
		}
		public long getNo_of_bottl() {
			return no_of_bottl;
		}
		public void setNo_of_bottl(long no_of_bottl) {
			this.no_of_bottl = no_of_bottl;
		}
		public String getImport_no() {
			return import_no;
		}
		public void setImport_no(String import_no) {
			this.import_no = import_no;
		}
		private String import_order_no ;

		public String getImport_order_no() {
			return import_order_no;
		}
		public void setImport_order_no(String import_order_no) {
			this.import_order_no = import_order_no;
		}
		
		
		//========================================= box and bottles ====================================

		private int requested_box;
		
		private double bottles_in_box ;
		
		private int no_of_box ;
		
		


		public double getBottles_in_box() {
			return bottles_in_box;
		}
		public void setBottles_in_box(double bottles_in_box) {
			this.bottles_in_box = bottles_in_box;
		}
		public int getNo_of_box() {
			return no_of_box;
		}
		public void setNo_of_box(int no_of_box) {
			this.no_of_box = no_of_box;
		}
		public int getRequested_box() {
			
			/*if(this.requested_qty > 0 && this.bottles_in_box > 0 ){
			this.requested_box =  (int) Math.ceil(this.requested_qty / this.bottles_in_box);
			}else{
				this.requested_box = 0 ;
			}*/
			
			return requested_box;
		}
		public void setRequested_box(int requested_box) {
			this.requested_box = requested_box;
		}
	
	
	

}
