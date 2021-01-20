package com.allClass_info.model;
import java.util.*;

import com.class_chapter.model.Class_chapterVO;
import com.class_info.model.Class_infoVO;
import com.class_unit.model.Class_unitVO;
import com.main_class.model.Main_classVO;
import com.main_message.model.Main_messageVO;
import com.member_info.model.Member_infoVO;
import com.order_info.model.Order_infoVO;
import com.order_list.model.Order_listVO;
import com.student_homework.model.Student_homeworkVO;
import com.sub_class.model.Sub_classVO;
import com.sub_message.model.Sub_messageVO;
import com.teacher_homework.model.Teacher_homeworkVO;
import com.test.model.TestVO;

public class AllClass_infoVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Class_infoVO class_infoVO;
	private Map<Class_chapterVO,List<Class_unitVO>> chapterAndUnit;
	private Map<Main_messageVO,List<Sub_messageVO>> messageClass;
	private Map<Main_messageVO,List<Sub_messageVO>> messageLearn;
	private Main_classVO main_class ; //JDBC ¨S¦³°µ
	private Sub_classVO sub_class ;
	private Member_infoVO teacher ; 
	private Map <Order_infoVO,List<Order_listVO>> Order;
	private Set <String> student ; 
	private Map <Teacher_homeworkVO,List<Student_homeworkVO>> homework;
	private List<TestVO>test;
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((class_infoVO == null) ? 0 : class_infoVO.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AllClass_infoVO other = (AllClass_infoVO) obj;
		if (class_infoVO == null) {
			if (other.class_infoVO != null)
				return false;
		} else if (!class_infoVO.equals(other.class_infoVO))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AllClass_infoVO [class_infoVO=" + class_infoVO + "]";
	}
	public Class_infoVO getClass_infoVO() {
		return class_infoVO;
	}
	public void setClass_infoVO(Class_infoVO class_infoVO) {
		this.class_infoVO = class_infoVO;
	}
	public Map<Class_chapterVO, List<Class_unitVO>> getChapterAndUnit() {
		return chapterAndUnit;
	}
	public void setChapterAndUnit(Map<Class_chapterVO, List<Class_unitVO>> chapterAndUnit) {
		this.chapterAndUnit = chapterAndUnit;
	}
	public Main_classVO getMain_class() {
		return main_class;
	}
	public void setMain_class(Main_classVO main_class) {
		this.main_class = main_class;
	}
	public Sub_classVO getSub_class() {
		return sub_class;
	}
	public void setSub_class(Sub_classVO sub_class) {
		this.sub_class = sub_class;
	}
	public Member_infoVO getTeacher() {
		return teacher;
	}
	public void setTeacher(Member_infoVO teacher) {
		this.teacher = teacher;
	}
	public Map<Order_infoVO, List<Order_listVO>> getOrder() {
		return Order;
	}
	public void setOrder(Map<Order_infoVO, List<Order_listVO>> order) {
		Order = order;
	}
	public Set<String> getStudent() {
		return student;
	}
	public void setStudent(Set<String> student) {
		this.student = student;
	}
	
	public Map<Teacher_homeworkVO, List<Student_homeworkVO>> getHomework() {
		return homework;
	}
	public void setHomework(Map<Teacher_homeworkVO, List<Student_homeworkVO>> homework) {
		this.homework = homework;
	}
	public List<TestVO> getTest() {
		return test;
	}
	public void setTest(List<TestVO> test) {
		this.test = test;
	}
	
	public Map<Main_messageVO, List<Sub_messageVO>> getMessageClass() {
		return messageClass;
	}
	public void setMessageClass(Map<Main_messageVO, List<Sub_messageVO>> messageClass) {
		this.messageClass = messageClass;
	}
	public Map<Main_messageVO, List<Sub_messageVO>> getMessageLearn() {
		return messageLearn;
	}
	public void setMessageLearn(Map<Main_messageVO, List<Sub_messageVO>> messageLearn) {
		this.messageLearn = messageLearn;
	}

}
