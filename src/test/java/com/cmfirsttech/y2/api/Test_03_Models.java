package com.cmfirsttech.y2.api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.Instant;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.mapper.MapperFields;
import com.cmfirsttech.y2.api.mapper.MappingConfig;
import com.cmfirsttech.y2.api.mapper.MappingType;
import com.cmfirsttech.y2.api.model.IModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;
import com.cmfirsttech.y2.api.model.impl.AccessPath;
import com.cmfirsttech.y2.api.model.impl.AdACTIONFormat;
import com.cmfirsttech.y2.api.model.impl.AdBLOCKFormat;
import com.cmfirsttech.y2.api.model.impl.AdCOMPAREFormat;
import com.cmfirsttech.y2.api.model.impl.AdCONDITFormat;
import com.cmfirsttech.y2.api.model.impl.AdCONTEXTFormat;
import com.cmfirsttech.y2.api.model.impl.AdPARAMFormat;
import com.cmfirsttech.y2.api.model.impl.AdSUBBLKFormat;
import com.cmfirsttech.y2.api.model.impl.AdUSREXPFormat;
import com.cmfirsttech.y2.api.model.impl.DataModelTarget;
import com.cmfirsttech.y2.api.model.impl.FieldAttribute;
import com.cmfirsttech.y2.api.model.impl.FieldCondition;
import com.cmfirsttech.y2.api.model.impl.FieldDetails;
import com.cmfirsttech.y2.api.model.impl.FieldDomain;
import com.cmfirsttech.y2.api.model.impl.FileAccessPaths;
import com.cmfirsttech.y2.api.model.impl.FileDetails;
import com.cmfirsttech.y2.api.model.impl.FileFunctions;
import com.cmfirsttech.y2.api.model.impl.FunctionDetails;
import com.cmfirsttech.y2.api.model.impl.FunctionPrototype;
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
		MODELS.put(AdACTIONFormat.class.getName(), AdACTIONFormat.class);
		MODELS.put(AdBLOCKFormat.class.getName(), AdBLOCKFormat.class);
		MODELS.put(AdCOMPAREFormat.class.getName(), AdCOMPAREFormat.class);
		MODELS.put(AdCONDITFormat.class.getName(), AdCONDITFormat.class);
		MODELS.put(AdCONTEXTFormat.class.getName(), AdCONTEXTFormat.class);
		MODELS.put(AdPARAMFormat.class.getName(), AdPARAMFormat.class);
		MODELS.put(AdSUBBLKFormat.class.getName(), AdSUBBLKFormat.class);
		MODELS.put(AdUSREXPFormat.class.getName(), AdUSREXPFormat.class);
		MODELS.put(FunctionPrototype.class.getName(), FunctionPrototype.class);
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
				if (skipMapping(field, directMapped, configsMap)) {
					continue;
				}
				assertTrue(configsMap.containsKey(field));
				MappingConfig mc = configsMap.remove(field);
				assertTrue(mc.field.equals(field));
				assertTrue(mc.nullIfEmpty == directMapped.nullIfEmpty());
				Class<?> fieldType = field.getType();
				if (fieldType.equals(Instant.class)) {
					assertTrue(mc.mappingType.equals(MappingType.INSTANT));
				} else if (fieldType.isEnum()) {
					assertTrue(mc.mappingType.equals(MappingType.NAMED_ENUM));
				} else if (field.getType().equals(Boolean.class)) {
					assertTrue(mc.mappingType.equals(MappingType.NAMED_BOOLEAN));
				} else if (IModel.class.isAssignableFrom(fieldType)) {
					assertTrue(mc.mappingType.equals(MappingType.SUB_MAP));
				} else if (Collection.class.isAssignableFrom(fieldType)) {
					assertTrue(mc.mappingType.equals(MappingType.COLLECTION));
				}
				switch (mc.mappingType) {
				case NAMED -> namedMapping(mc, field, directMapped);
				case NAMED_BOOLEAN -> booleanMapping(mc, field, directMapped);
				case NAMED_ENUM -> enumMapping(mc, field, directMapped);
				case INSTANT -> instantMapping(mc, field, directMapped);
				case SUB_MAP -> subMapping(mc, field, directMapped);
				case COLLECTION -> collectionMapping(mc, field, directMapped);
				default -> throw new IllegalArgumentException("Unexpected value: " + mc.mappingType);
				}
			}
			assertTrue(configsMap.isEmpty());
		}
		logger.info("** Completed Validation of Model annotations");
	}


	private boolean skipMapping(Field field, DirectMapped directMapped, Map<Field, MappingConfig> configsMap) {
		boolean skip = directMapped.mappingType().equals(MappingType.SKIP);
		if (skip) {
			assertFalse(configsMap.containsKey(field));
		}
		return skip;
	}


	private void collectionMapping(MappingConfig mc, Field field, DirectMapped directMapped) {
		namedMapping(mc, field, directMapped);
		assertTrue(Collection.class.isAssignableFrom(field.getType()));
		assertTrue(field.isAnnotationPresent(ModelClass.class));
	}


	private void subMapping(MappingConfig mc, Field field, DirectMapped directMapped) {
		namedMapping(mc, field, directMapped);
		assertTrue(IModel.class.isAssignableFrom(field.getType()));
	}


	private void instantMapping(MappingConfig mc, Field field, DirectMapped directMapped) {
		assertFalse(directMapped.instantDateSource().isBlank());
		assertFalse(directMapped.instantTimeSource().isBlank());
		assertTrue(mc.instantDateSource.equals(directMapped.instantDateSource()));
		assertTrue(mc.instantTimeSource.equals(directMapped.instantTimeSource()));
	}


	private void enumMapping(MappingConfig mc, Field field, DirectMapped directMapped) {
		namedMapping(mc, field, directMapped);
	}


	private void booleanMapping(MappingConfig mc, Field field, DirectMapped directMapped) {
		namedMapping(mc, field, directMapped);
	}


	private void namedMapping(MappingConfig mc, Field field, DirectMapped directMapped) {
		String mapSource = directMapped.mapSource();
		if (mapSource.isBlank()) {
			mapSource = field.getName();
		}
		assertTrue(mc.mapSource.equals(mapSource));
	}
	
}
