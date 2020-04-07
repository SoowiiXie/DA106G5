package com.grp_detail.model;

import java.util.List;

public class Grp_detailService {

	private Grp_detailDAO_interface dao;

	public Grp_detailService() {
		dao = new Grp_detailDAO();
	}

	public Grp_detailVO addEmp(String ename, String job, java.sql.Date hiredate,
			Double sal, Double comm, Integer deptno) {

		Grp_detailVO empVO = new Grp_detailVO();

		empVO.setEname(ename);
		empVO.setJob(job);
		empVO.setHiredate(hiredate);
		empVO.setSal(sal);
		empVO.setComm(comm);
		empVO.setDeptno(deptno);
		dao.insert(empVO);

		return empVO;
	}

	public Grp_detailVO updateEmp(Integer empno, String ename, String job,
			java.sql.Date hiredate, Double sal, Double comm, Integer deptno) {

		Grp_detailVO empVO = new Grp_detailVO();

		empVO.setEmpno(empno);
		empVO.setEname(ename);
		empVO.setJob(job);
		empVO.setHiredate(hiredate);
		empVO.setSal(sal);
		empVO.setComm(comm);
		empVO.setDeptno(deptno);
		dao.update(empVO);

		return empVO;
	}

	public void deleteEmp(Integer empno) {
		dao.delete(empno);
	}

	public Grp_detailVO getOneEmp(Integer empno) {
		return dao.findByPrimaryKey(empno);
	}

	public List<Grp_detailVO> getAll() {
		return dao.getAll();
	}
}
