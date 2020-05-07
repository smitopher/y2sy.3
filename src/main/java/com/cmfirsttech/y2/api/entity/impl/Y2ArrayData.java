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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cmfirsttech.y2.api.entity.AbstractEntity;


/**
 * ARRAY DATA                PHYSICAL FILE
 * 
 */
@Entity
@Table(name="YARRDTARFP")
public class Y2ArrayData extends AbstractEntity {


	@Id
	@Column(name="@@ARR")
	public Integer arraySurrogate;

	@Column(name="ARR", length=25)
	public String arrayName;

	@Column(name="MAXELEM")
	public Integer maximumNumberOfElement;

	@Column(name="ARRSEQ", length=1)
	public String arraySequencing_a_d_;

	@Column(name="UNIQUE", length=1)
	public String arrayUniqueIndicator;

	@Column(name="@@FIL")
	public Integer fileSurrogate;

}