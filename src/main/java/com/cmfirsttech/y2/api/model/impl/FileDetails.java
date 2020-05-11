package com.cmfirsttech.y2.api.model.impl;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jboss.logging.Logger;

import com.cmfirsttech.y2.api.constants.DataModelRelationType;
import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.entity.impl.Y2Field;
import com.cmfirsttech.y2.api.entity.impl.Y2File;
import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.mapper.MappingException;
import com.cmfirsttech.y2.api.model.AbstractModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;
import com.cmfirsttech.y2.api.model.internal.ModelClass;

@Y2EntityClass(entityClass = Y2File.class)
public class FileDetails extends AbstractModel{
	
	public Integer fileSurrogate;

	public String objectTypeForObjectType;

	public String objectAttributeForObjectAttribute;

	public String fileName;

	public BigDecimal documentationSequence;

	public String sourceMember;

	public String rpgFormatPrefix;

//	@Column(name="OLDDBS", length=1)
	public String pre_existantDatabase;

//	@Column(name="REQSYN", length=1)
	public String requiredBySynon_2;

//	@Column(name="@@NOTFND")
	public Integer recordNotFoundMsgSgt;

//	@Column(name="@@RCDEXS")
	public Integer recordExistsMsgSgt;

//	@Column(name="FILTXOV", length=1)
	public String overridePromptTextForFil;

//	@Column(name="FILMSID", length=7)
	public String defaultPromptMsgidForFil;

//	@Column(name="DSTFIL", length=1)
	public String distributedFile;

//	@Column(name="EXPDTE")
	public Integer exportDate;
	
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "y2File")
	@ModelClass(modelClass = DataModelTarget.class)
	public List<DataModelTarget> relations;
	
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "y2File")
	@ModelClass(modelClass = FileAccessPaths.class)
	public List<FileAccessPaths> accessPaths; 
	
//	@OneToMany(fetch = FetchType.EAGER)
	@ModelClass(modelClass = FileFunctions.class)
	public List<FileFunctions> functions;
	
	@DirectMapped(skip = true)
	public Map<Integer, String> attributes;

	@Override
	public void customMapping(IEntity entity) {
		super.customMapping(entity);
		attributes = new LinkedHashMap<>();
		for (DataModelTarget relation : relations) {
			if (relation.relationType.equals(DataModelRelationType.DEFINED_AS)) {
				continue;
			}
			for (ModelEntry attribute : relation.attributes) {
				if (attributes.containsKey(attribute.fieldSurrogate)) {
					continue;
				}
				Optional<Y2Field> y2Field = Optional.ofNullable(
						Y2Field.findById(attribute.fieldSurrogate));
				if (y2Field.isPresent()) {
					Integer fldSurrogate = y2Field.get().fieldSurrogate;
					String fieldName = y2Field.get().fieldName;
					attributes.put(fldSurrogate, fieldName.stripTrailing());
				} else {
					String message = String.format("Error mapping attribute %1$d", attribute.fieldSurrogate);;
					Logger.getLogger(getClass()).error(message);
//					throw new MappingException(message);
				}
			}
		}
	}
}
