/**
 * 
 */
package mx.org.inegi.geo.map.service.impl;

import mx.org.inegi.geo.map.domain.Layer;
import mx.org.inegi.geo.map.mapper.LayerStatsMapper;
import mx.org.inegi.geo.map.model.LayerStats;
import mx.org.inegi.geo.map.service.LayerStatsService;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author femat
 *
 */
@Service
public class LayerStatsServiceImpl implements LayerStatsService {

	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;

	@Override
	public void insertLayers(LayerStats layerStats) throws Exception {
		String project = layerStats.getProject();
		String session = layerStats.getSession();
		String userLocation = layerStats.getUserLocation();
		String type = layerStats.getType();
		SqlSession sqlSession = null;
		sqlSession = sqlSessionFactory.getObject().openSession(
				ExecutorType.BATCH);
		LayerStatsMapper batchMapper = sqlSession
				.getMapper(LayerStatsMapper.class);
		for (String layer : layerStats.getLayers()) {
			Layer l = new Layer(project, session, layer, userLocation, type);
			batchMapper.insertLayer(l);
		}
		sqlSession.flushStatements();
		sqlSession.commit();
		sqlSession.close();
	}

}
