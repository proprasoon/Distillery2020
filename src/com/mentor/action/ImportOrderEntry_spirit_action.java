package com.mentor.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.mentor.utility.Constants;

import org.richfaces.component.UIDataTable;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mentor.Datatable.ImportOrderEntry_spirit_dt;
import com.mentor.Datatable.eoi_app_exporder_dt;
import com.mentor.impl.CommonImpl;
import com.mentor.impl.ImportOrderEntry_spirit_impl;




public class ImportOrderEntry_spirit_action 
{



		
	ImportOrderEntry_spirit_impl impl = new ImportOrderEntry_spirit_impl(); 
		
		private String  country_id;
		private String  hidden;
		private boolean flag=false;
		
		public boolean isFlag() {
			return flag;
		}
		public void setFlag(boolean flag) {
			this.flag = flag;
		}
		
		public String getHidden() {
			try{
				impl.getDistDetails(this);
				if(impl.checkDate(this) == true){
					this.submitOrder=impl.ShowsaveDate(this);
					this.viewflg = "T";
				}
			}
			catch (Exception e){
				
			}
			return hidden;
		}

		public void setHidden(String hidden) {
			this.hidden = hidden;
		}

		

		private ArrayList country_list = new ArrayList();
		public String getCountry_id() {
			return country_id;
		}

		public void setCountry_id(String country_id) {
			this.country_id = country_id;
		}

		public ArrayList getCountry_list() {
			this.country_list=impl.getCountryList();
			return country_list;
		}

		public void setCountry_list(ArrayList country_list) {
			this.country_list = country_list;
		}

	/*	public String getUnitNm() {
			return unitNm;
		}

		public void setUnitNm(String unitNm) {
			this.unitNm = unitNm;
		}*/


		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public Date getValidtyDt() {
			return validtyDt;
		}

		public void setValidtyDt(Date validtyDt) {
			this.validtyDt = validtyDt;
		}

		private String  unit_nm;
		public String getUnit_nm() {
			return unit_nm;
		}
		public void setUnit_nm(String unit_nm) {
			this.unit_nm = unit_nm;
		}

		private int  purchase_ordrno;
		public int getPurchase_ordrno() {
			return purchase_ordrno;
		}
		public void setPurchase_ordrno(int purchase_ordrno) {
			this.purchase_ordrno = purchase_ordrno;
		}

		private Date  date = new Date();
		private Date  validtyDt ; 
		private int  seqId;
		private String  distNm;
		private String  distAdd;
		private int  distId;
		private String sprit_type;
		private double Quantity_bl;
		private double strenght;
		private double Quantity_al;
		
		
		public double getQuantity_bl() {
			return Quantity_bl;
		}
		public void setQuantity_bl(double quantity_bl) {
			Quantity_bl = quantity_bl;
		}
		public double getStrenght() {
			return strenght;
		}
		public void setStrenght(double strenght) {
			this.strenght = strenght;
		}
		public double getQuantity_al() {
			this.Quantity_al = (this.Quantity_bl * this.strenght)/100 ;
			return Quantity_al;
		}
		public void setQuantity_al(double quantity_al) {
			Quantity_al = quantity_al;
		}
		public String getSprit_type() {
			return sprit_type;
		}
		public void setSprit_type(String sprit_type) {
			this.sprit_type = sprit_type;
		}
		public ArrayList getSpirit_list() {
			
			this.spirit_list = impl.spirit_list(this);
			
			return spirit_list;
		}
		public void setSpirit_list(ArrayList spirit_list) {
			this.spirit_list = spirit_list;
		}

   private String unit_add;

		public String getUnit_add() {
	return unit_add;
}
public void setUnit_add(String unit_add) {
	this.unit_add = unit_add;
}



		private ArrayList spirit_list=new ArrayList();
		
		
		

		public String getDistNm() {
			return distNm;
		}

		public void setDistNm(String distNm) {
			this.distNm = distNm;
		} 

		public String getDistAdd() {
			return distAdd;
		}

		public void setDistAdd(String distAdd) {
			this.distAdd = distAdd;
		}

		public int getDistId() {
			return distId;
		}

		public void setDistId(int distId) {
			this.distId = distId;
		}

		public int getSeqId() {
			return seqId;
		}

		public void setSeqId(int seqId) {
			this.seqId = seqId;
		}
		
		public void delete(ActionEvent e) {
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
			ImportOrderEntry_spirit_dt dt = (ImportOrderEntry_spirit_dt) 
			this.getSubmitOrder().get(uiTable.getRowIndex());
			impl.deleterow(this,dt);
			this.submitOrder.remove(dt);
			}

		// =============================uplodeer====================StartO
		public void doc1uploadMethod(UploadEvent event) throws Exception {

			this.fileName = "";
			InputStream inFile = null;
			UploadItem item = event.getUploadItem();
			String FullfileName = item.getFileName();
			String FullfileExt = null;

			String arr[] = FullfileName.split(".pdf");
			this.fileName = arr[0];

			if (FullfileName != null && FullfileName.length() > 4) {
				FullfileExt = FullfileName.substring(FullfileName.lastIndexOf("."));
			}
			Random rand = new Random();
			int n = rand.nextInt(250) + 1;
			// String recordFile = impl.getMaxId() + "_"+ this.distillery_id+
			// "_IMPORT.pdf";
			recordFile = impl.getMaxId() + "_" + this.country_id + "_" + n+ "_PurchaseOrder.pdf";

			String path = item.getFile().getAbsolutePath();
			filePathphoto = item.getFile().getPath();
			if (filePathphoto != null && (FullfileExt.equalsIgnoreCase(".pdf"))) {

				inFile = new FileInputStream(path);
				boolean success = false;

				try 
				{
					String mypath = Constants.JBOSS_SERVER_PATH
							+ Constants.JBOSS_LINX_PATH + File.separator
							+ "ExciseUp" + File.separator + "ExportOutsideIndia" + File.separator + "SPIRIT"+ File.separator + "ImportOrder";
							
					mypathphoto = mypath + File.separator + recordFile;

					if (!(new File(mypath).exists())) {
						File file = new File(mypath);
						success = file.mkdirs();
					}
					inFile = new FileInputStream(path);
					apidoc1 = new BufferedInputStream(inFile);
					if (apidoc1.available() > 0) {

						FileOutputStream out = new FileOutputStream(mypath
								+ File.separator + recordFile);
						BufferedOutputStream outb = new BufferedOutputStream(out);
						int z = 0;
						while ((z = apidoc1.read()) != -1) {
							outb.write(z);
							doc1upload = true;
						}
						outb.flush();
						outb.close();

					} else {
						img1 = true;
						doc1upload = false;
					}
					// System.out.print("doc1 uploaded success fully");
					// this.setCount(this.getCount()+1);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			else {
				// System.out.print("1 NO FILE READ STARTED.");
				doc1upload = false;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								" MObile  no Required !!!!!!",
								"  MObile Required   !!!!!!"));
			}

		}

		public void resetpage() {

			// System.out.println("------file name----------"+this.getFileName());
			if (this.fileName != null) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Pdf Uploaded  !!!!!", "Pdf Uploaded  !!!!!"));
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								" Upload Pdf first !!!!!!",
								"Upload Pdf first  !!!!!!"));
			}
		}

		private String recordFile = "NA";

		public String getRecordFile() {
			return recordFile;
		}

		public void setRecordFile(String recordFile) {
			this.recordFile = recordFile;
		}

		public boolean doc1upload = false;

		public boolean isDoc1upload() {
			return doc1upload;
		}

		public void setDoc1upload(boolean doc1upload) {
			this.doc1upload = doc1upload;
		}

		public boolean isImg1() {
			return img1;
		}

		public void setImg1(boolean img1) {
			this.img1 = img1;
		}

		public static BufferedInputStream getApidoc1() {
			return apidoc1;
		}

		public static void setApidoc1(BufferedInputStream apidoc1) {
			ImportOrderEntry_spirit_action.apidoc1 = apidoc1;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		private boolean img1 = false;
		private static BufferedInputStream apidoc1 = null;
		private String fileName;

		public String pdfPath = "http:" + CommonImpl.serverIpAddressWithPort()
				+ "//ExciseUp//ImportOrderEntry//pdf";
		// D:\doc\ExciseUp\BondedWhereHouse\Objection
		private String mypathphoto = "";

		private String filePathphoto;

		public String getPdfPath() {
			return pdfPath;
		}

		public void setPdfPath(String pdfPath) {
			this.pdfPath = pdfPath;
		}

		public String getMypathphoto() {
			return mypathphoto;
		}

		public void setMypathphoto(String mypathphoto) {
			this.mypathphoto = mypathphoto;
		}

		public String getFilePathphoto() {
			return filePathphoto;
		}

		public void setFilePathphoto(String filePathphoto) {
			this.filePathphoto = filePathphoto;
		}

		// =============================uplodeer====================exit
		private boolean validateInput;

		public boolean isValidateInput() {
			
			  this.validateInput=true;
			                  // ====country_id=====null               ====unitNm                ====purchaseOrdrNo====0===date===Thu Sep 03 00:00:00 IST 2020===recordFile===NA===validtyDt===Thu Sep 03 00:00:00 IST 2020
			//System.out.println("====country_id====="+this.country_id +"====unitNm=="+this.unit_nm +"==purchaseOrdrNo===="+this.purchase_ordrno+"===date==="+this.date+"===recordFile==="+this.recordFile+"===validtyDt==="+this.validtyDt);
				if (validateInput) {
				
				    	   if(this.country_id.equalsIgnoreCase("NA") || this.country_id.length()==0 || this.country_id ==null ){
						this.validateInput = false;
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please select Country Dropdown"));	
					}
					else if (this .getUnit_nm().length()== 0  || this.getUnit_nm() == null  ) {
						this.validateInput = false;
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Enter Importing Unit Name"));	
					}
					else if (this.purchase_ordrno == 0) {
						this.validateInput = false;
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Enter Purchase Order No"));	
					}
					else if (this.date == null ) {
						this.validateInput = false;
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please select Date"));	
					}
					else if (this.recordFile.equalsIgnoreCase("NA") || this.recordFile == null) {
						this.validateInput = false;
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Upload Purchase Order"));	
					}
					else if (this.validtyDt == null) {
						this.validateInput = false;
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please select Validity Of Purchase Order"));	
					}
					 
				

				}
			
			return validateInput;
		}



		public void setValidateInput(boolean validateInput) {
			this.validateInput = validateInput;
		}

		public void save(){
			try{
				
			
		if (isValidateInput())
		{
			if (impl.checkbrewery(this) != false) 
			{
			impl.saveData(this);
			
			this.submitOrder=impl.ShowsaveDate(this);
			
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(
								"Registration Of Distillery as Export Unit is Pending For Approval!!!!!!",
								"Registration Of Distillery as Export Unit is Pending For Approval!!!!!"));

			}
			
		}
		}
			catch (Exception e){
				
			}
			
		}
		
		public void reset(){
			this.country_id=null;
			this.unit_nm=null;
			this.purchase_ordrno=0;
			this.date=null;
			this.recordFile=null;
			this.doc1upload = false;
			this.Quantity_al=0;
			this.Quantity_bl=0;
			this.strenght=0;
			this.sprit_type=null;
				
		}
		ArrayList submitOrder =new ArrayList();
		
		public ArrayList getSubmitOrder() {
			return submitOrder;
		}
		public void setSubmitOrder(ArrayList submitOrder) {
			this.submitOrder = submitOrder;
		}

		
		private int  distilleryId;
		public int getDistilleryId() {
			return distilleryId;
		}
		public void setDistilleryId(int distilleryId) {
			this.distilleryId = distilleryId;
		}
		private String viewflg = "F";

		public String getViewflg() {
			return viewflg;
		}

		public void setViewflg(String viewflg) {
			this.viewflg = viewflg;
		}
	}



