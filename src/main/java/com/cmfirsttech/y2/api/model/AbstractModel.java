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
package com.cmfirsttech.y2.api.model;

import com.cmfirsttech.y2.api.entity.IEntity;
import com.cmfirsttech.y2.api.mapper.IMapper;

public abstract class AbstractModel implements IModel {

	/**
	 * Do nothing method
	 * Implementing classes are intended to override this method for custom
	 * mapping requirements 
	 */
	@Override
	public void customMapping(IEntity entity, IMapper mapper) {
		return;
	}

}