package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

 
import com.mentor.Datatable.EOI_App_Exporder_SpiritDT;
import com.mentor.action.EOI_App_Exporder_SpiritAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class EOI_App_Exporder_SpiritImpl {
	
	
	
	

	//=-==================================    login  ================================================
	
	public void getDetails(EOI_App_Exporder_SpiritAction action){


		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionToDataBase.getConnection();

			
           String selQr =   
		 
		  "  SELECT int_app_id_f , vch_undertaking_name   FROM " +
		  "  public.dis_mst_pd1_pd2_lic     where vch_wrk_phon = '"+ResourceUtil.getUserNameAllReq()+"' " ;
	 
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();
			
			 
			while (rs.next()) {
				
				action.setDistillery_id(rs.getInt("int_app_id_f"));
				//action.setName_of_importing_unit(rs.getString("importing_unit_nm"));
				//action.setImport_order_date(Utility.convertSqlDateToUtilDate( rs.getDate("po_date")));
			}
     
			 //System.out.println("=======getDistillery_id=============="+action.getDistillery_id());
			
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				
				if (rs != null)
					rs.close();
				
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	
	
//======================================================icd list  ==============================================	

	public void imorting_name_order_date(EOI_App_Exporder_SpiritAction action){


		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionToDataBase.getConnection();

			
           String selQr =   
		 
		  "  SELECT  importing_unit_nm , po_date FROM " +
		  "   distillery.eoi_import_order_master_spirit   where seq_pk = "+action.getImport_order_no()+" " ;
	 
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();
			
			 
			while (rs.next()) {
				
				 
				action.setName_of_importing_unit(rs.getString("importing_unit_nm"));
				action.setImport_order_date(Utility.convertSqlDateToUtilDate( rs.getDate("po_date")));
			}
     
			//////System.out.println();
			
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				
				if (rs != null)
					rs.close();
				
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	
	public ArrayList getIcd_list(EOI_App_Exporder_SpiritAction action) {
	
	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	SelectItem item = new SelectItem();
	item.setLabel("--select--");
	item.setValue("");
	list.add(item);
	try {

		String query = "SELECT id,name  FROM licence.icd_master  order by name ";
		
		////System.out.println(" --getIcd_list--- "+query);

		conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			item = new SelectItem();

			item.setValue(rs.getString("id"));
			item.setLabel(rs.getString("name"));

			list.add(item);

		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {

			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return list;

}


	
//================================= show brand detail ===========================================

public ArrayList brand_display_detail(EOI_App_Exporder_SpiritAction action ) {
	
	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
     int sr = 1 ; ;

	try {

		String query = " select (quantity_al-coalesce(requested_al,'0')) as qty_al ,(quantity_bl -coalesce(requested_bl,'0')) as qty_bl,b.vch_spirit_name,spirit_id ,strength ,quantity_al ,quantity_bl ,requested_al ,requested_bl   " +
				       " from distillery.eoi_import_order_master_spirit a,  distillery.mst_spirit_type  b    " +
				       "  where a.spirit_id=b.int_spirit_type_id and    " +
				       "   a.int_dist_id='"+action.getDistillery_id()+"' and a.seq_pk='"+action.getImport_order_no()+"' ";
				
			
		 conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			
			EOI_App_Exporder_SpiritDT  dt = new EOI_App_Exporder_SpiritDT();
			dt.setSrno_brand(sr);
			dt.setSpirit_type(rs.getString("vch_spirit_name"));
			dt.setSpirit_id(rs.getInt("spirit_id"));
			dt.setQty_bl(rs.getDouble("qty_bl"));
			dt.setStrength(rs.getDouble("strength"));
			dt.setQty_al(rs.getDouble("qty_al"));
			//dt.setRequested_al(rs.getDouble("requested_al"));
			//dt.setRequested_bl(rs.getDouble("requested_bl"));
			 
			
				
			sr++ ;
									
			list.add(dt);

		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {

			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return list;

}


//================================= export order detail  ===========================================

public ArrayList export_order_display_detail(EOI_App_Exporder_SpiritAction action ) {
	
	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
   int sr = 1 ; ;

	try {

		String query =
		
				"	SELECT a.app_id,a.user1_name, a.recv_date, a.brc_number, a.leo_number,a.created_date , " +
				"    sum(b.reqstd_bottles) as reqstd_bottles                                   " +
				"	FROM distillery.eoi_app_for_export_order a , distillery.eoi_app_for_export_order_brand b  " + 
				"    where a.int_dist_id = '"+action.getDistillery_id()+"'  and       " +
			    "    a.order_id = '"+action.getImport_order_no()+"' and a.app_id= b.app_id_fk group by          " +
				"    a.app_id, a.recv_date, a.brc_number, a.leo_number,a.created_date,a.user1_name order by a.app_id desc         " ;

		
				
				
		////System.out.println("--export order===="+query);
		conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();
		
		SimpleDateFormat formatter= new SimpleDateFormat("dd MMMM yyyy");
	
		
		

		while (rs.next()) {
			
			String reg_date =formatter.format(rs.getDate("created_date"));
			
			
			EOI_App_Exporder_SpiritDT  dt = new EOI_App_Exporder_SpiritDT();
			
			dt.setSrno_export(sr);
			dt.setApplication_no(rs.getInt("app_id"));
			dt.setApplication_date(reg_date) ;
			dt.setNo_of_bottles(rs.getLong("reqstd_bottles")) ;
			dt.setCeo_no(rs.getInt("leo_number"));
			dt.setBrc_no(rs.getInt("brc_number"));
			dt.setReciept_at_icd(rs.getDate("recv_date"));
			
			sr++;
									
			list.add(dt);

		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {

			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return list;

}


//==================================max id  eoi_app_for_export_order ====================================================


public int seq_pk_eoi_app_export() {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String query = " SELECT max(app_id) as id FROM distillery.eoi_app_for_export_order_spirit";
	int maxid = 0;
	try {
		con = ConnectionToDataBase.getConnection();
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			maxid = rs.getInt("id");
		}
	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e
						.getMessage(), e.getMessage()));

		e.printStackTrace();
	} finally {
		try {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	return maxid + 1;

}




//================================= import select item  list===========================================

public ArrayList import_list(EOI_App_Exporder_SpiritAction action ) {
	
	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	SelectItem item = new SelectItem();
	item.setLabel("--select--");
	item.setValue("");
	list.add(item);
	try {

		String query = "SELECT po_number , seq_pk  FROM " +
				" distillery.eoi_import_order_master_spirit where int_dist_id = '"+action.getDistillery_id()+"' order by seq_pk ";

		
		 //System.out.println("==========import_list============="+query);
		conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			item = new SelectItem();

			item.setValue(rs.getString("seq_pk"));
			item.setLabel(rs.getString("po_number"));

			list.add(item);

		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {

			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return list;

}

//========================================== save details  ==========================================

public boolean savedetails(EOI_App_Exporder_SpiritAction act){

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 1;
	String query = ""; 
    int maxid = this.seq_pk_eoi_app_export();
	
	boolean flg=false;
	 try {

		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		 
		for(int i=0;i<act.getBrand_detaiil_list().size();i++){
			
			EOI_App_Exporder_SpiritDT dt = (EOI_App_Exporder_SpiritDT) act.getBrand_detaiil_list().get(i);
			
		query   =    "  INSERT INTO distillery.eoi_app_for_export_order_spirit( "+
                     "  app_id,  pi_date, pi_number, pi_pdf, created_date, int_dist_id, order_id,icd_id,puc_certificate,puc_no,qty_al ,qty_bl ,strength,requested_al,requested_bl ) "+
	                "  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);  ";
		
					
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1,maxid) ;
				pstmt.setDate(2, Utility.convertUtilDateToSQLDate(act.getValidity_demanded()));
				pstmt.setString(3, act.getPerforma_invoice_no());
				pstmt.setString(4, act.getUpload_performa_invoice());
				pstmt.setDate(5, Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setInt(6, act.getDistillery_id());
				pstmt.setInt(7, Integer.parseInt(act.getImport_order_no()));
				//pstmt.setString(8, "Excise-DL-"+act.getDistillery_id());	
				pstmt.setInt(8, Integer.parseInt(act.getIcd_id()));
				pstmt.setString(9, act.getUpload_puc_certificate());
				pstmt.setString(10, act.getPuc_no());
				pstmt.setDouble(11,  dt.getQty_al());
				pstmt.setDouble(12,  dt.getQty_bl());
				pstmt.setDouble(13,  dt.getStrength());
				pstmt.setDouble(14,  dt.getRequested_al());
				pstmt.setDouble(15,  dt.getRequested_bl());
				saveStatus = pstmt.executeUpdate();
				
				 //System.out.println("====save status= pi details=1"+saveStatus);
		}
			
			
		
		if (saveStatus > 0) {
				
			
			for(int i=0;i<act.getBrand_detaiil_list().size();i++){
				
				EOI_App_Exporder_SpiritDT dt = (EOI_App_Exporder_SpiritDT) act.getBrand_detaiil_list().get(i);
			 
				 query =
							    " UPDATE distillery.eoi_import_order_master_spirit  " +
								" SET  requested_al=coalesce(requested_al,0) + ? ,requested_bl=coalesce(requested_bl,0) + ? " +
								" where int_dist_id = ? and seq_pk=? "  ;
				
		 
					
					pstmt = conn.prepareStatement(query);
					//System.out.println("===getRequested_al===="+dt.getRequested_al()+"===getRequested_bl=="+dt.getRequested_bl()+"====getDistillery_id==="+act.getDistillery_id()+"----getImport_order_no==="+Integer.parseInt(act.getImport_order_no()));
					 
					pstmt.setDouble(1, dt.getRequested_al() );
					pstmt.setDouble(2, dt.getRequested_bl());
					pstmt.setInt(3,act.getDistillery_id());
					pstmt.setInt(4, Integer.parseInt(act.getImport_order_no()));
 								
					saveStatus = pstmt.executeUpdate();
					//System.out.println("===saveStatus===="+saveStatus);
			}
					
				}
 
 
		/*if (saveStatus > 0) {
					
					
					if(act.getBrand_detaiil_list().size()>0){
						
				 query =	" INSERT INTO distillery.eoi_app_for_export_order_brand( "+
							" app_id_fk, etin, distillery_id, reqstd_bottles, req_box)         "+
							" VALUES (?, ?, ?, ?, ?)  " ;
						        
						for(int i=0;i<act.getBrand_detaiil_list().size();i++){
									
							EOI_App_Exporder_SpiritDT dt = (EOI_App_Exporder_SpiritDT) act.getBrand_detaiil_list().get(i);
												
							pstmt = conn.prepareStatement(query);
							
							pstmt.setLong(1, maxid);
							pstmt.setString(2, dt.getEtin());
							pstmt.setInt(3, act.getDistillery_id());
							pstmt.setLong(4,(long)dt.getRequested_qty());
							pstmt.setInt(5, dt.getRequested_box());
										
							saveStatus = pstmt.executeUpdate();
							
							////System.out.println("save status==2="+saveStatus);	
							
					
						}
				}
				}*/
		
		if (saveStatus > 0) {
			conn.setAutoCommit(true);
			flg=true;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("SAVED SUCCESSFULLY !", "SAVED SUCCESSFULLY !"));
			act.reset();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("NOT SAVED !", "NOT SAVED !"));
			conn.rollback();

		}
	 }


	catch (SQLException e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(e.getMessage(), e.getMessage()));
		e.printStackTrace();
	}catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(e.getMessage(), e.getMessage()));
		e.printStackTrace();
	}

	finally {
		try {

			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
return flg;

}

//==================Delete Row

public ArrayList deleterow(EOI_App_Exporder_SpiritAction action,EOI_App_Exporder_SpiritDT dt )
{
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	ResultSet rs=null;
	ArrayList list=new ArrayList();
	int deleteStatus = 0;
	String delete1 ="";
	String delete ="";
	String update ="";

	try
	{
		conn = ConnectionToDataBase.getConnection();

		conn.setAutoCommit(false);


		delete1 =    " DELETE FROM distillery.eoi_app_for_export_order_spirit "+
				" WHERE app_id= ? ";


		pstmt1=conn.prepareStatement(delete1);

		pstmt1.setInt(1,dt.getApplication());

 //System.out.println("====Application-NO---"+dt.getApplication());

		 //System.out.println("----=============----delete1---==eoi_app_for_export_order===========----"+delete1);

		deleteStatus=pstmt1.executeUpdate();	

		  //System.out.println("==1st delete===="+deleteStatus); 


		if(deleteStatus>0)
		{

			for(int i=0;i<action.getDetail_list().size();i++){
				
				EOI_App_Exporder_SpiritDT dt1 = (EOI_App_Exporder_SpiritDT) action.getDetail_list().get(i);
				
			 


			update =    " UPDATE distillery.eoi_import_order_master_spirit  " +
					" SET  requested_al=coalesce(requested_al,0) - ? ,requested_bl=coalesce(requested_bl,0) - ? " +
					" where int_dist_id = ? and seq_pk=? "  ;




			pstmt1=conn.prepareStatement(update);

		/*	for(int i=0; i<action.detail_lis.size(); i++)
			{ 

				EOI_App_Exporder_SpiritDT dt1 = (EOI_App_Exporder_SpiritDT) action.detail_list.get(i);*/

				//System.out.println("===deleted bottles=="+dt1.getRequestedal()+"====getQty_bl======"+dt1.getRequestedbl()+"====dddd==="+action.getDistillery_id()+"===iiiii===="+Integer.parseInt(dt1.getImport_order_no1()));

				pstmt1.setDouble(1, dt1.getRequestedal());
				pstmt1.setDouble(2, dt1.getRequestedbl());
				pstmt1.setInt(3, action.getDistillery_id());
				pstmt1.setInt(4, Integer.parseInt(dt1.getImport_order_no1()));

				//System.out.println("----=============----Update row----===eoi_app_for_export_order_brand==========----"+update);

				deleteStatus=pstmt1.executeUpdate();

				//System.out.println("==3rd update===="+deleteStatus); 
			}
		}	

		if(deleteStatus>0)
		{
			conn.commit();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Application Deleted Successfully", "Application Deleted Successfully"));
		}
		else
		{
			conn.rollback();
		}	
	}
	catch(Exception e)
	{
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage() ,e.getMessage()));
		e.printStackTrace();
	}
	finally
	{
		try
		{	
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (pstmt1 != null)
				pstmt1.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	return list;	

}

  public ArrayList brand_display_detail(EOI_App_Exporder_SpiritAction action,int app_id) {
	
	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
     int sr = 1 ; ;

	try {

		String query = 
				
			   " SELECT distinct c.seq_pk,br.brand_name, pckg.package_name, b.etin, a.app_id, a.recv_date, a.brc_number, a.leo_number,a.created_date , b.reqstd_bottles"+
               " FROM distillery.eoi_app_for_export_order a , distillery.eoi_app_for_export_order_brand b ,  "+
               " distillery.brand_registration_20_21 br , distillery.packaging_details_20_21 pckg ,          "+
               " distillery.eoi_import_order_master_brand mst , distillery.eoi_import_order_master c                                                                                                  "+
               " where a.int_dist_id = '"+action.getDistillery_id()+"' and mst.package_id = pckg.package_id and  mst.brand_id = br.brand_id and c.seq_pk =a.order_id and"+
               " a.app_id = '"+app_id+"' and a.app_id= b.app_id_fk and mst.etin = b.etin ";
				
			
		////System.out.println("-- brand details ===="+query);
		conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();

		while (rs.next()) 
		{
			
			EOI_App_Exporder_SpiritDT  dt = new EOI_App_Exporder_SpiritDT();
			
			dt.setDeleted_bottles(rs.getInt("reqstd_bottles"));
			dt.setDel_etin(rs.getString("etin")) ;
			dt.setImport_order_no(rs.getString("seq_pk"));
			 
			
			sr++ ;
			list.add(dt);

		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {

			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return list;

}

  public ArrayList finalize(EOI_App_Exporder_SpiritAction action,EOI_App_Exporder_SpiritDT dt )
  {
  	Connection conn = null;
  	PreparedStatement pstmt = null;
  	PreparedStatement pstmt1 = null;
  	ResultSet rs=null;
  	ArrayList list=new ArrayList();
  	int deleteStatus = 0;
    String update ="";
  	
  	try
  	{
  		    conn = ConnectionToDataBase.getConnection();
  		    
  		    conn.setAutoCommit(false);
  		    
  		  
  		  update =      " UPDATE distillery.eoi_app_for_export_order_spirit set user1_name ='Excise-DL-"+action.getDistillery_id() +"' "+
              		    " WHERE app_id= ? ";
  	       
  	       
  	        pstmt1=conn.prepareStatement(update);
  			
  			pstmt1.setInt(1,dt.getApplication());
  			
  			deleteStatus=pstmt1.executeUpdate();	
  		
  			 if(deleteStatus>0)
  	     {
  	   conn.commit();
  	   FacesContext.getCurrentInstance().addMessage(null,
  	   new FacesMessage("Application Finalized Successfully", "Application Finalized Successfully"));
  	  }
  	else
  	{
  		conn.rollback();
  	}	
  	}
  	catch(Exception e)
  	{
  		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage() ,e.getMessage()));
  		e.printStackTrace();
  	}
  	finally
  	{
    		try
    		{	
    			if (conn != null)
  				conn.close();
  			if (pstmt != null)
  				pstmt.close();
  			if (pstmt1 != null)
  				pstmt1.close();
        		
        	}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
  return list;	

  }
  
 //=================================================================
  
  public ArrayList app_detail(EOI_App_Exporder_SpiritAction action ) {
		
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	   int sr = 1 ; ;

		try {

		 String query = "  select requested_al,requested_bl,order_id,strength,app_id,user1_name,created_date ,qty_al ,qty_bl  from distillery.eoi_app_for_export_order_spirit  " +
		 		"  where int_dist_id ='"+action.getDistillery_id()+"' " ;

			
					
					
		 //System.out.println("--export order===="+query);
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			
			SimpleDateFormat formatter= new SimpleDateFormat("dd MMMM yyyy");
		
			
			

			while (rs.next())
			
			{
				
				String reg_date =formatter.format(rs.getDate("created_date"));
				
				
				EOI_App_Exporder_SpiritDT  dt = new EOI_App_Exporder_SpiritDT();
				
				dt.setSrno(sr);
				dt.setApplication(rs.getInt("app_id"));
				dt.setApplication_dt(reg_date) ;
				dt.setRequestedal(rs.getDouble("requested_al"));
				dt.setRequestedbl(rs.getDouble("requested_bl"));
				dt.setStrength1(rs.getDouble("strength"));
				dt.setImport_order_no1(rs.getString("order_id"));
				
				if(rs.getString("user1_name")==null || rs.getString("user1_name").length()==0)
				{
				
				dt.setFlg(true);	
					
				}
				else
				{
				dt.setFlg(false);	
				}	
					
				sr++;
										
				list.add(dt);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;

	}
	
	
	
	
	
	
	
	
	

}
