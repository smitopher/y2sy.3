package com.cmfirsttech.y2.api.entity.impl;

import static com.cmfirsttech.y2.api.constants.TestReflections.REFLECT;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import com.cmfirsttech.y2.api.entity.IEntity;

import io.quarkus.runtime.StartupEvent;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class TestJPA {
	
	private Logger logger = Logger.getLogger(getClass());
	
	public static final Map<String, Class<? extends IEntity>> jpaEnties = new LinkedHashMap<>();
	public final Package expectedPackage = getClass().getPackage();

	
	@Inject
	EntityManager em;
	
	@Test
	public void testPackage() {
		logger.info("Starting Validation that all JPA Entities are in expected package");
		for (Class<? extends IEntity> entity : jpaEnties.values()) {
			logger.info(entity.getCanonicalName());
			Package entityPackage = entity.getPackage();
			assertTrue(entityPackage.equals(expectedPackage));
		}
		logger.info("Completed Validation that all JPA Entities are in expected package");
	}
	
	public static Stream<IEntity> getStream(Class<? extends IEntity> entity) {
		Stream<IEntity> stream = null;
		try {
			Object streamObject = entity.getMethod("streamAll").invoke(null);
			stream = (Stream<IEntity>)streamObject;
		} catch (Exception e) {}
		return stream;
	}

	@Test
	@Transactional
	public void testFetchFields() {
		logger.info("Starting JPA Entities mapped field fetch validation");
		for (Class<? extends IEntity> entityClass : jpaEnties.values()) {
			logger.info("** getting stream for " + entityClass.getSimpleName());
			Stream<IEntity> stream = getStream(entityClass);
			assertNotNull(stream);
			logger.info("** getting instance for " + entityClass.getSimpleName());
			Optional<IEntity> entityInstance = stream.findAny();
			assertNotNull(entityInstance);
			assertTrue(entityInstance.isPresent());
			stream.close();
			IEntity entity = entityInstance.get();
			for (Field field : entityClass.getFields()) {
				logger.info(field.getName());
				Optional<Field> entityField = entity.getMappedField(field.getName());
				assertTrue(entityField.isPresent());
				assertTrue(field.equals(entityField.get()));
				switch (fetchType(field)) {
				case LAZY:
					logger.info("** fetch is lazy");
					break;
				default:
					Object fieldValue = null;
					try {
						fieldValue = field.get(entity);
					} catch (Exception e) {}
					assertNotNull(fieldValue);
				}
			}
		}
	}
	
	private FetchType fetchType(Field field) {
		FetchType fetchType = FetchType.EAGER;
		boolean isCollection = Collection.class.isAssignableFrom(field.getType());
		boolean isEntity = IEntity.class.isAssignableFrom(field.getType());
		if (isCollection || isEntity) {
			for (Annotation annotation : field.getAnnotations()) {
				if (annotation instanceof OneToMany) {
					fetchType = ((OneToMany)annotation).fetch();
					break;
				}
				if (annotation instanceof ManyToOne) {
					fetchType = ((ManyToOne)annotation).fetch();
					break;
				}
			}
		}
		return fetchType;
	}

	static {
		for (Class<? extends IEntity> iEntity : REFLECT.instance.getSubTypesOf(IEntity.class)) {
			if (Modifier.isAbstract(iEntity.getModifiers()) || Modifier.isInterface(iEntity.getModifiers())) {
				continue;
			}
			jpaEnties.put(iEntity.getName(), iEntity);
		}
	}

}
