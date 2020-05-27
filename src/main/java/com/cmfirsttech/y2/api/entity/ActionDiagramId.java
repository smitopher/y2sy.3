package com.cmfirsttech.y2.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ActionDiagramId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "@@MSG")
	public Integer functionSurrogate;

	@Column(name="@@ELM")
	public Integer elementNo;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elementNo == null) ? 0 : elementNo.hashCode());
		result = prime * result + ((functionSurrogate == null) ? 0 : functionSurrogate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActionDiagramId other = (ActionDiagramId) obj;
		if (elementNo == null) {
			if (other.elementNo != null)
				return false;
		} else if (!elementNo.equals(other.elementNo))
			return false;
		if (functionSurrogate == null) {
			if (other.functionSurrogate != null)
				return false;
		} else if (!functionSurrogate.equals(other.functionSurrogate))
			return false;
		return true;
	}


}
