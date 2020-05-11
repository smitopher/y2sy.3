package com.cmfirsttech.y2.api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.mapper.MapperFields;
import com.cmfirsttech.y2.api.mapper.MappingConfig;
import com.cmfirsttech.y2.api.model.IModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;
import com.cmfirsttech.y2.api.model.impl.AccessPath;
import com.cmfirsttech.y2.api.model.impl.DataModelTarget;
import com.cmfirsttech.y2.api.model.impl.FieldAttribute;
import com.cmfirsttech.y2.api.model.impl.FieldCondition;
import com.cmfirsttech.y2.api.model.impl.FieldDetails;
import com.cmfirsttech.y2.api.model.impl.FieldDomain;
import com.cmfirsttech.y2.api.model.impl.FileAccessPaths;
import com.cmfirsttech.y2.api.model.impl.FileDetails;
import com.cmfirsttech.y2.api.model.impl.FileFunctions;
import com.cmfirsttech.y2.api.model.impl.FunctionDetails;
import com.cmfirsttech.y2.api.model.impl.ModelEntry;
import com.cmfirsttech.y2.api.model.impl.ObjectListEntry;
import com.cmfirsttech.y2.api.model.internal.ModelClass;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class Test_03_Models {
	
	public static final Map<String, Class<? extends IModel>> MODELS = new TreeMap<>();
	
	static {
		MODELS.put(AccessPath.class.getName(), AccessPath.class);
		MODELS.put(DataModelTarget.class.getName(), DataModelTarget.class);
		MODELS.put(FieldAttribute.class.getName(), FieldAttribute.class);
		MODELS.put(FieldCondition.class.getName(), FieldCondition.class);
		MODELS.put(FieldDetails.class.getName(), FieldDetails.class);
		MODELS.put(FieldDomain.class.getName(), FieldDomain.class);
		MODELS.put(FileAccessPaths.class.getName(), FileAccessPaths.class);
		MODELS.put(FileDetails.class.getName(), FileDetails.class);
		MODELS.put(FileFunctions.class.getName(), FileFunctions.class);
		MODELS.put(FunctionDetails.class.getName(), FunctionDetails.class);
		MODELS.put(ObjectListEntry.class.getName(), ObjectListEntry.class);
		MODELS.put(ModelEntry.class.getName(), ModelEntry.class);
	}
	
	@DirectMapped
	public static final Object DEFAULT_MAPPING_OBJECT = new Object();
	private DirectMapped defaultMapping;
	private final Logger logger = Logger.getLogger(getClass());
	
	public Test_03_Models() throws NoSuchFieldException, SecurityException {
		defaultMapping = getClass().getField("DEFAULT_MAPPING_OBJECT").getAnnotation(DirectMapped.class);
	}
	
	@Inject
	MapperFields mapperFields;
	
	
	@Test
	public void test_02_ModelAnnotations() {
		logger.info("** Starting Validation that all Models are annotated as expected");
		for (Class<? extends IModel> model : MODELS.values()) {
			logger.info("** " + model.getSimpleName());
			assertTrue(model.isAnnotationPresent(Y2EntityClass.class));
			Map<Field, MappingConfig> configsMap = new LinkedHashMap<>();
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
					String mapSource = directMapped.mapSource();
					if (mapSource.isBlank()) {
						mapSource = field.getName();
					}
					assertTrue(mc.mapSource.equals(mapSource));
				}
				assertTrue(mc.nullIfEmpty == directMapped.nullIfEmpty());
				assertTrue(mc.isSubMap == IModel.class.isAssignableFrom(field.getType()));
				assertTrue(mc.isCollection == Collection.class.isAssignableFrom(field.getType()));
				
				if (mc.isCollection) {
					assertTrue(field.isAnnotationPresent(ModelClass.class));
				}
			}
			assertTrue(configsMap.isEmpty());
		}
		logger.info("** Completed Validation of Model annotations");
	}
	
}
