package myclass.bll;

import java.io.InputStream;

import myclass.dal.uploadFileDal;

public class uploadFileBll {
	private String backMessage=null;
	private boolean flag=false;
	private String uploadFileName=null;
	private String saveFileName=null;
	
	public String getBackMessage() {
		return backMessage;
	}
	public void setBackMessage(String backMessage) {
		this.backMessage = backMessage;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public boolean uploadFileMethod(String driverPath,String tempFileName,String saveFileName,InputStream fileSource) {
		System.out.println("�鿴�ϴ��ļ����Ƿ������ϴ�");
		
//		String strchecked=uploadFileName.substring(uploadFileName.indexOf(".")+1,uploadFileName.length());
//		System.out.println("strcheckes:"+strchecked);
//		String strext[]= {"jpg","bmp","gif"};
//		flag=false;
//		for(String str:strext) {
//			if(str.equalsIgnoreCase(strchecked)) {
//				System.out.println("�ϴ��ļ�������");
//				flag=true;
//				break;
//			}
//		}
//		if(flag==false) {
//			backMessage="�ϴ��ļ���չ��������Ҫ��";
//			return flag;
//		}
		
			
		try {
			uploadFileDal upFile=new uploadFileDal(driverPath,tempFileName,saveFileName,fileSource);
			flag=upFile.uploadFileMethod();
			backMessage=upFile.getBackMessage();
			this.setUploadFileName(upFile.getUploadFileName());
			this.setSaveFileName(upFile.getSaveFileName());
		}catch(Exception exp) {
			flag=false;
			backMessage="�ϴ��ļ�ʧ��";
			this.setUploadFileName("");
			this.setSaveFileName("");
		}
		return flag;
	}
}
