package com.path.model;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;

public class PathService {

	private PathDAO_interface dao;//為了框架所以使用介面多型宣告,可以做到0相依性

	public PathService() {
		dao = new PathJDBCDAO();
	}

	public String addPath() {

		PathVO pathVO = new PathVO();

		dao.insert(pathVO);

		return pathVO.getPath_no();
	}

	public PathVO updatePath(String path_name, Integer path_difficulty, java.sql.Timestamp path_end, Double path_distance, String path_kml, Double path_lng, Double path_lat, String path_no, String path_pic) {

		PathVO pathVO = new PathVO();

		pathVO.setPath_name(path_name);
		pathVO.setPath_difficulty(path_difficulty);
		pathVO.setPath_end(path_end);
		pathVO.setPath_distance(path_distance);
		pathVO.setPath_kml(path_kml);
		pathVO.setPath_lng(path_lng);
		pathVO.setPath_lat(path_lat);
		pathVO.setPath_no(path_no);
		byte[] path_picByte = Base64.getMimeDecoder().decode(path_pic);
		pathVO.setPath_pic(path_picByte);
		dao.update(pathVO);
		

		return pathVO;
	}

	public void deletePath(String path_no) {
		dao.delete(path_no);
	}

	public PathVO getOnePath(String path_no) {
		return dao.findByPrimaryKey(path_no);
	}

	public List<PathVO> getAll() {
		return dao.getAll();
	}
}
