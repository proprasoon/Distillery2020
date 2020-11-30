package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import com.mentor.Datatable.ImportOrderEntry_spirit_dt;
import com.mentor.Datatable.eoi_app_exporder_dt;
import com.mentor.action.ImportOrderEntry_spirit_action;
import com.mentor.action.eoi_app_exporder_action;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class ImportOrderEntry_spirit_impl {
	
	

	
	public String getDistDetails(ImportOrderEntry_spirit_action act) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();
			String queryList = " SELECT int_app_id_f, vch_undertaking_name, vch_wrk_add  "
					+ " FROM  dis_mst_pd1_pd2_lic WHERE vch_wrk_phon='"
					+ ResourceUtil.getUserNameAllReq().trim() + "'  ";
        
		//System.out.println("==getDistDetails====" + queryList);
			
			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				act.setDistId(rs.getInt("int_app_id_f"));
				act.setDistNm(rs.getString("vch_undertaking_name"));
				act.setDistAdd(rs.getString("vch_wrk_add"));

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (ps2 != null)
					ps2.close();
				if (rs != null)
					rs.close();
				if (rs2 != null)
					rs2.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return "";

	}


	public ArrayList getCountryList() {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("NA");
		list.add(item);
		try {

			String query = "SELECT int_country_id, vch_country_name FROM public.country_mst order by vch_country_name";
			
			
			//System.out.println("==getCountryList====" + query);

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				item = new SelectItem();

				item.setValue(rs.getString("int_country_id"));
				item.setLabel(rs.getString("vch_country_name"));

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
	
	
	// ============================= save Data=======================

			    public String saveData(ImportOrderEntry_spirit_action act) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				int saveStatus1 = 0;
                act.setSeqId(getMaxId());
        		
				try {

					String query =" INSERT INTO distillery.eoi_import_order_master_spirit(  "+
							      " seq_pk, int_dist_id, country_id, importing_unit_nm, created_date , po_number, po_pdf,po_date , spirit_id, quantity_bl, strength, quantity_al,importing_unit_address)  "+
							      " VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)  ";
					
					
					conn = ConnectionToDataBase.getConnection();
					
					pstmt = conn.prepareStatement(query);
					
					
	                 
					pstmt.setInt(1, act.getSeqId());
					pstmt.setInt(2, act.getDistId());
					pstmt.setInt(3, Integer.parseInt(act.getCountry_id()));
					pstmt.setString(4, act.getUnit_nm());
					pstmt.setDate(5, Utility.convertUtilDateToSQLDate(act.getValidtyDt()));
					pstmt.setInt(6, act.getPurchase_ordrno());
					pstmt.setString(7, act.getRecordFile());
					pstmt.setDate(8, Utility.convertUtilDateToSQLDate(act.getDate()));
					
					pstmt.setInt(9, Integer.parseInt(act.getSprit_type()));
					pstmt.setDouble(10, act.getQuantity_bl());
					pstmt.setDouble(11, act.getStrenght());
					pstmt.setDouble(12, act.getQuantity_al());
					pstmt.setString(13, act.getUnit_add());
					
					saveStatus1 = pstmt.executeUpdate();
					
					if (saveStatus1 > 0) 
					{
                                FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"   Data Saved successfully!!! ",
										"  Data Saved successfully!!! "));
			
						act.reset();
					     
					
					} 
					else 
					{

						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"  Error message 'Data not saved'  All  Fields are Mandatory!!!. Error message 'Data not saved' All  Fields are Mandatory!!! ",
										"  Error message 'Data not saved'  All  Fields are Mandatory!!!. Error message 'Data not saved' All  Fields are Mandatory!!!  "));

					}
				} catch (Exception e) {
					FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									" RECORD NOT SAVED !!!!!!",
									"RECORD NOT SAVED !!!!!!"));
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
						
					}
				}
				return "";
			}
			
			//------------------------------checkkk
			

	        public boolean checkDate(ImportOrderEntry_spirit_action act) {

				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				boolean pass = false;
				String query = "";

				try {

					query ="SELECT  int_dist_id FROM distillery.eoi_import_order_master where int_dist_id='"+act.getDistId()+"'";
					
					//System.out.println("checkDate==="+query);
					conn = ConnectionToDataBase.getConnection();
					pstmt = conn.prepareStatement(query);

					rs = pstmt.executeQuery();
					if (rs.next()) {

						// act.setCheckReg(rs.getInt("registration_id"));
						act.setDistilleryId(rs.getInt("int_dist_id"));
						pass = true;

					}
					else {

						pass = false;
					}

				} catch (Exception e) {

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
				return pass;

			}

				
	
			//------------------------------sumbit Orede purchase
			//---------------------------------Brand List-------------------------------
			
			public ArrayList ShowsaveDate(ImportOrderEntry_spirit_action act) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ArrayList al = new ArrayList();
				int i = 1;
				conn = ConnectionToDataBase.getConnection();

				

				String sql = " SELECT distinct a.seq_pk, a.int_dist_id, a.country_id,b.vch_country_name, a.importing_unit_nm,a.importing_unit_address, a.po_date, a.po_number, a.po_pdf,c.vch_spirit_name ,         "+
						     " a.created_date, a.quantity_bl ,a.strength ,a.quantity_al,coalesce (a.requested_al,0) as requested_al ,coalesce (a.requested_bl ,0) as requested_bl                                                                                                     "+
						     " FROM distillery.eoi_import_order_master_spirit a , public.country_mst b , distillery.mst_spirit_type c                                                      "+
						     " where a.country_id=b.int_country_id and a.spirit_id = c.int_spirit_type_id and a.int_dist_id='"+act.getDistId()+"'                                                           "+
						     " group by a.seq_pk, a.int_dist_id, a.country_id,b.vch_country_name, a.importing_unit_nm, a.po_date, a.po_number,a.po_pdf, a.created_date ,c.vch_spirit_name  "+
						     " order by a.seq_pk desc  ";
		 
				// System.out.println("===vShowBrandList====" + sql);
				try {

					pstmt = conn.prepareStatement(sql);

					ResultSet rs = pstmt.executeQuery();

					// action.setFlag("T");
					while (rs.next()) {

						ImportOrderEntry_spirit_dt dt = new ImportOrderEntry_spirit_dt();

						dt.setCountryNm(rs.getString("vch_country_name"));
						dt.setUnitNM(rs.getString("importing_unit_nm"));
						dt.setPurchaseNo(rs.getString("po_number"));
						dt.setCrDate(rs.getDate("po_date"));
						dt.setOrderCopy(rs.getString("po_pdf"));
						dt.setValidDate(rs.getDate("created_date"));
						dt.setSpirit_name_dt(rs.getString("vch_spirit_name"));
						dt.setQantity_bl_dt(rs.getDouble("quantity_bl"));
						dt.setQuantity_al_dt(rs.getDouble("quantity_al"));
						dt.setStrength_dt(rs.getDouble("strength"));
						dt.setApp_no(rs.getInt("seq_pk"));
						dt.setUnit_add(rs.getString("importing_unit_address"));
						if(rs.getDouble("requested_al")>0 && rs.getDouble("requested_bl")>0)
						{
						   dt.setFlg(false);	
						}
						else 
						{
							dt.setFlg(true);	
						}
					
						  dt.setSr_n1(i);
						  al.add(dt);

						  i++;

					}

					if (conn != null)
						conn.close();
					if (pstmt != null)
						pstmt.close();

					if (rs != null)
						conn.close();

				} catch (Exception e) {
					e.printStackTrace();
				}

				return al;

			}
			
			
			
			// ==============get MAX Id====================

						public int getMaxId() {
							Connection conn = null;
							PreparedStatement pstmt = null;
							ResultSet rs = null;
							int maxid = 0;

							try {
								String query = "SELECT max(seq_pk) maxid from distillery.eoi_import_order_master_spirit ";

								conn = ConnectionToDataBase.getConnection();
								pstmt = conn.prepareStatement(query);

								rs = pstmt.executeQuery();
								if (rs.next()) {
									maxid = rs.getInt("maxid");
								}

							} catch (Exception e) {
								// e.printStackTrace();
							} finally {
								try {

									if (conn != null)
										conn.close();
									if (pstmt != null)
										pstmt.close();
									if (rs != null)
										rs.close();

								} catch (Exception e) {
									// e.printStackTrace();
								}
							}
							return maxid+1;
						}
						
						
						// ==============get MAX Id====================

						
						/*public int getMaxId1() {
							Connection conn = null;
							PreparedStatement pstmt = null;
							ResultSet rs = null;
							int maxid = 0;

							try {
								String query = "SELECT max(seq_pk) maxid from distillery.eoi_import_order_master_brand ";

								conn = ConnectionToDataBase.getConnection();
								pstmt = conn.prepareStatement(query);

								rs = pstmt.executeQuery();
								if (rs.next()) {
									maxid = rs.getInt("maxid");
								}

							} catch (Exception e) {
								// e.printStackTrace();
							} finally {
								try {

									if (conn != null)
										conn.close();
									if (pstmt != null)
										pstmt.close();
									if (rs != null)
										rs.close();

								} catch (Exception e) {
									// e.printStackTrace();
								}
							}
							return maxid + 1;
						}*/
						
						// ////////------------------------------------validtion-----------------------//////////
						 public boolean checkbrewery(ImportOrderEntry_spirit_action ac) {

								Connection conn = null;
								PreparedStatement pstmt = null;
								ResultSet rs = null;
								boolean pass = false;
								String query = "";

								try {

									query =" select approve_flag from distillery.reg_of_distilleryasexpunit where distillery_id="+ac.getDistId()+" and type='SPIRIT'   ";
									
									//System.out.println("checkDate==="+query);
									conn = ConnectionToDataBase.getConnection();
									pstmt = conn.prepareStatement(query);

									rs = pstmt.executeQuery();
									if (rs.next()) {
										pass = true;

									}
									else {

										pass = false;
									}

								} catch (Exception e) {

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
								return pass;

							}
						 
//============================================spirit list====================================		
						 
						 
						 public ArrayList spirit_list(ImportOrderEntry_spirit_action act) {
								ArrayList list = new ArrayList();
								Connection conn = null;
								PreparedStatement pstmt = null;
								ResultSet rs = null;

								SelectItem item = new SelectItem();
								item.setLabel("--select--");
								item.setValue("0");
								list.add(item);
								try {
									String query = " SELECT int_spirit_type_id, vch_spirit_name" +
											      "  FROM distillery.mst_spirit_type where permit_flg='T' order by vch_spirit_name ";

								 //System.out.println("spirit_list=" + query);
									conn = ConnectionToDataBase.getConnection();
									pstmt = conn.prepareStatement(query);

									rs = pstmt.executeQuery();

									while (rs.next()) {
										item = new SelectItem();

										item.setValue(rs.getInt("int_spirit_type_id"));
										item.setLabel(rs.getString("vch_spirit_name"));

										list.add(item);

									}

								} catch (Exception e) {
									FacesContext.getCurrentInstance().addMessage(null,
											new FacesMessage(e.getMessage(), e.getMessage()));
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
						 
//==============================================delete
						 
						 public ArrayList deleterow(ImportOrderEntry_spirit_action action,ImportOrderEntry_spirit_dt dt )
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
						 		    
						 		
						 		    
						 		  
						 	       delete1 =    " DELETE FROM distillery.eoi_import_order_master_spirit "+
						             		    " WHERE seq_pk= ? ";
						 	       
						 	       
						 	        pstmt1=conn.prepareStatement(delete1);
						 			
						 			pstmt1.setInt(1,dt.getApp_no());
						 			
						 		    deleteStatus=pstmt1.executeUpdate();	
						 		   
						 	    if(deleteStatus>0)
						 	   {
						 	
						 	  FacesContext.getCurrentInstance().addMessage(null,
						 	  new FacesMessage("Application Deleted Successfully", "Application Deleted Successfully"));
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




}
