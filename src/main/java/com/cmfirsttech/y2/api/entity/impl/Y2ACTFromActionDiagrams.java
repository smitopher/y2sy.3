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

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cmfirsttech.y2.api.entity.AbstractEntity;
import com.cmfirsttech.y2.api.entity.ActionDiagramId;


/**
 * MDL REF messages from action diagrams (*|S|@@REFMS
 * 
 */
@Entity
@Table(name="YMSGACT1JL")
public class Y2ACTFromActionDiagrams extends AbstractEntity {


	@EmbeddedId
	public ActionDiagramId adId;

	@Column(name="ELMTYP", length=3)
	public String elementType;

	@Column(name="ELMTTL", length=132)
	public String elementTitle;

	@Column(name="@@PRM")
	public Integer parameterChain;

	@Column(name="@@REFMSG")
	public Integer messageSurrogate;

	@Column(name="@@FIL")
	public Integer fileSurrogate;

	@Column(name="USRDEF", length=1)
	public String userDefined;

	@Column(name="SYSGEN", length=1)
	public String systemGenerated;

	@Column(name="SBMJOB", length=1)
	public String sbmjobAction;

	@Column(name="SBMJOBOVR", length=1)
	public String sbmjobOverride;

	@Column(name="@@SBMOVR")
	public Integer sbmjobOverrideSeclvl;

	@Column(name="MSG", length=25)
	public String messageName;

	@Column(name="FIL", length=25)
	public String fileName;

}