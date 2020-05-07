package com.cmfirsttech.y2.api;

import static com.cmfirsttech.y2.api.Test_03_Models.MODELS;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.mapper.CoreMapper;
import com.cmfirsttech.y2.api.model.IModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class Test_04_Mapping {
	
	private static final Logger LOGGER = Logger.getLogger(Test_04_Mapping.class);
	
	@Inject
	CoreMapper mapper;
	
	@Test
	public void testMapping() {
		
		for (Class<? extends IModel> iModelClass : MODELS.values()) {
			LOGGER.info(String.format("Testing Model %1$s for mapping", 
					iModelClass.getSimpleName()));
			Class<? extends IEntity> iEntityClass =
					iModelClass.getAnnotation(Y2EntityClass.class).entityClass();
			boolean entityFetched = true;
			LOGGER.info("fetching instance of " + iEntityClass.getSimpleName());
			IEntity entity = null;
			try {
				entity = Test_02_JPA.findEntityInstance(iEntityClass);
			} catch (Exception e) {
				LOGGER.error("Instance not fetched for Entity " + iEntityClass.getSimpleName());
				entityFetched = false;
			}
			assertTrue(entityFetched);
			if (entityFetched) {
				boolean modelMapped = true;
				try {
					IModel modelObject = iModelClass.getConstructor().newInstance();
					mapper.directMap(modelObject, entity);
				} catch (Exception e) {
					LOGGER.error(String.format("Model %1$s failed to map", iModelClass.getSimpleName()), e);
					modelMapped = false;
				}
				assertTrue(modelMapped);
			}
			
		}
		
	}

}
