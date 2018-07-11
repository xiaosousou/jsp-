package myclass.dal;

import java.io.*;

import mybean.data.uploadFileBean;


public class uploadFileDal {
	private String driverPath=null;
	private String tempFileName=null;
	private String saveFileName=null;
	private InputStream fileSource=null;
	private String uploadFileName=null;
	private String backMessage=null;
	boolean flag=false;

	public String getDriverPath() {
		return driverPath;
	}
	public void setDriverPath(String driverPath) {
		this.driverPath = driverPath;
	}
	public String getTempFileName() {
		return tempFileName;
	}
	public void setTempFileName(String tempFileName) {
		this.tempFileName = tempFileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public InputStream getFileSource() {
		return fileSource;
	}
	public void setFileSource(InputStream fileSource) {
		this.fileSource = fileSource;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
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
	public uploadFileDal(String driverPath,String tempFileName,String saveFileName,InputStream fileSource) {
		super();
		this.driverPath=driverPath;
		this.tempFileName=tempFileName;
		this.saveFileName=saveFileName;
		this.fileSource=fileSource;
	}
	public boolean uploadFileMethod() {
		try {//�ϴ��ļ�����ʱ�ļ���
			//driverPath=driverPath.replaceAll("\\\\", "/");  
			//saveFileName=saveFileName.replaceAll("\\\\", "/");
			File f1=new File(driverPath,tempFileName);
			FileOutputStream fos=new FileOutputStream(f1);
			byte b[]=new byte[10000];
			int n;
			while((n=fileSource.read(b))!=-1) {
				fos.write(b, 0, n);
			}
			fos.close();
			fileSource.close();
			//��ȡ��ʱ�ļ��еڶ��е���Ϣ
			RandomAccessFile random=new RandomAccessFile(f1,"r");
			int second=1;
			String secondLine=null;
			while(second<=2) {
				secondLine=random.readLine();
				second++;
			}
			//�õ��ϴ��ļ����ļ���
			int position=secondLine.lastIndexOf('\\');
			uploadFileName=secondLine.substring(position+1, secondLine.length()-1);
			System.out.println("uploadFileName"+uploadFileName);
			//���ļ�ת��Ϊ����
			byte cc[]=uploadFileName.getBytes("utf-8");
			uploadFileName=new String(cc);
			int extposition=uploadFileName.lastIndexOf('.');
			String extName=uploadFileName.substring(extposition+1, uploadFileName.length());
			//��ȡ�ϴ���ʱ�ļ��ĵ��Ļس�����λ��
			random.seek(0);
			long forthEndPosition=0;
			int forth=1;
			while((n=random.readByte())!=-1&&forth<=4) {
				if(n=='\n') {
					forthEndPosition=random.getFilePointer();
					forth++;
				}
			}
			//ɾ���������ļ�
			saveFileName=saveFileName.concat("."+extName);
			System.out.println("saveFileName"+saveFileName);//����ļ���
			File dir=new File(driverPath);
			dir.mkdir();
			File file[]=dir.listFiles();
			for(int k=0;k<file.length;k++) {
				if(file[k].getName().equals(saveFileName)) {
					file[k].delete();
				}
			}
			//�������� �����ļ�  
			
			File savingFile=new File(driverPath,saveFileName);
			System.out.println(driverPath+saveFileName);//���·��
			RandomAccessFile random2=new RandomAccessFile(savingFile, "rw");
			//����ʱ�ļ��л���ϴ��ļ��Ľ���λ��
			random.seek(random.length());
			long endPosition=random.getFilePointer();
			long mark=endPosition;
			int j=1;
			while((mark>=0)&&(j<=6)) {
				mark--;
				random.seek(mark);
				n=random.readByte();
				if(n=='\n') {
					endPosition=random.getFilePointer();
					j++;
				}
			}
			random.seek(forthEndPosition);
			long startPoint=random.getFilePointer();
			while(startPoint<endPosition-1) {
				n=random.readByte();
				random2.write(n);
				startPoint=random.getFilePointer();
			}
			random2.close();
			random.close();
			f1.delete();
			flag=true;
			backMessage="�ɹ��ϴ�";
		}catch(Exception exp) {
			System.out.println("uploadFileMethod"+exp.toString());
			exp.printStackTrace();
			backMessage=""+exp;
			flag=false;
		}
		return flag;
	}
}
