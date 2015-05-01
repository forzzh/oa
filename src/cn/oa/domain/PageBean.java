package cn.oa.domain;

import java.util.List;

/**
 * ������ҳ
 * @author zzh
 *
 */
public class PageBean {
	private int currentPage;//��ǰҳ
	private int pageSize;//ÿҳ��ʾ������
	
	private int recordCount;//�ܼ�¼��
	private List recordList;//��ҳ�������б�
	
	//����
	private int pageCount;//��ҳ��
	private int beginPageIndex;
	private int endPageIndex;
	
	//����4����������3��
	public PageBean(int currentPage, int pageSize, int recordCount,
			List recordList) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.recordList = recordList;
		
		pageCount = (recordCount + pageSize - 1)/pageSize;
		//beginPageIndex
		//��ҳ��������10��ȫ����ʾ
		if (pageCount <= 10) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		} else {
			//����ʾ��ǰҳ��ǰ4���ͺ�5��
			beginPageIndex = currentPage - 4;
			endPageIndex = currentPage + 5;
			
			//ǰ��Ĳ���4��
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			//���治��5��ʱ
			if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 9;
			}
		}
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	
	public List getRecordList() {
		return recordList;
	}

	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}

	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getBeginPageIndex() {
		return beginPageIndex;
	}
	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}
	public int getEndPageIndex() {
		return endPageIndex;
	}
	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
	
	
}
