/*******************************************************************************
 * Copyright 2020 christopher.smith
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.cmfirsttech.y2.api.model.impl;

import com.cmfirsttech.y2.api.constants.DataModelRelationType;
import com.cmfirsttech.y2.api.constants.ObjectType;
import com.cmfirsttech.y2.api.constants.RelationExtension;
import com.cmfirsttech.y2.api.constants.RelationLevel;
import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.entity.impl.Y2EntityRelations;
import com.cmfirsttech.y2.api.mapper.DirectMapped;
import com.cmfirsttech.y2.api.model.AbstractModel;
import com.cmfirsttech.y2.api.model.Y2EntityClass;
 

@Y2EntityClass(entityClass = Y2EntityRelations.class)
public class DataModelTarget extends AbstractModel{
	
	public Integer relationSurrogate;
	
	@DirectMapped(skip = true)
	public RelationLevel relationLevel;
	
	public Integer relationSequence;
	
	@DirectMapped(skip = true)
	public DataModelRelationType relationType;
	
	@DirectMapped(skip = true)
	public RelationExtension relationQualified;

	@DirectMapped(skip = true)
	public Integer referencedObjectSurrogate;
	
	@DirectMapped(skip = true)
	public String refencedObject;
	
	@DirectMapped(skip = true)
	public ObjectType referencedObjectType;
	
	@DirectMapped(skip = true)
	public String forName;
	
	@DirectMapped(skip = true)
	public RelationExtension relationSharing;
	
	@DirectMapped(skip = true)
	public Integer sharingSurrogate;
	
	@DirectMapped(skip = true)
	public String sharingObject;
	
	public Integer selectRecordSurrogate;
	
	@Override
	public void customMapping(IEntity entity) {
		Y2EntityRelations relation = (Y2EntityRelations) entity;
		relationLevel = RelationLevel.getRelationLevel(relation.relationLevel).get();
		relationType = DataModelRelationType.fromCodeValue(relation.relationType);
		referencedObjectSurrogate = relation.referencedObjectSurrogate;
		switch (relationType) {
		case OWNED_BY:
		case REFERS_TO:
			refencedObject = relation.referencedFileName;
			referencedObjectType = ObjectType.valueOf(relation.referencedFileObjectType);
			forName = relation.forName;
			if (forName.isEmpty()) {
				forName = null;
			}
			sharingSurrogate = relation.sharingObjectSurrogate;
			sharingObject = relation.sharingFileName;
			if (sharingObject.isEmpty()) {
				sharingObject = null;
			}
			switch (sharingSurrogate.intValue()) {
			case 0:
				relationSharing = RelationExtension.ALL;
				break;
			case -2:
				relationSharing = RelationExtension.NONE;
				break;
			default:
				relationSharing = RelationExtension.FILE;
				break;
			}
			break;
		case QUALIFIED_BY:
			refencedObject = relation.referencedFieldName;
			referencedObjectType = ObjectType.valueOf(relation.referencedFieldObjectType);
			sharingSurrogate = relation.sharingObjectSurrogate;
			switch (sharingSurrogate.intValue()) {
			case -3:
				relationQualified = RelationExtension.NEXT;
				break;
			default:
				relationQualified = RelationExtension.PREVIOUS;
				break;
			}
			forName = relation.forName;
			if (forName.isEmpty()) {
				forName = null;
			}
			break;
		case DEFINED_AS:
		case EXTENDED_BY:
		case INCLUDES:
			refencedObject = relation.referencedFileName;
			referencedObjectType = ObjectType.valueOf(relation.referencedFileObjectType);
			break;
		default:
			refencedObject = relation.referencedFieldName;
			referencedObjectType = ObjectType.valueOf(relation.referencedFieldObjectType);
			break;
		}
	}
	
}
