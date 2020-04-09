package com.path.model;

import java.sql.Timestamp;
import java.util.List;

public class PathService {

	private PathDAO_interface dao;//為了框架所以使用介面多型宣告,可以做到0相依性

	public PathService() {
		dao = new PathJDBCDAO();
	}

	public PathVO addPath(String path_name, Integer path_difficulty, java.sql.Timestamp path_start, java.sql.Timestamp path_end, byte[] path_pic, Double path_distance, String path_kml, Double path_lng, Double path_lat) {

		PathVO pathVO = new PathVO();

		pathVO.setPath_name(path_name);
		pathVO.setPath_difficulty(path_difficulty);
		pathVO.setPath_start(path_start);
		pathVO.setPath_end(path_end);
		pathVO.setPath_pic(path_pic);
		pathVO.setPath_distance(path_distance);
		pathVO.setPath_kml(path_kml);
		pathVO.setPath_lng(path_lng);
		pathVO.setPath_lat(path_lat);
		dao.insert(pathVO);

		return pathVO;
	}

	public PathVO updatePath(String path_name, Integer path_difficulty,Integer path_popular, java.sql.Timestamp path_start, java.sql.Timestamp path_end, byte[] path_pic, Double path_distance, String path_kml, Double path_lng, Double path_lat) {

		PathVO pathVO = new PathVO();

		pathVO.setPath_name(path_name);
		pathVO.setPath_difficulty(path_difficulty);
		pathVO.setPath_popular(path_popular);
		pathVO.setPath_start(path_start);
		pathVO.setPath_end(path_end);
		pathVO.setPath_pic(path_pic);
		pathVO.setPath_distance(path_distance);
		pathVO.setPath_kml(path_kml);
		pathVO.setPath_lng(path_lng);
		pathVO.setPath_lat(path_lat);
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
