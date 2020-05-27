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
package com.cmfirsttech.y2.api.entity.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.jboss.logging.Logger;

import com.cmfirsttech.y2.api.entity.AbstractEntity;
import com.cmfirsttech.y2.api.entity.ActionDiagramId;
import com.cmfirsttech.y2.api.entity.ActionDiagramPrototypeId;
import com.cmfirsttech.y2.api.mapper.MappingException;


/**
 * LDO Message action prototype  Physical file
 * 
 */
@Entity
@Table(schema = "Y2SY", name="YMSGACTPDP")
public class Y2ActionDiagramPrototype extends AbstractEntity {


	@EmbeddedId
	public ActionDiagramPrototypeId adid;

	@Column(name="ELMTYP", length=3)
	public String elementType;

	@Column(name="ELMTTL", length=80)
	public String elementTitle;

	@Column(name="ELMATR", length=3)
	public String elementAttribute;

	@Column(name="@@NXTELM")
	public Integer nextElementNo;

	@Column(name="@@SUB1")
	public Integer subChain1Sgt;

	@Column(name="@@SUB2")
	public Integer subChain2Sgt;

	@Column(name="@@SUB3")
	public Integer subChain3Sgt;

	@Column(name="HIDE", length=1)
	public String hideElement;

	@Column(name="ALWELMDLT", length=1)
	public String allowElementDeletion;

	@Column(name="ALWELMEDT", length=1)
	public String allowElementEdit;

	@Column(name="ALWELMINS", length=1)
	public String allowElementInsertion;

	@Column(name="CNDSTB", length=8)
	public String conditionStub;

	@Column(name="DSPSEQ", precision=5, scale=2)
	public BigDecimal displaySequenceNo;

	@Column(name="ELMTTLMSID", length=7)
	public String defaultPromptMsgidForElmttl;

	@Column(name="FMTSEQ")
	public Integer formatSequenceNumber;
	
	public Y2FunctionActionDiagram mapfromPrototype() {
		Y2FunctionActionDiagram ad = new Y2FunctionActionDiagram();
		for (Field field : ad.getFields()) {
			try {
				if (field.isAnnotationPresent(EmbeddedId.class)) {
					ActionDiagramId embeddedId = new ActionDiagramId();
					embeddedId.functionSurrogate = adid.functionSurrogate;
					embeddedId.elementNo = adid.elementNo;
					ad.adId = embeddedId;
					continue;
				}
				Optional<Field> prototype = getMappedField(field.getName());
				if (prototype.isPresent()) {
					field.set(ad, prototype.get().get(this));
					continue;
				}
				if (field.getType().equals(String.class)) {
					field.set(ad, "");
					continue;
				}
				if (field.getType().equals(Integer.class)) {
					field.set(ad, Integer.valueOf(0));
					continue;
				}
				if (field.getType().equals(BigDecimal.class)) {
					field.set(ad, BigDecimal.ZERO);
					continue;
				}
				String message = String.format("AD Prototype mapping error for field %1$s", field.getName());
				Logger.getLogger(getClass()).error(message);
				throw new MappingException(message);
			} catch (ReflectiveOperationException e) {
				String message = String.format("AD Prototype mapping error for field %1$s", field.getName());
				Logger.getLogger(getClass()).error(message, e);
				throw new MappingException(message, e);
			}
		}
		return ad;
	}

}