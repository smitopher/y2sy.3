package com.cmfirsttech.y2.api.model.impl;

import static com.cmfirsttech.y2.api.constants.TestReflections.REFLECT;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.mapper.MapperFields;
import com.cmfirsttech.y2.api.mapper.MappingConfig;
import com.cmfirsttech.y2.api.model.IModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;
import com.cmfirsttech.y2.api.model.internal.ModelClass;

import io.quarkus.runtime.StartupEvent;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class Test_Models {
	
	private static final Set<Class<? extends IModel>> models = new HashSet<>();
	private final Logger logger = Logger.getLogger(getClass());
	private final Package expectedPackage = getClass().getPackage();
	
	@DirectMapped
	private static final Object defaultMappingObject = new Object();
	private static final DirectMapped defaultMapping = 
			defaultMappingObject.getClass().getAnnotation(DirectMapped.class);
	
	@Inject
	MapperFields mapperFields;
	
	
	@Test
	public void testPackage() {
		logger.info("** Starting Validation that all Models are in expected package");
		for (Class<? extends IModel> entity : models) {
			logger.info(entity.getCanonicalName());
			Package modelPackage = entity.getPackage();
			assertTrue(modelPackage.equals(expectedPackage));
		}
		logger.info("** Completed Validation that all Models are in expected package");
	}
	
	@Test
	public void testModelAnnotations() {
		logger.info("** Starting Validation that all Models are annotated as expected");
		for (Class<? extends IModel> model : models) {
			logger.info("** " + model.getSimpleName());
			assertTrue(model.isAnnotationPresent(Y2EntityClass.class));
			Map<Field, MappingConfig> configsMap = new HashMap<>();
			Set<MappingConfig> configs = mapperFields.getMappingFields(model);
			for (MappingConfig mappingConfig : configs) {
				configsMap.put(mappingConfig.field, mappingConfig);
			}
			for (Field field : model.getFields()) {
				logger.info(field.getName());
				DirectMapped directMapped = field.getAnnotation(DirectMapped.class);
				if (directMapped == null) {
					directMapped = defaultMapping;
				}
				if (directMapped.skip()) {
					assertFalse(configsMap.containsKey(field));
					continue;
				} else {
					assertTrue(configsMap.containsKey(field));
				}
				MappingConfig mc = configsMap.remove(field);
				assertTrue(mc.field.equals(field));
				if (field.getType().equals(Instant.class)) {
					assertFalse(directMapped.instantDateSource().isBlank());
					assertFalse(directMapped.instantTimeSource().isBlank());
					assertTrue(mc.instantDateSource.equals(directMapped.instantDateSource()));
					assertTrue(mc.instantTimeSource.equals(directMapped.instantTimeSource()));
				} else {
					assertFalse(directMapped.mapSource().isBlank());
					assertTrue(mc.mapSource.equals(directMapped.mapSource()));
				}
				assertTrue(mc.nullIfEmpty == directMapped.nullIfEmpty());
				assertTrue(mc.isSubMap == IModel.class.isAssignableFrom(field.getType()));
				assertTrue(mc.isCollection == Collection.class.isAssignableFrom(field.getType()));
				
				if (mc.isCollection) {
					assertTrue(field.getType().isAnnotationPresent(ModelClass.class));
				}
			}
			assertTrue(configsMap.isEmpty());
		}
		logger.info("** Completed Validation of Model annotations");
	}
	
	
	
	void onStart(@Observes StartupEvent ev) {
		Set<Class<? extends IModel>> iModels = REFLECT.instance.getSubTypesOf(IModel.class);
		for (Class<? extends IModel> iModel : iModels) {
			if (Modifier.isAbstract(iModel.getModifiers()) || Modifier.isInterface(iModel.getModifiers())) {
				continue;
			}
			models.add(iModel);
		}
	}

}
